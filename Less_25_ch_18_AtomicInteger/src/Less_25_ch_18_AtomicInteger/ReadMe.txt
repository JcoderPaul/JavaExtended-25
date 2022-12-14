*** AtomicInteger ***

Чтобы разобрать использование атомарных операций в Java рассмотрим следующий пример:
---------------------------------------------------------------------------------------
public class Counter {
    int counter;

    public void increment() {
        counter++;
    }
}
---------------------------------------------------------------------------------------
В случае однопоточной среды это работает отлично; однако, как только мы разрешаем запись
более чем одному потоку, мы начинаем получать противоречивые результаты. Причина этому в
операции приращения (counter++), которая может выглядеть как атомарная операция, но на
самом деле представляет собой комбинацию трех операций:
- получение значения,
- увеличение,
- обратная запись обновленного значения.
Если два потока попытаются получить и обновить значение одновременно, это может привести
к потере обновлений.

Один из способов управления доступом к объекту - использование блокировок. Этого можно
достичь, используя ключевое слово synchronized в сигнатуре метода приращения. Ключевое
слово synchronized гарантирует, что только один поток может входить в метод одновременно:
---------------------------------------------------------------------------------------
public class SynchronizedCounterWithLock {
    private volatile int counter;

    public synchronized void increment() {
        counter++;
    }
}
---------------------------------------------------------------------------------------
Кроме того, нам нужно добавить ключевое слово volatile, чтобы обеспечить надлежащую видимость
ссылок среди потоков.

Использование блокировок решает проблему. Однако производительность страдает в этом случае.
Когда несколько потоков пытаются получить блокировку, один из них выигрывает, а остальные
потоки либо блокируются, либо приостанавливаются.

Процесс приостановки и последующего возобновления потока очень дорог и влияет на общую
эффективность системы.

В небольшой программе, такой как счетчик, время, затрачиваемое на переключение контекста,
может быть намного больше, чем фактическое выполнение кода, что значительно снижает общую
эффективность.

*** Атомарные операции ***

Существует направление исследований, посвященное созданию неблокирующих алгоритмов для
конкурентных сред. Эти алгоритмы используют низкоуровневые атомарные машинные инструкции,
такие как сравнение и замена (CAS, compare-and-swap), для обеспечения целостности данных.

Типичная операция CAS работает с тремя операндами:
- Место в памяти для работы (M);
- Существующее ожидаемое значение (A) переменной;
- Новое значение (B), которое необходимо установить.

Операция CAS атомарно обновляет значение в M до B, но только если существующее значение в M
совпадает с A, в противном случае никаких действий не предпринимается. В обоих случаях
возвращается существующее значение в M. Это объединяет три шага - получение значения,
сравнение значения и обновление значения - в одну операцию на машинном уровне.

Когда несколько потоков пытаются обновить одно и то же значение через CAS, один из них
выигрывает и обновляет значение. Однако, в отличие от блокировок, никакой другой поток не
приостанавливается; вместо этого им просто сообщают, что им не удалось обновить значение.
Затем потоки могут перейти к дальнейшей работе, и переключение контекста полностью исключено.

Еще одно последствие состоит в том, что основная логика программы становится более сложной.
Это потому, что мы должны обработать сценарий, когда операция CAS не удалась. Мы можем повторять
его снова и снова, пока он не увенчается успехом, или мы можем ничего не делать и двигаться
дальше в зависимости от варианта использования.

*** Методы приращения и уменьшения в классе AtomicInteger ***

Первая группа методов обновляет значение и возвращает то, которое было до обновления:

*** Группа методов обновляет значение и возвращает то, которое было до обновления:
- public int getAndAdd (int delta) - Atomiclly добавляет данное значение к текущему значению;

---------------------------------------------------------------------------------------
public final int getAndAdd(int delta) {
    for (;;) {
        int current = get();
        int next = current + delta;
        if (compareAndSet(current, next))
            return current;
    }
}
---------------------------------------------------------------------------------------

- public int getAndDecrement () - Атомно уменьшает на единицу текущее значение;

---------------------------------------------------------------------------------------
public final int getAndDecrement() {
    for (;;) {
        int current = get();
        int next = current - 1;
        if (compareAndSet(current, next))
            return current;
    }
}
---------------------------------------------------------------------------------------

- public int getAndIncrement () - Атомно увеличивает на единицу текущее значение;

---------------------------------------------------------------------------------------
public final int getAndIncrement() {
   for (;;) {
       int current = get();
       int next = current + 1;
       if (compareAndSet(current, next))
           return current;
   }
}
---------------------------------------------------------------------------------------

*** Вторая группа методов обновляет значение и возвращает то, которое стало после обновления:
- public int incrementAndGet () - Атомно увеличивает на единицу текущее значение;

---------------------------------------------------------------------------------------
public final int incrementAndGet() {
    for (;;) {
        int current = get();
        int next = current + 1;
        if (compareAndSet(current, next))
            return next;
    }
}
---------------------------------------------------------------------------------------

- public int decrementAndGet () - Атомно уменьшает на единицу текущее значение;

---------------------------------------------------------------------------------------
public final int decrementAndGet() {
    for (;;) {
        int current = get();
        int next = current - 1;
        if (compareAndSet(current, next))
            return next;
    }
}
---------------------------------------------------------------------------------------

- public int addAndGet (int delta) - Атомно добавляет данное значение к текущему значению;

---------------------------------------------------------------------------------------
public final int addAndGet(int delta) {
    for (;;) {
        int current = get();
        int next = current + delta;
        if (compareAndSet(current, next))
            return next;
    }
}
---------------------------------------------------------------------------------------

Как видно во многих случаях используется функция compareAndSet,
представляющая операцию CAS (compare-and-swap), которая использует
unsafe пакет:

- public boolean compareAndSet (ожидаемое int, обновление int) - Атомно устанавливает
  значение для данного обновленного значения, если текущее значение совпадает с
  ожидаемым значением;

---------------------------------------------------------------------------------------
public final boolean compareAndSet(int expect, int update) {
   return unsafe.compareAndSwapInt(this, valueOffset, expect, update);
}
---------------------------------------------------------------------------------------

- public double doubleValue () - Возвращает значение указанного числа в виде двойного числа;
- public float floatValue () - Возвращает значение указанного числа в виде числа с плавающей запятой;

- public int get () - Получает текущее значение;

- public int getAndSet (int newValue) - Атомно устанавливает заданное значение и возвращает старое значение;

- public int intValue () - Возвращает значение указанного числа в виде целого числа;

- public void lazySet (int newValue) - Метод класса AtomicReference используется для установки значения
  объекта AtomicReference с эффектами памяти, как указано в VarHandle.setRelease(java.lang.Object…), чтобы
  гарантировать, что предыдущие загрузки и сохранения не будут переупорядочены после этого доступа;
---------------------------------------------------------------------------------------
// Java program to demonstrate
// AtomicReference.lazySet() method
import java.util.concurrent.atomic.AtomicReference;

public class GFG {
    public static void main(String[] args)
    {

        // create an atomic reference object.
        AtomicReference<Integer> ref
            = new AtomicReference<Integer>();

        // set some value using lazySet method
        ref.lazySet(67545678);

        // print value
        System.out.println("Integer value = "
                           + ref.get());
    }
}

На экране: Integer value = 67545678
---------------------------------------------------------------------------------------

- public long longValue () - Возвращает значение указанного числа в виде long;

- public набор void (int newValue) - Устанавливается на заданное значение;
- public String toString () - Возвращает строковое представление текущего значения;
- public boolean weakCompareAndSet (ожидаемое, обновление int) - Атомно устанавливает значение для
  данного обновленного значения, если текущее значение совпадает с ожидаемым значением;

- См. оригинал (ENG): [Class AtomicInteger](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/atomic/AtomicInteger.html)

---
### AtomicInteger

Чтобы разобрать использование атомарных операций в Java рассмотрим следующий пример:

```java
        public class Counter {
            int counter;
        
            public void increment() {
                counter++;
            }
        }
```

В случае однопоточной среды это работает отлично; однако, как только мы разрешаем запись более чем одному потоку, мы начинаем получать 
противоречивые результаты. Причина этому в операции приращения - `counter++`, которая может выглядеть как атомарная операция, но на
самом деле представляет собой комбинацию трех операций:
- получение значения,
- увеличение,
- обратная запись обновленного значения.

Если два потока попытаются получить и обновить значение одновременно, это может привести к потере обновлений.

Один из способов управления доступом к объекту - использование блокировок. Этого можно достичь, используя [ключевое слово `synchronized`](https://docs.oracle.com/javase/tutorial/essential/concurrency/syncmeth.html) 
в сигнатуре метода приращения. Ключевое слово `synchronized` гарантирует, что только один поток может входить в метод одновременно:

```java
        public class SynchronizedCounterWithLock {
            private volatile int counter;
        
            public synchronized void increment() {
                counter++;
            }
        }
```

Кроме того, нам нужно добавить [ключевое слово `volatile`](https://docs.oracle.com/cd/E19253-01/816-4854/codingpractices-1/index.html), 
чтобы обеспечить надлежащую видимость ссылок среди потоков.

Использование блокировок решает проблему. Однако производительность страдает в этом случае. Когда несколько потоков пытаются 
получить блокировку, один из них выигрывает, а остальные потоки либо блокируются, либо приостанавливаются.

Процесс приостановки и последующего возобновления потока очень дорог и влияет на общую эффективность системы.

В небольшой программе, такой как счетчик, время, затрачиваемое на переключение контекста, может быть намного больше, чем 
фактическое выполнение кода, что значительно снижает общую эффективность.

---
### Атомарные операции

Существует направление исследований, посвященное созданию неблокирующих алгоритмов для конкурентных сред. Эти алгоритмы 
используют низкоуровневые атомарные машинные инструкции, такие как сравнение и замена (CAS, compare-and-swap), для 
обеспечения целостности данных.

Типичная операция CAS работает с тремя операндами:
- Место в памяти для работы (M);
- Существующее ожидаемое значение (A) переменной;
- Новое значение (B), которое необходимо установить.

Операция CAS атомарно обновляет значение в M до B, но только если существующее значение в M совпадает с A, в противном случае 
никаких действий не предпринимается. В обоих случаях возвращается существующее значение в M. Это объединяет три шага - получение 
значения, сравнение значения и обновление значения - в одну операцию на машинном уровне.

Когда несколько потоков пытаются обновить одно и то же значение через CAS, один из них выигрывает и обновляет значение. Однако, 
в отличие от блокировок, никакой другой поток не приостанавливается; вместо этого им просто сообщают, что им не удалось обновить 
значение. Затем потоки могут перейти к дальнейшей работе, и переключение контекста полностью исключено.

Еще одно последствие состоит в том, что основная логика программы становится более сложной. Это потому, что мы должны обработать 
сценарий, когда операция CAS не удалась. Мы можем повторять его снова и снова, пока он не увенчается успехом, или мы можем ничего 
не делать и двигаться дальше в зависимости от варианта использования.

---
### Методы приращения и уменьшения в [классе AtomicInteger](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/atomic/AtomicInteger.html)

Первая группа методов обновляет значение и возвращает то, которое было до обновления:

**Группа методов обновляет значение и возвращает то, которое было до обновления:**
- `public int getAndAdd(int delta)` - Atomiclly добавляет данное значение к текущему значению;

```java
        public final int getAndAdd(int delta) {
            for (;;) {
                int current = get();
                int next = current + delta;
                if (compareAndSet(current, next))
                    return current;
            }
        }
```
- `public int getAndDecrement()` - Атомарно уменьшает на единицу текущее значение;

```java
        public final int getAndDecrement() {
            for (;;) {
                int current = get();
                int next = current - 1;
                if (compareAndSet(current, next))
                    return current;
            }
        }
```
- `public int getAndIncrement()` - Атомарно увеличивает на единицу текущее значение;

```java
        public final int getAndIncrement() {
           for (;;) {
               int current = get();
               int next = current + 1;
               if (compareAndSet(current, next))
                   return current;
           }
        }
```

**Вторая группа методов обновляет значение и возвращает то, которое стало после обновления:**
- `public int incrementAndGet()` - Атомарно увеличивает на единицу текущее значение;

```java
        public final int incrementAndGet() {
            for (;;) {
                int current = get();
                int next = current + 1;
                if (compareAndSet(current, next))
                    return next;
            }
        }
```
- `public int decrementAndGet()` - Атомарно уменьшает на единицу текущее значение;

```java
        public final int decrementAndGet() {
            for (;;) {
                int current = get();
                int next = current - 1;
                if (compareAndSet(current, next))
                    return next;
            }
        }
```
- `public int addAndGet(int delta)` - Атомарно добавляет данное значение к текущему значению;

```java
        public final int addAndGet(int delta) {
            for (;;) {
                int current = get();
                int next = current + delta;
                if (compareAndSet(current, next))
                    return next;
            }
        }
```

Как видно во многих случаях используется функция [compareAndSet](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/atomic/AtomicInteger.html#compareAndSet-int-int-), 
представляющая операцию CAS (compare-and-swap), которая использует unsafe пакет:

- `public boolean compareAndSet(ожидаемое int, обновление int)` - Атомарно устанавливает значение для данного обновленного значения,
если текущее значение совпадает с ожидаемым значением;

```java
        public final boolean compareAndSet(int expect, int update) {
           return unsafe.compareAndSwapInt(this, valueOffset, expect, update);
        }
```

- `public double doubleValue()` - Возвращает значение указанного числа в виде двойного числа;
- `public float floatValue()` - Возвращает значение указанного числа в виде числа с плавающей запятой;
- `public int get()` - Получает текущее значение;
- `public int getAndSet(int newValue)` - Атомно устанавливает заданное значение и возвращает старое значение;
- `public int intValue()` - Возвращает значение указанного числа в виде целого числа;
- `public void lazySet(int newValue)` - Метод класса [AtomicReference](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/atomic/AtomicReference.html)
используется для установки значения объекта AtomicReference с эффектами памяти, как указано в VarHandle.setRelease(java.lang.Object…), чтобы гарантировать, что
предыдущие загрузки и сохранения не будут переупорядочены после этого доступа;

```java
        // Java program to demonstrate
        // AtomicReference.lazySet() method
        import java.util.concurrent.atomic.AtomicReference;
        
        public class GFG {
            public static void main(String[] args)
            {
                // create an atomic reference object.
                AtomicReference<Integer> ref = new AtomicReference<Integer>();
        
                // set some value using lazySet method
                ref.lazySet(67545678);
        
                // print value
                System.out.println("Integer value = " + ref.get());
            }
        }
```

На экране: 

```        
        Integer value = 67545678
```
- `public long longValue()` - Возвращает значение указанного числа в виде long;
- `public набор void(int newValue)` - Устанавливается на заданное значение;
- `public String toString()` - Возвращает строковое представление текущего значения;
- `public boolean weakCompareAndSet(ожидаемое, обновление int)` - Атомарно устанавливает значение для данного обновленного значения, если текущее значение совпадает с ожидаемым значением;

---
**См. так же:**
- [Краткое описание пакета java.util.concurrent](./СoncurrentAtomicClasses.md)
- [java.util.concurrent.atomic](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/atomic/package-summary.html)
- [Class AtomicInteger](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/atomic/AtomicInteger.html)
- [AtomicInteger.java from GitHub](https://github.com/openjdk-mirror/jdk7u-jdk/blob/master/src/share/classes/java/util/concurrent/atomic/AtomicInteger.java)
- [Synchronized Methods (from The Java™ Tutorials)](https://docs.oracle.com/javase/tutorial/essential/concurrency/syncmeth.html)
- [Declaring a Variable Volatile (from ORACLE Doc)](https://docs.oracle.com/cd/E19253-01/816-4854/codingpractices-1/index.html)

--- 
- [An Introduction to Atomic Variables in Java](https://www.baeldung.com/java-atomic-variables)
- [Understanding Atomic Integer in Java](https://medium.com/@reetesh043/understanding-atomic-integer-in-java-c0f2aafe6837)
- [AtomicInteger Class In Java](https://www.codementor.io/@noelkamphoa/atomicinteger-class-in-java-2oh5icptfe)
- [Understanding Java AtomicInteger with an Example](https://www.javacodegeeks.com/understanding-java-atomicinteger-with-an-example.html)
- [AtomicInteger (from jenkov.com)](https://jenkov.com/tutorials/java-util-concurrent/atomicinteger.html)
- [Java’s AtomicInteger.incrementAndGet() Method Explained](https://medium.com/@AlexanderObregon/javas-atomicinteger-incrementandget-method-explained-8a4f4f521c4e)
- [Java Concurrency - AtomicInteger Class](https://www.tutorialspoint.com/java_concurrency/concurrency_atomic_integer.htm)
- [Java AtomicInteger Example](https://mkyong.com/java/java-atomicinteger-example/)

---
- [Synchronized Methods (from The Java™ Tutorials by ORACLE)](https://docs.oracle.com/javase/tutorial/essential/concurrency/syncmeth.html)
- [Guide to the Synchronized Keyword in Java](https://www.baeldung.com/java-synchronized)
- [Synchronization in Java](https://www.geeksforgeeks.org/java/synchronization-in-java/)
- [Java Synchronized Blocks (from jenkov.com)](https://jenkov.com/tutorials/java-concurrency/synchronized.html)
- [How Java’s Thread Synchronization Works](https://medium.com/@AlexanderObregon/how-javas-thread-synchronization-works-99c5ba3cda85)
- [Синхронизация потоков. Оператор synchronized в Java](https://javarush.com/groups/posts/1994-sinkhronizacija-potokov-operator-synchronized)
- [Java synchronized Keyword](https://www.w3schools.com/java/ref_keyword_synchronized.asp)
- [Synchronization in Java - How Multiple Threads Share Data Safely](https://gauravrshegekar.substack.com/p/synchronization-in-java)
- [Mastering Synchronization: Best Practices and Patterns in Java](https://medium.com/@alxkm/mastering-synchronization-best-practices-and-patterns-in-java-86214b53211d)

---
- [Volatile Variables and Thread Safety](https://www.baeldung.com/java-volatile-variables-thread-safety)
- [Volatile, Lock-free, Immutable, Atomic в Java. Как понять и начать использовать](https://habr.com/ru/companies/bercut/articles/822253/)
- [How Volatile in Java works? Example of volatile keyword in Java](https://www.javacodegeeks.com/2018/03/volatile-java-works-example-volatile-keyword-java.html)
- [Применение volatile](https://javarush.com/quests/lectures/questmultithreading.level06.lecture04)
- [Java - volatile keyword](https://www.tutorialspoint.com/java/volatile_keyword_in_java.htm)
- [`volatile` Keyword in Java](https://www.datacamp.com/doc/java/volatile)
- [The Meaning of Volatile Variables in the Java Memory Model](https://medium.com/@AlexanderObregon/the-meaning-of-volatile-variables-in-the-java-memory-model-60a0c0c45ead)

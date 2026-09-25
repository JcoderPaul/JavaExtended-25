### Нелокирующие потокобезопасные данные

[Пакет java.util.concurrent.atomic](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/atomic/package-summary.html) - это набор классов, поддерживающих неблокирующее
поточно-ориентированное программирование для отдельных переменных. По сути, классы в этом пакете расширяют понятие volatile значений, полей и элементов массива до тех, которые 
также обеспечивают атомарную операцию условного обновления формы:

```
        - boolean compareAndSet(expectedValue, updateValue);
```

Этот метод (который различается по типам аргументов в разных классах) атомарно устанавливает переменную updateValue, если она в настоящее время содержит ожидаемое значение, 
возвращая true в случае успеха. Классы в этом пакете также содержат методы для получения и безоговорочной установки значений, а также более слабую операцию условного атомарного 
обновления weakCompareAndSet.

Спецификации этих методов позволяют реализациям использовать эффективные атомарные инструкции машинного уровня, доступные на современных процессорах. Однако на некоторых платформах 
поддержка может повлечь за собой некоторую форму внутренней блокировки. Таким образом, не гарантируется, что методы будут неблокирующими - поток может временно заблокироваться 
перед выполнением операции.

Каждый из экземпляров классов
- [AtomicBoolean](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/atomic/AtomicBoolean.html),
- [AtomicInteger](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/atomic/AtomicInteger.html),
- [AtomicLong](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/atomic/AtomicLong.html),
- [AtomicReference](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/atomic/AtomicReference.html).

Обеспечивает доступ и обновления к одной переменной соответствующего типа. Каждый класс также предоставляет соответствующие служебные методы для этого типа.

Например, классы [AtomicLong](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/atomic/AtomicLong.html) и [AtomicInteger](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/atomic/AtomicInteger.html) предоставляют методы атомарного приращения.

Одно из применений - создание порядковых номеров, например:

```java
        class Sequencer {
            private final AtomicLong sequenceNumber = new AtomicLong(0);
            public long next() {
                return sequenceNumber.getAndIncrement();
            }
        }
```

Несложно определить новые служебные функции, которые, как и [getAndIncrement](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/atomic/AtomicLong.html#getAndIncrement--), 
атомарно применяют функцию к значению.

Например, для заданного преобразования long transform(long input) можно написать следующее:

```java
        long getAndTransform(AtomicLong var) {
            long prev, next;
            do {
              prev = var.get();
              next = transform(prev);
            } while (!var.compareAndSet(prev, next));
            return prev; // return next; для transformAndGet
        }
```

Эффекты памяти для доступа и обновления атомарных переменных обычно соответствуют правилам для volatile, как указано в [Спецификации языка Java (модель памяти 17.4)](https://docs.oracle.com/javase/specs/jls/se7/html/jls-17.html#jls-17.4):
- `get` имеет эффекты памяти при чтении volatile переменной;
- `set` имеет эффекты памяти записи (назначения) volatile переменной;
- `lazySet` имеет эффекты памяти записи (назначения) volatile переменной, за исключением того, что он позволяет переупорядочивать с последующими (но не предыдущими) действиями с памятью,
которые сами по себе не накладывают ограничений переупорядочения с обычными не-volatile записями. Среди других контекстов использования lazySet может применяться при обнулении для сбора
мусорных ссылок, к которые больше никогда не будут доступны.
- `weakCompareAndSet` атомарно считывает и записывает переменную по условию, но не создает каких-либо упорядоченных операций (happens-before orderings), поэтому не дает никаких гарантий
относительно предыдущих или последующих операций чтения и записи любых переменных, кроме цели weakCompareAndSet.
- `compareAndSet` и все другие операции чтения и обновления, такие как getAndIncrement, имеют эффекты памяти как чтения, так и записи volatile переменных.

В дополнение к классам, представляющим отдельные значения, этот пакет содержит классы Updater-ы, которые можно использовать для получения операций compareAndSet в любом выбранном volatile 
поле любого выбранного класса.

- [AtomicReferenceFieldUpdater](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/atomic/AtomicReferenceFieldUpdater.html),
- [AtomicIntegerFieldUpdater](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/atomic/AtomicIntegerFieldUpdater.html)
- [AtomicLongFieldUpdater](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/atomic/AtomicLongFieldUpdater.html).
Это утилиты на основе рефлексии, которые обеспечивают доступ к связанным типам полей. В основном они используются в атомарных структурах данных, в которых несколько volatile полей одного и
того же узла (например, ссылки узла дерева) независимо подвергаются атомарным обновлениям. Эти классы обеспечивают большую гибкость в том, как и когда использовать атомарные обновления, за
счет более неуклюжей настройки на основе рефлексии, менее удобного использования и более слабых гарантий.

Классы: 
- [AtomicIntegerArray](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/atomic/AtomicIntegerArray.html),
- [AtomicLongArray](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/atomic/AtomicLongArray.html),
- [AtomicReferenceArray](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/atomic/AtomicReferenceArray.html).

Дополнительно расширяют поддержку атомарных операций для массивов этих типов. Эти классы также примечательны тем, что предоставляют семантику volatile доступа для своих элементов массива, 
которая не поддерживается для обычных массивов.

Атомарные классы также поддерживают метод weakCompareAndSet, который имеет ограниченную применимость. На некоторых платформах weak версия может быть более эффективной, чем compareAndSet 
в обычном случае, но отличается тем, что любой конкретный вызов метода weakCompareAndSet может ложно возвращать false значение (то есть без видимой причины). Возврат false означает только то, 
что операцию можно повторить при желании, полагаясь на гарантию того, что повторный вызов, когда переменная содержит ожидаемое значение и ни один другой поток также не пытается установить 
переменную, в конечном итоге будет успешным.

(Такие ложные сбои могут быть, например, из-за эффектов конкуренции за память, которые не связаны с тем, равны ли ожидаемые и текущие значения.)

Кроме того, weakCompareAndSet не обеспечивает гарантии упорядочения, которые обычно необходимы для управления синхронизацией. Однако этот метод может быть полезен для обновления счетчиков 
и статистики, когда такие обновления не связаны с другими событиями, происходящими до упорядочения программы. Когда поток видит обновление атомарной переменной, вызванное weakCompareAndSet, 
он не обязательно видит обновления любых других переменных, которые произошли до weakCompareAndSet. Это может быть приемлемо, например, при обновлении статистики производительности, но редко 
в противном случае.

Класс [AtomicMarkableReference](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/atomic/AtomicMarkableReference.html) связывает одно логическое значение со ссылкой. Например, 
этот бит может использоваться внутри структуры данных для обозначения того, что объект, на который имеется ссылка, был логически удален. Класс [AtomicStampedReference](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/atomic/AtomicStampedReference.html) связывает целочисленное значение со ссылкой. Это может быть использовано, например, для 
представления номеров версий, соответствующих серии обновлений.

Атомарные классы разработаны в первую очередь как строительные блоки для реализации неблокирующих структур данных и связанных классов инфраструктуры. Метод compareAndSet не является общей 
заменой блокировки. Он применяется только тогда, когда критические обновления объекта ограничиваются одной переменной.

Атомарные классы не являются заменой общего назначения для `java.lang.Integer` и связанных классов. Они не определяют такие методы, как `equals`, `hashCode` и `compareTo`. Поскольку ожидается, 
что атомарные переменные будут видоизменяться, они не подходят для ключей хэш-таблицы. Кроме того, классы предоставляются только для тех типов, которые обычно используются в предполагаемых 
приложениях.

Например, не существует атомарного класса для представления байта. В тех редких случаях, когда вы хотели бы это сделать, вы можете использовать [AtomicInteger](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/atomic/AtomicInteger.html) для хранения байтовых значений и соответствующего преобразования.

Вы также можете хранить float, используя преобразования [Float.floatToRawIntBits(float)](https://docs.oracle.com/javase/8/docs/api/java/lang/Float.html#floatToRawIntBits-float-) и
[Float.intBitsToFloat(int)](https://docs.oracle.com/javase/8/docs/api/java/lang/Float.html#intBitsToFloat-int-), и double с помощью преобразований [Double.doubleToRawLongBits(double)](https://docs.oracle.com/javase/8/docs/api/java/lang/Double.html#doubleToRawLongBits-double-) и [Double.longBitsToDouble(long)](https://docs.oracle.com/javase/8/docs/api/java/lang/Double.html#longBitsToDouble-long-).

---
**Доп. материал:**
- [Пакет java.util.concurrent.atomic](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/atomic/package-summary.html)
- [Atomic classes](https://medium.com/@kaustubh.saha/atomic-classes-e4a8a64e6a71)
- [An Introduction to Atomic Variables in Java](https://www.baeldung.com/java-atomic-variables)
- [Atomic Variables in Java with Examples](https://www.geeksforgeeks.org/java/atomic-variables-in-java-with-examples/)

--- 
- [Multithreading Concepts Part 1: Atomicity and Immutability](https://dev.to/anwaar/multithreading-key-concepts-for-engineers-part-1-4g73)
- [Multithreading Concepts Part 2 : Starvation](https://dev.to/anwaar/multithreading-concepts-part-2-starvation-1abb)
- [Debug and Monitor Java App with VisualVM and jstack](https://dev.to/anwaar/debug-and-monitor-java-app-with-visualvm-and-jstack-aoa)
- [Multithreading Concepts Part 3 : Deadlock](https://dev.to/anwaar/multithreading-concepts-part-3-deadlock-4ip6)

--- 
- [Java 8 Concurrency Tutorial: Atomic Variables and ConcurrentMap](https://winterbe.com/posts/2015/05/22/java8-concurrency-tutorial-atomic-concurrent-map-examples/)
- [Java Concurrency: Atomic Variables Introduction](https://egkatzioura.com/2024/08/22/java-concurrency-atomic-variables-introduction/)
- [Atomic Variables](https://codefinity.com/courses/v2/64fdb450-1405-4e74-8cd4-45fc2ebd37e5/58cddf1e-6e70-473c-b05e-7da5b4523a57/bff5f17a-4bb5-416d-8a00-09725f766f47)
- [Java concurrency: fixing races with AtomicReference](https://openliberty.io/blog/2021/09/21/building-on-atomic-reference.html)
- [Java Concurrency: Atomic Variables](https://dzone.com/articles/java-concurrency-atomic-variables)
- [Java Concurrency: Atomicity](https://www.technotez.net/java/java-concurrency/concurrency-atomicity)

---
- [Java Concurrency - Part 1 : Threads](https://baptiste-wicht.com/posts/2010/05/java-concurrency-part-1-threads.html)
- [Java Concurrency : Part 2 - Manipulate Threads](https://baptiste-wicht.com/posts/2010/05/java-concurrency-part-2-manipulate-threads.html)
- [Java Concurrency – Part 3 : Synchronization with intrinsic locks](https://baptiste-wicht.com/posts/2010/08/java-concurrrency-synchronization-locks.html)
- [Java Concurrency - Part 4 : Semaphores](https://baptiste-wicht.com/posts/2010/08/java-concurrency-part-4-semaphores.html)
- [Java Concurrency - Part 5 : Monitors (Locks and Conditions)](https://baptiste-wicht.com/posts/2010/09/java-concurrency-part-5-monitors-locks-and-conditions.html)
- [Java Concurrency - Part 6 : Atomic Variables](https://baptiste-wicht.com/posts/2010/09/java-concurrency-atomic-variables.html)
- [Java Concurrency - Part 7 : Executors and thread pools](https://baptiste-wicht.com/posts/2010/09/java-concurrency-part-7-executors-and-thread-pools.html)

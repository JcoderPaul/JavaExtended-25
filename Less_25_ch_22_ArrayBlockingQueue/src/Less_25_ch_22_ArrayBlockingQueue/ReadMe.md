См. оригинал [description](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/package-summary.html#package.description) (ENG): [Package java.util.concurrent](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/package-summary.html)

---
### Блокирующие очереди пакета concurrent

Пакет java.util.concurrent включает классы для формирования блокирующих очередей с поддержкой многопоточности. 
Блокирующие очереди используются в тех случаях, когда нужно выполнить (проверить выполненение) какие-либо условия 
для продолжения потоками своей работы.

Блокирующие очереди могут реализовывать интерфейсы [BlockingQueue](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/BlockingQueue.html), [BlockingDeque](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/BlockingDeque.html), [TransferQueue](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/TransferQueue.html).
В пакете java.util.concurrent имеются следующие реализации блокирующих очередей :

- [ArrayBlockingQueue](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/ArrayBlockingQueue.html) — ограниченная [блокирующая очередь](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/BlockingQueue.html), поддерживаемая массивом.;
- [LinkedBlockingQueue](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/LinkedBlockingQueue.html) — [блокирующая очередь](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/BlockingDeque.html) с возможностью ограничения количества узлов, основанная на связанных узлах.;
- [LinkedBlockingDeque](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/LinkedBlockingDeque.html) — [блокирующая двунаправленная очередь](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/BlockingDeque.html) с возможностью ограничения размера , основанная на связанных узлах.;
- [SynchronousQueue](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/SynchronousQueue.html) — [блокирующую очередь](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/BlockingQueue.html) без емкости (операция добавления одного потока находится в ожидании соответствующей операции удаления в другом потоке);
- [LinkedTransferQueue](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/LinkedTransferQueue.html) — реализация очереди на основе интерфейса [TransferQueue](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/TransferQueue.html);
- [DelayQueue](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/DelayQueue.html) — неограниченная [блокирующая очередь](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/BlockingQueue.html), в которой элемент может быть взят только после истечения срока его задержки, реализующая [интерфейс Delayed](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/Delayed.html);
- [PriorityBlockingQueue](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/PriorityBlockingQueue.html) — неограниченная [блокирующая очередь](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/BlockingQueue.html), использующая те же правила упорядочивания, что и [класс PriorityQueue](https://docs.oracle.com/javase/8/docs/api/java/util/PriorityQueue.html), и предоставляющая блокирующие операции извлечения..

Использование очередей [пакета java.util.concurrent](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/package-summary.html) может стать решением проблем взаимных блокировок и «голодания».

---
### Интерфейс BlockingQueue

[Интерфейс BlockingQueue](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/BlockingQueue.html) определяет блокирующую очередь, наследующую свойства [интерфейса Queue](https://docs.oracle.com/javase/8/docs/api/java/util/Queue.html), в которой элементы хранятся в порядке «первый пришел, первый вышел» (FIFO – first in, first out).

Реализация данного интерфейса обеспечивает блокировку потока в двух случаях:
- при попытке получения элемента из пустой очереди;
- при попытке размещения элемента в полной очереди.

Когда поток пытается получить элемент из пустой очереди, то он переводится в состояние ожидания до тех пор, пока какой-либо другой поток не разместит элемент в 
очереди. Аналогично при попытке положить элемент в полную очередь - поток ставится в ожидание до тех пор, пока другой поток не заберет элемент из очереди и, 
таким образом, не освободит место в ней. Естественно, понятие "полная очередь" подразумевает ограничение размера очереди.

BlockingQueue решает проблему передачи собранных одним потоком элементов для обработки в другой поток без явных хлопот о проблемах синхронизации.

**Основные методы интерфейса BlockingQueue:**
- `boolean add(E e)` - Немедленное добавление элемента в очередь, если это возможно; метод возвращает `true` при благополучном завершении операции, либо вызывает [IllegalStateException](https://docs.oracle.com/javase/8/docs/api/java/lang/IllegalStateException.html), если очередь полная.
- `boolean contains(Object o)` - Проверка наличия объекта в очереди; если объект найден в очереди метод вернет `true`.
- `boolean offer(E e)` - Немедленное размещение элемента в очереди при наличие свободного места; метод вернет `true` при успешном завершении операции, в противном случае вернет `false`.
- `boolean offer(E e, long timeout, TimeUnit unit)` - Размещение элемента в очереди при наличии свободного места; при необходимости определенное ожидание времени, пока не освободиться место.
- `E poll(long timeout, TimeUnit unit)` - Чтение и удаление элемента из очереди в течение определенного времени (таймаута).
- `void put(E e)` - Размещение элемента в очереди, ожидание при необходимости освобождения свободного места.
- `int remainingCapacity()` - Получения количества элементов, которое можно разместить в очереди без блокировки, либо Integer.MAX_VALUE при отсутствии внутреннего предела.
- `boolean remove(Object o)` - Удаление объекта из очереди, если он в ней присутствует.
- `E take()` - Получение с удалением элемента из очереди, при необходимости ожидание пока элемент не станет доступным.

BlockingQueue не признает нулевых элементов (null) и вызывает [NullPointerException](https://docs.oracle.com/javase/8/docs/api/java/lang/NullPointerException.html) при попытке
добавить или получить такой элемент. Нулевой элемент возвращает метод poll, если в течение таймаута не был размещен в очереди очередной элемент.

Методы BlockingQueue можно разделить на 4 группы, по-разному реагирующие на невозможность выполнения операции в текущий момент и откладывающие их выполнение на время:
- первые вызывают Exception;
- вторые возвращают определенное значение (null или false);
- третьи блокируют поток на неопределенное время до момента выполнения операции;
- четвертые блокируют поток на определенное время.

Эти методы представлены в следующем виде:

| Вызывает | Exception | Чтение значения | Блокировка    | Чтение с задержкой
|----------|-----------|-----------------|---------------|----------------------
| Insert	 | add(e)    | offer(e)        | put(e)        | offer(e, time, unit)
| Remove	 | remove()  | poll()          | take()        | poll(time, unit)
| Проверка | element() |	peek()         | не применимый | не применимый

Cм.пример [Less_25_BlockingQueue_Step3](./Less_25_BlockingQueue_Step3.java)

---
**Доп. материалы:**
- [Guide to java.util.concurrent.BlockingQueue](https://www.baeldung.com/java-blocking-queue)
- [BlockingQueue Interface in Java](https://www.geeksforgeeks.org/java/blockingqueue-interface-in-java/)
- [Java BlockingQueue Example](https://www.digitalocean.com/community/tutorials/java-blockingqueue-example)
- [BlockingQueue In Java](https://medium.com/@reetesh043/blockingqueue-in-java-36ed1ee8e9f5)
- [BlockingQueue Interface in Java (+ Code Example)](https://www.happycoders.eu/algorithms/java-blockingqueue/)
- [Java BlockingQueue](https://jenkov.com/tutorials/java-util-concurrent/blockingqueue.html)
- [What Is Java BlockingQueue? Methods and Implementations](https://redisson.pro/glossary/java-blockingqueue.html)
- [BlockingQueue and its Implementations](https://codefinity.com/courses/v2/64fdb450-1405-4e74-8cd4-45fc2ebd37e5/6be91a0d-988a-400a-9f74-2f2f14d4d0d3/16281c2e-5777-47c2-b0f3-06e03d4c2bd2)

---
### Интерфейс BlockingDeque

[Интерфейс BlockingDeque](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/BlockingDeque.html), также, как и BlockingQueue, определяет блокирующую, 
но двунаправленную очередь, наследующую свойства [интерфейса Deque](https://docs.oracle.com/javase/8/docs/api/java/util/Deque.html) и ориентированную на многопотоковое 
исполнение, не разрешающую нулевые элементы и с возможностью ограничения емкости. Реализации интерфейса BlockingDeque блокируют операции получения элементов, если 
очередь пустая, и добавления элемента в очередь, если она полная.

Методы BlockingDeque объединены в 4 группы, по-разному реагирующие на невозможность выполнения операции в текущий момент и откладывающие их выполнение на небольшое время:
- первые вызывают Exception;
- вторые возвращают определенное значение (null или false);
- третьи блокируют поток на неопределенное время до момента выполнения операции;
- четвертые блокируют поток на определенное время.

Методы представлены в следующей таблице:

Первый Элемент (голова):

| Вызывает | Exception     | Чтение значения | Блокировка    | Чтение с задержкой
|----------|---------------|-----------------|---------------|---------------------------
| Insert	 | addFirst(e)   | offerFirst(e)   | putFirst(e)   | offerFirst(e, time, unit)
| Remove	 | removeFirst() | pollFirst()     | takeFirst()   | pollFirst(time, unit)
| Проверка | getFirst()    | peekFirst()     | не применимый | не применимый

Последний Элемент (хвост):

| Вызывает | Exception     | Чтение значения | Блокировка    | Чтение с задержкой
|----------|---------------|-----------------|---------------|--------------------------
| Insert	 | addLast(e)    | offerLast(e)    | putLast(e)    | offerLast(e, time, unit)
| Remove	 | removeLast()  | pollLast()      | takeLast()    | pollLast(time, unit)
| Проверка | addLast       | peekLast()      | не применимый | не применимый

Реализация BlockingDeque может использоваться непосредственно в качестве BlockingQueue с механизмом FIFO. Следующие представленные в таблице методы и наследованные от интерфейса
BlockingQueue, точно эквивалентны методам BlockingDeque :

**Insert (вставка):**  

| Метод BlockingQueue | Метод эквивалентный BlockingDeque
|---------------------|---------------------------------------
|add(e)	              | addLast(e)
|offer(e)             | offerLast(e)
|put(e)	              | putLast(e)
|offer(e, time, unit) | offerLast(e, time, unit)

**Remove (удаление):**

| Метод BlockingQueue | Метод эквивалентный BlockingDeque
|---------------------|---------------------------------------
| remove()            | removeFirst()
| poll()              | pollFirst()
| take()              | takeFirst()
| poll(time, unit)    | pollFirst(time, unit)

**Examine (Проверка):**

| Метод BlockingQueue | Метод эквивалентный BlockingDeque
|---------------------|---------------------------------------
| element()           | getFirst()
| peek()              | peekFirst()

Действия по размещению объекта в BlockingDeque выполняются перед действиями проверки доступа или удаления элемента из очереди в другом потоке.

---
**Доп. материалы:**
- [BlockingDeque in Java](https://www.geeksforgeeks.org/java/blockingdeque-in-java/)
- [BlockingDeque](https://jenkov.com/tutorials/java-util-concurrent/blockingdeque.html)
- [BlockingDeque element() method in java with examples](https://www.geeksforgeeks.org/java/blockingdeque-element-method-in-java-with-examples/)
- [BlockingDeque peek() method in Java with examples](https://www.geeksforgeeks.org/java/blockingdeque-peek-method-in-java-with-examples/)
- [Java : BlockingDeque with Examples](https://programming-tips.jp/archives/a0/67/index.html)

---
### Очередь ArrayBlockingQueue

[Класс блокирующей очереди ArrayBlockingQueue](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/ArrayBlockingQueue.html) реализует классический 
ограниченного размера кольцевой буфер FIFO — «первым прибыл - первым убыл». Новые элементы вставляются в хвост очереди; операции извлечения отдают элемент из 
головы очереди. Создаваемая емкость очереди не может быть изменена.

Попытки вставить (put) элемент в полную очередь приведет к блокированию работы потока, попытка извлечь (take) элемент из пустой очереди также блокирует поток.

Данный класс поддерживает дополнительную политику справедливости параметром fair в конструкторе для упорядочивания работы ожидающих потоков производителей 
(вставляющих элементы) и потребителей (извлекающих элементы). По умолчанию упорядочивание работы очереди не гарантируется. Но если очередь создана с «fair=true», 
реализация класса ArrayBlockingQueue предоставляет доступ потоков в порядке FIFO. Справедливость обычно уменьшает пропускную способность, но также снижает 
изменчивость и предупреждает исчерпание ресурсов.

Класс [ArrayBlockingQueue](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/ArrayBlockingQueue.html) и его iterator реализуют все дополнительные 
методы [Collection](https://docs.oracle.com/javase/8/docs/api/java/util/Collection.html) и [Iterator](https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html).
Метод toArray() возвращает массив элементов очереди типа Object[].

**Конструкторы класса ArrayBlockingQueue:**
- `ArrayBlockingQueue(int capacity)` - конструктор создает очередь фиксированной емкости, c политикой доступа по умолчанию.
- `ArrayBlockingQueue(int capacity, boolean fair)` - конструктор с фиксированной емкостью и указанной политикой доступа.
- `ArrayBlockingQueue(int capacity, boolean fair, Collection<? extends E> c)` - конструктор создает очередь с фиксированной емкостью, указанной политикой доступа и включает в очередь элементы.

---
**Доп. материалы:**
- [ArrayBlockingQueue Class in Java](https://www.geeksforgeeks.org/java/arrayblockingqueue-class-in-java/)
- [ArrayBlockingQueue vs. LinkedBlockingQueue](https://www.baeldung.com/java-arrayblockingqueue-vs-linkedblockingqueue)
- [Java ArrayBlockingQueue class](https://howtodoinjava.com/java/collections/java-arrayblockingqueue/)
- [ArrayBlockingQueue contains() method in Java](https://www.geeksforgeeks.org/java/arrayblockingqueue-contains-method-in-java/)
- [Java ArrayBlockingQueue Examples](https://www.codejava.net/java-core/concurrency/java-arrayblockingqueue-examples)
- [How to use BlockingQueue in Java? ArrayBlockingQueue and LinkedBlockingQueue Example Tutorial](https://javarevisited.blogspot.com/2012/12/blocking-queue-in-java-example-ArrayBlockingQueue-LinkedBlockingQueue.html)
- [Java ArrayBlockingQueue - A High Performance Data Structure for a Multithreaded Application](https://topdeveloperacademy.com/articles/java-arrayblockingqueue-a-thread-safe-bound-size-queue)
- [ArrayBlockingQueue](https://jenkov.com/tutorials/java-util-concurrent/arrayblockingqueue.html)
- [ArrayBlockingQueue take() method in Java](https://www.geeksforgeeks.org/java/arrayblockingqueue-take-method-in-java/)

---
### Очередь LinkedBlockingQueue

[Класс блокирующей очереди LinkedBlockingQueue](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/LinkedBlockingQueue.html), основанный на соединенных узлах, регулирует
порядок поступления и выдачи элементов FIFO — «первым прибыл - первым убыл». Новые элементы вставляются в хвост очереди, а операции чтения извлекают элемент из головы очереди.
У соединенных на узлах очереди обычно более высокая пропускная способность, чем у основанной на массиве очереди, но менее предсказуемая производительность в большинстве многопоточных
приложений.

**Конструкторы класса LinkedBlockingQueue:**
- `LinkedBlockingQueue()` - конструктор создает пустую очередь фиксированной емкости.
- `LinkedBlockingQueue(int capacity)` - конструктор создает очередь с фиксированной емкостью capacity.
- `LinkedBlockingQueue(Collection<? extends E> c)` - конструктор создает очередь с набором элементов.

Если используется конструктор без указания емкости очереди, то используется значение по умолчанию [Integer.MAX_VALUE](https://docs.oracle.com/javase/8/docs/api/java/lang/Integer.html#MAX_VALUE).

Класс LinkedBlockingQueue и его iterator реализуют все опциональные методы [Collection](https://docs.oracle.com/javase/8/docs/api/java/util/Collection.html) и [Iterator](https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html). Метод toArray() возвращает массив элементов очереди типа Object[].

---
**Доп. материалы:**
- [LinkedBlockingQueue Class in Java](https://www.geeksforgeeks.org/java/linkedblockingqueue-class-in-java/)
- [LinkedBlockingQueue vs ConcurrentLinkedQueue](https://www.baeldung.com/java-queue-linkedblocking-concurrentlinked)
- [Java LinkedBlockingQueue (+ Code Examples)](https://www.happycoders.eu/algorithms/linkedblockingqueue-java/)
- [Java LinkedBlockingQueue Example](https://www.codejava.net/java-core/concurrency/java-linkedblockingqueue-example)
- [LinkedBlockingQueue poll() method in Java](https://www.geeksforgeeks.org/java/linkedblockingqueue-poll-method-in-java/)
- [LinkedBlockingQueue.java from GitHub by OpenJDK](https://github.com/openjdk-mirror/jdk7u-jdk/blob/master/src/share/classes/java/util/concurrent/LinkedBlockingQueue.java)
- [LinkedBlockingQueue spliterator() method with Examples](https://www.geeksforgeeks.org/java/java-8-linkedblockingqueue-spliterator-method-with-examples/)
- [Java LinkedBlockingQueue With Examples](https://www.netjstech.com/2016/03/linkedblockingqueue-in-java.html)
- [Java : LinkedBlockingQueue with Examples](https://programming-tips.jp/archives/a0/69/index.html)

---
### Очередь LinkedBlockingDeque

[Класс LinkedBlockingDeque](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/LinkedBlockingDeque.html) создает двунаправленную очередь с реализацией [интерфейса
BlockingDeque](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/BlockingDeque.html), наследуемого от [интерфейса Deque](https://docs.oracle.com/javase/8/docs/api/java/util/Deque.html). 
Данный класс может иметь ограничение на количество элементов в очереди. Если ограничение не задано, то оно равно значению [Integer.MAX_VALUE](https://docs.oracle.com/javase/8/docs/api/java/lang/Integer.html#MAX_VALUE).

Конструкторы класса:
- `LinkedBlockingDeque()` - Создает объект LinkedBlockingDeque вместимостью Integer.MAX_VALUE;
- `LinkedBlockingDeque(int capacity)` - Создает объект LinkedBlockingDeque вместимостью Integer.MAX_VALUE, первоначально содержащий элементы заданной коллекции, добавляемые в порядке обхода итератора коллекции;
- `LinkedBlockingDeque(Collection<? extends E> c)` - Создает объект LinkedBlockingDeque с заданной (фиксированной) вместимостью;

Класс LinkedBlockingDeque и его iterator реализуют все опциональные методы [Collection](https://docs.oracle.com/javase/8/docs/api/java/util/Collection.html) и [Iterator](https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html).

Элементы в двустороннюю очередь LinkedBlockingDeque можно добавлять при помощи следующих методов:
- `boolean add(E e)`;
- `void addFirst(E e)`;
- `void addLast(E e)`;

Метод `add()` аналогичен методу `addLast()`. В случае нехватки места в двусторонней очереди вызывается исключение [IllegalStateException](https://docs.oracle.com/javase/8/docs/api/java/lang/IllegalStateException.html).

Элементы можно также добавить при помощи следующих методов:
- `boolean offer(E e)`;
- `boolean offerFirst(E e)`;
- `boolean offerLast(E e)`;

В отличие от добавления элементов при помощи метода add...(), при добавлении элементов методом offer...() возвращается false, если элемент не может быть добавлен.

Для удаления элементов имеются методы:
- `remove()`;
- `removeFirst()`;
- `removeLast()`;

Методы remove...() выбрасывают исключение [NoSuchElementException](https://docs.oracle.com/javase/8/docs/api/java/util/NoSuchElementException.html) при пустой двусторонней очереди.

- `poll()`;
- `pollFirst()`;
- `pollLast()`;

Методы poll...() используются для чтения с удалением и возвращают значение null, если очередь пуста.

Несмотря на то, что работа с двусторонними очередями предполагает удаление элементов только с концов очереди, можно удалять определенный объект очереди при помощи следующих методов:
- `boolean remove(Object o)`;
- `boolean removeFirstOccurrence(Object o)`;
- `boolean removeLastOccurrence(Object o)`;

Так как концептуально двусторонняя очередь является привязанной с двух сторон, то можно проводить поиск элементов в любом порядке. Итератор iterator() можно использовать для поиска
элементов с начала до конца, а descendingIterator() — для поиска элементов в обратном направлении с конца до начала.

**!!! Однако нельзя получить доступ к элементу по его местоположению !!!**

Метод toArray() возвращает массив элементов очереди типа Object[].

Двусторонние очереди позволяют сформировать удобные структуры данных при использовании рекурсивных процедур, как, например, работа с лабиринтами и разбор исходных данных.
Так, при разборе исходных данных, можно сохранять правильные варианты в очереди, добавляя их с одной стороны. Если вариант при проверке оказывается неверным, то он удаляется, 
возвращаясь к последнему верному элементу. В этом случае используется только одна сторона очереди, как в стеке. При достижении «дна» необходимо вернуться в начало для 
получения решения, которое начинается с последнего элемента. Другим типичным примером является планировщик заданий в операционной системе.

Пример демонстрирует использование интерфейса BlockingDeque, а вернее его реализацию — класса LinkedBlockingDeque с установленными границами.
Это далеко не лучший пример использования двусторонней очереди, но он позволяет показать применение API и возникающие при достижении предела 
очереди события.

Cм. пример [Less_25_LinkedBlockingDeque_Step4](./Less_25_LinkedBlockingDeque_Step4.java)

---
**Доп. материалы:**
- [LinkedBlockingDeque in Java with Examples](https://www.geeksforgeeks.org/java/linkedblockingdeque-in-java-with-examples/)
- [Java LinkedBlockingDeque (+ Code Examples)](https://www.happycoders.eu/algorithms/linkedblockingdeque-java/)
- [LinkedBlockingDeque in Java](https://www.tutorialspoint.com/article/linkedblockingdeque-in-java)
- [Java LinkedBlockingDeque With Examples](https://www.netjstech.com/2016/04/linkedblockingdeque-in-java.html)
- [LinkedBlockingDeque addAll() method in Java with Examples](https://www.geeksforgeeks.org/java/linkedblockingdeque-addall-method-in-java-with-examples/)
- [LinkedBlockingDeque](https://commons.apache.org/proper/commons-pool/xref/org/apache/commons/pool2/impl/LinkedBlockingDeque.html)
- [Java : LinkedBlockingDeque with Examples](https://programming-tips.jp/archives/a0/71/index.html)
- [LinkedBlockingDeque forEach() method in Java with Examples](https://www.geeksforgeeks.org/java/linkedblockingdeque-foreach-method-in-java-with-examples/)
- [Java Program to Implement LinkedBlockingDeque API](https://www.geeksforgeeks.org/java/java-program-to-implement-linkedblockingdeque-api/)

---
### Очередь SynchronousQueue

[Класс SynchronousQueue](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/SynchronousQueue.html) формирует блокирующую очередь, в которой каждая операция
добавления в одном потоке должна ждать соответствующей операции удаления в другом потоке и наоборот. В сущности, SynchronousQueue является еще одной реализацией 
представленного выше интерфейса BlockingQueue. Данный тип очереди предоставляет удобный способ обмена одиночными элементами между потоками посредством семантики
блокировки, используемой в ArrayBlockingQueue.

Синхронная очередь не имеет внутренней емкости, даже в один элемент.

См. пример [Less_25_SynchQueues_Step5](./Less_25_SynchQueues_Step5.java)

---
**Доп. материалы:**
- [A Guide to Java SynchronousQueue](https://www.baeldung.com/java-synchronous-queue)
- [SynchronousQueue in Java (+ Code Examples)](https://www.happycoders.eu/algorithms/synchronousqueue-java/)
- [Java SynchronousQueue with Example](https://howtodoinjava.com/java/collections/synchronousqueue-class/)
- [Java Program to Implement SynchronousQueue API](https://www.geeksforgeeks.org/java/java-program-to-implement-synchronousqueue-api/)
- [SynchronousQueue Example in Java – Producer Consumer Solution](https://www.javacodegeeks.com/2014/06/synchronousqueue-example-in-java-producer-consumer-solution.html)
- [SynchronousQueue.java from GitHub by OpenJDK](https://github.com/openjdk-mirror/jdk7u-jdk/blob/master/src/share/classes/java/util/concurrent/SynchronousQueue.java)
- [Java SynchronousQueue Examples](https://www.codejava.net/java-core/concurrency/java-synchronousqueue-examples)
- [How to use SynchronousQueue in Java? Prouder Consumer Example](https://javarevisited.blogspot.com/2014/06/synchronousqueue-example-in-java.html)
- [Java SynchronousQueue With Examples](https://www.netjstech.com/2016/03/synchronousqueue-in-java.html)

---
### Очередь [LinkedTransferQueue](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/LinkedTransferQueue.html)

В отличие от реализации очередей интерфейса [BlockingQueue](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/BlockingQueue.html), где потоки могут быть
блокированы при чтении, если очередь пустая, либо при записи, если очередь полная, очереди интерфейса [TransferQueue](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/TransferQueue.html) блокируют поток записи до тех пор, пока другой поток не извлечет элемент. Для этого следует использовать [метод transfer](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/LinkedTransferQueue.html#transfer-E-).

Иначе говоря, реализация BlockingQueue гарантирует, что элемент, созданный производителем (Producer), должен находиться в очереди, в то время как реализация TransferQueue 
гарантирует, что элемент Producer'а «получает» потребитель (Consumer).

См. пример [Less_25_TransferQueue_Step6](./Less_25_TransferQueue_Step6.java)

---
**Доп. материалы:**
- [LinkedTransferQueue in Java](https://www.geeksforgeeks.org/java/linkedtransferqueue-in-java-with-examples/)
- [LinkedTransferQueue in Java (+ Code Examples)](https://www.happycoders.eu/algorithms/linkedtransferqueue-java/)
- [Guide to the Java TransferQueue](https://www.baeldung.com/java-transfer-queue)
- [Java TransferQueue Vs. LinkedTransferQueue](https://howtodoinjava.com/java/collections/transferqueue-linkedtransferqueue/)
- [LinkedTransferQueue transfer() method in Java with Examples](https://www.geeksforgeeks.org/java/linkedtransferqueue-transfer-method-in-java-with-examples/)
- [Transfer Queue](https://medium.com/@kaustubh.saha/transfer-queue-6f95bb5c978d)
- [Java LinkedTransferQueue With Examples](https://www.netjstech.com/2016/04/linkedtransferqueue-in-java.html)
- [Java TransferQueue Tutorial with Examples](https://o7planning.org/13637/java-transferqueue)

---
### Очередь DelayQueue

Неограниченная очередь блокирования элементов [DelayQueue](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/DelayQueue.html) реализует [интерфейс Delayed](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/Delayed.html) и позволяет извлекать элемент с некоторой временно́й задержкой. Если задержка не истекла,
то метод poll вернет null. Очередь не разрешает запись нулевых элементов.

Метод класса [getDelay(TimeUnit.NANOSECONDS)](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/Delayed.html#getDelay-java.util.concurrent.TimeUnit-) вернет 
значение меньше или равное нулю, если время еще не истекло.

Метод [size](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/DelayQueue.html#size--) возвращает общее количество элементов с истекшим и неистекшим временем задержки.

Класс DelayQueue и его iterator реализуют все дополнительные методы [Collection](https://docs.oracle.com/javase/8/docs/api/java/util/Collection.html) и [Iterator](https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html) интерфейсы.

Этот класс является частью [Java Collections Framework](https://docs.oracle.com/javase/8/docs/technotes/guides/collections/index.html).

---
**Доп. материалы:**
- [Guide to DelayQueue](https://www.baeldung.com/java-delay-queue)
- [DelayQueue Class in Java](https://www.geeksforgeeks.org/java/delayqueue-class-in-java-with-example/)
- [Java DelayQueue (+ Code Examples)](https://www.happycoders.eu/algorithms/delayqueue-java/)
- [How do i set up a DelayQueue's Delay](https://stackoverflow.com/questions/23219801/how-do-i-set-up-a-delayqueues-delay)
- [DelayQueue](https://jenkov.com/tutorials/java-util-concurrent/delayqueue.html)
- [Java Program to Implement DelayQueue API](https://www.geeksforgeeks.org/java/java-program-to-implement-delayqueue-api/)
- [DelayQueue.java from GitHub by OpenJDK](https://github.com/openjdk-mirror/jdk7u-jdk/blob/master/src/share/classes/java/util/concurrent/DelayQueue.java)
- [Java DelayQueue Examples](https://www.codejava.net/java-core/concurrency/java-delayqueue-examples)
- [Changing delay, and hence the order, in a DelayQueue](https://www.javacodegeeks.com/2012/09/changing-delay-and-hence-order-in.html)
- [Java DelayQueue – Blocking Queue for Delayed Elements](https://howtodoinjava.com/java/multi-threading/java-delayqueue/)

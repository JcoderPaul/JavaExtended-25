- См. оф. док. (ENG): [Class CountDownLatch](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/CountDownLatch.html)

---
### Класс CountDownLatch

Иногда требуется, чтобы поток исполнения находился в режиме ожидания до тех пор, пока не наступит одно или больше событий. 
Для этих целей в многопоточном программировании создан [класс CountDownLatch](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/CountDownLatch.html), 
реализующий блокировку потоков с обратным отсчетом.

Объект этого класса изначально создается с количеством событий, которые должны произойти до того момента, как будет снята 
блокировка потоков. Всякий раз, когда происходит событие, значение счетчика уменьшается.

Как только значение счетчика достигнет нуля, блокировка потоков будет снята.

В классе CountDownLatch имеется приведенный ниже конструктор, где параметр 'число' определяет количество событий, которые 
должны произойти до того, как будет снята блокировка.

- [`CountDownLatch(int число)`](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/CountDownLatch.html#CountDownLatch-int-) - Создает объект, CountDownLatch инициализированный заданным значением счетчика.

Для ожидания по счетчику CountDownLatch в потоке исполнения вызывается [метод await()](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/CountDownLatch.html#await--),
общие формы которого приведены ниже:
- [`void await() throws InterruptedException`](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/CountDownLatch.html#await--) - ожидание длится до тех пор, пока отсчет, связанный с вызывающим объектом типа CountDownLatch, не достигнет нуля.

Cм. пример [Less_25_CountDownLatch_Step1](../Less_25_CountDownLatch_Step1.java)

- [`boolean await(long 'ожидание', TimeUnit 'единица_времени') throws InterruptedException`](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/CountDownLatch.html#await-long-java.util.concurrent.TimeUnit-) - ожидание длится только в течение определенного периода времени, определяемого параметром 'ожидание'. Время ожидания указывается в единицах, обозначаемых параметром 'единица_времени', который принимает объект перечисления [TimeUnit](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/TimeUnit.html).

Cм. пример [Less_25_CountDownLatch_Step2](../Less_25_CountDownLatch_Step2.java)

Метод await() изначально возвращает логическое значение false. Если достигнут предел времени ожидания, или если обратный отсчет достигает нуля, тогда возвращается true.

Чтобы известить о событии которое повлияло на изменение счетчика CountDownLatch, следует вызвать метод countDown(). Всякий раз, когда вызывается метод countDown() на 
объекте счетчике, отсчет, связанный с вызывающим объектом, уменьшается на единицу.

Класс CountDownLatch является эффективным и простым в употреблении средством синхронизации, которое окажется полезным в тех случаях, когда поток исполнения должен 
находиться в состоянии ожидания до тех пор, пока не произойдет одно или несколько событий.

---
**Доп. материал:**
- [Class CountDownLatch](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/CountDownLatch.html)
- [Class InterruptedException](https://docs.oracle.com/javase/8/docs/api/java/lang/InterruptedException.html)
- [Enum TimeUnit](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/TimeUnit.html)

---
- [CountDownLatch in Java](https://www.geeksforgeeks.org/java/countdownlatch-in-java/)
- [Guide to CountDownLatch in Java](https://www.baeldung.com/java-countdown-latch)
- [Understanding CountDownLatch in Java - A Comprehensive Guide](https://medium.com/javarevisited/understanding-countdownlatch-in-java-4f9cdff5a15f)
- [CountDownLatch.java **(from GitHub OpenJDK)**](https://github.com/openjdk-mirror/jdk7u-jdk/blob/master/src/share/classes/java/util/concurrent/CountDownLatch.java)
- [What is CountDownLatch in Java - Concurrency Example Tutorial](https://javarevisited.blogspot.com/2012/07/countdownlatch-example-in-java.html)
- [Java’s CountDownLatch.await() Method Explained](https://medium.com/@AlexanderObregon/javas-countdownlatch-await-method-explained-2fa0c0c4b044)
- [CountDownLatch **(from jenkov.com)**](https://jenkov.com/tutorials/java-util-concurrent/countdownlatch.html)
- [CountDownLatch **(from developer.android.com)**](https://developer.android.com/reference/java/util/concurrent/CountDownLatch)
- [Difference Between CountDownLatch And CyclicBarrier in Java](https://www.geeksforgeeks.org/java/difference-between-countdownlatch-and-cyclicbarrier-in-java/)
- [How to use the Java CountDownLatch](https://vladmihalcea.com/java-countdownlatch/)
- [Java CountDownLatch](https://www.concretepage.com/java/java-countdownlatch)
- [Ожидание завершения потоков с помощью CountDownLatch](https://www.codorbits.com/course/countdownlatch-in-java/)
- [Java Concurrency Basics: CountDownLatch and CyclicBarrier](https://www.pixelstech.net/article/1521976430-java-concurrency-basics%3A-countdownlatch-and-cyclicbarrier)
- [Справочник по синхронизаторам java.util.concurrent.* (с крутыми картинками)))](https://habr.com/ru/articles/277669/)
- [CountDownLatch vs. Semaphore](https://www.baeldung.com/java-countdownlatch-vs-semaphore)

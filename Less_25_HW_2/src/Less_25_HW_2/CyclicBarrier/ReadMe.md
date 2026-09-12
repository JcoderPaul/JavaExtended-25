**См. оригиналы (ENG):**
- **[Class CyclicBarrier](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/CyclicBarrier.html)**
- **[Class CountDownLatch](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/CountDownLatch.html)**

---
### Класс CyclicBarrier

В программировании нередко возникают такие ситуации, когда два или более потоков должны находиться в режиме ожидания в 
предопределенной точке исполнения до тех пор, пока все эти потоки не достигнут данной точки.

**Барьерная синхронизация** — метод синхронизации в распределённых вычислениях, при котором выполнение параллельного 
алгоритма или его части можно разделить на несколько этапов, разделённых барьерами. В частности, с помощью барьера 
можно организовать точку сбора частичных результатов вычислений, в которой подводится итог этапа вычислений.

Использование барьеров как примитивов синхронизации особенно полезно при циклической организации этапов расчетов.

Барьер для группы потоков (или процессов) в исходном коде означает, что каждый поток (процесс) должен остановиться в 
этой точке и подождать достижения барьера всеми потоками (процессами) группы. Когда все потоки (процессы) достигли 
барьера, их выполнение продолжается.

---
#### CyclicBarrier

CyclicBarrier реализует шаблон синхронизации 'Барьер'. Циклический барьер является точкой синхронизации, в которой 
указанное количество параллельных потоков встречается и блокируется. Как только все потоки прибыли, выполняется опционное 
действие (или не выполняется, если барьер был инициализирован без него), и, после того, как оно выполнено, барьер ломается и
ожидающие потоки «освобождаются».

В конструктор барьера `CyclicBarrier(int parties)` и `CyclicBarrier(int parties, Runnable barrierAction)` обязательно передается 
количество сторон, которые должны «встретиться», и, опционально, действие, которое должно произойти, когда стороны встретились, 
но перед тем когда они будут «отпущены».

Барьер похож на CountDownLatch, но главное различие между ними в том, что мы не можем заново использовать «замок» - CountDownLatch 
после того, как его счётчик достигнет нуля, а барьер мы можем использовать снова, даже после того, как он сломается (достигнет 
заданного значения).

CyclicBarrier является альтернативой метода `join()`, который «собирает» потоки только после того, как они выполнились.

---
#### Class CyclicBarrier

```java
  public class CyclicBarrier extends Object
```

Средство синхронизации, позволяющее всем потокам ожидать друг друга, пока они не достигнут общей точки - барьера. CyclicBarriers 
полезны в программах, включающих группу потоков фиксированного размера, которые должны время от времени ожидать друг друга. Барьер 
называется циклическим, потому что его можно использовать повторно после освобождения ожидающих потоков.

CyclicBarrier поддерживает необязательную [Runnable](https://docs.oracle.com/javase/8/docs/api/java/lang/Runnable.html) команду, 
которая запускается один раз для каждой точки барьера после прибытия последнего потока в группе, но до освобождения каких-либо потоков.
Это барьерное действие полезно для обновления общего состояния до того, как какая-либо из сторон продолжит работу.

**Вот пример использования барьера в схеме параллельной декомпозиции:**

```java 
 class Solver {
   final int N;
   final float[][] data;
   final CyclicBarrier barrier;

   class Worker implements Runnable {
     int myRow;
     Worker(int row) { myRow = row; }
     public void run() {
       while (!done()) {
         processRow(myRow);

         try {
           barrier.await();
         } catch (InterruptedException ex) {
           return;
         } catch (BrokenBarrierException ex) {
           return;
         }
       }
     }
   }

   public Solver(float[][] matrix) {
     data = matrix;
     N = matrix.length;
     Runnable barrierAction =
       new Runnable() { public void run() { mergeRows(...); }};
     barrier = new CyclicBarrier(N, barrierAction);

     List<Thread> threads = new ArrayList<Thread>(N);
     for (int i = 0; i < N; i++) {
       Thread thread = new Thread(new Worker(i));
       threads.add(thread);
       thread.start();
     }

     // wait until done
     for (Thread thread : threads)
       thread.join();
   }
 }
```

Здесь каждый рабочий поток обрабатывает строку матрицы, а затем ожидает на барьере, пока не будут обработаны все строки. 
Когда все строки обработаны, выполняется указанное Runnable действие барьера, которое объединяет строки. Если в результате 
объединения найдено решение, метод done() возвращает управление true, и каждый рабочий поток завершает работу.

Если действие барьера не зависит от приостановления работы участников на момент его выполнения, то любой из потоков в группе 
может выполнить это действие после его освобождения. Для этого каждый вызов метода await() возвращает индекс прибытия этого 
потока к барьеру. Затем вы можете выбрать, какой поток должен выполнить действие барьера, например:

```java 
 if (barrier.await() == 0) {
   // log the completion of this iteration
 }
```
 
В данном случае CyclicBarrier используется модель прерывания по принципу «всё или ничего» для неудачных попыток синхронизации: 
если поток преждевременно покидает точку барьера из-за прерывания, сбоя или таймаута, все остальные потоки, ожидающие в этой 
точке барьера, также покинут её ненормально [BrokenBarrierException](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/BrokenBarrierException.html) 
(или [InterruptedException](https://docs.oracle.com/javase/8/docs/api/java/lang/InterruptedException.html) если они тоже были 
прерваны примерно в то же время).

Эффекты согласованности памяти: действия в потоке до вызова await() происходят до действий, которые являются частью барьерного 
действия, которые, в свою очередь, происходят до действий, следующих за успешным возвратом из соответствующего await() в других 
потоках.

---
#### Методы

---
- `int await()` - Ожидает, пока все стороны не вызовут await на этом барьере.

Ожидает, пока все стороны не вызовут await на этом барьере. Если текущий поток прибыл не последним, то он отключается в целях 
планирования потоков и остается бездействующим до тех пор, пока не произойдет одно из следующих событий:
- приходит последний поток;
- какой-то другой поток прерывает текущий поток;
- какой-то другой поток прерывает один из других ожидающих потоков;
- таймаут некоторых других потоков в ожидании барьера;
- какой-то другой поток вызывает reset() для данного текущего барьера.

Если текущий поток:
- имеет установленный статус прерывания при входе в этот метод;
- или же прерывается во время ожидания;

то, генерируется InterruptedException, и состояние прерванного текущего потока сбрасывается.

Если барьер reset() во время ожидания любого потока, или если барьер нарушается при вызове await, или во время ожидания любого
потока, выбрасывается исключение BrokenBarrierException.

Если какой-либо поток прерывается во время ожидания, то все остальные ожидающие потоки будут генерировать исключение 
BrokenBarrierException, и барьер будет переведен в "сломанное состояние" - "broken state".

Если текущий поток является последним прибывшим потоком, а в конструкторе было указано ненулевое барьерное действие, то текущий поток 
выполняет действие, прежде чем позволить другим потокам продолжить работу. Если во время действия барьера возникает исключение, то это 
исключение будет распространено в текущем потоке, и барьер будет переведен в "сломанное состояние" - "broken state".

**Возвращает:** индекс прибытия текущего потока, где индекс getParties() - 1 указывает на прибытие первым, а ноль указывает на прибытие последним

**Исключения:**
- `InterruptedException` - если текущий поток был прерван во время ожидания;
- `BrokenBarrierException` — если другой поток был прерван или истекло время ожидания текущего потока, или барьер был сброшен, или барьер был сломан
при вызове await, или действие барьера (если оно присутствует) не удалось из-за исключения.

---
- `int await(long timeout, TimeUnit unit)` - Ожидает, пока все стороны не вызовут await на этом барьере, или пока не истечет указанное время ожидания.
- `int getNumberWaiting()` - Возвращает количество сторон, ожидающих в данный момент у барьера.
- `int getParties()` - Возвращает количество сторон, необходимое для преодоления этого барьера.
- `boolean isBroken()` - Запрашивает, не сломан ли этот барьер.
- `void reset()` - Сбрасывает барьер в исходное состояние.

---
**Cм. док. (ENG):** 
- [CyclicBarrier](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/CyclicBarrier.html);
- [CountDownLatch](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/CountDownLatch.html);

---
**Доп. материал:**
- [CyclicBarrier in Java with Examples](https://www.geeksforgeeks.org/java/cyclicbarrier-in-java-with-examples/)
- [CyclicBarrier in Java](https://www.baeldung.com/java-cyclic-barrier)
- [Real Life Examples For CountDownLatch and CyclicBarrier](https://stackoverflow.com/questions/10156191/real-life-examples-for-countdownlatch-and-cyclicbarrier)
- [Mastering `CyclicBarrier` in Java with Real-World Code Examples](https://dev.to/devcorner/mastering-cyclicbarrier-in-java-with-real-world-code-examples-ok0)
- [Java CyclicBarrier vs CountDownLatch](https://www.baeldung.com/java-cyclicbarrier-countdownlatch)
- [CyclicBarrierExample.java (from GitHub Gist Example)](https://gist.github.com/Waterfox83/0e21e88b5178fdfe53c06cf7b7129473)
- [CyclicBarrier in Java's Multithreading](https://ducmanhphan.github.io/2019-12-23-CyclicBarrier-in-Java's-Multithreading/)

---
- [Difference Between CountDownLatch And CyclicBarrier in Java](https://www.geeksforgeeks.org/java/difference-between-countdownlatch-and-cyclicbarrier-in-java/)
- [CountDownLatch and CyclicBarrier in Java](https://medium.com/hprog99/countdownlatch-and-cyclicbarrier-in-java-9fec58575f51)
- [Barriers in Java concurrency](https://www.waitingforcode.com/java-concurrency/barriers-in-java-concurrency/read)
- [CyclicBarrier in Java](https://javadevcentral.com/cyclicbarrier-in-java/)

---
- [Guide to CountDownLatch in Java](https://www.baeldung.com/java-countdown-latch)
- [CountDownLatch in Java](https://www.geeksforgeeks.org/java/countdownlatch-in-java/)
- [How is CountDownLatch used in Java Multithreading?](https://stackoverflow.com/questions/17827022/how-is-countdownlatch-used-in-java-multithreading)
- [CountDownLatch in Java](https://www.waitingforcode.com/java-concurrency/countdownlatch-in-java/read)
- [CountDownLatch in Java — A Complete Guide with Robust Example](https://dev.to/devcorner/countdownlatch-in-java-a-complete-guide-with-robust-example-8kb)
- [CountDownLatchExample.java (from GitHub Gist Example)](https://gist.github.com/SvetlinDimitrov/8d38852be28079af2970bc3e2620d964)
- [CountDownLatch in Java](https://javadevcentral.com/countdownlatch-in-java/)
- [CountDownLatch](https://jenkov.com/tutorials/java-util-concurrent/countdownlatch.html)
- [What is a Java CountDownLatch?](https://redisson.pro/glossary/java-countdownlatch.html)
- [The CountDownLatch in JAVA](https://seyfertsoft.com/the-countdownlatch-in-java/)
- [Testing Asynchronous Code in Java with CountDownLatch](https://onraz.github.io/java/2014/08/05/testing-asynchronous-code-in-java-with-countdownlatch.html)
- [CountDownLatch in Java](https://www.javabyexamples.com/countdownlatch-in-java)
- [Java CountDownLatch with Example](https://howtodoinjava.com/java/multi-threading/when-to-use-countdownlatch-java-concurrency-example-tutorial/)

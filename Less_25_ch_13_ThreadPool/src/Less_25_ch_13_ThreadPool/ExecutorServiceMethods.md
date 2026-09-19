- **См. оф. док.: [Interface ExecutorService](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/ExecutorService.html)**

### ExecutorService

Все родителя: [Executor](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/ExecutorService.html)

Все наследники: [ScheduledExecutorService](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/ScheduledExecutorService.html)

Реализующие классы:
[AbstractExecutorService](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/AbstractExecutorService.html), 
[ForkJoinPool](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/ForkJoinPool.html), 
[ScheduledThreadPoolExecutor](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/ScheduledThreadPoolExecutor.html), 
[ThreadPoolExecutor](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/ThreadPoolExecutor.html)

```java
  public interface ExecutorService
    extends Executor
```
Интерфейс [Executor](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/Executor.html), предоставляющий методы для управления завершением 
и методы, позволяющие отслеживать работу [Future](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/Future.html) ход выполнения одной или 
нескольких асинхронных задач.

Исполнитель [ExecutorService](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/ExecutorService.html) может быть остановлен, что приведет к 
отклонению новых задач. Для остановки исполнителя предусмотрены два разных метода в ExecutorService. Метод [shutdown()](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/ExecutorService.html#shutdown--) позволяет выполнить ранее отправленные задачи перед завершением работы, в то время как метод [shutdownNow()](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/ExecutorService.html#shutdownNow--) 
предотвращает запуск ожидающих задач и пытается остановить выполняющиеся в данный момент задачи. После завершения работы у исполнителя нет активно 
выполняемых задач, нет задач, ожидающих выполнения, и новые задачи не могут быть отправлены. Неиспользуемый исполнитель ExecutorService следует остановить, 
чтобы освободить его ресурсы.

Метод submit расширяет базовый метод [Executor.execute(Runnable)](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/Executor.html#execute-java.lang.Runnable-), 
создавая и возвращая объект [Future](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/Future.html), который можно использовать для отмены выполнения и/или 
ожидания завершения. Методы [invokeAny](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/ExecutorService.html#invokeAny-java.util.Collection-) и [invokeAll](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/ExecutorService.html#invokeAll-java.util.Collection-) выполняют наиболее часто используемые формы пакетного 
выполнения, выполняя набор задач, а затем ожидая завершения хотя бы одной или всех задач. 

(Класс [ExecutorCompletionService](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/ExecutorCompletionService.html) можно использовать для написания 
пользовательских вариантов этих методов.)

Класс [Executors](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/Executors.html) предоставляет фабричные методы для служб исполнителей, входящих в данный пакет.

**Примеры использования:**

Пример сетевого сервиса, в котором потоки из пула потоков обрабатывают входящие запросы. 
Он использует предварительно настроенный фабричный метод [Executors.newFixedThreadPool(int)](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/Executors.html#newFixedThreadPool-int-):

```java
 class NetworkService implements Runnable {
   private final ServerSocket serverSocket;
   private final ExecutorService pool;

   public NetworkService(int port, int poolSize)
       throws IOException {
     serverSocket = new ServerSocket(port);
     pool = Executors.newFixedThreadPool(poolSize);
   }

   public void run() { // run the service
     try {
       for (;;) {
         pool.execute(new Handler(serverSocket.accept()));
       }
     } catch (IOException ex) {
       pool.shutdown();
     }
   }
 }

 class Handler implements Runnable {
   private final Socket socket;
   Handler(Socket socket) { this.socket = socket; }
   public void run() {
     // чтение и обработка запроса из сокета
   }
 }
```

Следующий метод завершает работу ExecutorServiceв два этапа: сначала вызывается метод shutdownдля отклонения входящих задач, а затем shutdownNow, 
при необходимости, вызывается метод для отмены оставшихся задач:

```java 
 void shutdownAndAwaitTermination(ExecutorService pool) {
   pool.shutdown(); // Disable new tasks from being submitted
   try {
     // Ожидаем пока завершатся текущие задачи.
     if (!pool.awaitTermination(60, TimeUnit.SECONDS)) {
       pool.shutdownNow(); // Cancel currently executing tasks
       // Ожидаем пока задачи отреагируют на отмену.
       if (!pool.awaitTermination(60, TimeUnit.SECONDS))
           System.err.println("Pool did not terminate");
     }
   } catch (InterruptedException ie) {
     // (Повторно) отменить, если текущий поток также прерван
     pool.shutdownNow();
     // Сохранить состояние прерывания
     Thread.currentThread().interrupt();
   }
 }
```

Эффекты согласованности памяти: 
Действия в потоке, предшествующие отправке задачи Runnable или Callable в поток ExecutorService-а происходят до любых действий, выполняемых 
этой задачей, которые, в свою очередь, происходят до получения результата через [Future.get()](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/Future.html#get--).

---
#### Методы

- `boolean awaitTermination(long timeout, TimeUnit unit)`	- Блокирует (заставляет ждать) поток в котором он был вызван до тех пор, пока все задачи ExecutorService не завершат свое выполнение после запроса на завершение
работы от метода *.shutdown(), например, или пока не наступит тайм-аут или не будет прерван текущий поток, в зависимости от того, что произойдет раньше;
- `<T> List<Future<T>> invokeAll (Collection<? extends Callable<T>> tasks)` -	Выполнение задач с возвращением списка задач с их статусом и результатами завершения;
- `<T> List<Future<T>> invokeAll (Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit)` - Выполнение задач с возвращением списка задач с их статусом и результатами завершения в течение заданного времени;
- `<T> T invokeAny(Collection<? extends Callable<T>> tasks)` - Выполнение задач с возвращением результата успешно выполненной задачи (т. е. без создания исключения), если таковые имеются;
- `<T> T invokeAny(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit)`	- Выполнение задач в течение заданного времени с возвращением результата успешно выполненной задачи (т. е. без создания сключения), если таковые имеются;
- `boolean isShutdown()` - Возвращает true, если исполнитель сервиса остановлен (shutdown);
- `boolean isTerminated()` - Возвращает true, если все задачи исполнителя сервиса завершены по команде остановки (shutdown);
- `void shutdown()` - Упорядоченное завершение работы, при котором ранее отправленные задачи выполняются, а новые задачи не принимаются;
- `List<Runnable> shutdownNow()` - Остановка всех активно выполняемых задач, остановка обработки ожидающих задач, возвращение списка задач, ожидающих выполнения;
- `<T> Future<T> submit(Callable<T> task)` - Завершение выполнения задачи, возвращающей результат в виде объекта Future;
- `Future<?> submit(Runnable task)` - Завершение выполнения задачи, возвращающей объект Future, представляющий данную задачу;
- `<T> Future<T> submit(Runnable task, T result)` - Завершение выполнения задачи, возвращающей объект Future, представляющий данную задачу;

---
**См. док. (ENG):** [Interface ExecutorService](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/ExecutorService.html)

---
- [Java Executor Framework](https://www.geeksforgeeks.org/java/what-is-java-executor-framework/)
- [A Guide to the Java ExecutorService](https://www.baeldung.com/java-executor-service-tutorial)
- [Java ExecutorService](https://jenkov.com/tutorials/java-util-concurrent/executorservice.html)
- [Java.util.concurrent.ExecutorService Interface with Examples](https://www.geeksforgeeks.org/java/java-util-concurrent-executorservice-interface-with-examples/)
- [Java ExecutorService tutorial (from David Vlijmincx)](https://davidvlijmincx.com/posts/how-to-java-executorservice/)
- [Java’s ExecutorService.awaitTermination() Method Explained](https://medium.com/@AlexanderObregon/javas-executorservice-awaittermination-method-explained-76cc9a32cffd)
- [ExecutorServiceSample.java (from GitHub)](https://gist.github.com/NeoAyush/a521ef8e5c53f1a015b1cc78b61eac6c)
- [Introduction to ExecutorService in Java](https://blog.ycrash.io/introduction-to-executorservice-in-java/)
- [ExecutorService in Java and examples.](https://medium.com/@pravvich/executorservice-in-java-and-examples-75c240d78241)
- [Что такое ExecutorService?](https://habr.com/ru/articles/554608/)
- [ExecutorService in Java – Java ExecutorService Examples](https://techvidvan.com/tutorials/java-executorservice/)
- [Executor And ExecutorService in Java With Examples](https://www.netjstech.com/2016/04/executor-and-executorservice-in-java-concurrency.html)
- [Интерфейс ExecutorService](https://javarush.com/quests/lectures/jru.module2.lecture18)

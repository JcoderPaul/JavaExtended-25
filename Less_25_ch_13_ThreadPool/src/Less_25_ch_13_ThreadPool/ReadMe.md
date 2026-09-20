См.:
- [Thread Pools - The Java™ Tutorials - from ORACLE](https://docs.oracle.com/javase/tutorial/essential/concurrency/pools.html)
- [Executors  - The Java™ Tutorials - from ORACLE](https://docs.oracle.com/javase/tutorial/essential/concurrency/executors.html)

### Пул потоков

Создавать потоки для выполнения большого количества задач очень трудоемко: создание потока и освобождение ресурсов — дорогостоящие операции. 
Для решения проблемы ввели пулы потоков и очереди задач, из которых берутся задачи для пулов.

Пул потоков — своего рода контейнер, в котором содержатся потоки, которые могут выполнять задачи, и после выполнения очередной самостоятельно 
переходить к следующей.

Вторая причина создания пулов потоков — возможность разделить объект, выполняющий код, и непосредственно код задачи, которую необходимо выполнить. 
Использование пула потоков обеспечивает лучший контроль создания потоков и экономит ресурсы создания потоков. Также использование пула потоков 
упрощает разработку многопоточных программ, упрощая создание и манипулирование потоками. 

За созданием и управлением пулом потоков отвечают несколько классов и интерфейсов, которые называются "Executor Framework in Java".

---
### Executor Framework

Рассмотрим основные интерфейсы и классы, входящие в этот фреймворк.

Его основные интерфейсы:

---
- [Executor - интерфейс](https://docs.oracle.com/javase/tutorial/essential/concurrency/exinter.html), содержащий [метод execute()](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/Executor.html#execute-java.lang.Runnable-) для запуска задачи, заданной запускаемым объектом Runnable;
    
- [ExecutorService - интерфейс](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/ExecutorService.html) потомок [интерфейса Executor](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/Executor.html), который добавляет функциональность для управления жизненным циклом потоков.
Он включает в себя [метод submit()](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/ExecutorService.html#submit-java.lang.Runnable-), который
аналогичен [методу execute()](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/Executor.html#execute-java.lang.Runnable-), но более универсален.
Перегруженные версии метода submit() могут принимать как [выполняемый *Runnable*](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/ExecutorService.html#submit-java.lang.Runnable-),
так и [вызываемый *Callable*](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/ExecutorService.html#submit-java.util.concurrent.Callable-) объект.
Вызываемые объекты аналогичны выполняемым, за тем исключением, что задача, определенная вызываемым объектом, также может возвращать значение. Поэтому, если мы
передаем [объект Callable](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/Callable.html) методу submit(), он возвращает объект [Future](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/Future.html). Этот объект можно использовать для получения возвращаемого значения [Callable](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/Callable.html) и управления статусом как Callable, так и Runnable задач.
    
- [ScheduledExecutorService - интерфейс](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/ScheduledExecutorService.html) потомок от [ExecutorService](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/ExecutorService.html). Он добавляет функциональность, которая позволяет планировать выполнение задач
в коде.
    
- [Executors - класс](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/Executors.html), который по умолчанию включает в себя методы для создания
различных типов служб-исполнителей. С помощью этого класса и интерфейсов можно создавать пулы потоков.

Объекты, которые реализуют [интерфейс Executor](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/Executor.html), могут выполнять runnable-задачу. 
Интерфейс Executor имеет один [метод void execute(Runnable command)](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/Executor.html#execute-java.lang.Runnable-). 
После вызова этого метода и передачи задачи на выполнение, задача в будущем будет выполнена асинхронно. 

**Также этот интерфейс разделяет, кто будет выполнять задачу и что будет выполняться! В отличие от класса Thread.**

---

Интерфейс ExecutorService наследуется от интерфейса Executor и предоставляет возможности для: 
- выполнения заданий Callable;
- прерывания выполняемой задачи;
- завершения работы пула потоков.

Для выполнения задач, которые возвращают результат, существует [метод submit()](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/ExecutorService.html#submit-java.lang.Runnable-), 
возвращающий объект, [который реализует интерфейс Future<T>](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/Future.html). С помощью этого объекта можно узнать, есть ли результат, 
вызовом [метода isDone()](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/Future.html#isDone--). С помощью [метода get()](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/Future.html#get--) можно получить результат выполнения задачи, если он есть. Также можно отменить задание на выполнение при помощи [метода cancel()](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/Future.html#cancel-boolean-).

Класс [Executors — утилитный клас](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/Executors.html), как например, класс Collections. Класс Executors создает классы, которые
реализуют интерфейсы Executor и ExecutorService. 

**В классе Executors есть четыре основных метода, которые используются для создания пулов потоков:**

- [Executors.newFixedThreadPool(Thread N)](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/Executors.html#newFixedThreadPool-int-) — пул потоков, который содержит фиксированное количество потоков.

Таким образом, когда мы, например, отправим 'X' задач, будет создано 'N' новых потоков и будут выполнено 'N'
задач. Если (Х > N), то остальные (X - N) задачи находятся в очереди ожидания. Как только какая-либо задача
выполнится потоком, и он освободится этим же потоком будет выбрана и выполнена следующая задача, см. 
пример [Less_25_ThreadPool_Step1](../Less_25_ch_13_ThreadPool/Less_25_FixedPool_Step1.java)

- [Executors.newSingleThreadExecutor()](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/Executors.html#newSingleThreadExecutor--) — пул потоков, в котором есть только один поток.

В данном случае будет создан только один новый поток и одновременно будет выполняться только одна задача.
Остальные 'X' задач находятся в очереди ожидания. Как только задача выполнится потоком, этот поток тут же
выберет и выполнит следующую и т.д., см. пример [Less_25_ThreadPool_Step2](../Less_25_ch_13_ThreadPool/Less_25_SinglePool_Step2.java)

- [Executors.newCachedThreadPool()](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/Executors.html#newCachedThreadPool--) - возвращает пул потоков, если в пуле не хватает потоков, в нем будет создан новый поток.

Когда мы создаем пул потоков с помощью этого метода, максимальный размер пула потоков устанавливается на
максимальное целочисленное значение в Java, необходимое для текущей работы. Этот метод создает новые потоки
по запросу и разрушает потоки, которые простаивают больше минуты, если запрос отсутствует.

Таким образом, этот метод — хороший выбор, если нам хочется добиться большей производительности очереди,
чем это возможно с методом - `newFixedThreadPool()`. Но если мы хотим ограничить количество параллельно
выполняемых задач во имя управления ресурсами, лучше использовать - `newFixedThreadPool()`.

- [Executors.newScheduledThreadPool(Tread N)](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/Executors.html#newScheduledThreadPool-int-) — этот пул потоков позволяет запускать задания с определенной периодичностью или один раз по истечении промежутка времени, количество заранее созданных потоков можно задавать.

Метод newScheduledThreadPool() создает пул потоков, который может планировать выполнение задач после заданной задержки или через регулярные промежутки времени. 
Этот метод возвращает [ScheduledExecutorService](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/ScheduledExecutorService.html).

**Существует три метода для планирования задач в таком пуле потоков:**

- [schedule() - метод](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/ScheduledExecutorService.html#schedule-java.util.concurrent.Callable-long-java.util.concurrent.TimeUnit-) принимает три аргумента: задачу, задержку и [промежуток времени задержки](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/TimeUnit.html), используется для планирования задачи после фиксированной задержки;
- [scheduleAtFixedRate() - метод](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/ScheduledExecutorService.html#scheduleAtFixedRate-java.lang.Runnable-long-long-java.util.concurrent.TimeUnit-) используется для планирования задачи после фиксированной задержки и последующего периодического выполнения этой задачи;
- [scheduleWithFixedDelay() - метод](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/ScheduledExecutorService.html#scheduleWithFixedDelay-java.lang.Runnable-long-long-java.util.concurrent.TimeUnit-) используется для планирования задачи после начальной задержки, а затем выполнения задач с фиксированной задержкой после завершения предыдущей задачи..
  
---
### !!! Важные замечания как использовать фреймворк Executor !!!

- Никогда не ставить в очередь задачи, которые в это самое время ожидают результатов от других задач. **Это может привести к тупику.**
- Пул потоков, после окончания работы, [должен быть явно завершен путем вызова метода shutdown()](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/ExecutorService.html#shutdown--). Если этого не сделать, программа будет продолжать работать без конца. Если мы отправим исполнителю другую задачу после завершения работы, она выдаст [исключение RejectedExecutionException](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/RejectedExecutionException.html).
- Нужно с осторожностью, применять потоки для длительных операций. **Это может привести к бесконечному ожиданию потока и в конечном итоге — к утечке ресурсов.**
- Для эффективной настройки пула потоков необходимо понимать особенности задач. Если задачи очень разные, имеет смысл использовать разные пулы потоков для разных типов задач, чтобы правильно их настроить.

---
**Доп. материалы:**
- [Executors (from ORACLE Tutorials)](https://docs.oracle.com/javase/tutorial/essential/concurrency/executors.html)
- [Executor Interfaces (from ORACLE Tutorials)](https://docs.oracle.com/javase/tutorial/essential/concurrency/exinter.html)
- [Fork / Join (from ORACLE Tutorials)](https://docs.oracle.com/javase/tutorial/essential/concurrency/forkjoin.html)

--- 
- [Executor Framework in Java](https://medium.com/@kaustubh.saha/executor-framework-in-java-25e98c40899f)
- [What is Executor Framework in Java and how to use it?](https://www.edureka.co/blog/executor-framework/)
- [Deep Dive Into Java Executor Framework](https://dzone.com/articles/deep-dive-into-java-executorservice)
- [A Guide to the Java ExecutorService](https://www.baeldung.com/java-executor-service-tutorial)
- [ava Executor Framework](https://www.linkedin.com/pulse/java-executor-framework-alphadot-tech-dvmef)
- [MultiThreading: Java Executor Framework](https://www.codingshuttle.com/spring-boot-handbook/multi-threading-java-executor-framework/)
- [Modern Java Concurrency Foundations: From Threads to the Executor Framework](https://blog.stackademic.com/modern-java-concurrency-foundations-from-threads-to-the-executor-framework-86859e514125)
- [Лекции по многопоточности (Университет Тафта)](https://www.cs.tufts.edu/comp/150CCP/lectures/)

---
- [Introduction to Thread Pools in Java](https://www.baeldung.com/thread-pool-java-and-guava)
- [Thread Pool in Java](https://www.geeksforgeeks.org/java/thread-pools-java/)
- [Thread Pool in Java](https://www.tpointtech.com/java-thread-pool)
- [Thread Pools in Java](https://medium.com/hprog99/thread-pools-in-java-f8df6e71de8c)
- [Thread Pools in Java](https://techvidvan.com/tutorials/thread-pools-in-java/)
- [ThreadPoolExecutor](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/ThreadPoolExecutor.html)
- [Finally Getting the Most out of the Java Thread Pool](https://stackify.com/java-thread-pools/)
- [Thread Pools](https://jenkov.com/tutorials/java-concurrency/thread-pools.html)
- [Java Thread Pool Implementation and Best Practices in Business Applications](https://www.alibabacloud.com/blog/java-thread-pool-implementation-and-best-practices-in-business-applications_601528)
- [Java Thread Pools and its Usage](https://dev.to/md_monowarulamin200042/java-thread-pools-and-its-usage-4o68)
- [Understanding Thread Pools, Worker Threads, and Types of Thread Pools](https://www.tothenew.com/blog/understanding-thread-pools-worker-threads-and-types-of-thread-pools/)
- [Threads, ThreadPools and Executors - Multi Thread Processing In Java](https://softwaremill.com/threadpools-executors-and-java/)
- [Java Thread Pool and ThreadPoolExecutor Tutorial](https://www.centron.de/en/tutorials/java-thread-pool-and-threadpoolexecutor-tutorial)
- [Java - Thread Pools](https://www.tutorialspoint.com/java/java_thread_pool.htm)

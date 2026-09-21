См. :
- [`Interface Callable<V>`](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/Callable.html)
- [`Interface Future<V>`](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/Future.html)

### Интерфейсы Callable и Future в Java

Очень часто при работе с потоками нам нужно получать какой-то результат и было бы очень удобно, чтобы поток сам возвращал результаты своей работы. 
Именно поэтому еще в Java 5 появились интерфейсы [`Callable<V>`](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/Callable.html) и 
[`Future<V>`](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/Future.html). Интерфейс `Callable<V>` очень похож на интерфейс 
[Runnable](https://docs.oracle.com/javase/8/docs/api/java/lang/Runnable.html), но может вернуть результат в виде объекта 
[Object](https://docs.oracle.com/javase/8/docs/api/java/lang/Object.html) и способен бросать исключения. Совместное использование двух реализаций 
данных интерфейсов позволяет получить результат в виде некоторого объекта.

---
### Интерфейс `Callable<V>`

Интерфейс [`Callable<V>`](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/Callable.html) очень похож на интерфейс 
[Runnable](https://docs.oracle.com/javase/8/docs/api/java/lang/Runnable.html). Объекты, реализующие данные интерфейсы, исполняются другим потоком. 
Однако, в отличие от Runnable, интерфейс Callable использует Generic-и для определения типа возвращаемого объекта. Runnable содержит метод 
[run()](https://docs.oracle.com/javase/8/docs/api/java/lang/Runnable.html#run--), описывающий действие потока во время выполнения, а Callable – 
метод [call()](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/Callable.html#call--).

```java
        public interface Callable {
            <V> call() throws Exception;
        }
```

---
### Интерфейс `Future<V>`

Интерфейс [Future](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/Future.html) также, как и интерфейс 
[Callable](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/Callable.html), использует Generic-и. Методы 
интерфейса можно использовать для проверки завершения работы потока, ожидания завершения и получения результата. Результат 
выполнения может быть получен [методом *.get()](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/Future.html#get--), 
если поток завершил работу. Прервать выполнения задачи можно [методом *.cancel()](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/Future.html#cancel-boolean-). 
Дополнительные методы позволяют определить завершение задачи: нормальное или прерванное. 

Если задача завершена, то прервать ее уже невозможно.

**Методы интерфейса Future:**
- `cancel (boolean mayInterruptIfRunning)` - попытка завершения задачи, если она еще не начата;
- `<V> get()` - ожидание (при необходимости) завершения задачи, после чего можно будет получить результат;
- `<V> get(long timeout, TimeUnit unit)` - ожидание (при необходимости) завершения задачи в течение определенного времени, после чего можно будет получить результат;
- `isCancelled()` - вернет true, если выполнение задачи будет прервано прежде завершения;
- `isDone()` - вернет true, если задача завершена;

---
### !!! ВСПОМНИМ !!!

Есть несколько разных способов делегировать задачи ExecutorService.

- `execute(Runnable)` - метод [ExecutorService-а execute(Runnable)](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/Executor.html#execute-java.lang.Runnable-),
доставшийся в наследство от [интерфейса Executor](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/Executor.html) принимает ТОЛЬКО объект [java.lang.Runnable](https://docs.oracle.com/javase/8/docs/api/java/lang/Runnable.html) и выполняет его асинхронно.
  
```java
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable(){
            public void run(){
                System.out.println("asynchronous task");
            }
        });
        executorService.shutdown();
```

- `submit(Callable или Runnable)` - [метод принимает реализацию Callable (Runnable)](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/ExecutorService.html#submit-java.util.concurrent.Callable-)
и возвращает будущий объект или объект интерфейса Future. Будущий объект можно использовать для проверки завершения выполнения Callable.
                                          
```java
        Future future = executorService.submit(new Callable(){
            public Object call() throws Exception{
            System.out.println("Asynchronous callable"); // На экране - Asynchroous callable
            return "Callable Result";
            }
        });
        System.out.println("future.get() = " future.get()); // future.get = Callable Result
```

- [`invokeAny()` - метод](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/ExecutorService.html#invokeAny-java.util.Collection-) принимает коллекцию вызываемых объектов.
Вызов этого метода не возвращает объект Future, но возвращает результат одного случайного из вызываемых объектов.
                        
```java
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Set<Callable<String>> callables = new HashSet<Callable<String>>();
        
            callables.add(new Callable<String>(){
                public String call() throws Exception{
                return"task A";
                }
            });
        
            callables.add(new Callable<String>(){
                public String call() throws Exception{
                return"task B";
                }
            });
        
            callables.add(new Callable<String>(){
                public String call() throws Exception{
                return"task C";
                }
            });
        
        String result = executorService.invokeAny(callables);
        System.out.println("result = " + result);
        executorService.shutdown();
```

- [`invokeAll()` - метод](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/ExecutorService.html#invokeAll-java.util.Collection-) возвращает все вызываемые объекты, переданные в качестве параметров. Он возвращает Future объекты, которые можно использовать для получения результатов выполнения каждого вызываемого объекта.
                        
```java
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Set<Callable<String>> callables = new HashSet<Callable<String>>();
        
            callables.add(new Callable<String>(){
                public String call() throws Exception{
                return "Task A";
                }
            });
        
            callables.add(new Callable<String>(){
                public String call() throws Exception{
                return "Task B";
                }
            });
        
            callables.add(new Callable<String>(){
                public String call() throws Exception{
                return "Task C";
                }
            });
        
        List<Future<String>> futures = executorService.invokeAll(callables);
        
            for(Future<String> future: futures){
                System.out.println(" future.get = " + future.get());
            }
        
        executorService.shutdown();
```

---
### !!! ПОВТОРИМ !!!

Запускаемые и вызываемые интерфейсы (Runnable и Callable) очень похожи друг на друга. Разница видна в объявлении интерфейсов. 
Оба интерфейса представляют собой задачу, которая может выполняться одновременно потоком или ExecutorService.

Вызываемое объявление:

```java
        public interface Callable{
            public object call() throws Exception;
        }
```

Выполняемое объявление:

```java
        public interface Runnable{
            public void run();
        }
```

**Основное различие между ними заключается в том, что метод call() может возвращать объект 
из вызова метода, так же метод call() может вызвать исключение, а метод run() – нет!**

---
**Доп. материалы:**
- [`Interface Callable<V>`](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/Callable.html)
- [Runnable vs. Callable in Java](https://www.baeldung.com/java-runnable-callable)
- [Callable interface in Java](https://www.geeksforgeeks.org/java/callable-interface-in-java/)
- [An Adventure in Java Concurrency — Callable vs Runnable Explained](https://medium.com/@aariff.deen/an-adventure-in-java-concurrency-callable-vs-runnable-explained-496ba004ba99)
- [Java Callable](https://jenkov.com/tutorials/java-util-concurrent/java-callable.html)
- [Callable и Future](https://javarush.com/quests/lectures/jru.module2.lecture17)
- [Mastering Java Multithreading Thread Pools, Callable, Future, and Concurrency Utilities](https://blog.masteringbackend.com/mastering-java-multithreading-thread-pools-callable-future-and-concurrency-utilities)
- [The difference between the Runnable and Callable interfaces in Java](https://stackoverflow.com/questions/141284/the-difference-between-the-runnable-and-callable-interfaces-in-java)

---
- [`Interface Future<V>`](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/Future.html)
- [Guide to java.util.concurrent.Future](https://www.baeldung.com/java-future)
- [Future Interface in Java](https://www.geeksforgeeks.org/java/future-interface-in-java/)
- [Java Futures & Promises Explained (with a Tiny DIY Implementation)](https://medium.com/@sadhana_p/java-futures-promises-explained-with-a-tiny-diy-implementation-c09359a01be8)
- [Java’s CompletableFuture vs. Future](https://concurrencydeepdives.com/java-future-vs-completablefuture/)
- [Future and FutureTask in java](https://www.geeksforgeeks.org/java/future-and-futuretask-in-java/)
- [Java Future](https://jenkov.com/tutorials/java-util-concurrent/java-future.html)
- [Difference Between Future, CompletableFuture, and Rxjava’s Observable](https://www.baeldung.com/java-future-completablefuture-rxjavas-observable)
- [CompletableFuture vs. Future in Java](https://www.javacodegeeks.com/2024/12/completablefuture-vs-future-in-java.html)
- [Future in Java](https://www.waitingforcode.com/java-concurrency/future-in-java/read)

---
- [Interface Runnable](https://docs.oracle.com/javase/8/docs/api/java/lang/Runnable.html)
- [Java Runnable Interface](https://www.geeksforgeeks.org/java/runnable-interface-in-java/)
- [Java : Runnable Interface (The Runnable Interface in Java: A Detailed Guide with Examples)](https://medium.com/@RupamThakre/java-runnable-interface-41a679616175)
- [Thread, Runnable, Callable, ExecutorService, and Future - all the ways to create threads in Java](https://dev.to/danielrendox/thread-runnable-callable-executorservice-and-future-all-the-ways-to-create-threads-in-java-2o86)
- [Java’s Multithreading: A Deep Dive into Runnable and Callable Interfaces](https://medium.com/@reetesh043/javas-multithreading-a-deep-dive-into-runnable-and-callable-interfaces-9a6f842b183f)
- [Implementing a Runnable vs Extending a Thread](https://www.baeldung.com/java-runnable-vs-extending-thread)
- [Implement Runnable vs Extend Thread in Java](https://www.geeksforgeeks.org/java/implement-runnable-vs-extend-thread-in-java/)
- [Runnable и Thread](https://younglinux.info/java/runnable)

---
- [Class Object](https://docs.oracle.com/javase/8/docs/api/java/lang/Object.html)
- [Interface ExecutorService](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/ExecutorService.html)
- [Interface Executor](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/Executor.html)
- [What is ExecutorService in Java?](https://medium.com/@gowthamkalyan322/what-is-executorservice-in-java-90d6f537ac8f)
- [Java’s ExecutorService: A Practical Guide with Real-Life Example](https://medium.com/@tafseerrimpi/javas-executorservice-a-practical-guide-with-real-life-example-0fb777a31f70)
- [ExecutorServiceBasicCode (from GitHub)](https://github.com/TafseerBinte21/ExecutorServiceBasicCode)
- [Naming Executor Service Threads and Thread Pool in Java](https://www.baeldung.com/java-naming-executor-service-thread)
- [Thread pools with Java's ExecutorService](https://tuhrig.de/thread-pools-with-javas-executorservice/)
- [ExecutorService – Waiting for Threads to Finish](https://www.baeldung.com/java-executor-wait-for-threads)
- [Настраиваем ExecutorService'ы на практике](https://habr.com/ru/articles/802113/)

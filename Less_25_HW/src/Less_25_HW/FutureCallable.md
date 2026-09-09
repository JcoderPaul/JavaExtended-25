- [`Interface Callable<V>`](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/Callable.html)
- [`Interface Future<V>`](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/Future.html)

---
### Интерфейсы Callable и Future в Java ***

Очень часто при работе с потоками нам нужно получать какой-то результат и было бы очень удобно, чтобы поток сам возвращал результаты своей работы. 
Именно поэтому еще в Java 5 появились интерфейсы [`Callable<V>`](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/Callable.html) и 
[`Future<V>`](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/Future.html). Интерфейс `Callable<V>` очень похож на интерфейс Runnable, 
но может вернуть результат в виде [объекта Object](https://docs.oracle.com/javase/8/docs/api/java/lang/Object.html) и способен бросать исключения. 
Совместное использование двух реализаций данных интерфейсов позволяет получить результат в виде некоторого объекта.

---
#### Интерфейс `Callable<V>`

Интерфейс `Callable<V>` очень похож на интерфейс `Runnable`. Объекты, реализующие данные интерфейсы, исполняются другим потоком. Однако, в отличие 
от Runnable, интерфейс `Callable` использует Generic-и для определения типа возвращаемого объекта. `Runnable` содержит метод `run()`, описывающий 
действие потока во время выполнения, а `Callable` – метод `call()`.

```java
    public interface Callable {
        <V> call() throws Exception;
    }
```

---
#### Интерфейс `Future<V>`

Интерфейс Future также, как и интерфейс Callable, использует Generic-и. Методы интерфейса можно использовать для проверки завершения работы потока, 
ожидания завершения и получения результата. Результат выполнения может быть получен методом `*.get()`, если поток завершил работу. Прервать выполнения
задачи можно методом `*.cancel()`. Дополнительные методы позволяют определить завершение задачи: нормальное или прерванное. Если задача завершена, то 
прервать ее уже невозможно.

**Методы интерфейса Future:**
- `cancel (boolean mayInterruptIfRunning)` - попытка завершения задачи, если она еще не начата;
- `<V> get()` - ожидание (при необходимости) завершения задачи, после чего можно будет получить результат;
- `<V> get(long timeout, TimeUnit unit)` - ожидание (при необходимости) завершения задачи в течение определенного времени, после чего можно будет получить результат;
- `isCancelled()` - вернет true, если выполнение задачи будет прервано прежде завершения;
- `isDone()` - вернет true, если задача завершена;

---
**!!! ВСПОМНИМ !!!**

**Есть несколько разных способов делегировать задачи `ExecutorService`:**

- `execute(Runnable)` - метод `ExecutorService execute(Runnable)` принимает ТОЛЬКО объект [Runnable](https://docs.oracle.com/javase/8/docs/api/java/lang/Runnable.html) и выполняет его асинхронно.

```java
    ExecutorService executorService = Executors.newSingleThreadExecutor();
    executorService.execute(new Runnable(){
        public void run(){
            System.out.println("asynchronous task");
        }
    });
    executorService.shutdown();
```

- `submit(Callable или Runnable)` - метод принимает реализацию Callable (Runnable) и возвращает будущий объект или объект интерфейса Future. Будущий объект можно использовать для проверки завершения выполнения Callable.

```java
    Future future = executorService.submit(new Callable(){
        public Object call() throws Exception{
        System.out.println("Asynchronous callable"); // На экране - Asynchroous callable
        return "Callable Result";
        }
    });
    System.out.println("future.get() = " future.get()); // future.get = Callable Result
```

- `invokeAny()` - метод принимает коллекцию вызываемых объектов. Вызов этого метода не возвращает Future Object, но возвращает результат одного случайного из вызываемых объектов.

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

- `invokeAll()` - метод возвращает все вызываемые объекты, переданные в качестве параметров. Он возвращает Future объекты, которые можно использовать для получения результатов выполнения каждого вызываемого объекта.

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
**!!! ПОВТОРИМ !!!**

Запускаемый и вызываемый интерфейсы Runnable и Callable очень похожи друг на друга. Разница видна в объявлении интерфейсов. Оба интерфейса представляют собой задачу, которая может выполняться одновременно
потоком или ExecutorService.

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

Основное различие между ними заключается в том, что метод `call()` может возвращать объект из вызова метода. И метод `call()` может вызвать исключение, а метод `run()` – нет!

---
**Для получения более полной информации см.:**
- [`Interface Callable<V>`](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/Callable.html);
- [`Interface Future<V>`](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/Future.html);
- [Interface Runnable](https://docs.oracle.com/javase/8/docs/api/java/lang/Runnable.html);
- [Class Object](https://docs.oracle.com/javase/8/docs/api/java/lang/Object.html);

---
**Доп. материалы:**
- [Callable interface in Java](https://www.geeksforgeeks.org/java/callable-interface-in-java/)
- [Runnable vs. Callable in Java](https://www.baeldung.com/java-runnable-callable)
- [Java callable with multiple methods](https://stackoverflow.com/questions/69291249/java-callable-with-multiple-methods)
- [How to Use ExecutorService and Callable in Java. Java Interview](https://medium.com/@alxkm/how-to-use-executorservice-and-callable-in-java-bd2123cf4d8e)
- [Java Concurrency Tutorial – Callable, Future](https://www.javacodegeeks.com/2011/09/java-concurrency-tutorial-callable.html)
- [Callable vs Future in Java](https://www.geeksforgeeks.org/java/callable-future-java/)
- [When to Use Callable and Supplier in Java](https://www.baeldung.com/java-callable-vs-supplier)
- [Java Callable and Future Tutorial](https://www.javaguides.net/2018/09/java-callable-and-future-tutorial.html)

---
- [Guide to java.util.concurrent.Future](https://www.baeldung.com/java-future)
- [Mastering CompletableFuture in Java-With Practical Examples. Java Interview](https://medium.com/@alxkm/mastering-completablefuture-in-java-with-practical-examples-c91fa0346792)
- [Future Interface in Java](https://www.geeksforgeeks.org/java/future-interface-in-java/)
- [Best way to use Future in java](https://stackoverflow.com/questions/63158743/best-way-to-use-future-in-java)
- [Future and FutureTask in java](https://www.geeksforgeeks.org/java/future-and-futuretask-in-java/)
- [Transform a Future into CompletableFuture](https://www.baeldung.com/java-transform-future-completablefuture)
- [What Problems Does Future Solve in Concurrent Java Programming?](https://dev.to/realnamehidden1_61/what-problems-does-future-solve-in-concurrent-java-programming-eag)
- [Java Futures & Promises Explained (with a Tiny DIY Implementation)](https://medium.com/@sadhana_p/java-futures-promises-explained-with-a-tiny-diy-implementation-c09359a01be8)

---
- [Java Runnable Interface](https://www.geeksforgeeks.org/java/runnable-interface-in-java/)
- [In a simple to understand explanation, what is Runnable in Java?](https://stackoverflow.com/questions/13327571/in-a-simple-to-understand-explanation-what-is-runnable-in-java)
- [Callable & Runnable & Future : Exploring Java’s Asynchronous Task Models](https://medium.com/@asatryanmanuk2000/callable-runnable-future-exploring-javas-asynchronous-task-models-da74b700efb2)
- [Java Threads: Implement Runnable Interface](https://codingnomads.com/how-to-create-java-thread-with-java-runnable)
- [Java classes for threads](https://psp2dam.github.io/psp_pages/en/unit3/runnable.html)
- [Implementing a Runnable vs Extending a Thread](https://www.baeldung.com/java-runnable-vs-extending-thread)
- [What is Runnable interface in Java?](https://www.tutorialspoint.com/article/what-is-runnable-interface-in-java)
- [Runnable Interface in Java - Step-by-Step Implementation](https://codegym.cc/groups/posts/958-runnable-interface-in-java---step-by-step-implementation)
- [Understanding Runnable and Callable in Java: Examples and Code Demos](https://medium.com/tuanhdotnet/understanding-runnable-and-callable-in-java-examples-and-code-demos-b63dc1ec9679)
- [Creating and Starting Java Threads](https://jenkov.com/tutorials/java-concurrency/creating-and-starting-threads.html)

---
- [How to use Java Lambda expression to create thread via Runnable](https://www.codejava.net/java-core/the-java-language/java-8-lambda-runnable-example)
- [Create a Thread with Runnable in Java](https://davidvlijmincx.com/posts/thread-with-runnable/)
- [Difference Between Callable and Runnable in Java](https://www.geeksforgeeks.org/java/difference-between-callable-and-runnable-in-java/)
- [Java Runnable Example](https://examples.javacodegeeks.com/java-runnable-example/)

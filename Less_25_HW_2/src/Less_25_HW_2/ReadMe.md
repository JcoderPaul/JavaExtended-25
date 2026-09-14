Повторение пройденного материала по многопоточности - Multithreading. Примеры и комментарии.

Отдельные уроки содержат:
- [Less_25_HW_JoinInOtherThread](../Less_25_HW_2/Less_25_HW_JoinInOtherThread.java) - применение метода *.join() не только в основном потоке.
- [Less_25_HW_WhichIsFasterOneThreadOrMany](../Less_25_HW_2/Less_25_HW_WhichIsFasterOneThreadOrMany.java) - наглядный пример того, что многопоточность при правильном применении позволяет значительно увеличить скорость работы приложения.
- [Less_25_HW_DaemonThread](../Less_25_HW_2/Less_25_HW_DaemonThread.java) - пример создания и работы потока-демона.
- [Less_25_HW_DaemonInThreadPool](../Less_25_HW_2/Less_25_HW_DaemonInThreadPool.java) - пример того, как создать пул потоков демонов.
- [Less_25_HW_DaemonInInfoLoadSimulation](../Less_25_HW_2/Less_25_HW_DaemonInInfoLoadSimulation.java) - пример создания пула потоков демонов.

Папки содержат:
- [AtomicDate](../Less_25_HW_2/AtomicDate) - примеры работы с атомиками.
- [CyclicBarrier](../Less_25_HW_2/CyclicBarrier) - пример работы с циклическим барьером (см. [ReadMe](../Less_25_HW_2/CyclicBarrier/ReadMe.md))
    - [CyclicBarrier_Step1](../Less_25_HW_2/CyclicBarrier/CyclicBarrier_Step1.java) - простой пример по работе циклического барьера;
    - [СarRaceImitation_Step2](../Less_25_HW_2/CyclicBarrier/%D0%A1arRaceImitation_Step2.java) - пример одновременного применения:
        - [Semaphore](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/Semaphore.html),
        - [CyclicBarrier](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/CyclicBarrier.html),
        - [CountDownLatch](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/CountDownLatch.html)
      и потокобезопасной коллекции.
- [RaceConditionAndDecision](../Less_25_HW_2/RaceConditionAndDecision) - пример состояния гонки и варианты решения данной проблемы.
- [SimpleMultithreading](../Less_25_HW_2/SimpleMultithreading) - простые примеры создания и применения потоков;
- [TimingTask](../Less_25_HW_2/TimingTask) - решения задачи см. [ReadMe](../Less_25_HW_2/TimingTask/ReadMe.md)
- [WaitAndNotify](../Less_25_HW_2/WaitAndNotify) - пример работы методов `*.wait()`, `*.notify()` и `*.notifyAll()`

---
**Доп. материал:**
- [Multithreading in Java](https://jse.readthedocs.io/en/latest/threads/multithreadingInJava.html)
- [Multithreading in Java](https://www.geeksforgeeks.org/java/multithreading-in-java/)
- [Java Multithreading Tutorial](https://www.geeksforgeeks.org/java/java-multithreading-tutorial/)
- [Java Multithreading Program with Example](https://www.geeksforgeeks.org/java/java-multithreading-program-with-example/)
- [Java Concurrency: Synchronization and Multithreading](https://www.j-labs.pl/en/tech-blog/java-concurrency-synchronization-and-multithreading/)
- [Lesson: Concurrency (from ORACLE DOCs)](https://docs.oracle.com/javase/tutorial/essential/concurrency/)
- [Java Threads](https://www.w3schools.com/java/java_threads.asp)
- [Java Concurrency/Multithreading Examples (from GitHub)](https://github.com/callicoder/java-concurrency-examples)
- [Java-Multithreading (from GitHub)](https://github.com/RameshMF/java-multithreading)
- [Threads and Multithreading in Java (from Department of Computer Science and Engineering of Indian Institute of Technology Kharagpur)](https://cse.iitkgp.ac.in/~dsamanta/java/ch6.htm)
- [Multithreading in java with examples](https://beginnersbook.com/2013/03/multithreading-in-java/)

---
- [Multithreading in Java](https://dotnettutorials.net/lesson/multithreading-in-java/)
- [A Complete MultiThreading Tutorial In Java](https://www.c-sharpcorner.com/article/a-complete-multithreading-tutorial-in-java)
- [Multithreading in Java](https://www.igmguru.com/blog/multithreading-in-java)
- [Multithreading in Java: A Complete Introduction](https://stackify.com/multithreading-in-java-a-complete-introduction/)
- [Multithreading in Java: How to Get Started with Threads](https://www.freecodecamp.org/news/how-to-get-started-with-multithreading-in-java/)
- [Multithreading in Java: A Comprehensive Guide](https://dev.to/dhanush9952/multithreading-in-java-a-comprehensive-guide-1c53)

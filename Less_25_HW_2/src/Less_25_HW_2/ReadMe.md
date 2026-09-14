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

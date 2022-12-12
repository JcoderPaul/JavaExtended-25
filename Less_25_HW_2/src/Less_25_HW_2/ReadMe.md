Повторение пройденного материала по многопоточности - Multithreading. Примеры и комментарии.
Отдельные уроки содержат:
- [Less_25_HW_JoinInOtherThread](https://github.com/JcoderPaul/JavaExtended-25/blob/master/Less_25_HW_2/src/Less_25_HW_2/Less_25_HW_JoinInOtherThread.java) - применение метода *.join() не только в основном потоке.
- [Less_25_HW_WhichIsFasterOneThreadOrMany](https://github.com/JcoderPaul/JavaExtended-25/blob/master/Less_25_HW_2/src/Less_25_HW_2/Less_25_HW_WhichIsFasterOneThreadOrMany.java) - наглядный пример того, что многопоточность
                                            при правильном применении позволяет значительно
                                            увеличить скорость работы приложения.
- [Less_25_HW_DaemonThread](https://github.com/JcoderPaul/JavaExtended-25/blob/master/Less_25_HW_2/src/Less_25_HW_2/Less_25_HW_DaemonThread.java) - пример создания и работы потока-демона.
- [Less_25_HW_DaemonInThreadPool](https://github.com/JcoderPaul/JavaExtended-25/blob/master/Less_25_HW_2/src/Less_25_HW_2/Less_25_HW_DaemonInThreadPool.java) - пример того, как создать пул потоков демонов.
- [Less_25_HW_DaemonInInfoLoadSimulation](https://github.com/JcoderPaul/JavaExtended-25/blob/master/Less_25_HW_2/src/Less_25_HW_2/Less_25_HW_DaemonInInfoLoadSimulation.java) - пример создания пула потоков демонов.

Папки содержат:
- [AtomicDate](https://github.com/JcoderPaul/JavaExtended-25/tree/master/Less_25_HW_2/src/Less_25_HW_2/AtomicDate) - примеры работы с атомиками.
- [CyclicBarrier](https://github.com/JcoderPaul/JavaExtended-25/tree/master/Less_25_HW_2/src/Less_25_HW_2/CyclicBarrier) - пример работы с циклическим барьером (см. [ReadMe.txt](https://github.com/JcoderPaul/JavaExtended-25/blob/master/Less_25_HW_2/src/Less_25_HW_2/CyclicBarrier/ReadMe.txt))
    - [CyclicBarrier_Step1](https://github.com/JcoderPaul/JavaExtended-25/blob/master/Less_25_HW_2/src/Less_25_HW_2/CyclicBarrier/CyclicBarrier_Step1.java) - простой пример по работе циклического барьера;
    - [СarRaceImitation_Step2](https://github.com/JcoderPaul/JavaExtended-25/blob/master/Less_25_HW_2/src/Less_25_HW_2/CyclicBarrier/%D0%A1arRaceImitation_Step2.java) - пример одновременного применения: Semaphore,
                               CyclicBarrier, CountDownLatch и потокобезопасной
                               коллекции.
- [RaceConditionAndDecision](https://github.com/JcoderPaul/JavaExtended-25/tree/master/Less_25_HW_2/src/Less_25_HW_2/RaceConditionAndDecision) - пример состояния гонки и варианты решения данной проблемы.
- [SimpleMultithreading](https://github.com/JcoderPaul/JavaExtended-25/tree/master/Less_25_HW_2/src/Less_25_HW_2/SimpleMultithreading) - простые примеры создания и применения потоков;
- [TimingTask](https://github.com/JcoderPaul/JavaExtended-25/tree/master/Less_25_HW_2/src/Less_25_HW_2/TimingTask) - решения задачи см. [ReadMe.txt](https://github.com/JcoderPaul/JavaExtended-25/blob/master/Less_25_HW_2/src/Less_25_HW_2/TimingTask/ReadMe.txt)
- [WaitAndNotify](https://github.com/JcoderPaul/JavaExtended-25/tree/master/Less_25_HW_2/src/Less_25_HW_2/WaitAndNotify) - пример работы методов *.wait(), *.notify() и *.notifyAll()
### Многопоточность - Multithreading

Большинство языков программирования поддерживают такую важную функциональность как многопоточность, 
и Java в этом плане не исключение. При помощи многопоточности мы можем выделить в приложении несколько 
потоков, которые будут выполнять различные задачи одновременно. Если у нас, допустим, графическое 
приложение, которое посылает запрос к какому-нибудь серверу или считывает и обрабатывает огромный 
файл, то без многопоточности у нас бы блокировался графический интерфейс на время выполнения задачи. 
А благодаря потокам мы можем выделить отправку запроса или любую другую задачу, которая может долго 
обрабатываться, в отдельный поток. 

Поэтому большинство реальных приложений, которые многим из нас приходится использовать, практически 
не мыслимы без многопоточности.

Многопоточность решает две основные задачи:
1. Многопоточность позволяет одновременно выполнять несколько действий;
Пример из обычной жизни: Не только жена (один поток) моет посуду, ходит в магазин, стирает вещи, но в 
работе участвуют муж и дети (еще n-потоков).

Пример из жизни IT специалиста: У нас есть программа с пользовательским интерфейсом. При нажатии кнопки 
«Продолжить» внутри программы должны произойти какие-то вычисления, а пользователь должен увидеть следующий 
экран интерфейса. Если эти действия осуществляются последовательно, после нажатия кнопки «Продолжить» 
программа просто зависнет. Пользователь будет видеть все тот же экран с кнопкой «Продолжить», пока все 
внутренние вычисления не будут выполнены, и программа не дойдет до части, где начнется отрисовка интерфейса.
И вот тут все начинают нервничать... долго ... что-то!

И тогда на ум приходит, аналогия из жизни: длинная вереница машин на однорядном мосту (жаль, что он не многорядный).

И программист решает переделать программу, и «распараллелить» процессы. 
Пусть нужные вычисления выполняются в одном потоке, а отрисовка интерфейса — в другом. У большинства компьютеров 
хватит на это ресурсов. В таком случае программа не будет «подвисать», и пользователь будет спокойно переходить 
между экранами интерфейса не заботясь о том, что происходит внутри.

2. Многопоточность позволяет ускорить вычисления;
Если наш процессор имеет несколько ядер, а большинство процессоров сейчас многоядерные, список наших задач могут 
параллельно решать несколько ядер. Очевидно, что если нам нужно решить 1000 задач и каждая из них решается за 
секунду, одно ядро справится со списком за 1000 секунд, два ядра — за 500 секунд, три — за 333.3... и так далее.

Однако, современные системы очень умны, и даже на одном вычислительном ядре они способны реализовать 
параллельность, или псевдопараллельность, когда задачи выполняются попеременно.

Главный класс в библиотеке Java, относящийся к многопоточности находится в java.lang.Thread.
Собственно говоря, потоки в Java представляются экземплярами класса Thread.

---
### Класс Thread
В Java функциональность отдельного потока заключается в классе Thread. И чтобы создать новый поток, 
нам надо создать объект этого класса. Но все потоки не создаются сами по себе. Когда запускается 
программа, начинает работать главный поток этой программы. От этого главного потока порождаются 
все остальные дочерние потоки. 

---
По умолчанию именем главного потока будет main.
Для управления потоком класс Thread предоставляет ряд методов. Наиболее используемые из них:
- getName(): возвращает имя потока;
- setName(String name): устанавливает имя потока;
- getPriority(): возвращает приоритет потока;
- setPriority(int proirity): устанавливает приоритет потока. Приоритет является одним из ключевых 
  факторов для выбора системой потока из кучи потоков для выполнения. В этот метод в качестве параметра 
  передается числовое значение приоритета - от 1 до 10. По умолчанию главному потоку выставляется 
  средний приоритет - 5;
- isAlive(): возвращает true, если поток активен;
- isInterrupted(): возвращает true, если поток был прерван;
- join(): ожидает завершение потока;
- run(): определяет точку входа в поток;
- sleep(): приостанавливает поток на заданное количество миллисекунд;
- start(): запускает поток, вызывая его метод run();

---
В примерах напоминалках рассмотрены:
- [Less_25_ch_1_ClassThread](https://github.com/JcoderPaul/JavaExtended-25/tree/master/Less_25_ch_1_ClassThread/src/Less_25_ch_1_ClassThread)
- [Less_25_ch_2_InterfaceRunnable](https://github.com/JcoderPaul/JavaExtended-25/tree/master/Less_25_ch_2_InterfaceRunnable/src/Less_25_ch_2_InterfaceRunnable)
- [Less_25_ch_3_ThreadNamePriority](https://github.com/JcoderPaul/JavaExtended-25/tree/master/Less_25_ch_3_ThreadNamePriority/src/Less_25_ch_3_ThreadNamePriority)
- [Less_25_ch_4_SleepJoinThread](https://github.com/JcoderPaul/JavaExtended-25/tree/master/Less_25_ch_4_SleepJoinThread/src/Less_25_ch_4_SleepJoin)
- [Less_25_ch_5_Volatile](https://github.com/JcoderPaul/JavaExtended-25/tree/master/Less_25_ch_5_Volatile/src/Less_25_ch_5_Volatile)
- [Less_25_ch_6_SynchronizedMethods](https://github.com/JcoderPaul/JavaExtended-25/tree/master/Less_25_ch_6_SynchronizedMethods/src/Less_25_ch_6_SynchronizedMethods)
- [Less_25_ch_7_SynchronizedBlocks](https://github.com/JcoderPaul/JavaExtended-25/tree/master/Less_25_ch_7_SynchronizedBlocks/src/Less_25_ch_7_SynchronizedBlocks)
- [Less_25_ch_8_WaitAndNotify](https://github.com/JcoderPaul/JavaExtended-25/tree/master/Less_25_ch_8_WaitAndNotify/src/Less_25_ch_8_WaitAndNotify)
- [Less_25_ch_9_Deadlock](https://github.com/JcoderPaul/JavaExtended-25/tree/master/Less_25_ch_9_Deadlock/src/Less_25_ch_9_Deadlock)
- [Less_25_ch_10_ReentrantLock](https://github.com/JcoderPaul/JavaExtended-25/tree/master/Less_25_ch_10_ReentrantLock/src/Less_25_ch_10_ReentrantLock)
- [Less_25_ch_11_DaemonThread](https://github.com/JcoderPaul/JavaExtended-25/tree/master/Less_25_ch_11_DaemonThread/src/Less_25_ch_11_DaemonThread)
- [Less_25_ch_12_Interruption](https://github.com/JcoderPaul/JavaExtended-25/tree/master/Less_25_ch_12_Interruption/src/Less_25_ch_12_Interruption)
- [Less_25_ch_13_ThreadPool](https://github.com/JcoderPaul/JavaExtended-25/tree/master/Less_25_ch_13_ThreadPool/src/Less_25_ch_13_ThreadPool)
- [Less_25_ch_14_InterfaceCallable](https://github.com/JcoderPaul/JavaExtended-25/tree/master/Less_25_ch_14_InterfaceCallable/src/Less_25_ch_14_InterfaceCallable)
- [Less_25_ch_15_Semaphore](https://github.com/JcoderPaul/JavaExtended-25/tree/master/Less_25_ch_15_Semaphore/src/Less_25_ch_15_Semaphore)
- [Less_25_ch_16_CountDownLatch](https://github.com/JcoderPaul/JavaExtended-25/tree/master/Less_25_ch_16_CountDownLatch/src/Less_25_ch_16_CountDownLatch)
- [Less_25_ch_17_Exchanger](https://github.com/JcoderPaul/JavaExtended-25/tree/master/Less_25_ch_17_Exchanger/src/Less_25_ch_17_Exchanger)
- [Less_25_ch_18_AtomicInteger](https://github.com/JcoderPaul/JavaExtended-25/tree/master/Less_25_ch_18_AtomicInteger/src/Less_25_ch_18_AtomicInteger)
- [Less_25_ch_19_SynchronizedCollection](https://github.com/JcoderPaul/JavaExtended-25/tree/master/Less_25_ch_19_SynchronizedCollection/src/Less_25_ch_19_SynchronizedCollection)
- [Less_25_ch_20_ConcurrentCollection](https://github.com/JcoderPaul/JavaExtended-25/tree/master/Less_25_ch_20_ConcurrentCollection/src/Less_25_ch_20_ConcurrentCollection)
- [Less_25_ch_21_CopyOnWriteArrayList](https://github.com/JcoderPaul/JavaExtended-25/tree/master/Less_25_ch_21_CopyOnWriteArrayList/src/Less_25_ch_21_CopyOnWriteArrayList)
- [Less_25_ch_22_ArrayBlockingQueue](https://github.com/JcoderPaul/JavaExtended-25/tree/master/Less_25_ch_22_ArrayBlockingQueue/src/Less_25_ch_22_ArrayBlockingQueue)

### Deadlock или взаимная блокировка

Это ошибка, которая происходит когда потоки - Threads имеют циклическую зависимость от пары синхронизированных объектов.

Представим, что один поток захватил монитор объекта 'ПЕРВЫЙ', а другой поток завладел монитором объекта 'ВТОРОЙ'.

**!!! НО !!!**

Теперь, если один поток в объекте 'ПЕРВЫЙ' пытается вызвать любой синхронизированный метод объекта 'ВТОРОЙ', а 
объект 'ВТОРОЙ', в то же самое время пытается вызвать любой синхронизированный метод объекта 'ПЕРВЫЙ', то **потоки 
застрянут в процессе ожидания друг друга - взаимной блокировки**.

Поскольку для успешного выполнения операции потоку захватившему монитор объекта 'ПЕРВЫЙ', нужен монитор объекта 
'ВТОРОЙ', а он занят потоком, который захватил этот монитор и не отпускает его, по тем же самым причинам, что и 
первый поток. Т.е. для завершения своих операций второму потоку захватившему монитор объекта 'ВТОРОЙ' нужен 
монитор объекта 'ПЕРВЫЙ'.

**!!! При входе в синхронизированный метод объекта, его lock запирается, а когда из метода выходят, он 
освобождается или отпирается. Но выход из метода возможен, только после его удачного завершения. Если нет
возможности завершить метод, lock не отопрется, монитор останется занятым !!!**

Ни у первого потока, ни у второго нет причин отпускать захваченные мониторы, ведь методы не завершены, а для 
завершения методов обоим потокам нужны мониторы захваченные друг другом.

Переговоры зашли в тупик - смертельный замок - Deadlock.

Классический [пример из «tutorial» – учебного пособия java.docs](https://docs.oracle.com/javase/tutorial/essential/concurrency/deadlock.html) приведен в [Less_25_Deadlock_Step1](./Less_25_Deadlock_Step1.java).

Например, добавлен какой-то метод, позволяющий другой нити успеть выполниться.

Обычно порядок одновременно происходящих событий (запланированный порядок, рассчитанная задержка, скорости выполнения), 
позволяет избежать взаимной блокировки потоков.

---
**БОЛЕЕ ЖИВОЙ ПРИМЕР:**
Например, две девочки Маша и Даша в детском саду делают аппликацию. Для работы каждой нужны ножницы и цветная бумага. 
Предположим Маша взяла ножницы (поток Маша вошла в монитор объекта ножницы), а Даша хапнула бумагу (поток Даша вошла в
монитор объекта бумага). Каждая из них ждет другой предмет и не хочет делиться тем, что взяла. Они не могут продолжить 
свою работу и будут ждать вечно (пока воспитательница не поможет им, или пока Маша не ушатает Дашу ножницами и не заберет 
бумагу себе, обратный вариант тоже возможен...).

---
Другие варианты 'взаимных блокировок':
- [Livelock](./ReadMeLiveLock.md)
- [Starvation](./ReadMeLockStarvation.md)

---
**Доп. материалы:**
- [Deadlock](https://docs.oracle.com/javase/tutorial/essential/concurrency/deadlock.html)
- [The Java™ Tutorials](https://docs.oracle.com/javase/tutorial/essential/concurrency/index.html)
- [Deadlock in Java Multithreading](https://www.geeksforgeeks.org/java/deadlock-in-java-multithreading/)
- [Java Thread Deadlock and Livelock](https://www.baeldung.com/java-deadlock-livelock)
- [Simple Deadlock Examples](https://stackoverflow.com/questions/1385843/simple-deadlock-examples)
- [Deadlock in Java Multithreading](https://www.tpointtech.com/deadlock-in-java)
- [Thread Synchronization](https://www.artima.com/insidejvm/ed2/threadsynchP.html#:~:text=Java's%20monitor%20supports%20two%20kinds,without%20interfering%20with%20each%20other)
- [Java’s Synchronization Saga: Understanding Deadlocks and Monitors](https://medium.com/@amitvsolutions/javas-synchronization-saga-understanding-deadlocks-and-monitors-1c2e8143a88a)
- [Deadlock in Java](https://www.scaler.com/topics/deadlock-in-java/)

---
- [Multithreading Concepts Part 1: Atomicity and Immutability](https://dev.to/anwaar/multithreading-key-concepts-for-engineers-part-1-4g73)
- [Multithreading Concepts Part 2 : Starvation](https://dev.to/anwaar/multithreading-concepts-part-2-starvation-1abb)
- [Debug and Monitor Java App with VisualVM and jstack](https://dev.to/anwaar/debug-and-monitor-java-app-with-visualvm-and-jstack-aoa)
- [Multithreading Concepts Part 3 : Deadlock](https://dev.to/anwaar/multithreading-concepts-part-3-deadlock-4ip6)

---
- [In Java, what is the difference between a monitor and a lock](https://stackoverflow.com/questions/49610644/in-java-what-is-the-difference-between-a-monitor-and-a-lock)
- [Difference Between Lock and Monitor in Java Concurrency](https://www.geeksforgeeks.org/java/difference-between-lock-and-monitor-in-java-concurrency/)

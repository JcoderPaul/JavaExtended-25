### Java Lock API

Обычно в многопоточной среде для достижения потокобезопасности, используется ключевое слово [synchronized](https://docs.oracle.com/javase/tutorial/essential/concurrency/syncmeth.html). 
Однако у данного способа существует конкурент в виде [Lock API](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/locks/Lock.html).

В большинстве случаев, ключевое слово synchronized является хорошим выбором, но все же он имеет некоторые недостатки. Именно поэтому еще в Java 1.5 был
введен [Concurrency API](https://docs.oracle.com/en/java/javase/26/core/concurrency.html) и пакет [java.util.concurrent.locks](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/locks/package-summary.html) c [интерфейсом Lock](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/locks/Lock.html) и некоторыми дополнительными классами, которые усовершенствовали механизм блокировки.

Важные моменты в Concurrency Lock API:

    1. Lock: Это базовый интерфейс в Lock API. Он обеспечивает все функции ключевого
             слова synchronized, добавляя новые методы для удобной работы.
               Например:
                - метод lock() - получить lock для работы;
                - метод unlock() - освободить lock;
                - метод tryLock() - ожидать lock на протяжении определенного времени;
                - метод newCondition() - создать Condition.
                - метод lockInterruptibly() throws InterruptedException - ожидает, пока не
                  будет получена блокировка, если поток не прерван
                - метод boolean tryLock() - пытается получить блокировку, если блокировка получена,
                  то возвращает true. Если блокировка не получена, то возвращает false. В отличие
                  от метода lock() не ожидает получения блокировки, если она недоступна.

    2. Condition: Это похоже на wait-notify модель с рядом дополнительных функций. Объект
                  Condition всегда создается с помощью объекта Lock. Такой важный метод, как
                  await() очень похож на wait(), а методы signal(), signalAll() похожи на
                  notify() и notifyAll().

    3. ReadWriteLock содержит пару связанных локов: первый только для чтения, второй для записи.
                     Лок для чтения может предоставлять доступ одновременно для нескольких потоков.

    4. Класс ReentrantLock — это наиболее используемая реализация интерфейса Lock. Эта реализация
                             интерфейса Lock аналогична использованию ключевого слова synchronized.
                             Кроме реализации интерфейса Lock, ReentrantLock содержит ряд вспомогательных
                             методов для работы с потоками.

Организация блокировки в общем случае довольно проста: для получения блокировки вызывается метод [lock()](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/locks/Lock.html#lock--),
а после окончания работы с общими ресурсами вызывается метод [unlock()](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/locks/Lock.html#unlock--), который снимает блокировку.

Объект [Condition](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/locks/Condition.html) позволяет управлять блокировкой.

Как правило, для работы с блокировками используется класс [ReentrantLock](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/locks/ReentrantLock.html) 
из пакета [java.util.concurrent.locks](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/locks/package-summary.html). Данный класс реализует интерфейс 
[Lock](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/locks/Lock.html), как указано выше. И как synchronized, обеспечивает многопоточность,
но имеет дополнительные возможности, связанные с опросом о блокировании (lock polling), ожиданием блокирования в течение определенного времени и прерыванием ожидания 
блокировки. Кроме того, [ReentrantLock](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/locks/ReentrantLock.html) предлагает гораздо более высокую 
эффективность функционирования в условиях жесткой состязательности.

Другими словами, когда несколько потоков пытаются получить доступ к совместно используемому ресурсу, виртуальной машине JVM потребуется меньше времени на установление 
очередности потоков и больше времени на ее выполнение.

В переводе reentrant может означать повторно используемый (повторный вход). Что это означает - блокировка с повторным входом? Это учет количества получения определенных 
блокировок. Т.е. один и тот же поток повторно получает одну и ту же блокировку. Но для того, чтобы реально разблокировать необходимо уже будет два раза снять блокировку. 
Это аналогично использованию synchronized; если поток повторно входит в синхронный блок, защищенный монитором, то блокировка не будет снята при выходе потока из второго 
(или последующего) блока synchronized, блокировка будет снята только когда поток выйдет из первого блока synchronized, в который он вошел под защитой монитора.

Одним из интересных методов [интерфейса Lock](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/locks/Lock.html) и его [реализации ReentrantLock](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/locks/ReentrantLock.html) является запрос блокировки с возможностью прерывания процесса ожидания. 
Т.е. если поток запрашивает блокировку методом [lockInterruptibly()](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/locks/ReentrantLock.html#lockInterruptibly--) 
и не получает ее сразу же, то переходит в процесс ожидания.

Методом interrupt работу потока можно прервать. Тогда ожидающий блокировки поток просыпается, и генерируется исключительная ситуация [InterruptedException](https://docs.oracle.com/javase/8/docs/api/java/lang/InterruptedException.html). После этого попыток доступа к защищенному ресурсу не делается и освобождать блокировку не 
требуется.

---
**Доп. материалы:**
- [Guide to java.util.concurrent.Locks](https://www.baeldung.com/java-concurrent-locks)
- [Overview of Lock API in java](https://dev.to/dayanandaeswar/overview-of-lock-api-in-java-5d4f)
- [Java Locks: ReentrantLock, ReadWriteLock, StampedLock, and Semaphore Explained](https://medium.com/@alxkm/java-locks-reentrantlock-readwritelock-stampedlock-and-semaphore-explained-a72f0dcda326)
- [Locks API](https://tech-learn.dev/java/multithreading/java-locks-api)
- [What is Lock in Java](https://www.geeksforgeeks.org/java/what-is-locking-in-java/)
- [Synchronization vs Lock](https://stackoverflow.com/questions/4201713/synchronization-vs-lock)
- [Locking Mechanism in Java](https://medium.com/hprog99/locking-mechanism-in-java-c23142d4707b)
- [Java Locks: A Deep Dive into Locking Mechanisms](https://wslisam.medium.com/java-locks-a-deep-dive-into-locking-mechanisms-aa3d20a82fa8)
- [Java Concurrency - Lock Interface](https://www.tutorialspoint.com/java_concurrency/concurrency_lock.htm)
- [Java Locks (with Examples)](https://howtodoinjava.com/java/multi-threading/how-to-use-locks-in-java-java-util-concurrent-locks-lock-tutorial-and-example/)
- [Java Lock](https://jenkov.com/tutorials/java-util-concurrent/lock.html)

---
- [Java Documentation](https://docs.oracle.com/en/java/index.html)
- [JDK 26 Documentation](https://docs.oracle.com/en/java/javase/26/index.html)
- [JDK 21 Documentation](https://docs.oracle.com/en/java/javase/21/index.html)

--- 
- [Overview of the java.util.concurrent](https://www.baeldung.com/java-util-concurrent)
- [java.util.concurrent Package](https://www.geeksforgeeks.org/java/java-util-concurrent-package/)
- [Advanced Java Concurrency: Patterns and Best Practices](https://medium.com/@ShantKhayalian/advanced-java-concurrency-patterns-and-best-practices-6cc071b5d96c)
- [Structured Concurrency in Java](https://www.baeldung.com/java-structured-concurrency)
- [Java Concurrency: A Comprehensive Guide](https://www.tatvasoft.com/outsourcing/2025/08/java-concurrency.html)
- [Java Concurrency (from GitHub)](https://github.com/marcusvieira88/java-concurrency)
- [Modern Java Concurrency API: Then and now](https://wearecommunity.io/communities/javaro/articles/1907)
- [Java 8 Concurrency Tutorial: Threads and Executors](https://winterbe.com/posts/2015/04/07/java8-concurrency-tutorial-thread-executor-examples/)
- [Mastering Java Concurrency Models](https://dev.to/blog-genius/mastering-java-concurrency-models-25o)
- [Concurrency and parallelism in Java](https://medium.com/@peterlee2068/concurrency-and-parallelism-in-java-f625bc9b0ca4)
- [Java Concurrency API Guide for Multithreading](https://learncodewithdurgesh.com/tutorials/core-java-tutorial-for-beginners/java-concurrency-api-guide-for-multithreading)

--- 
- [ReentrantLock in Java](https://www.geeksforgeeks.org/java/reentrant-lock-in-java/)
- [Why use a ReentrantLock if one can use synchronized(this)?](https://stackoverflow.com/questions/11821801/why-use-a-reentrantlock-if-one-can-use-synchronizedthis)
- [Java Lock Example - ReentrantLock](https://www.digitalocean.com/community/tutorials/java-lock-example-reentrantlock)
- [ReEntrantLocks in java - Detailed explanation with full program](https://www.javamadesoeasy.com/2015/03/reentrantlocks-in-java.html)
- [Mastering Multithreading in Java: Part 5 – ReentrantLock and Volatile](https://www.linkedin.com/pulse/mastering-multithreading-java-part-5-reentrantlock-volatile-crowley-6ffyf)
- [How to Use Locks in Multi-Threaded Java Program](https://www.geeksforgeeks.org/java/how-to-use-locks-in-multi-threaded-java-program/)
- [Java Lock Example (from GitHub)](https://gist.github.com/vikasverma787/9acfb081c4f4364b8100557635cc6178)
- [What is the Re-entrant lock and concept in general?](https://stackoverflow.com/questions/1312259/what-is-the-re-entrant-lock-and-concept-in-general)
- [Reentrant Locks in Java Explained:Simplified Concurrency Control](https://javagyansite.com/2023/06/25/reentrant-locks-in-java/)

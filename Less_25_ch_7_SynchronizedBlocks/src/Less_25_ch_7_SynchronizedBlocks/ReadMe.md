### Синхронизация потоков.

При работе потоки нередко обращаются к каким-то общим ресурсам, которые определены вне потока,
например, обращение к какому-то файлу. Если одновременно несколько потоков обратятся к общему
ресурсу, то результаты выполнения программы могут быть неожиданными и даже непредсказуемыми.

---
### Модификатор synchronized.

Чтобы избежать подобной ситуации, надо синхронизировать потоки. Одним из способов синхронизации
является использование ключевого слова synchronized. Этот оператор предваряет блок кода или метод,
который подлежит синхронизации.

При создании синхронизированного блока кода после оператора synchronized идет объект-заглушка:
synchronized(res). Причем в качестве объекта может использоваться только объект какого-нибудь
класса, но не примитивного типа.

---
**Доп. материал:**
- [Guide to the Synchronized Keyword in Java](https://www.baeldung.com/java-synchronized)
- [Synchronized Methods from The Java™ Tutorials by ORACLE](https://docs.oracle.com/javase/tutorial/essential/concurrency/syncmeth.html)
- [Synchronization in Java](https://www.geeksforgeeks.org/java/synchronization-in-java/)
- [Synchronized Keyword in Java](https://www.datacamp.com/doc/java/synchronized)
- [Java different synchronization level](https://medium.com/@qingedaig/java-different-synchronized-level-9fb8b957d960)
- [Synchronized Method, Synchronized Block & ReentrantLock in Java](https://blog.devgenius.io/synchronized-method-synchronized-block-reentrantlock-in-java-8bc5996b056a)
- [A practical guide on Synchronization in Java](https://www.boardinfinity.com/blog/understanding-synch/)
- [Синхронизация потоков. Оператор synchronized](https://metanit.com/java/tutorial/8.3.php)
- [Java's synchronized explained](https://nataliiadziubenko.com/2025/02/01/javas-synchronized-explained.html)

---
### Понятие Монитор.

Каждый объект в Java имеет ассоциированный с ним монитор. Монитор представляет своего рода инструмент
для управления доступа к объекту. Когда выполнение кода доходит до оператора synchronized, монитор
объекта res блокируется, и на время его блокировки монопольный доступ к блоку кода имеет только один
поток, который и произвел блокировку. После окончания работы блока кода, монитор объекта res освобождается
и становится доступным для других потоков.

После освобождения монитора его захватывает другой поток, а все остальные потоки продолжают ожидать его
освобождения. При применении оператора synchronized к методу (пока этот метод не завершит выполнение)
монопольный доступ имеет только один поток, который успел перехватить монитор и начал его выполнение.

См. [статью "Монитор"](./ReadMeAboutMonitor.md)

---
**Доп. материал:**
- [Monitor in Java](https://www.geeksforgeeks.org/java/monitor-in-java/)
- [What's the meaning of an object's monitor in Java?](https://stackoverflow.com/questions/9848616/whats-the-meaning-of-an-objects-monitor-in-java-why-use-this-word)
- [What Is a Monitor in Computer Science?](https://www.baeldung.com/cs/monitor)
- [How Java’s wait() Really Works: A Deep Dive into ObjectMonitor (Part 1)](https://medium.com/@nikolaykudinov/how-javas-wait-really-works-a-deep-dive-into-objectmonitor-part-1-7b87dc64bf50)
- [How Java’s notify() Really Works: A Deep Dive into ObjectMonitor (Part 2)](https://medium.com/@nikolaykudinov/how-javas-notify-really-works-a-deep-dive-into-objectmonitor-part-2-a292bdefb198)
- [How Java’s notifyAll() Really Works: A Deep Dive into ObjectMonitor (Part 3)](https://medium.com/@nikolaykudinov/how-javas-notifyall-really-works-a-deep-dive-into-objectmonitor-part-3-36810cc361e8)
- [How Java Manages Thread Synchronization with Locks and Monitors](https://medium.com/@AlexanderObregon/how-java-manages-thread-synchronization-with-locks-and-monitors-541ce7c7a0b2)
- [Monitors in Java (PDF)](https://courses.cs.vt.edu/cs5204/sp99/Overheads/2UP/2UPJavaMonitors.pdf)
- [Concurrency Fundamentals: Deadlocks and Object Monitors](https://www.javacodegeeks.com/2015/09/concurrency-fundamentals-deadlocks-and-object-monitors.html)

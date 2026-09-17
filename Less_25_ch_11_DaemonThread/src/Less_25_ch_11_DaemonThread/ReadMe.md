### Daemon thread или Демоны потоки

**Демон** в широком значении – **фоновая программа**. В Java потоки-демоны имеют схожий смысл: это потоки для фоновых действий по обслуживанию основных потоков. 
Потоки не-демоны называются пользовательскими или "user thread".

Поток (thread) "рождается" демоном, если его родитель демон. Свойство [Java thread](https://docs.oracle.com/javase/8/docs/api/java/lang/Thread.html) [isDaemon можно
переключать](https://docs.oracle.com/javase/8/docs/api/java/lang/Thread.html#isDaemon--) в любой момент до старта потока. По сравнению с пользовательскими потоками
демоны имеют меньший приоритет выполнения.

Когда все пользовательские треды завершились, JVM завершает работу. Демоны не выполняют самостоятельных задач, поэтому не препятствуют остановке, программа завершается 
не дожидаясь окончания их работы.

Daemon thread может быть полезен для таких действий, как инвалидация кэша, периодическая актуализация значений из внешних источников, освобождение неиспользуемых 
пользовательских ресурсов. Потоки демоны в Java подобны поставщикам услуг для других потоков или объектов, работающих в том же процессе, что и поток демона. Потоки 
демоны используются для фоновых задач поддержки и нужны только во время выполнения обычных потоков.

Обычные потоки и потоки демоны отличаются тем, что происходит при выходе из программы. Когда JVM останавливает основные, пользовательские потоки, все оставшиеся потоки 
демоны прекращают свою работу: в конце концов блоки не выполняются, стеки не разматываются — JVM просто завершается.

По этой причине потоки демоны следует использовать с осторожностью, и их опасно использовать для задач, которые могут выполнять любые операции ввода-вывода (ошибка 
передачи и приема данных).

---
**Методы потоков демонов:**
- `setDaemon (true / false)` - метод используется, чтобы указать, что поток является потоком демоном.
- `public boolean isDaemon()` - метод используется для определения того, является ли поток потоком демоном или нет.

Поток демон в Java — это поток, который работает в фоновом режиме и в основном создается JVM для выполнения фоновых задач, таких как сборка мусора и другие домашние задачи.

---
**Примечание:**
- Любой поток, созданный основным потоком, который выполняет основной метод в Java, по умолчанию НЕ является демоном (наследуя природу родителя). Перефразирум → поток наследует
свою природу от порождающего потока, который его создает, т.е. основные признаки наследуются - демона может породить только демон, но поток порожденный НЕ демоном может им стать.
А поскольку основной поток является потоком, не являющимся демоном, любой другой поток, созданный из него, будет оставаться НЕ-демоном до тех пор, пока он явно не станет демоном,
вызвав [setDaemon(true)](https://docs.oracle.com/javase/8/docs/api/java/lang/Thread.html#setDaemon-boolean-);
- `Thread.setDaemon(true)` создает демон-поток (daemon thread), но его можно вызвать только ПЕРЕД запуском thread (потока) в Java. Он выдаст исключение [IllegalThreadStateException](https://docs.oracle.com/javase/8/docs/api/java/lang/IllegalThreadStateException.html), если соответствующий поток уже запущен и работает.

---
**Доп. материал:**
- [Class Thread](https://docs.oracle.com/javase/8/docs/api/java/lang/Thread.html)
- [Java Daemon Thread (article from **geeksforgeeks.org**)](https://www.geeksforgeeks.org/java/daemon-thread-java/)
- [Daemon Threads in Java (article from **baeldung.com**)](https://www.baeldung.com/java-daemon-thread)
- [Java - Daemon Thread](https://www.tutorialspoint.com/java/java_daemon_thread.htm)
- [Difference Between Daemon Threads and User Threads In Java](https://www.geeksforgeeks.org/java/difference-between-daemon-threads-and-user-threads-in-java/)
- [Daemon Thread in Java (article from **tpointtech.com**)](https://www.tpointtech.com/daemon-thread-in-java)
- [Daemon Thread in Java (article from **techvidvan.com**)](https://techvidvan.com/tutorials/daemon-thread-in-java/)
- [Daemon Threads in Java (from GitHub)](https://github.com/shaikbasha-dev/04-Java-Multithreading/blob/main/11-Daemon%20Threads%20in%20Java.md)
- [What is Daemon thread in Java and Difference to Non daemon thread - Tutorial Example](https://javarevisited.blogspot.com/2012/03/what-is-daemon-thread-in-java-and.html)
- [Daemon Thread in Java (article from **smartprogramming.in**)](https://smartprogramming.in/tutorials/java/daemon-thread)
- [Daemon Threads in Java (article from **howtodoinjava.com**)](https://howtodoinjava.com/java/multi-threading/daemon-threads/)
- [Daemon Thread in Java with Examples (article from **dotnettutorials.net**)](https://dotnettutorials.net/lesson/daemon-thread-in-java/)

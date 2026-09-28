См. материалы:
- [Class Thread](https://docs.oracle.com/javase/8/docs/api/java/lang/Thread.html)
- [Interface Runnable](https://docs.oracle.com/javase/8/docs/api/java/lang/Runnable.html)

### Многопоточность в Java

Большинство языков программирования поддерживают такую важную функциональность как многопоточность, и 
Java в этом плане не исключение. При помощи многопоточности мы можем выделить в приложении несколько 
потоков (нитей, направлений), которые будут выполнять различные задачи одновременно.

Если у нас, допустим, графическое приложение, которое посылает запрос к какому-нибудь серверу или 
считывает и обрабатывает огромный файл, то без многопоточности у нас бы блокировался графический 
интерфейс на время выполнения задачи. Но, благодаря потокам мы можем выделить отправку запроса или 
любую другую задачу, которая может долго обрабатываться, в отдельный поток, не подвешивая интерфейс 
и не оставляя пользователя в недоумении и ожидании.

Поэтому большинство реальных приложений, которые многим из нас приходится использовать, практически 
не мыслимы без многопоточности.

Для создания нового потока мы можем создать новый класс, и далее, либо наследовать его от [класса 
Thread](https://docs.oracle.com/javase/8/docs/api/java/lang/Thread.html), либо реализуя в классе 
[интерфейс Runnable](https://docs.oracle.com/javase/8/docs/api/java/lang/Runnable.html).

---
### Класс [Thread](https://docs.oracle.com/javase/8/docs/api/java/lang/Thread.html)

В Java функциональность отдельного потока заключается в классе Thread. И чтобы создать новый поток, 
нам надо создать объект этого класса. Но все потоки не создаются сами по себе. Когда запускается 
программа, начинает работать главный поток этой программы. От этого главного потока порождаются все 
остальные дочерние потоки.

С помощью статического [метода Thread.currentThread()](https://docs.oracle.com/javase/8/docs/api/java/lang/Thread.html#currentThread--) 
мы можем получить текущий поток выполнения:

```java
         public static void main(String[] args) {
                  Thread t = Thread.currentThread(); // Получить текущий поток
                  System.out.println(t.getName()); // main
         }
```

По умолчанию именем главного потока будет main.

---
Для управления потоком класс Thread предоставляет ряд методов. Наиболее используемые из них:

- `getName()` - возвращает имя потока;
- `setName(String name)` - устанавливает имя потока;
- `getPriority()` - возвращает приоритет потока;
- `setPriority(int proirity)` - устанавливает приоритет потока;

Приоритет является одним из ключевых факторов для выбора системой потока из кучи потоков для выполнения. 
В этот метод в качестве параметра передается числовое значение приоритета - от 1 до 10. По умолчанию 
главному потоку выставляется средний приоритет - 5.

- `isAlive()` - возвращает true, если поток активен;
- `isInterrupted()` - возвращает true, если поток был прерван;
- `join()` - ожидает завершение потока;
- `run()` - определяет точку входа в поток;
- `sleep()` - приостанавливает поток на заданное количество миллисекунд;
- `start()` - запускает поток, вызывая его метод run();

---
**Доп. материалы:**
- [Thread.java from GitHob of OpenJDK](https://github.com/openjdk/jdk/blob/master/src/java.base/share/classes/java/lang/Thread.java)
- [Class Thread from Java Doc 21](https://javadoc.scijava.org/Java21/java.base/java/lang/Thread.html)
- [Java Threads](https://www.geeksforgeeks.org/java/java-threads/)
- [Java Threads](https://www.w3schools.com/java/java_threads.asp)
- [Lifecycle and States of a Thread in Java](https://www.geeksforgeeks.org/java/lifecycle-and-states-of-a-thread-in-java/)
- [Understanding Java Threads: A Complete Guide](https://medium.com/@kiranpuli/understanding-java-threads-a-complete-guide-64b14807c502)
- [Understanding Threads in Java: A Detailed Guide](https://blog.stackademic.com/understanding-threads-in-java-a-detailed-guide-73e0eb285a49)
- [Java Threads](https://www.datacamp.com/doc/java/threads)
- [Java - creating a new thread](https://stackoverflow.com/questions/17758411/java-creating-a-new-thread)
- [Thread In Java | Lifecycle, Methods, Priority & More (+Examples)](https://unstop.com/blog/thread-in-java)
- [Java Threads: Thread Life Cycle and Threading Basics](https://www.simplilearn.com/tutorials/java-tutorial/thread-in-java)

--- 
- [Uses of Interface java.lang.Runnable](https://docs.oracle.com/javase/8/docs/api/java/lang/class-use/Runnable.html)
- [Runnable.java from GitHob of OpenJDK](https://github.com/openjdk/jdk/blob/master/src/java.base/share/classes/java/lang/Runnable.java)
- [Java Runnable Interface](https://www.geeksforgeeks.org/java/runnable-interface-in-java/)
- [Java : Runnable Interface - The Runnable Interface in Java: A Detailed Guide with Examples](https://medium.com/@RupamThakre/java-runnable-interface-41a679616175)
- [What is Runnable interface in Java?](https://www.tutorialspoint.com/article/what-is-runnable-interface-in-java)
- [Java classes for threads](https://psp2dam.github.io/psp_pages/en/unit3/runnable.html)
- [Java Runnable Interface](https://zetcode.com/java/lang-runnable/)
- [Implementing a Runnable vs Extending a Thread](https://www.baeldung.com/java-runnable-vs-extending-thread)
- [Создание и запуск новых нитей (трэдов)](https://javarush.com/quests/lectures/questcore.level06.lecture02)
- [Runnable Interface in Java](https://www.scaler.com/topics/runnable-interface-in-java/)
- [В чем разница между Thread и Runnable?](https://ru.stackoverflow.com/questions/399989/%D0%92-%D1%87%D0%B5%D0%BC-%D1%80%D0%B0%D0%B7%D0%BD%D0%B8%D1%86%D0%B0-%D0%BC%D0%B5%D0%B6%D0%B4%D1%83-thread-%D0%B8-runnable)
- [Runnable Interface in Java to Create Threads](https://techvidvan.com/tutorials/runnable-interface-in-java/)

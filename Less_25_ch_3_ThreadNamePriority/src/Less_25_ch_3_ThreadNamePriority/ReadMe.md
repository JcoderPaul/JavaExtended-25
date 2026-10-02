См. док. (ENG): [Class Thread](https://docs.oracle.com/javase/8/docs/api/java/lang/Thread.html)

---
### Класс Thread

В Java функциональность отдельного потока заключается в классе Thread. И чтобы создать новый поток, нам надо создать объект этого класса.
Но все потоки не создаются сами по себе. Когда запускается программа, начинает работать главный поток этой программы. От этого главного потока
порождаются все остальные дочерние потоки.

С помощью статического метода Thread.currentThread() мы можем получить текущий поток выполнения:

```java
public static void main(String[] args) {
         
    Thread t = Thread.currentThread(); // Получить текущий поток
    System.out.println(t.getName()); // main
}
```
По умолчанию именем главного потока будет main.

Для управления потоком класс Thread предоставляет ряд методов. Наиболее используемые из них:

- `getName()` - возвращает имя потока;
- `setName(String name)` - **устанавливает имя потока**;
- `getPriority()` - возвращает приоритет потока;
- `setPriority(int proirity)` - **устанавливает приоритет потока**;

Приоритет является одним из ключевых факторов для выбора системой потока из кучи потоков для выполнения. В метод `setPriority(int proirity)` в 
качестве параметра передается числовое значение приоритета - от 1 до 10. По умолчанию главному потоку выставляется средний приоритет - 5.

- `isAlive()` - возвращает true, если поток активен;
- `isInterrupted()` - возвращает true, если поток был прерван;
- `join()` - ожидает завершение потока;
- `run()` - определяет точку входа в поток;
- `sleep()` - приостанавливает поток на заданное количество миллисекунд;
- `start()` - запускает поток, вызывая его метод run();

---
**Доп. материал:**
- [Java Thread Priority in Multithreading](https://www.geeksforgeeks.org/java/java-thread-priority-multithreading/)
- [Priority of a Thread in Java](https://techvidvan.com/tutorials/thread-priority-in-java/)
- [Priority of a Thread in Java](https://www.baeldung.com/java-thread-priority)
- [Understanding "priority" in java threads](https://stackoverflow.com/questions/10792840/understanding-priority-in-java-threads)
- [Java Thread Priority](https://codingnomads.com/java-thread-priority)
- [Thread Priority in Java](https://www.tpointtech.com/thread-priority-in-java)
- [Thread Priority](https://algomaster.io/learn/java/thread-priority)
- [Java Thread Priority](http://www.btechsmartclass.com/java/java-threads-priority.html)
- [Java - Thread Priority](https://www.tutorialspoint.com/java/java_thread_priority.htm)
- [Thread Priority In Java - Constants, Limitations & More (+Examples)](https://unstop.com/blog/thread-priority-in-java)
- [Thread Methods & Priority](https://codenbuild.com/docs/java/multithread/methods/)
- [Java Thread Priority Example](https://www.javaguides.net/2018/09/java-thread-priority-example.html)
- [Understanding Java Threads in Practice: Naming, Priority, and Uncaught Exceptions](https://www.linkedin.com/pulse/understanding-java-threads-practice-naming-priority-anderson-carlucci-svtcf)

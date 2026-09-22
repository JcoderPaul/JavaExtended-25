- См. [Class Semaphore](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/Semaphore.html)

---
### Семафоры или Semaphore

Семафоры представляют еще одно средство синхронизации для доступа к ресурсу. В Java семафоры представлены 
[классом Semaphore](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/Semaphore.html), который 
располагается [в пакете java.util.concurrent](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/package-summary.html).

Для управления доступом к ресурсу семафор использует счетчик, представляющий количество разрешений. Если 
значение счетчика больше нуля, то поток получает доступ к ресурсу, при этом счетчик уменьшается на единицу. 
После окончания работы с ресурсом поток освобождает семафор, и счетчик увеличивается на единицу. Если же 
счетчик равен нулю, то поток блокируется и ждет, пока не получит разрешение от семафора на доступ к ресурсу.

Установить количество разрешений для доступа к ресурсу можно с помощью конструкторов [класса Semaphore](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/Semaphore.html):

- [Semaphore(int permits)](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/Semaphore.html#Semaphore-int-);
- [Semaphore(int permits, boolean fair)](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/Semaphore.html#Semaphore-int-boolean-);

Где параметр `permits` указывает на количество допустимых разрешений для доступа к ресурсу, а параметр `fair` 
во втором конструкторе позволяет установить очередность получения доступа. Если он равен true, то разрешения 
будут предоставляться ожидающим потокам в том порядке, в каком они запрашивали доступ. Если же он равен false, 
то разрешения будут предоставляться в неопределенном порядке.

---
#### Методы Semaphore:

Для получения разрешения на доступ к ресурсу у семафора надо вызвать метод `acquire()`, который имеет две формы:

- [void acquire()](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/Semaphore.html#acquire--) throws [InterruptedException](https://docs.oracle.com/javase/8/docs/api/java/lang/InterruptedException.html);
- [void acquire(int permits)](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/Semaphore.html#acquire-int-) throws [InterruptedException](https://docs.oracle.com/javase/8/docs/api/java/lang/InterruptedException.html);

Для получения одного разрешения применяется первый вариант, а для получения нескольких разрешений - второй вариант. 
После вызова этого метода поток блокируется пока не получит разрешение.

После окончания работы с ресурсом полученное ранее разрешение надо освободить с помощью метода `release()`:

- [void release()](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/Semaphore.html#release--)
- [void release(int permits)](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/Semaphore.html#release-int-)

Первый вариант метода освобождает одно разрешение, а второй вариант - количество разрешений, указанных в permits.

Семафоры отлично подходят для решения задач, где надо ограничивать доступ. Например, классическая задача про 
обедающих философов. Ее суть: есть несколько философов, допустим, пять, но одновременно за столом могут сидеть 
не более двух. И надо, чтобы все философы пообедали, но при этом не возникло взаимоблокировки философами друг 
друга в борьбе за тарелку и вилку 

Cм.пример [Less_25_DiningPhilosophers_Step2](./Less_25_DiningPhilosophers_Step2.java)

---
**Доп. материал:**
- [Class Semaphore](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/Semaphore.html)
- [Semaphore.java (from GitHub OpenJDK-Mirror)](https://github.com/openjdk-mirror/jdk7u-jdk/blob/master/src/share/classes/java/util/concurrent/Semaphore.java)
- [Semaphores in Java](https://www.baeldung.com/java-semaphore)
- [Semaphore in Java](https://www.geeksforgeeks.org/java/semaphore-in-java/)
- [Semaphores](https://jenkov.com/tutorials/java-concurrency/semaphores.html)
- [What is a Semaphore in Java?](https://www.javapro.academy/what-is-a-semaphore/)
- [Semaphore in Java - Controlling Concurrency with Precision](https://medium.com/javarevisited/semaphore-in-java-6824fe663975)
- [Java Concurrency Tutorial – Semaphores](https://www.javacodegeeks.com/2011/09/java-concurrency-tutorial-semaphores.html)
- [Java Semaphore examples](https://mkyong.com/java/java-semaphore-examples/)
- [Semaphore example from GitHub](https://gist.github.com/pedrominicz/1d976280c13e2900fa16aa5c7f2dfebf)
- [What Is a Java Semaphore?](https://redisson.pro/glossary/java-semaphore.html)
- [Be Aware of the Java Semaphore Trap](https://www.linkedin.com/pulse/aware-java-semaphore-trap-a-n-m-bazlur-rahman-2sxtc)
- [Techniques for Managing Concurrency in Java Using Semaphores](https://dev.to/anh_trntun_4732cf3d299/techniques-for-managing-concurrency-in-java-using-semaphores-5ai)
- [Semaphore in Java: Simple Guide with Real-time Scenarios](https://medium.com/@praveen369soni/semaphore-in-java-simple-guide-with-real-time-scenarios-e3eb1147db04)

### Завершение и прерывание потока

Обычно поток представляют как последовательный набор операций. После выполнения последней операции
поток завершается. Однако нередко имеет место и другая организация потока в виде бесконечного цикла.
Например, поток сервера в бесконечном цикле прослушивает определенный порт на предмет получения данных.
И в этом случае мы также можем предусмотреть механизм завершения потока.

Распространенный способ завершения потока представляет опрос логической переменной. И если она равна,
например, false, то поток завершает бесконечный цикл и заканчивает свое выполнение. 

Однако существуют специальный метод.

---
### Метод interrupt

Это способ вызова завершения или прерывания потока представляет [метод interrupt()](https://docs.oracle.com/javase/8/docs/api/java/lang/Thread.html#interrupt--). Вызов этого метода
устанавливает у потока статус, что он прерван. Сам метод возвращает - *true*, если поток может быть прерван, в ином случае возвращается - *false*.

При этом сам *вызов этого метода НЕ завершает поток, он только устанавливает статус: (false/true)* т.е., что некто пытается остановить данный поток. Если такой запрос есть, то метод 
[isInterrupted() класса Thread](https://docs.oracle.com/javase/8/docs/api/java/lang/Thread.html#isInterrupted--) будет возвращать значение - *true*. Мы можем проверить значение 
возвращаемое данным методом и произвести некоторые действия по прерыванию (или не прерыванию) потока на который "выписали заказ" - [interrupt()](https://docs.oracle.com/javase/8/docs/api/java/lang/Thread.html#interrupt--).

Если основная функциональность заключена в классе, который реализует [интерфейс Runnable](https://docs.oracle.com/javase/8/docs/api/java/lang/Runnable.html), то там можно проверять 
статус потока с помощью метода [Thread.currentThread().isInterrupted()](https://docs.oracle.com/javase/8/docs/api/java/lang/Thread.html#isInterrupted--).
(см. пример [Less_25_Interruption_Step2](https://github.com/JcoderPaul/JavaExtended-25/blob/master/Less_25_ch_12_Interruption/src/Less_25_ch_12_Interruption/Less_25_Interruption_Step2.java))

Однако при получении статуса потока с помощью метода isInterrupted() следует учитывать, что если мы обрабатываем в цикле исключение [InterruptedException](https://docs.oracle.com/javase/8/docs/api/java/lang/InterruptedException.html) в блоке catch, то при перехвате исключения статус потока автоматически сбрасывается, 
и после этого isInterrupted будет возвращать false.

(см. пример [Less_25_Interruption_Step3](https://github.com/JcoderPaul/JavaExtended-25/blob/master/Less_25_ch_12_Interruption/src/Less_25_ch_12_Interruption/Less_25_Interruption_Step3.java) - в данном примере, когда поток вызовет метод [interrupt](https://docs.oracle.com/javase/8/docs/api/java/lang/Thread.html#interrupt--), метод [sleep](https://docs.oracle.com/javase/8/docs/api/java/lang/Thread.html#sleep-long-) сгенерирует исключение [InterruptedException](https://docs.oracle.com/javase/8/docs/api/java/lang/InterruptedException.html), и управление перейдет к блоку catch. Но если мы проверим статус потока внутри блока catch, то увидим, что метод isInterrupted возвращает false. Как вариант, в этом случае мы можем повторно прервать текущий поток, опять же вызвав метод interrupt(). Тогда при новой итерации цикла while метода isInterrupted возвратит true, и произойдет выход из цикла.)

---
**Доп. материалы:**
- [Interrupts (from The Java™ Tutorials - ORACLE Docs)](https://docs.oracle.com/javase/tutorial/essential/concurrency/interrupt.html)
- [Interrupting a Thread in Java](https://www.geeksforgeeks.org/java/interrupting-a-thread-in-java/)
- [Java’s Thread.interrupt() Method Explained](https://medium.com/@AlexanderObregon/javas-thread-interrupt-method-explained-0df0f8f6a428)
- [What does `java.lang.Thread.interrupt()` do?](https://stackoverflow.com/questions/3590000/what-does-java-lang-thread-interrupt-do)
- [Interrupting a Thread](https://www.tpointtech.com/interrupting-a-thread)
- [Java - Interrupting Thread](https://www.tutorialspoint.com/java/java_interrupting_thread.htm)
- [Java’s Mysterious Interrupt](https://carlmastrangelo.com/blog/javas-mysterious-interrupt)
- [Java Multithreading 7: Interrupt and end thread](https://nicklee1006.github.io/Java-Multithreading-7-Interrupt-and-end-thread/)
- [How to Handle InterruptedException in Java](https://www.baeldung.com/java-interrupted-exception)
- [What does java.lang.Thread.interrupt() do?](https://codemia.io/knowledge-hub/path/what_does_javalangthreadinterrupt_do)
- [Interrupting Java threads](https://dineshonjava.com/interrupting-java-threads/)
- [Thread Interruption In Java](https://javaconceptoftheday.com/thread-interruption-java/)
- [Java Thread interrupt() Method](https://www.tutorialspoint.com/java/lang/thread_interrupt.htm)
- [Java Thread sleep and interrupt methods.](https://samedesilva.medium.com/java-thread-sleep-and-interrupt-methods-3850e6201169)
- [Killing threads in Java](https://www.geeksforgeeks.org/java/killing-threads-in-java/)

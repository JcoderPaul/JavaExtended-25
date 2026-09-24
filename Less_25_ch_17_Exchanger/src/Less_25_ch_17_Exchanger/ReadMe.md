- См. исх. статью(ENG): [`Class Exchanger<V>`](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/Exchanger.html)

---
### Класс Exchanger - обмен между потоками.

Синхронизаторы – вспомогательные утилиты для синхронизации потоков, которые дают возможность разработчику регулировать 
и/или ограничивать работу потоков и предоставляют более высокий уровень абстракции, чем основные примитивы языка (мониторы).

**Класс Exchanger предназначен для обмена данными между потоками.** Он является типизированным и типизируется типом данных, 
которыми потоки должны обмениваться.

Пакет: [java.util.concurrent](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/package-summary.html)

```java
  public class Exchanger<V>
    extends Object
```

Точка синхронизации, в которой потоки могут объединяться в пары и обмениваться элементами внутри пар. Каждый поток при входе 
в [метод exchange](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/Exchanger.html#exchange-V-) предоставляет 
некоторый объект, сопоставляет его с партнерским потоком и получает объект партнера при возврате. Обменник можно рассматривать 
как двунаправленную форму [SynchronousQueue](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/SynchronousQueue.html). 
Обменники могут быть полезны в таких приложениях, как генетические алгоритмы и проектирование конвейеров. 

**Пример использования:** Вот основные моменты класса, который использует механизм Exchanger обмена буферами между потоками, 
чтобы поток, заполняющий буфер, получал свежеочищенный буфер, когда он ему нужен, передавая заполненный буфер потоку, очищающему буфер.

```java
 class FillAndEmpty {
   Exchanger<DataBuffer> exchanger = new Exchanger<DataBuffer>();
   DataBuffer initialEmptyBuffer = ... a made-up type
   DataBuffer initialFullBuffer = ...

   class FillingLoop implements Runnable {
     public void run() {
       DataBuffer currentBuffer = initialEmptyBuffer;
       try {
         while (currentBuffer != null) {
           addToBuffer(currentBuffer);
           if (currentBuffer.isFull())
             currentBuffer = exchanger.exchange(currentBuffer);
         }
       } catch (InterruptedException ex) { ... handle ... }
     }
   }

   class EmptyingLoop implements Runnable {
     public void run() {
       DataBuffer currentBuffer = initialFullBuffer;
       try {
         while (currentBuffer != null) {
           takeFromBuffer(currentBuffer);
           if (currentBuffer.isEmpty())
             currentBuffer = exchanger.exchange(currentBuffer);
         }
       } catch (InterruptedException ex) { ... handle ...}
     }
   }

   void start() {
     new Thread(new FillingLoop()).start();
     new Thread(new EmptyingLoop()).start();
   }
 }
```

**Эффекты согласованности памяти:** для каждой пары потоков, успешно обменивающихся объектами через событие Exchanger, 
действия, предшествующие событию [exchange()](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/Exchanger.html#exchange-V-long-java.util.concurrent.TimeUnit-) 
в каждом потоке, происходят до действий, следующих за возвратом из соответствующего события exchange() в другом потоке.

---
Обмен данными производится с помощью единственного метода этого класса exchange():

- [`V exchange(V x) throws InterruptedException`](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/Exchanger.html#exchange-V-) - Ожидает прибытия другого потока в точку обмена (если только [текущий поток не прерван](https://docs.oracle.com/javase/8/docs/api/java/lang/Thread.html#interrupt--)), а затем передает ему заданный объект, получая взамен его собственный объект.
- [`V exchange(V x, long timeout, TimeUnit unit) throws InterruptedException, TimeoutException`](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/Exchanger.html#exchange-V-long-java.util.concurrent.TimeUnit-) - Ожидает прибытия другого потока в эту точку обмена (если только текущий [поток не прерван](https://docs.oracle.com/javase/8/docs/api/java/lang/Thread.html#interrupt--) или не истекло указанное [время ожидания](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/TimeUnit.html)), а затем передает ему заданный объект, получая взамен его собственный объект.

Параметр x представляет буфер данных для обмена. Вторая форма метода также определяет параметр timeout - время ожидания и unit - тип временных единиц,
применяемых для параметра timeout.

---
Exchanger (обменник) может понадобиться, для того, чтобы обменяться данными между двумя потоками в определенной точки работы обоих потоков. 
Обменник — обобщенный класс, он параметризируется типом объекта для передачи.

Обменник является точкой синхронизации пары потоков: поток, вызывающий у обменника метод exchange() блокируется и ждет другой поток. Когда 
другой поток вызовет тот же метод, произойдет обмен объектами: каждая из них получит аргумент другой в методе exchange(). Стоит отметить, 
что обменник поддерживает передачу null значения. Это дает возможность использовать его для передачи объекта в одну сторону, или, просто как
точку синхронизации двух потоков.

---
**Доп. материалы:**
- [`Class Exchanger<V>` from Java Doc 8](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/Exchanger.html)
- [`Class Exchanger<V>` from Java Doc 26](https://docs.oracle.com/en/java/javase/26/docs/api//java.base/java/util/concurrent/Exchanger.html)
- [Class Object](https://docs.oracle.com/javase/8/docs/api/java/lang/Object.html)
- [Class Thread](https://docs.oracle.com/javase/8/docs/api/java/lang/Thread.html)
- [Enum TimeUnit](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/TimeUnit.html)
- [Exchanger.java from GitHub OpenJDK](https://github.com/openjdk/jdk/blob/master/src/java.base/share/classes/java/util/concurrent/Exchanger.java)

--- 
- [Introduction to Exchanger in Java](https://www.baeldung.com/java-exchanger)
- [Java.util.concurrent.Exchanger class with Examples](https://www.geeksforgeeks.org/java/java-util-concurrent-exchanger-class-with-examples/)
- [Exchanger](https://jenkov.com/tutorials/java-util-concurrent/exchanger.html)
- [Introduction to Exchanger](https://codesignal.com/learn/courses/advanced-concurrency-utilities/lessons/data-exchange-between-threads-using-exchanger)
- [How to use Exchanger in Java with Example](https://www.javacodegeeks.com/2020/05/how-to-use-exchanger-in-java-with-example.html)
- [Understanding Exchanger in Java](https://vasylchenko.me/post/java/concurrency/exchanger/)
- [Java Concurrency In Depth-4 (Phaser & Exchanger)](https://medium.com/@abhijeetkarmakar1920/java-concurrency-in-depth-4-phaser-exchangers-42009348fca5)
- [Java 8 Concurrency Tutorial](https://nirajsonawane.github.io/2018/06/10/Concurrency-1-0/)
- [Java Concurrency Tutorials](https://www.javacodegeeks.com/java-concurrency-tutorials)
- [Обмен между потоками. Класс Exchanger](https://metanit.com/java/tutorial/8.7.php)
- [Java Exchanger With Examples](https://www.netjstech.com/2016/02/exchanger-in-java-concurrency.html)
- [How to exchange data between two threads using Exchanger in Java](https://roytuts.com/how-to-exchange-data-between-two-threads-using-exchanger-in-java/)
- [Exchanger](https://medium.com/@kaustubh.saha/exchanger-f3cbf4f65b75)

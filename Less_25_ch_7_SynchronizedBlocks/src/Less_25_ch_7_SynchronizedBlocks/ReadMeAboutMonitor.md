- [Java Language and Virtual Machine Specifications](https://docs.oracle.com/javase/specs/index.html)
- [The Java Language Environment: Contents](https://www.oracle.com/java/technologies/language-environment.html)
- [Threads and Locks](https://docs.oracle.com/javase/specs/jls/se7/html/jls-17.html)

---
### Монитор

В "чистом" Java такого класса как «монитор», нет, однако у каждого объекта типа Object есть свой собственный
«монитор». Так как у всех классов общий родитель — Object, все они имеют свой собственный «монитор».

**Концепция «монитор» внутри себя содержит 4 поля:**
- **locked** - типа boolean, которое показывает, захвачен монитор или нет;
- **owner** - типа Thread - в это поле записывается поток, который захватил данный монитор;
- **blocked set** — в это множество попадают потоки, которые не смогли захватить блокировку, или поток, который выходит из состояния wait;
- **wait set** — в это множество попадают потоки, для которых был вызван метод wait.

Blocked set, как и wait set, представляет собой неупорядоченное множество, не допускающее дубликатов.
Т. е. в wait set или blocked set один и тот же поток не может быть записан два раза.

Поля монитора невозможно получить через рефлексию.

У каждого объекта есть методы wait(), notify() и notifyAll(), которые этот объект унаследовал
от класса Object. Использование ключевого слова synchronized гарантирует, что блоки кода будут
выполняться только одним потоком в каждую конкретную единицу времени.

**Есть два варианта использования ключевого слова synchronized:**
- Два потока выполняют код (так называемая критическая секция), который в каждый момент времени может выполнять только один поток.
- Один поток ожидает какое-то событие. Это поведение обеспечивается методами wait(), notify() и notifyAll().

**Рассмотрим ситуацию, когда поток попадает в synchronized блок, выполняет критическую секцию и выходит из блока синхронизации:**
Ключевое слово synchronized всегда используется с объектом монитор. Сперва проверяются переменные locked и owner. Если эти поля 
false и null, соответственно, они заполняются. Поле locked принимает значение true, а в поле owner записывается ссылка на 
захватывающий поток. Как только это произошло, считается, что поток выполнил код, который соответствует открывающей фигурной 
скобке synchronized блока, и поток занял эту блокировку. После того как поток выполнил код, который соответствует закрывающейся 
фигурной скобке блока синхронизации, переменные locked и owner в мониторе очищаются.

**Рассмотрим ситуацию, когда поток пытается захватить уже занятый монитор:**
Сначала проверяется, что переменная locked == true, затем сравнивается переменная owner. Если переменная owner не равна тому потоку, 
который хочет захватить монитор, то поток блокируется и попадает в множество blocked set монитора. Если сравнение переменных owner 
дает результат true, это значит, что один и тот же поток пытается захватить монитор повторно — в этом случае поток не блокируется. 
Такое поведение называется реентернабельностью. Пример такой ситуации — рекурсивные методы. После того, как блокировка освободилась, 
другой поток покидает blocked set и захватывает монитор. В blocked set может находиться множество потоков. В этом случае выбирается
произвольный поток, который далее может захватить монитор.

Монитором может выступать простой объект, ключевое слово 'this', а также объект типа *.class. Примеры см. ниже:

```java
  public class SomeClass {
  
      private final Object PRIVATE_LOCK_OBJECT = new Object();
  
      public synchronized void firstMethod() {
          //some code
      }
  
      public void theSameAsFirstMethod() {
          synchronized(this) { //some code }
      }
  
      public void theBestMethodUsingSynchro() {
          synchronised(PRIVATE_LOCK_OBJECT) { //some code }
      }
  
      public static void synchronizedOnStaticMethod() {
          synchronized(SomeClass.class) { //some code }
      }
  
      public static synchronized void synchronizedOnStaticMethod() {
      //some code
      }
  }
```

- Когда метод объявляется с ключевым словом synchronized, это эквивалентно коду, когда всё его тело
обернуто в synchronized блок и блокировкой служит объект this.
- Когда статический метод используется с ключевым словом synchronized, это эквивалентно тому, когда
в качестве блокировки используется объект SomeClass.class.

Однако самый лучший способ — объявить private final константу, по которой и производится синхронизация.
Стоит заметить, что конструкция с использованием ключевого слова synchronized — синтаксическая и
проверяется компилятором. Т. е. всегда должна быть открывающая фигурная скобка и соответствующая ей
закрывающая фигурная скобка synchronized блока.

Synchronized блоки могут быть вложенными друг в друга см. пример:

```java
  final Object LOCK = new Object();
  
      synchronized(LOCK) {
          synchronized(LOCK) {
              synchronized(LOCK) {
              }
          }
      }
```

**Как показано в предыдущем примере, можно несколько раз захватить монитор на одном и том же объекте.
Нет способа определить, сколько раз был захвачен монитор, и !!! не стоит строить такую логику в программе !!!
Освобождение монитора происходит после выхода из верхнего synchronized блока.**

Еще пример в котором показан очередной вариант вложенных синхронизаций:

```java
  Object LOCK_A = new Object();
  Object LOCK_B = new Object();
  Object LOCK_C = new Object();
  
  synchronized(LOCK_A) {
      synchronized(LOCK B) {
          synchronized(LOCK_C) {
          }
      }
  }
```

В данном случае сначала захватываются мониторы LOCK_A, затем LOCK_B и LOCK_С, а освобождаются мониторы в обратном порядке.

---
**Доп. материал:**
- [Monitor in Java](https://www.geeksforgeeks.org/java/monitor-in-java/)
- [Difference Between Lock and Monitor in Java Concurrency](https://www.geeksforgeeks.org/java/difference-between-lock-and-monitor-in-java-concurrency/)
- [Thread Synchronization](https://www.artima.com/insidejvm/ed2/threadsynchP.html)
- [Monitors (by Penn Engineering - Computer and Information Science)](https://www.cis.upenn.edu/~bcpierce/courses/629/papers/Java-tutorial/java/threads/monitors.html)
- [Монитор (синхронизация) from Wiki](https://ru.wikipedia.org/wiki/%D0%9C%D0%BE%D0%BD%D0%B8%D1%82%D0%BE%D1%80_(%D1%81%D0%B8%D0%BD%D1%85%D1%80%D0%BE%D0%BD%D0%B8%D0%B7%D0%B0%D1%86%D0%B8%D1%8F))
- [В чем разница между мьютексом, монитором и семафором](https://javarush.com/groups/posts/2174-v-chem-raznica-mezhdu-mjhjuteksom-monitorom-i-semaforom)
- [Многопоточная среда в разработке JAVA](https://habr.com/ru/articles/1022934/)
- [Под капотом многопоточной синхронизации в Java: как потоки договариваются через Mark Word](https://habr.com/ru/articles/982600/)
- [What's a monitor in Java?](https://stackoverflow.com/questions/3362303/whats-a-monitor-in-java)
- [What Is a Monitor in Computer Science?](https://www.baeldung.com/cs/monitor)
- [Object monitor pattern](https://www.waitingforcode.com/java-concurrency/object-monitor-pattern/read)
- [Exploring Java's ObjectMonitor](https://www.linkedin.com/pulse/exploring-javas-objectmonitor-sandeep-kumar-jhg0c)
- [Implementing Monitors in Java](https://medium.com/@sarayusri17/implementing-monitors-in-java-435731144e14)
- [Monitor Object](https://medium.com/@maheshmaddi92/5-2-monitor-object-6ed26a1b1fa4)
- [Java Concurrency: Object monitor methods](https://egkatzioura.com/2023/03/24/java-concurrency-object-monitor-methods/)
- [Java Monitor Object Usage Considerations (PDF Presintation)](https://www.dre.vanderbilt.edu/~schmidt/cs254/2021-PDFs/8.5.1-monitor-object-usage-considerations.pdf)

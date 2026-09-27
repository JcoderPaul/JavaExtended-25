### Cинхронизированные коллекции

[Фреймворк collections]([Collections Framework Overview](https://docs.oracle.com/javase/8/docs/technotes/guides/collections/overview.html)) является ключевым компонентом Java. Он предоставляет большое
количество интерфейсов и реализаций, что позволяет нам создавать различные типы коллекций и управлять ими простым 
способом.

Хотя использование и управление простыми несинхронизированными коллекциями в целом предсказуемо, но это может стать 
сложным и подверженным ошибкам процессом при работе в многопоточных средах.

Для обхода этой проблемы в Java создали различные оболочки обертки для синхронизации, которые реализованы в [классе Collections](https://docs.oracle.com/javase/8/docs/api/java/util/Collections.html).

Эти оболочки позволяют легко создавать синхронизированные представления - view нужных коллекций с помощью нескольких 
статических фабричных методов:

- **Метод [synchronizedCollection()](https://docs.oracle.com/javase/8/docs/api/java/util/Collections.html#synchronizedCollection-java.util.Collection-)** - Синхронизированная оболочка -
возвращает потокобезопасную коллекцию, резервную копию которой создает указанная коллекция [Collection](https://docs.oracle.com/javase/8/docs/api/java/util/Collection.html).

См. пример [Less_25_SynchronizedCollection_Step4](./Less_25_SynchronizedCollection_Step4.java))

- **Метод [synchronizedList()](https://docs.oracle.com/javase/8/docs/api/java/util/Collections.html#synchronizedList-java.util.List-)** - Аналогично методу synchronizedCollection(), мы
можем использовать оболочку [synchronizedList()](https://docs.oracle.com/javase/8/docs/api/java/util/Collections.html#synchronizedList-java.util.List-) для создания синхронизированного
cписка (List). Метод возвращает потокобезопасное представление указанного списка:

```java
 List list = Collections.synchronizedList(new ArrayList());
      ...
  synchronized (list) {
      Iterator i = list.iterator(); // Must be in synchronized block
      while (i.hasNext())
          foo(i.next());
  }
```

Использование метода synchronizedList() выглядит почти идентично его аналогу более высокого уровня, synchronizedCollection().

См. пример [Less_25_SynchronizedCollection_Step3](./Less_25_SynchronizedCollection_Step3.java))

**!!! Если мы хотим выполнить итерацию по синхронизированной коллекции и хотим избежать
неожиданных результатов, мы должны явно реализовать синхронизацию цикла итератора,
заключив его в синхронизированный блок!!!**

См. пример [Less_25_SynchronizedCollection_Step3](./Less_25_SynchronizedCollection_Step3.java))

Во всех случаях, когда нам нужно выполнить итерацию по синхронизированной коллекции, мы должны реализовать эту идиому. 
Это связано с тем, что итерация синхронизированной коллекции выполняется с помощью нескольких вызовов коллекции. 
Поэтому они должны выполняться как единая атомарная операция.

---
- **Метод [synchronizedMap()](https://docs.oracle.com/javase/8/docs/api/java/util/Collections.html#synchronizedMap-java.util.Map-)** -
Данный метод используется для простого создания синхронизированной Map коллекции.

Пример: 

```java
Map m = Collections.synchronizedMap(new HashMap());
      ...
  Set s = m.keySet();  // Needn't be in synchronized block
      ...
  synchronized (m) {  // Synchronizing on m, not s!
      Iterator i = s.iterator(); // Must be in synchronized block
      while (i.hasNext())
          foo(i.next());
  }
```

- **Метод [synchronizedSortedMap()](https://docs.oracle.com/javase/8/docs/api/java/util/Collections.html#synchronizedSortedMap-java.util.SortedMap-)** -
Метод который можно использовать для создания синхронизированной SortedMap коллекции.

Пример: 

```java
 SortedMap m = Collections.synchronizedSortedMap(new TreeMap());
      ...
  Set s = m.keySet();  // Needn't be in synchronized block
      ...
  synchronized (m) {  // Synchronizing on m, not s!
      Iterator i = s.iterator(); // Must be in synchronized block
      while (i.hasNext())
          foo(i.next());
  }
```
или

```java
 SortedMap m = Collections.synchronizedSortedMap(new TreeMap());
  SortedMap m2 = m.subMap(foo, bar);
      ...
  Set s2 = m2.keySet();  // Needn't be in synchronized block
      ...
  synchronized (m) {  // Synchronizing on m, not m2 or s2!
      Iterator i = s.iterator(); // Must be in synchronized block
      while (i.hasNext())
          foo(i.next());
  }
```

- **Метод [synchronizedSet()](https://docs.oracle.com/javase/8/docs/api/java/util/Collections.html#synchronizedSet-java.util.Set-)** -
Данный метод позволяет создавать синхронизированные наборы Set.
  
Пример:
```java
Set s = Collections.synchronizedSet(new HashSet());
      ...
  synchronized (s) {
      Iterator i = s.iterator(); // Must be in the synchronized block
      while (i.hasNext())
          foo(i.next());
  }
```

- **Метод [synchronizedSortedSet()](https://docs.oracle.com/javase/8/docs/api/java/util/Collections.html#synchronizedSortedSet-java.util.SortedSet-)** -
Метод возвращает потокобезопасную версию отсортированного Set-а SortedSet.
  
Пример: 
```java
SortedSet s = Collections.synchronizedSortedSet(new TreeSet());
      ...
  synchronized (s) {
      Iterator i = s.iterator(); // Must be in the synchronized block
      while (i.hasNext())
          foo(i.next());
  }
```

или
```java
SortedSet s = Collections.synchronizedSortedSet(new TreeSet());
  SortedSet s2 = s.headSet(foo);
      ...
  synchronized (s) {  // Note: s, not s2!!!
      Iterator i = s2.iterator(); // Must be in the synchronized block
      while (i.hasNext())
          foo(i.next());
  }
```

---
Синхронизированные коллекции обеспечивают потокобезопасность за счет блокировки монитора, и все коллекции блокируются. 
Внутренняя блокировка реализуется с помощью синхронизированных блоков в рамках методов обернутой коллекции.

Как и следовало ожидать, синхронизированные коллекции обеспечивают согласованность/целостность данных в многопоточных средах. 
Однако они могут привести к снижению производительности, так как ТОЛЬКО ОДИН поток может одновременно получить доступ к коллекции 
(он же синхронизированный доступ).

Созданные обертки выполняют роль декораторов, в которых методы работы с элементами коллекций такие как метод get и метод put 
синхронизированы посредством блокировки.

**!!! Важно запомнить, что после создания потокобезопасных декораторов обращение к базовой
структуре данных должно происходить, только через эти синхронизированные декораторы !!!**

---
Еще раз [java.util.Collections](https://docs.oracle.com/javase/8/docs/api/java/util/Collections.html) обеспечивает следующие синхронизирующие методы:
- `public static Collection synchronizedCollection(Collection c)`;
- `public static List synchronizedList(List list)`;
- `public static Map synchronizedMap(Map m)`;
- `public static Set synchronizedSet(Set s)`;
- `public static SortedMap synchronizedSortedMap(SortedMap m)`;
- `public static SortedSet synchronizedSortedSet(SortedSet s)`;

И снова, повторим, обертки управляют лишь методами набора данных get и put. Для обхода синхронизированной коллекции, код обхода коллекции должен быть
синхронизирован на самой обертке:

```java
    List synchList = Collections.synchronizedList(new ArrayList());
    
      synchronized (synchList) {
        Iterator iter = synchList.iterator();
        while (iter.hasNext()){
               . . .               // Do something
        }
     }
```
     
---
**Доп. материалы:**
- [Class Collections](https://docs.oracle.com/javase/8/docs/api/java/util/Collections.html)
- [Collections Framework Overview (from ORACLE Docs)](https://docs.oracle.com/javase/8/docs/technotes/guides/collections/overview.html)
- [Java Collection Framework Tutorial](https://www.geeksforgeeks.org/java/java-collection-tutorial/)
- [Collections in Java](https://www.tpointtech.com/collections-in-java)
- [Collections Framework in Java](https://smartprogramming.in/tutorials/java/collections-framework)
- [Java Collections Class (from jenkov.com)](https://jenkov.com/tutorials/java-collections/collections.html)
- [Mastering the Java Collections Framework: A Comprehensive Guide for Beginners](https://www.linkedin.com/pulse/mastering-java-collections-framework-comprehensive-guide-mustufa-khan-4peff)
- [Java - Collections Framework](https://www.tutorialspoint.com/java/java_collections.htm)
- [Java Collection Framework – An Exclusive Guide on Collection Framework](https://techvidvan.com/tutorials/java-collection-framework/)
- [Java Collection Framework](https://medium.com/@danidutharuka12345/java-collection-framework-022fdaa922f5)
- [Collections in Java - Java Collection Framework](https://www.mygreatlearning.com/blog/collection-in-java/)
- [The Collections Framework](https://dev.java/learn/api/collections-and-streams/collections-framework/)
- [Java Collection Programs - Basic to Advanced](https://www.geeksforgeeks.org/java/java-collection-programs/)
- [How to Use the Java Collections Framework – A Guide for Developers](https://www.freecodecamp.org/news/java-collections-framework-reference-guide/)
- [What is the Collection Framework in Java? Benefits, Types & Diagram](https://utho.com/blog/java-collection-framework-benefits-types-diagram/)
- [Java Collections – Interface, List, Queue, Sets in Java With Examples](https://www.edureka.co/blog/java-collections/)
- [Mastering Java Collections in Detail: A Complete Guide with Code, Diagrams, and Use Cases](https://medium.com/@shubhamjha642/mastering-java-collections-in-detail-a-complete-guide-with-code-diagrams-and-use-cases-ec2fc9724c11)
- [Java Collections Framework](https://www.w3schools.com/java/java_collections.asp)

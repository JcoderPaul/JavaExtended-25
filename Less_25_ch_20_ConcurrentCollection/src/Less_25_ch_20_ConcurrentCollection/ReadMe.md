См. исходник (ENG): Class [`ConcurrentHashMap<K,V>`](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/ConcurrentHashMap.html)

---
### ConcurrentHashMap

До появления в JDK 1.5 реализации [ConcurrentHashMap](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/ConcurrentHashMap.html), существовало несколько способов
описания хэш-таблиц. Первоначально в JDK 1.0 был клас [Hashtable](https://docs.oracle.com/javase/8/docs/api/java/util/Hashtable.html).

**Hashtable — потокобезопасная и легкая в использовании реализация хэш-таблицы**. Проблема HashTable заключалась, 
в первую очередь, в том, что при доступе к элементам таблицы производилась её полная блокировка.

Все методы Hashtable были синхронизированными. Это являлось серьёзным ограничением для многопоточной среды, поскольку 
плата за блокировку всей таблицы резкое падение быстродействия. В JDK 1.2 на помощь Hashtable [пришёл HashMap](https://docs.oracle.com/javase/8/docs/api/java/util/HashMap.html) 
и его потокобезопасное представление - [Collections.synchronizedMap](https://docs.oracle.com/javase/8/docs/api/java/util/Collections.html#synchronizedMap-java.util.Map-).

Причин для такого разделения было несколько:
- Не каждый программист и не каждое решение нуждались в использовании потокобезопасной хэш-таблицы.
- Программисту необходимо было дать выбор, какой вариант ему удобнее использовать.

Таким образом, c JDK 1.2 список вариантов реализации хэш-карт в Java пополнился ещё двумя способами. Однако эти способы 
не избавили разработчиков от появления в их коде race conditions, которые могли привести к появлению [ConcurrentModificationException](https://docs.oracle.com/javase/8/docs/api/java/util/ConcurrentModificationException.html).

JDK 1.5 предоставила более [производительный и масштабируемый ConcurrentHashMap](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/ConcurrentHashMap.html).

К моменту появления [ConcurrentHashMap](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/ConcurrentHashMap.html) Java-разработчики нуждались в следующей реализации HashMap:
- потокобезопасность;
- отсутствие блокировок всей таблицы на время доступа к ней;
- желательно, чтобы отсутствовали блокировки таблицы при выполнении операции чтения;

Основные преимущества и особенности реализации [ConcurrentHashMap](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/ConcurrentHashMap.html):
- [Map](https://docs.oracle.com/javase/8/docs/api/java/util/Map.html) имеет схожий с [hashmap](https://docs.oracle.com/javase/8/docs/api/java/util/HashMap.html) интерфейс взаимодействия;
- Операции чтения не требуют блокировок и выполняются параллельно;
- Операции записи зачастую также могут выполняться параллельно без блокировок;
- При создании указывается требуемый concurrencyLevel, определяемый по статистике чтения и записи;
- Элементы Map имеют значение value, объявленное как volatile;

---
**См. доп. материал:** [ConcurrentHashMap in Java](https://www.geeksforgeeks.org/java/concurrenthashmap-in-java/)

---

```
public class ConcurrentHashMap<K,V> extends 
    AbstractMap<K,V> 
      implements ConcurrentMap<K,V>, Serializable`
```

Здесь:
- K — это тип объекта-ключа,
- V — тип объекта-значения.

Конструкторы ConcurrentHashMap:
- Concurrency-Level (уровень параллелизма) — количество потоков, одновременно обновляющих Map. 
Реализация выполняет внутреннюю настройку размера, чтобы обеспечить поддержку заданного количества потоков.
- Коэффициент загрузки (Load-Factor) — это пороговое значение, используемое для управления изменением размера.
- Начальная емкость (Initial Capacity) - количество элементов, на которое изначально рассчитана реализация.
Если емкость данной Map равна 10, это означает, что она может хранить 10 записей.

1. `ConcurrentHashMap()` - Создает новую пустую Map-у с начальным размером таблицы по умолчанию (16), фактором загрузки - 0.75, и уровнем параллелизма - 16.
```
  ConcurrentHashMap<K, V> chm = new ConcurrentHashMap<>();
```

2. `ConcurrentHashMap(int initialCapacity)` - Создает новую пустую Map-у с заданной начальной емкостью - `initialCapacity`, с фактором загрузки - 0.75, и уровнем параллелизма - 16.
```
  ConcurrentHashMap<K, V> chm = new ConcurrentHashMap<>(int initialCapacity);
```

3. `ConcurrentHashMap(int initialCapacity, float loadFactor)` - Создает новую пустую Map-у с заданной начальной емкостью - `initialCapacity`, с фактором загрузки - `loadFactor`, и уровнем параллелизма - 16.
```
  Declaration: ConcurrentHashMap<K, V> chm = new ConcurrentHashMap<>(int initialCapacity, float loadFactor);
```

4. `ConcurrentHashMap(int initialCapacity, float loadFactor, int concurrencyLevel)` - Создает новую пустую Map-у с заданной начальной емкостью - `initialCapacity`, с фактором загрузки - `loadFactor`, и уровнем параллелизма - `concurrencyLevel`.
```
  ConcurrentHashMap<K, V> chm = new ConcurrentHashMap<>(int initialCapacity, float loadFactor, int concurrencyLevel);
```

5. `ConcurrentHashMap(Map m)` - Создает новую Map-у с теми же характеристиками, что и у переданной Map-ы.
```
  Declaration: ConcurrentHashMap<K, V> chm = new ConcurrentHashMap<>(Map m);
```

---
**Доп. материалы:**
- [ConcurrentHashMap.java from GitHub of OpenJDK](https://github.com/openjdk/jdk/blob/master/src/java.base/share/classes/java/util/concurrent/ConcurrentHashMap.java)
- [`Class ConcurrentHashMap<K,V>`](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/ConcurrentHashMap.html)
- [`Class Hashtable<K,V>`](https://docs.oracle.com/javase/8/docs/api/java/util/Hashtable.html)
- [Class Collections](https://docs.oracle.com/javase/8/docs/api/java/util/Collections.html)
- [Class ConcurrentModificationException](https://docs.oracle.com/javase/8/docs/api/java/util/ConcurrentModificationException.html)

--- 
- [ConcurrentHashMap in Java](https://www.geeksforgeeks.org/java/concurrenthashmap-in-java/)
- [A Guide to ConcurrentMap](https://www.baeldung.com/java-concurrent-map)
- [Deep Dive into ConcurrentHashMap](https://www.linkedin.com/pulse/deep-dive-concurrenthashmap-spsoftglobal-gnz0f)
- [How ConcurrentHashMap Works Internally After Java 8?](https://javaconceptoftheday.com/how-concurrenthashmap-works-internally-after-java-8/)
- [ConcurrentHashMap: How It Works and Why It’s So Damn Fast (Also, Where’s My Thread?)](https://medium.com/@shivu.a.1945/concurrenthashmap-how-it-works-and-why-its-so-damn-fast-also-where-s-my-thread-605bccbad7a7)
- [ConcurrentHashMap: Thread Safety and Flexibility in Java](https://www.centron.de/en/tutorials/concurrenthashmap-thread-safety-and-flexibility-in-java)
- [Difference between HashMap and ConcurrentHashMap in Java](https://dev.to/realnamehidden1_61/difference-between-hashmap-and-concurrenthashmap-in-java-5cha)
- [Reading and Writing With a ConcurrentHashMap](https://www.baeldung.com/concurrenthashmap-reading-and-writing)
- [HashMap и ConcurrentHashMap популярные вопросы на собеседованиях](https://javarush.com/groups/posts/713-hashmap-i-concurrenthashmap-populjarnihe-voprosih-na-sobesedovanijakh)

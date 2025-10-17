### ConcurrentHashMap

До появления в JDK 1.5 реализации ConcurrentHashMap, существовало несколько способов
описания хэш-таблиц. Первоначально в JDK 1.0 был клас Hashtable.

Hashtable — потокобезопасная и легкая в использовании реализация хэш-таблицы. Проблема
HashTable заключалась, в первую очередь, в том, что при доступе к элементам таблицы
производилась её полная блокировка.

Все методы Hashtable были синхронизированными. Это являлось серьёзным ограничением для
многопоточной среды, поскольку плата за блокировку всей таблицы резкое падение
быстродействия. В JDK 1.2 на помощь Hashtable пришёл HashMap и его потокобезопасное
представление (view) - Collections.synchronizedMap.

Причин для такого разделения было несколько:
- Не каждый программист и не каждое решение нуждались в использовании потокобезопасной
  хэш-таблицы.
- Программисту необходимо было дать выбор, какой вариант ему удобнее использовать.

Таким образом, c JDK 1.2 список вариантов реализации хэш-карт в Java пополнился ещё
двумя способами. Однако эти способы не избавили разработчиков от появления в их коде
race conditions, которые могли привести к появлению ConcurrentModificationException.

JDK 1.5 предоставила более производительный и масштабируемый ConcurrentHashMap.

К моменту появления ConcurrentHashMap Java-разработчики нуждались в следующей
реализации HashMap:
- потокобезопасность;
- отсутствие блокировок всей таблицы на время доступа к ней;
- желательно, чтобы отсутствовали блокировки таблицы при выполнении операции чтения;

Основные преимущества и особенности реализации ConcurrentHashMap:
- Map имеет схожий с hashmap интерфейс взаимодействия;
- Операции чтения не требуют блокировок и выполняются параллельно;
- Операции записи зачастую также могут выполняться параллельно без блокировок;
- При создании указывается требуемый concurrencyLevel, определяемый по статистике чтения и записи;
- Элементы Map имеют значение value, объявленное как volatile;
---

From https://www.geeksforgeeks.org/concurrenthashmap-in-java/

---
- public class ConcurrentHashMap<K,V> extends AbstractMap<K,V> implements ConcurrentMap<K,V>, Serializable
Here, K is the key Object type and V is the value Object type.

Constructors of ConcurrentHashMap:
- Concurrency-Level: It is the number of threads concurrently updating the map.
  The implementation performs internal sizing to try to accommodate this many threads.
- Load-Factor: It’s a threshold, used to control resizing.
- Initial Capacity: Accommodation of a certain number of elements initially provided
  by the implementation. if the capacity of this map is 10. It means that it can store
  10 entries.

1. **ConcurrentHashMap()** - Creates a new, empty map with a default initial capacity (16),
   load factor (0.75) and concurrencyLevel (16).

Declaration: ConcurrentHashMap<K, V> chm = new ConcurrentHashMap<>();

2. **ConcurrentHashMap(int initialCapacity)** - Creates a new, empty map with the specified
   initial capacity, and with default load factor (0.75) and concurrencyLevel (16).

Declaration: ConcurrentHashMap<K, V> chm = new ConcurrentHashMap<>(int initialCapacity);

3. **ConcurrentHashMap(int initialCapacity, float loadFactor)** - Creates a new, empty map with the
   specified initial capacity and load factor and with the default concurrencyLevel (16).

Declaration: ConcurrentHashMap<K, V> chm = new ConcurrentHashMap<>(int initialCapacity, float loadFactor);

4. **ConcurrentHashMap(int initialCapacity, float loadFactor, int concurrencyLevel)** - Creates a new, empty
   map with the specified initial capacity, load factor, and concurrency level.

Declaration: ConcurrentHashMap<K, V> chm = new ConcurrentHashMap<>(int initialCapacity, float loadFactor, int concurrencyLevel);

5. **ConcurrentHashMap(Map m)** - Creates a new map with the same mappings as the given map.

Declaration: ConcurrentHashMap<K, V> chm = new ConcurrentHashMap<>(Map m);

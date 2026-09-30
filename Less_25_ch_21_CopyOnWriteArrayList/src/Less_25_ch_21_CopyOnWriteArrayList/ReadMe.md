См. оригинал (ENG): [`Class CopyOnWriteArrayList<E>`](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/CopyOnWriteArrayList.html)

---
### CopyOnWriteArrayList

```java
        public class CopyOnWriteArrayList<E>
            implements List<E>, RandomAccess, Cloneable, java.io.Serializable
```

**CopyOnWriteArrayList - потокобезопасный вариант коллекции ArrayList**. Как и ArrayList, CopyOnWriteArray управляет массивом для хранения его элементов. 
Разница в том, что все мутативные операции, такие как: `add`, `set`, `remove`, `clear` и т.д. создают новую копию массива, которым он управляет.

Стоимость использования [CopyOnWriteArrayList](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/CopyOnWriteArrayList.html) очень высока, нам 
придется платить больше за ресурс и производительность. Однако CopyOnWriteArrayList пригодится, когда мы не можем или не хотим синхронизировать при обходе 
(traversal) элементов списка.

Для лучшего понимания рассмотрим ситуацию, когда два Thread используют один и тот же объектList. В то время как Thread-A обходит (traversal) элементы List, 
он замораживает действия вставки или обновления в List для целостности данных, но, очевидно, это влияет на действия Thread-B.

Когда мы создаем объект [Iterator](https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html) из CopyOnWriteArrayList, он проходит через элементы 
текущего массива (когда был создан Iterator). Элементы этого массива не изменяются за время существования Iterator.

Это возможно потому, что любые действия: `add`, `set`, `remove`, `clear` и т.д. на CopyOnWriteArrayList создают другой массив, который является копией 
текущего массива.

**!!!! Действия изменения элементов на самом Iterator (add, set, remove) не поддерживаются. Эти методы выбросают [UnsupportedOperationException](https://docs.oracle.com/javase/8/docs/api/java/lang/UnsupportedOperationException.html) !!!**

**!!! ПОВТОРИМ !!! Как следует из названия, CopyOnWriteArrayList создает клонированную внутреннюю копию базового ArrayList для каждой операции `add()` или 
`set()`. Из-за этих дополнительных накладных расходов в идеале мы должны использовать CopyOnWriteArrayList только тогда, когда у нас очень частые операции 
чтения, а не много вставок или обновлений.**

---
```java
public class CopyOnWriteArrayList<E>
        extends Object
                implements List<E>, RandomAccess, Cloneable, Serializable
```

**Параметры типа:** E — тип элементов, хранящихся в этой коллекции.

**Все реализованные интерфейсы:**
- [Serializable](https://docs.oracle.com/javase/8/docs/api/java/io/Serializable.html),
- [Cloneable](https://docs.oracle.com/javase/8/docs/api/java/lang/Cloneable.html),
- [`Iterable<E>`](https://docs.oracle.com/javase/8/docs/api/java/lang/Iterable.html),
- [`Collection<E>`](https://docs.oracle.com/javase/8/docs/api/java/util/Collection.html),
- [`List<E>`](https://docs.oracle.com/javase/8/docs/api/java/util/List.html),
- [RandomAccess](https://docs.oracle.com/javase/8/docs/api/java/util/RandomAccess.html)

Мы можем использовать один из следующих конструкторов для создания CopyOnWriteArrayList :
- `CopyOnWriteArrayList()` - создает пустой список;
- `CopyOnWriteArrayList(Collection c)` - создает список, инициализированный со всеми элементами переданными в 'c';
- `CopyOnWriteArrayList(Object[] obj)` - создает список, содержащий копию данного массива obj.

---
#### Методы CopyOnWriteArrayList:

- `add(E e)` - Добавляет указанный элемент в конец этого списка.
- `add(int index, E element)` - Вставляет указанный элемент в указанную позицию этого списка.
- `addAll(Collection<? extends E> c)` - Добавляет все элементы из указанной коллекции в конец данного списка в том порядке, в котором они возвращаются итератором этой коллекции.
- `addAll(int index, Collection<? extends E> c)` - Вставляет все элементы из указанной коллекции в этот список, начиная с указанной позиции.
- `addAllAbsent(Collection<? extends E> c)` - Добавляет в конец данного списка все элементы из указанной коллекции, которых еще нет в этом списке, в порядке их возврата итератором указанной коллекции.
- `addIfAbsent(E e)` - Добавляет элемент, если его еще нет.
- `clear()` - Удаляет все элементы из этого списка.
- `clone()` - Возвращает поверхностную копию этого списка.
- `contains(Object o)` - Возвращает значение true, если этот список содержит указанный элемент.
- `containsAll(Collection<?> c)` - Возвращает значение true, если этот список содержит все элементы указанной коллекции.
- `equals(Object o)` - Сравнивает указанный объект с данным списком на предмет равенства.
- `forEach(Consumer<? super E> action)` - Выполняет заданное действие для каждого элемента объекта Iterable, пока не будут обработаны все элементы или пока действие не вызовет исключение.
- `get(int index)` - Возвращает элемент, находящийся на указанной позиции в этом списке.
- `hashCode()` - Возвращает значение хеш-кода для этого списка.
- `indexOf(E e, int index)` - Возвращает индекс первого вхождения указанного элемента в этот список при поиске в прямом направлении, начиная с заданного индекса, или -1, если элемент не найден.
- `indexOf(Object o)` - Возвращает индекс первого вхождения указанного элемента в этот список или -1, если список не содержит этого элемента.
- `isEmpty()` - Возвращает true, если этот список не содержит элементов.
- `iterator()` - Возвращает итератор по элементам этого списка в надлежащем порядке.
- `lastIndexOf(E e, int index)` - Возвращает индекс последнего вхождения указанного элемента в этот список, выполняя поиск в обратном направлении от заданного индекса, или -1, если элемент не найден.
- `lastIndexOf(Object o)` - Возвращает индекс последнего вхождения указанного элемента в этот список или -1, если список не содержит этого элемента.
- `listIterator()` - Возвращает итератор списка для элементов этого списка (в надлежащем порядке).
- `listIterator(int index)` - Возвращает итератор списка по элементам этого списка (в надлежащем порядке), начиная с указанной позиции в списке.
- `remove(int index)` - Удаляет элемент, находящийся на указанной позиции в этом списке.
- `remove(Object o)` - Удаляет первое вхождение указанного элемента из этого списка, если он в нем присутствует.
- `removeAll(Collection<?> c)` - Удаляет из этого списка все элементы, содержащиеся в указанной коллекции.
- `removeIf(Predicate<? super E> filter)` - Удаляет из этой коллекции все элементы, удовлетворяющие заданному предикату.
- `replaceAll(UnaryOperator<E> operator)` - Заменяет каждый элемент этого списка результатом применения оператора к этому элементу.
- `retainAll(Collection<?> c)` - Оставляет в этом списке только те элементы, которые содержатся в указанной коллекции.
- `set(int index, E element)` - Заменяет элемент на указанной позиции в этом списке на указанный элемент.
- `size()` - Возвращает количество элементов в этом списке.
- `sort(Comparator<? super E> c)` - Сортирует этот список в соответствии с порядком, заданным указанным компаратором.
- `spliterator()` - Возвращает Spliterator для элементов этого списка.
- `subList(int fromIndex, int toIndex)` - Возвращает представление части этого списка в диапазоне от `fromIndex` (включительно) до `toIndex` (не включая).
- `toArray()` - Возвращает массив, содержащий все элементы этого списка в надлежащем порядке (от первого до последнего элемента).
- `toArray(T[] a)` - Возвращает массив, содержащий все элементы этого списка в надлежащем порядке (от первого до последнего); тип возвращаемого массива совпадает с типом указанного массива.
- `toString()` - Возвращает строковое представление этого списка.

---
- Методы унаследованные от класса [java.lang.Object](https://docs.oracle.com/javase/8/docs/api/java/lang/Object.html): [finalize](https://docs.oracle.com/javase/8/docs/api/java/lang/Object.html#finalize--), [getClass](https://docs.oracle.com/javase/8/docs/api/java/lang/Object.html#getClass--), [notify](https://docs.oracle.com/javase/8/docs/api/java/lang/Object.html#notify--), [notifyAll](https://docs.oracle.com/javase/8/docs/api/java/lang/Object.html#notifyAll--), [wait](https://docs.oracle.com/javase/8/docs/api/java/lang/Object.html#wait--), [wait](https://docs.oracle.com/javase/8/docs/api/java/lang/Object.html#wait-long-), [wait](https://docs.oracle.com/javase/8/docs/api/java/lang/Object.html#wait-long-int-)
- Методы унаследованные от интерфейса [java.util.Collection](https://docs.oracle.com/javase/8/docs/api/java/util/Collection.html): [parallelStream](https://docs.oracle.com/javase/8/docs/api/java/util/Collection.html#parallelStream--), [stream](https://docs.oracle.com/javase/8/docs/api/java/util/Collection.html#stream--)

---
**Доп. материалы:**
- [`Class CopyOnWriteArrayList<E>`](https://docs.oracle.com/en/java/javase/26/docs/api/java.base/java/util/concurrent/CopyOnWriteArrayList.html)
- [CopyOnWriteArrayList.java from GitHub of OpenJDK Mirror](https://github.com/openjdk-mirror/jdk7u-jdk/blob/master/src/share/classes/java/util/concurrent/CopyOnWriteArrayList.java)
- [CopyOnWriteArrayList in Java](https://www.geeksforgeeks.org/java/copyonwritearraylist-in-java/)
- [Guide to CopyOnWriteArrayList](https://www.baeldung.com/java-copy-on-write-arraylist)
- [CopyOnWriteArrayList (from Java Training School)](https://javatrainingschool.com/copyonwritearraylist-and-copyonwritearrayset/)
- [The CopyOnWriteArrayList Internals: A Deep Dive](https://medium.com/@reetesh043/the-copyonwritearraylist-internals-a-deep-dive-ff3ebad87697)
- [CopyOnWriteArrayList & ConcurrentHashMap in Java](https://blog.devgenius.io/copyonwritearraylist-concurrenthashmap-in-java-ac52bdcdc433)
- [CopyOnWriteArrayList](https://docs.telusko.com/docs/java/collection-internals/copyonwritearraylist)
- [CopyOnWriteArrayList and Collection.toArray()](https://www.javaspecialists.eu/archive/Issue290-CopyOnWriteArrayList-and-Collection.toArray.html)
- [CopyOnWriteArrayList listIterator() method in Java](https://www.geeksforgeeks.org/java/copyonwritearraylist-listiterator-method-in-java/)
- [CopyOnWriteArrayList In Java](https://www.javacodegeeks.com/2019/03/copyonwritearraylist-java.html)
- [CopyOnWriteArrayList as thread-safe ArrayList](https://www.waitingforcode.com/java-concurrency/copyonwritearraylist-as-thread-safe-arraylist/read)
- [CopyOnWriteArrayList set() method in Java with Examples](https://www.geeksforgeeks.org/java/copyonwritearraylist-set-method-in-java-with-examples/)
- [Java CopyOnWriteArrayList Tutorial with Examples](https://o7planning.org/13641/java-copyonwritearraylist)
- [Understanding and Using Java’s CopyOnWriteArrayList](https://ankurm.com/understanding-and-using-javas-copyonwritearraylist/)

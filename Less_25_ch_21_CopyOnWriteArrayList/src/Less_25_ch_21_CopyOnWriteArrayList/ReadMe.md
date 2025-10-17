### CopyOnWriteArrayList

        public class CopyOnWriteArrayList<E>
            implements List<E>, RandomAccess, Cloneable, java.io.Serializable

CopyOnWriteArrayList - это потокобезопасный вариант of ArrayList. Как и ArrayList,
CopyOnWriteArray управляет массивом для хранения его элементов. Разница в том, что
все мутативные операции, такие как add, set, remove, clear,... создают новую копию
массива, которым он управляет.

Стоимость использования CopyOnWriteArrayList очень высока, нам придется платить больше
за ресурс и производительность. Однако CopyOnWriteArrayList пригодится, когда мы не можем
или не хотим синхронизировать при обходе (traversal) элементов списка.

Для лучшего понимания рассмотрим ситуацию, когда два Thread используют один и тот же объект
List. В то время как Thread-A обходит (traversal) элементы List, он замораживает действия
вставки или обновления в List для целостности данных, но, очевидно, это влияет на действия
Thread-B.

Когда мы создаем объект Iterator из CopyOnWriteArrayList, он проходит через элементы текущего
массива (когда был создан Iterator). Элементы этого массива не изменяются за время существования
Iterator.

Это возможно потому, что любые действия add, set, remove, clear, .. на CopyOnWriteArrayList создают
другой массив, который является копией текущего массива.

        !!!! Действия изменения элементов на самом Iterator (add, set, remove) не поддерживаются.
        Эти методы выбросают UnsupportedOperationException !!!

        !!! ПОВТОРИМ !!!
        Как следует из названия, CopyOnWriteArrayList создает клонированную внутреннюю копию базового
        ArrayList для каждой операции add () или set () . Из-за этих дополнительных накладных расходов
        в идеале мы должны использовать CopyOnWriteArrayList только тогда, когда у нас очень частые
        операции чтения, а не много вставок или обновлений.

Мы можем использовать один из следующих конструкторов для создания CopyOnWriteArrayList :
- CopyOnWriteArrayList (): создает пустой список
- opyOnWriteArrayList (Collection 'c'): создает список, инициализированный со всеми элементами в 'c'
- CopyOnWriteArrayList (Object [] obj): создает список, содержащий копию данного массива obj
---

From https://www.geeksforgeeks.org/copyonwritearraylist-in-java/

---
Methods of CopyOnWriteArrayList:

- add(E e) - Appends the specified element to the end of this list.
- add(int index, E element) - Inserts the specified element at the specified position in this list.
- addAll(Collection<? extends E> c) - Appends all of the elements in the specified collection to the
  end of this list, in the order that they are returned by the specified collection’s iterator.

- addAll(int index, Collection<? extends E> c) - Inserts all of the elements in the specified collection
  into this list, starting at the specified position.

- addAllAbsent(Collection<? extends E> c) - Appends all of the elements in the specified collection that
  are not already contained in this list, to the end of this list, in the order that they are returned by
  the specified collection’s iterator.

- addIfAbsent(E e) - Appends the element, if not present.
- clear() - Removes all of the elements from this list.
- clone() - Returns a shallow copy of this list.
- contains(Object o) - Returns true if this list contains the specified element.
- containsAll(Collection<?> c) - Returns true if this list contains all of the elements of the
  specified collection.

- equals(Object o) - Compares the specified object with this list for equality.
- forEach(Consumer<? super E> action) - Performs the given action for each element of the Iterable
  until all elements have been processed or the action throws an exception.
- get(int index) - Returns the element at the specified position in this list.
- hashCode() - Returns the hash code value for this list.
- indexOf(E e, int index) - Returns the index of the first occurrence of the specified element in
  this list, searching forwards from the index, or returns -1 if the element is not found.

- indexOf(Object o) - Returns the index of the first occurrence of the specified element in this
  list, or -1 if this list does not contain the element.

- isEmpty() - Returns true if this list contains no elements.
- iterator() - Returns an iterator over the elements in this list in the proper sequence.
- lastIndexOf(E e, int index) - Returns the index of the last occurrence of the specified element
  in this list, searching backward from the index, or returns -1 if the element is not found.

- lastIndexOf(Object o)	- Returns the index of the last occurrence of the specified element in this
  list, or -1 if this list does not contain the element.

- listIterator() - Returns a list iterator over the elements in this list (in proper sequence).
- listIterator(int index) - Returns a list iterator over the elements in this list (in proper sequence),
  starting at the specified position in the list.

- remove(int index) - Removes the element at the specified position in this list.
- remove(Object o) - Removes the first occurrence of the specified element from this list, if it is present.
- removeAll(Collection<?> c) - Removes from this list all of its elements that are contained in the
  specified collection.

- removeIf(Predicate<? super E> filter)	Removes all of the elements of this collection that satisfy
  the given predicate.

- replaceAll(UnaryOperator<E> operator) - Replaces each element of this list with the result of applying
  the operator to that element.

- retainAll(Collection<?> c) - Retains only the elements in this list that are contained in the specified
  collection.

- set(int index, E element) - Replaces the element at the specified position in this list with the
  specified element.

- size() - Returns the number of elements in this list.
- sort(Comparator<? super E> c) - Sorts this list according to the order induced by the specified Comparator.
- spliterator() - Returns a Spliterator over the elements in this list.
- subList(int fromIndex, int toIndex) - Returns a view of the portion of this list between fromIndex,
  inclusive, and toIndex, exclusive.

- toArray() - Returns an array containing all of the elements in this list in proper sequence
  (from first to the last element).

- toArray(T[] a) - Returns an array containing all of the elements in this list in proper sequence
  (from first to the last element); the runtime type of the returned array is that of the specified array.

- toString() - Returns a string representation of this list.

См. док. (ORACLE ENG):
- [Declaring a Variable Volatile](https://docs.oracle.com/cd/E19253-01/816-4854/codingpractices-1/index.html)
- [Threads and Locks](https://docs.oracle.com/javase/specs/jls/se8/html/jls-17.html)

---
### Модификатор volatile

В компьютере есть два вида памяти – глобальная (обычная) и встроенная в процессор.

Встроенная в процессор делится на регистры, затем кэш первого уровня (L1), кэш второго уровня (L2) и третьего уровня (L3).

Эти виды памяти отличаются по скорости работы. Самая быстрая и самая маленькая память – это регистры, затем идет 
кэш процессора (L1, L2, L3) и, наконец, глобальная память (самая медленная).

Скорость работы глобальной памяти и кэша процессора сильно отличаются, поэтому Java-машина позволяет каждому 
потоку - thread-у хранить самые часто используемые переменные в локальной памяти потока - в кэше процессора.

И процессом распределения памяти и обращения к ней, мы практически не управляем – всю работу делает Java-машина, 
максимально оптимизируя скорость работы.

**И тут возникает проблема:**

Когда два потока работают с одной и той же переменной, каждый из них может сохранить ее копию в своем внутреннем 
локальном кэше. И тогда может получиться ситуация, когда один поток меняет важную переменную, а второй поток не 
видит этого изменения, т.к. по-прежнему работает со своей копией этой переменной.

Чтобы избежать подобного казуса в Java существует специальный модификатор – volatile.

**Если есть переменная, к которой обращаются из разных потоков, ее необходимо пометить модификатором volatile, 
чтобы Java-машина не помещала ее в кэш.**

```java
    public volatile int count = 0;
```

Однако модификатор volatile гарантирует только потокобезопасное чтение/запись переменной, но не потокобезопасное 
ее изменение.

Например:
- Этап 1: Значение переменной 'Х' копируется из глобальной памяти в регистровую память процессора CPU_1.
- Этап 2: Внутри памяти процессора CPU_1 переменная 'Х' изменяется, например, увеличивается на 1.
- Этап 3: Значение переменной копируется из кэша CPU_1 процессора в глобальную память.
(но есть еще поток и CPU_2, который может делать нечто похожее с той же переменной)

Модификатор volatile, гарантирует, что при обращении к переменной она будет прочитана из памяти (этап 1).
А если какой-то поток захочет присвоить ей новое значение, то оно обязательно окажется в глобальной памяти (этап 3).
**Но Java-машина не гарантирует, что не будет переключения потоков между этапами 1 и 3, или потоки не
изменят переменную одновременно, т.е. помешают друг другу.**

**В идеале, в программе должен существовать только один поток, который меняет значение volatile переменной,
и множество других потоков, которые эту переменную только читают.**

---
**Доп. материал:**
- [Guide to the Volatile Keyword in Java](https://www.baeldung.com/java-volatile)
- [What is the volatile keyword useful for?](https://stackoverflow.com/questions/106591/what-is-the-volatile-keyword-useful-for)
- [Java Volatile Keyword (from jenkov.com)](https://jenkov.com/tutorials/java-concurrency/volatile.html)
- [Volatile Keyword in Java](https://www.geeksforgeeks.org/java/volatile-keyword-in-java/)
- [Volatile Keyword in Java Explained](https://builtin.com/articles/volatile-keyword-in-java)
- [Understanding volatile in Java and Why it’s NOT a replacement for Synchronization](https://medium.com/@sachinkg12/understanding-volatile-in-java-and-why-its-not-a-replacement-for-synchronization-27927edd7b52)
- [Understanding Java's Volatile Keyword](https://wearecommunity.io/communities/java_americas/articles/7032)
- [Volatile Keyword in Java: Usage & Examples](https://www.igmguru.com/blog/volatile-keyword-in-java)
- [Volatile vs. Atomic Variables in Java](https://www.baeldung.com/java-volatile-vs-atomic)
- [The volatile keyword in Java](https://www.javamex.com/tutorials/synchronization_volatile.shtml)
- [Java Concurrency: Understanding the ‘Volatile’ Keyword](https://dzone.com/articles/java-concurrency-understanding-the-volatile-keyword)
- [3 Tips for volatile fields in java](https://vmlens.com/articles/3_tips_volatile_fields/)
- [Volatile Variables and Thread Safety](https://www.baeldung.com/java-volatile-variables-thread-safety)
- [Volatile (computer programming)](https://en.wikipedia.org/wiki/Volatile_(computer_programming))

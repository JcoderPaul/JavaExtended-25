- **См. оригинал (ENG):** [`Class ThreadLocal<T>`](https://docs.oracle.com/javase/8/docs/api/java/lang/ThreadLocal.html)

---
### Class ThreadLocal

```java
  public class ThreadLocal<T>
      extends Object
```

**Пакет:** [java.lang](https://docs.oracle.com/javase/8/docs/api/java/lang/package-summary.html)

**Прямой подкласс:** [`Class InheritableThreadLocal<T>`](https://docs.oracle.com/javase/8/docs/api/java/lang/InheritableThreadLocal.html)

Данный класс предоставляет локальные переменные потока. Эти переменные отличаются от своих обычных аналогов тем, что каждый поток, обращающийся
к ним (через свой метод get или set), имеет свою собственную, независимо инициализированную копию переменной.

Экземпляры ThreadLocal обычно представляют собой закрытые статические поля в классах, которые хотят связать состояние с потоком (например, 
идентификатор пользователя или идентификатор транзакции).

Например, приведенный ниже класс генерирует уникальные идентификаторы, локальные для каждого потока. Идентификатор потока назначается при первом
вызове ThreadId.get() и остается неизменным при последующих вызовах:

---

```java
  import java.util.concurrent.atomic.AtomicInteger;
  
   public class ThreadId {
       /*
       Атомарное целое число, содержащее идентификатор следующего потока,
       который будет назначен.
       */
       private static final AtomicInteger nextId = new AtomicInteger(0);
  
       // Локальная переменная потока, содержащая идентификатор каждого потока
       private static final ThreadLocal<Integer> threadId =
           new ThreadLocal<Integer>()
           {
               @Override
               protected Integer initialValue()
               {
                   return nextId.getAndIncrement();
               }
           };
  
       /*
       Возвращает уникальный идентификатор текущего
       потока, присваивая его при необходимости
       */
       public static int get() {
           return threadId.get();
       }
   }
```

---
Каждый поток содержит неявную ссылку на свою копию локальной переменной потока, пока поток жив и экземпляр ThreadLocal доступен; после того, как поток уходит,
все его копии локальных экземпляров потока подлежат сборке мусора (если не существуют другие ссылки на эти копии).

**Конструктор:** [`public ThreadLocal()`](https://docs.oracle.com/javase/8/docs/api/java/lang/ThreadLocal.html#ThreadLocal--) - Создает локальную переменную потока.

---
#### МЕТОДЫ:

- `protected T initialValue()` - Возвращает "начальное значение" текущего потока для этой локальной переменной потока. Этот метод будет вызываться при первом
доступе потока к переменной с помощью метода get(), если поток ранее не вызывал метод `set(T)`, и в этом случае метод `initialValue` не будет вызываться для
потока. Обычно этот метод вызывается не более одного раза для каждого потока, но он может вызываться снова в случае последующих вызовов функции `remove()`, за
которой следует `get()`.

Эта реализация просто возвращает `null`; если программист хочет, чтобы локальные переменные потока имели начальное значение, отличное от нуля, ThreadLocal должен 
быть подклассом, а этот метод переопределен. Обычно используется анонимный внутренний класс.

**Возвращает:** начальное значение для этого локального потока

- `public T get()` - Возвращает значение в копии текущего потока этой локальной переменной потока. Если переменная не имеет значения для текущего потока, она
сначала инициализируется значением, возвращаемым вызовом метода `initialValue()`.

**Возвращает:** текущее значение потока этого локального потока

- `public void set(T value)` - Устанавливает для копии текущего потока эту локальную переменную потока указанное значение. Большинству подклассов не нужно будет
переопределять этот метод, полагаясь исключительно на метод `initialValue()` для установки значений локальных переменных потока.

**Параметры:** `value` - значение, которое будет сохранено в текущей копии потока этого thread-local.

- `public void remove()` - Удаляет значение текущего потока для этой локальной переменной потока. Если эта локальная переменная потока впоследствии будет прочитана
текущим потоком, ее значение будет повторно инициализировано путем вызова его метода `initialValue()`, если только ее значение не будет установлено текущим потоком
в промежутке времени. Это может привести к многократному вызову метода `initialValue` в текущем потоке.

---
См. [`Class ThreadLocal<T>`](https://docs.oracle.com/javase/8/docs/api/java/lang/ThreadLocal.html)

---
**Доп. материал:**
- [Thread-Local Variables](https://docs.oracle.com/en/java/javase/21/core/thread-local-variables.html#GUID-2CEB9041-3DF7-43DA-868F-E0596F4B63FD)
- [ThreadLocal.java (from GitHub by OpenJDK)](https://github.com/openjdk/jdk/blob/master/src/java.base/share/classes/java/lang/ThreadLocal.java)
- [`Class ThreadLocal<T>` (from ORACLE Doc Java 17)](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/ThreadLocal.html)
- [An Introduction to ThreadLocal in Java](https://www.baeldung.com/java-threadlocal)
- [Understanding ThreadLocal vs Thread in Java](https://medium.com/@sachinkg12/understanding-threadlocal-vs-thread-in-java-a908b5390207)
- [Java.lang.ThreadLocal Class in Java](https://www.geeksforgeeks.org/java/java-lang-threadlocal-class-java/)
- [Java ThreadLocal](https://jenkov.com/tutorials/java-concurrency/threadlocal.html)
- [Mastering Thread-Local Variables in Java: Explanation and Issues](https://dzone.com/articles/mastering-thread-local-variables-in-java-a-compreh)
- [ThreadLocal and InheritableThreadLocal in Java: The Complete Guide](https://medium.com/@alxkm/threadlocal-and-inheritablethreadlocal-in-java-the-complete-guide-4acb7907f2f0)
- [What is the Use and need of thread local](https://stackoverflow.com/questions/17008906/what-is-the-use-and-need-of-thread-local)
- [Java ThreadLocal Example](https://www.digitalocean.com/community/tutorials/java-threadlocal-example)
- [Java ThreadLocal Example](https://mangohost.net/blog/java-threadlocal-example/)
- [Java ThreadLocal set() Method](https://www.tutorialspoint.com/java/lang/threadlocal_set.htm)
- [Understanding ThreadLocal in Java: When and Why to Use It](https://deepanshutiw03.medium.com/understanding-threadlocal-in-java-when-and-why-to-use-it-2c8cb75a22ce)
- [ThreadLocal Java Example](https://javatutorial.net/threadlocal-java-example/)
- [ThreadLocal и проблемы с памятью: что вы должны знать](https://habr.com/ru/companies/otus/articles/849796/)

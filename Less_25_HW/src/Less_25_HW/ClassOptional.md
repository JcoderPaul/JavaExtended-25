- **См. исходную статью (ENG): [Class Optional<T>](https://docs.oracle.com/javase/8/docs/api/java/util/Optional.html)**

---
### Class Optional - Класс Optional

```java
  public final class Optional<T> extends Object
```

Объект-контейнер, который может содержать или не содержать ненулевое значение. 
Если значение присутствует, `isPresent()` вернет - `true`, а `get()` вернет значение.

Предоставляются дополнительные методы, которые зависят от наличия или отсутствия содержащегося 
значения, такие как `orElse()` - возвращает значение по умолчанию, если значение отсутствует 
и `ifPresent()` - выполняет блок кода, если значение присутствует.

Использование операций с идентификацией, включая ссылочное равенство `==`, идентификационный 
хэш-код или синхронизацию в экземплярах Optional может привести к непредсказуемым результатам, 
и его следует избегать.

---
#### МЕТОДЫ

- `static <T> Optional<T> empty()` - Возвращает пустой необязательный экземпляр.
- `boolean equals(Object obj)` - Указывает, является ли какой-либо другой объект «равным» этому необязательному.
- `Optional<T> filter(Predicate<? super T> predicate)` - Если значение присутствует, и значение соответствует заданному предикату, верните необязательный элемент, описывающий значение, в противном случае верните пустой необязательный элемент.
- `<U> Optional<U> flatMap(Function<? super T,Optional<U>> mapper)` - Если значение присутствует, примените к нему предоставленную функцию сопоставления с факультативом, верните этот результат, в противном случае верните пустое факультативное значение.
- `T get()` - Если значение присутствует в этом необязательном, возвращает значение, в противном случае генерирует исключение NoSuchElementException.
- `int hashCode()` - Возвращает значение хэш-кода текущего значения, если оно есть, или 0 (ноль), если значение отсутствует.
- `void ifPresent(Consumer<? super T> consumer)` - Если значение присутствует, вызовите указанного потребителя со значением, в противном случае ничего не делайте.
- `boolean isPresent()` - Возвращает true, если присутствует значение, иначе false.
- `<U> Optional<U> map(Function<? super T,? extends U> mapper)` - Если значение присутствует, примените к нему предоставленную функцию сопоставления, и если результат не равен нулю, верните необязательный параметр, описывающий результат.
- `static <T> Optional<T> of(T value)` - Возвращает необязательный элемент с указанным текущим ненулевым значением.
- `static <T> Optional<T> ofNullable(T value)` - Возвращает необязательный элемент, описывающий указанное значение, если он не равен нулю, в противном случае возвращает пустой необязательный элемент.
- `T orElse(T other)` - Вернуть значение, если оно присутствует, иначе вернуть другое.
- `T orElseGet(Supplier<? extends T> other)` - Верните значение, если оно присутствует, в противном случае вызовите другое и верните результат этого вызова.
- `<X extends Throwable> T orElseThrow(Supplier<? extends X> exceptionSupplier)` - Верните содержащееся значение, если оно присутствует, в противном случае создайте исключение, которое будет создано предоставленным поставщиком.
- `String toString()` - Возвращает непустое строковое представление этого опционала, пригодное для отладки.

---
- **Более подробно см. [Class Optional<T>](https://docs.oracle.com/javase/8/docs/api/java/util/Optional.html)**

---
**Доп. материал:**
- [Class Optional<T> (from ORACLE Java 21)](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/Optional.html)
- [Optional Class in Java 8 – A Comprehensive Tutorial](https://javatechonline.com/optional-class-in-java-8-optional/)
- [Complete Java Optional Guide with Best Practices (from gist.github.com)](https://gist.github.com/carefree-ladka/cc6426829c8865a45f727db7368e94fe)
- [Guide To Java Optional](https://www.baeldung.com/java-optional)
- [Java 8 Optional Class](https://www.geeksforgeeks.org/java/java-8-optional-class/)
- [Uses for Optional (from stackoverflow.com)](https://stackoverflow.com/questions/23454952/uses-for-optional)
- [Optional in Java: Best Practices for Safer Code. Java Interview](https://medium.com/@alxkm/optional-in-java-best-practices-for-safer-code-3f4a3b80e122)
- [Java Optional](https://hyperskill.org/university/java/java-optional)
- [Java - Optional Class](https://www.tutorialspoint.com/java/java_optional_class.htm)
- [Optional Class in Java 8: Making Your Code More Clear and Concise](https://www.linkedin.com/pulse/optional-class-java-8-making-your-code-more-clear-amit-s8jhc)
- [Java 8 Optional Class](https://code.likeagirl.io/java-8-optional-class-cde70f787c35)
- [Java 8 Optional Class with Examples](https://www.javaguides.net/2018/07/java-8-optional-class.html)

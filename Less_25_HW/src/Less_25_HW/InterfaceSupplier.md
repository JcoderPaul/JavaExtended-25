- См. оригинал (ENG): [`Interface Supplier<T>`](https://docs.oracle.com/javase/8/docs/api/java/util/function/Supplier.html)

---
### Interface Supplier - Интерфейс Supplier

```java
           @FunctionalInterface
           public interface Supplier<T>
```
- **Параметры типа:** T - тип результатов, предоставляемых текущим поставщиком.
- **Интерфейс:** Функциональный интерфейс, поэтому его можно использовать в качестве цели назначения для лямбда-выражения или ссылки на метод (method reference).

`Supplier<T>` не принимает никаких аргументов, но должен возвращать объект типа `<T>`

Интерфейс-репрезентация поставщика результатов. Не имеет требования, чтобы каждый раз при вызове поставщика возвращался
новый или некий особый результат.

Метод [функционального интерфейса](https://docs.oracle.com/javase/8/docs/api/java/util/function/package-summary.html) `Supplier<T>` - `*.get()` - получает результат.

**Пример:**
```java
           String t = "One";
           Supplier<String> supplierStr = () -> t.toUpperCase();
           System.out.println(supplierStr.get());
           // На экран: ONE
```

**Supplier — это простой интерфейс, указывающий, что данная реализация является поставщиком какого-то результата.**
Повторим, данный интерфейс, не накладывает никаких дополнительных ограничений, которые реализация поставщика 
должна возвращать при каждом новом получении результата.

У поставщика есть только один метод get() и нет никаких других методов по умолчанию или статических методов.

**Пример:**
```java
           public void supplier() {
               Supplier<Double> doubleSupplier1 = () -> Math.random();
               DoubleSupplier doubleSupplier2 = Math::random;
           
               System.out.println(doubleSupplier1.get());
               System.out.println(doubleSupplier2.getAsDouble());
           }
```

Интерфейс Supplier имеет свои примитивные варианты, такие как: 
- [IntSupplier](https://docs.oracle.com/javase/8/docs/api/java/util/function/IntSupplier.html), 
- [DoubleSupplier](https://docs.oracle.com/javase/8/docs/api/java/util/function/DoubleSupplier.html),
и т.д.

Обратите внимание, что имя метода - `get()` используется для универсального интерфейса поставщика. 
Однако для примитивных вариантов этот метод соответствует примитивному типу:
- IntSupplier → int getAsInt();
- DoubleSupplier → double getAsDouble();
- LongSupplier → long getAsLong();
- BooleanSupplier → boolean getAsBoolean();

Одно из основных применений этого интерфейса это использование для включения отложенного выполнения. 
Это означает отсрочку выполнения до тех пор, пока оно не понадобится.

Например, в классе Optional есть метод orElseGet. Этот метод срабатывает, если у option нет данных.

**Например:**
```java
           public void supplierWithOptional() {
               Supplier<Double> doubleSupplier = () -> Math.random();
               Optional<Double> optionalDouble = Optional.empty();
               System.out.println(optionalDouble.orElseGet(doubleSupplier));
           }
```

---
**См. исходник статьи (ENG):** [`Interface Supplier<T>`](https://docs.oracle.com/javase/8/docs/api/java/util/function/Supplier.html)

---
**Доп. материалы:**
- [Supplier Interface in Java with Examples](https://www.geeksforgeeks.org/java/supplier-interface-in-java-with-examples/)
- [When and why would you use Java's Supplier and Consumer interfaces?](https://stackoverflow.com/questions/28417262/when-and-why-would-you-use-javas-supplier-and-consumer-interfaces)
- [Supplier<T> in JAVA!](https://medium.com/java-today/supplier-t-in-java-f9c9d0537c43)
- [Java Supplier](https://zetcode.com/java/supplier/)
- [When we should use Supplier in Java 8?](https://stackoverflow.com/questions/40244571/when-we-should-use-supplier-in-java-8)
- [Java Supplier Interface Example](https://www.javaguides.net/2020/04/java-8-supplier-interface-example.html)
- [Supplier Functional Interface in Java](https://www.examclouds.com/java/java-core/java-util-function-supplier)
- [Functional Interfaces Review — Supplier](https://medium.com/@ByteCodeBlogger/functional-interfaces-review-supplier-129a324e2359)
- [Uses of Interface java.util.function.Supplier](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/function/class-use/Supplier.html)
- [Java 8 Consumer and Supplier Example](https://examples.javacodegeeks.com/java-development/core-java/java-8-consumer-supplier-example/)
- [Java 8 Supplier Examples](https://mkyong.com/java8/java-8-supplier-examples/)
- [Mastering java.util.function.Supplier in Java](https://www.javapro.academy/mastering-java-util-function-supplier-in-java/)
- [Java Supplier Tutorial with Examples](https://o7planning.org/13351/java-supplier)

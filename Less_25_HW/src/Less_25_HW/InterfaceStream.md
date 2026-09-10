- **См. исходник (ENG): [Interface Stream](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html)**

---
### Интерфейс Stream

**Пакет:** [java.util.stream](https://docs.oracle.com/javase/8/docs/api/java/util/stream/package-summary.html)

**Все суперинтерфейсы:**
- [`AutoCloseable`](https://docs.oracle.com/javase/8/docs/api/java/lang/AutoCloseable.html), 
- [`BaseStream<Integer,IntStream>`](https://docs.oracle.com/javase/8/docs/api/java/util/stream/BaseStream.html)

```java
  public interface Stream<T>
    extends BaseStream<T,Stream<T>>
```

Последовательность примитивных элементов с целочисленным значением, поддерживающая последовательные и параллельные агрегатные операции.
Данный [стрим интерфейс](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html) специализируется на работе с примитивами int.

В следующем примере показана агрегатная операция с использованием [Stream](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html) 
и [IntStream](https://docs.oracle.com/javase/8/docs/api/java/util/stream/IntStream.html), вычисляющая сумму весов красных виджетов:

```java
  int sum = widgets.stream()
                   .filter(w -> w.getColor() == RED)
                   .mapToInt(w -> w.getWeight())
                   .sum();
```

В данном примере виджеты — это `Collection<Widget>`. Мы создаем поток объектов Widget с помощью [Collection.stream()]((https://docs.oracle.com/javase/8/docs/api/java/util/Collection.html#stream--)), 
фильтруем его, чтобы создать поток, содержащий только красные виджеты, а затем преобразуем его в поток значений int, представляющих вес каждого красного виджета.
Затем этот поток суммируется для получения общего веса.

Помимо Stream, представляющего собой поток ссылок на объекты, существуют примитивные специализации IntStream описанная в данном разделе, 
[LongStream](https://docs.oracle.com/javase/8/docs/api/java/util/stream/LongStream.html) и [DoubleStream](https://docs.oracle.com/javase/8/docs/api/java/util/stream/DoubleStream.html), 
все они называются «stream».

Для выполнения вычислений потоковые операции объединяются в потоковый конвейер. Конвейер потока нуждается в источнике. Источником может быть:
- массивом,
- коллекцией,
- функцией-генератором,
- каналом ввода-вывода
и т. д.

Другими элементами stream контейнера могут быть нуль или более промежуточных операций (которые преобразуют поток в другой поток, 
например [filter(Predicate)](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html#filter-java.util.function.Predicate-)) 
и терминальная операция (выдающая результат или побочный эффект, например [count()](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html#count--)
или [forEach(Consumer)](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html#forEach-java.util.function.Consumer-)).

Потоки ленивы; вычисление исходных данных выполняется только при инициации терминальной операции, а исходные элементы используются 
только по мере необходимости.

Коллекции и потоки, несмотря на внешнее сходство, имеют разные цели. Коллекции в первую очередь связаны с эффективным управлением и доступом
к своим элементам. Потоки, напротив, не предоставляют средства для прямого доступа или управления своими элементами, а вместо этого они связаны 
с декларативным описанием своего источника и вычислительных операций, которые будут выполняться в совокупности с этим источником.

Однако, если предоставленные потоковые операции не обеспечивают желаемой функциональности для выполнения контролируемого обхода, можно 
использовать операции интерфейса [BaseStream](https://docs.oracle.com/javase/8/docs/api/java/util/stream/BaseStream.html):
- [`BaseStream.iterator()`](https://docs.oracle.com/javase/8/docs/api/java/util/stream/BaseStream.html#iterator--);
- [`BaseStream.spliterator()`](https://docs.oracle.com/javase/8/docs/api/java/util/stream/BaseStream.html#spliterator--);

Конвейер stream, как и пример «виджетов» выше, можно рассматривать как запрос к источнику потока. Если источник не был специально 
разработан для параллельного изменения (например, ConcurrentHashMap), изменение источника потока во время запроса может привести 
к непредсказуемому или ошибочному поведению.

Большинство потоковых операций принимают параметры, описывающие заданное пользователем поведение, например лямбда-выражение 
`w -> w.getWeight()`, переданное в mapToInt в приведенном выше примере.

**Для сохранения правильного поведения эти поведенческие параметры:**
- не должны мешать (они [не изменяют источник потока](https://docs.oracle.com/javase/8/docs/api/java/util/stream/package-summary.html#NonInterference));
- в большинстве случаев должны быть [без состояния](https://docs.oracle.com/javase/8/docs/api/java/util/stream/package-summary.html#Statelessness)
(их результат не должен зависеть от какого-либо состояния, которое может измениться во время выполнения потокового конвейера).

Такие параметры всегда являются экземплярами [функционального интерфейса](https://docs.oracle.com/javase/8/docs/api/java/util/function/package-summary.html), 
такого как [`Function<T,R>`](https://docs.oracle.com/javase/8/docs/api/java/util/function/Function.html), и часто являются лямбда-выражениями или ссылками 
на методы. Если не указано иное, эти параметры должны быть ненулевыми.

Над stream-ом следует работать (вызывая операцию промежуточного или конечного потока) только один раз. Это исключает, например, «разветвленные» потоки, 
когда один и тот же источник питает два или более конвейера, или несколько обходов одного и того же потока.

Реализация потока может вызвать [IllegalStateException](https://docs.oracle.com/javase/8/docs/api/java/lang/IllegalStateException.html), если обнаружит,
что поток используется повторно. Однако, поскольку некоторые потоковые операции могут возвращать своего получателя, а не новый объект потока, обнаружение 
повторного использования во всех случаях может оказаться невозможным.

Stream имеет метод [BaseStream.close()](https://docs.oracle.com/javase/8/docs/api/java/util/stream/BaseStream.html#close--) и реализуют [AutoCloseable](https://docs.oracle.com/javase/8/docs/api/java/lang/AutoCloseable.html), но почти все экземпляры потока на самом деле не нужно закрывать после использования.
Как правило, только потоки, источником которых является канал ввода-вывода (например, возвращаемые [Files.lines(Path, Charset)](https://docs.oracle.com/javase/8/docs/api/java/nio/file/Files.html#lines-java.nio.file.Path-java.nio.charset.Charset-)) требуют закрытия.

Большинство stream-ов поддерживаются коллекциями, массивами или генерирующими функциями, которые не требуют специального управления ресурсами.
(Если stream требует закрытия, его можно объявить как ресурс в операторе try-with-resources)

Stream конвейеры могут выполняться последовательно или [параллельно](https://docs.oracle.com/javase/8/docs/api/java/util/stream/package-summary.html#Parallelism). 
Этот режим выполнения является свойством stream. Stream-ы создаются с начальным выбором последовательного или параллельного выполнения.

Например:
- [`Collection.stream()`](https://docs.oracle.com/javase/8/docs/api/java/util/Collection.html#stream--) - создает последовательный stream,
- [`Collection.parallelStream()`](https://docs.oracle.com/javase/8/docs/api/java/util/Collection.html#parallelStream--) - создает параллельный stream.

Выбор режима выполнения может быть изменен методами:
- [`BaseStream.sequential()`](https://docs.oracle.com/javase/8/docs/api/java/util/stream/BaseStream.html#sequential--);
- [`BaseStream.parallel()`](https://docs.oracle.com/javase/8/docs/api/java/util/stream/BaseStream.html#parallel--).

Состояние или режим можно запросить с помощью метода [BaseStream.isParallel()](https://docs.oracle.com/javase/8/docs/api/java/util/stream/BaseStream.html#isParallel--).

---
Для более глубокого изучения материала [см. документацию по интерфейсу Stream](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html)
и [документацию по пакету для java.util.stream](https://docs.oracle.com/javase/8/docs/api/java/util/stream/package-summary.html) для получения дополнительной 
информации по спецификации потоков, потоковых операций, потоковых конвейеров и параллелизма.

В отличие от классического Stream, интерфейс IntStream дополняет линейку методов своими удобными функциями, такими как MAX, MIN, SUM и т.д.

---
#### МЕТОДЫ:

- `boolean allMatch(IntPredicate predicate)` - Возвращает, соответствуют ли все элементы этого stream-а предоставленному предикату.
- `boolean anyMatch(IntPredicate predicate)` - Возвращает, соответствуют ли какие-либо элементы этого stream-а предоставленному предикату.
- `DoubleStream asDoubleStream()` - Возвращает DoubleStream, состоящий из элементов этого stream-а, преобразованных в double.
- `LongStream asLongStream()` - Возвращает LongStream, состоящий из элементов этого stream-а, преобразованных в long.
- `OptionalDouble average()` - Возвращает OptionalDouble, описывающий среднее арифметическое элементов этого stream-а, или пустой необязательный элемент, если этот stream пуст.
- `Stream<Integer> boxed()` - Возвращает stream, состоящий из элементов этого stream-а, каждый из которых упакован в целое число.
- `static IntStream.Builder builder()` - Возвращает построитель для IntStream.
- `<R> R	collect(Supplier<R> supplier, ObjIntConsumer<R> accumulator, BiConsumer<R,R> combiner)` - Выполняет изменяемую операцию сокращения элементов этого stream-а.
- `static IntStream concat(IntStream a, IntStream b)` - Создает лениво объединенный stream, элементами которого являются все элементы первого stream-а, за которыми следуют все элементы второго stream-а.
- `long count()` - Возвращает количество элементов в этом stream-е.
- `IntStream	distinct()` - Возвращает stream, состоящий из отдельных элементов этого stream-а.
- `static IntStream empty()` - Возвращает пустой последовательный IntStream.
- `IntStream	filter(IntPredicate predicate)` - Возвращает stream, состоящий из элементов этого stream-а, соответствующих заданному предикату.
- `OptionalInt findAny()` - Возвращает OptionalInt, описывающий некоторый элемент stream-а, или пустой OptionalInt, если stream пуст.
- `OptionalInt findFirst()` - Возвращает OptionalInt, описывающий первый элемент этого stream-а, или пустой OptionalInt, если stream пуст.
- `IntStream	flatMap(IntFunction<? extends IntStream> mapper)` - Возвращает stream, состоящий из результатов замены каждого элемента этого stream-а содержимым сопоставленного stream-а, полученного путем применения предоставленной функции сопоставления к каждому элементу.
- `void forEach(IntConsumer action)` - Выполняет действие для каждого элемента этого stream-а.
- `void forEachOrdered(IntConsumer action)` - Выполняет действие для каждого элемента этого stream-а, гарантируя, что каждый элемент обрабатывается в порядке встречи для stream-ов, имеющих определенный порядок встречи.
- `static IntStream generate(IntSupplier s)` - Возвращает бесконечный последовательный неупорядоченный stream, в котором каждый элемент создается предоставленным IntSupplier.
- `static IntStream iterate(int seed, IntUnaryOperator f)` - Возвращает бесконечный последовательный упорядоченный IntStream, созданный итеративным применением функции f к начальному начальному элементу, создавая stream, состоящий из seed, f(seed), f(f(seed)) и т. д.
- `PrimitiveIterator.OfInt iterator()` - Возвращает итератор для элементов этого stream-а.
- `IntStream	limit(long maxSize)` - Возвращает stream, состоящий из элементов этого stream-а, усеченный так, чтобы его длина не превышала maxSize.
- `IntStream	map(IntUnaryOperator mapper)` - Возвращает stream, состоящий из результатов применения заданной функции к элементам этого stream-а.
- `DoubleStream mapToDouble(IntToDoubleFunction mapper)` - Возвращает DoubleStream, состоящий из результатов применения заданной функции к элементам этого stream-а.
- `LongStream mapToLong(IntToLongFunction mapper)` - Возвращает LongStream, состоящий из результатов применения заданной функции к элементам этого stream-а.
- `<U> Stream<U>	mapToObj(IntFunction<? extends U> mapper)` - Возвращает объектный stream, состоящий из результатов применения заданной функции к элементам этого stream-а.
- `OptionalInt max()` - Возвращает OptionalInt, описывающий максимальный элемент этого stream-а, или пустой необязательный элемент, если этот stream пуст.
- `OptionalInt min()` - Возвращает OptionalInt, описывающий минимальный элемент этого stream-а, или пустой необязательный элемент, если этот stream пуст.
- `boolean noneMatch(IntPredicate predicate)` - Возвращает значение, указывающее, не соответствуют ли никакие элементы этого stream-а предоставленному предикату.
- `static IntStream of(int... values)` - Возвращает последовательный упорядоченный stream, элементами которого являются указанные значения.
- `static IntStream of(int t)` - Возвращает последовательный IntStream, содержащий один элемент.
- `IntStream	parallel()` - Возвращает эквивалентный параллельный stream.
- `IntStream	peek(IntConsumer action)` - Возвращает stream, состоящий из элементов этого stream-а, дополнительно выполняя указанное действие над каждым элементом по мере потребления элементов из результирующего stream-а.
- `static IntStream range(int startInclusive, int endExclusive)` - Возвращает последовательный упорядоченный IntStream от startInclusive (включительно) до endExclusive (исключительно) с шагом приращения 1.
- `static IntStream rangeClosed(int startInclusive, int endInclusive)` - Возвращает последовательный упорядоченный IntStream от startInclusive (включительно) до endInclusive (включительно) с шагом приращения 1.
- `OptionalInt reduce(IntBinaryOperator op)` - Выполняет сокращение элементов этого stream-а, используя ассоциативную функцию накопления, и возвращает OptionalInt, описывающий уменьшенное значение, если оно есть.
- `int reduce(int identity, IntBinaryOperator op)` - Выполняет сокращение элементов этого stream-а, используя предоставленное значение идентификатора и ассоциативную функцию накопления, и возвращает уменьшенное значение.
- `IntStream sequential()` - Возвращает эквивалентный последовательный stream.
- `IntStream skip(long n)` - Возвращает stream, состоящий из оставшихся элементов этого stream-а после отбрасывания первых n элементов stream-а.
- `IntStream sorted()` - Возвращает stream, состоящий из элементов этого stream-а в отсортированном порядке.
- `Spliterator.OfInt spliterator()` - Возвращает разделитель для элементов этого stream-а.
- `int sum()` - Возвращает сумму элементов в этом stream-е.
- `IntSummaryStatistics summaryStatistics()` - Возвращает IntSummaryStatistics, описывающую различные сводные данные об элементах этого stream-а.
- `int[] toArray()` - Возвращает массив, содержащий элементы этого stream-а.

---
**Более подробно описание классов и интерфейсов см.:**
- [IntStream](https://docs.oracle.com/javase/8/docs/api/java/util/stream/IntStream.html);
- [BaseStream](https://docs.oracle.com/javase/8/docs/api/java/util/stream/BaseStream.html);
- [Stream](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html);
- [DoubleStream](https://docs.oracle.com/javase/8/docs/api/java/util/stream/DoubleStream.html);
- [LongStream](https://docs.oracle.com/javase/8/docs/api/java/util/stream/LongStream.html);

---
- **[Пакет java.util.stream](https://docs.oracle.com/javase/8/docs/api/java/util/stream/package-summary.html)**

---
**Доп. материал:**
- [Java Stream Examples](https://www.geeksforgeeks.org/java/java-stream-examples/)
- [The Java Stream API Tutorial](https://www.baeldung.com/java-8-streams)
- [Java Stream Api Examples (on GitHub)](https://gist.github.com/tahniat-ashraf/a03c2545adf0c5fbc339568b2a756846)
- [Stream In Java](https://www.geeksforgeeks.org/java/stream-in-java/)
- [Complete guide to Java Stream API in pictures and examples](https://blog.annimon.com/stream-api/)
- [Mastering Java Streams: 25+ Hands-On Examples](https://medium.com/@anil.goyal0057/mastering-java-streams-25-hands-on-examples-45d56ba52cf2)
- [Using Java Streams in Java 8 and Beyond](https://www.jrebel.com/blog/java-streams-in-java-8)
- [Introduction to Java Streams](https://www.baeldung.com/java-8-streams-introduction)
- [Understanding Java Stream API](https://www.codejava.net/java-core/collections/understanding-java-8-stream-api)
- [49 Java Stream Examples That’ll Make You Rethink Every for-Loop You Ever Wrote](https://blog.stackademic.com/49-java-stream-examples-thatll-make-you-rethink-every-for-loop-you-ever-wrote-08bab9e9137f)
- [Java Streams — A quick primer and application](https://edward-heaver.medium.com/java-streams-a-quick-primer-and-application-7a19b0cf883)
- [Understanding Java Streams: Your Step-by-Step Tutorial with Code Examples](https://www.hungrycoders.com/blog/understanding-java-streams-with-code-examples)
- [Easy Hacks: How to Use Java Streams for Working With Data](https://blog.jetbrains.com/idea/2024/05/easy-hacks-how-to-use-java-streams-for-working-with-data/)
- [A Guide to Java Streams: In-Depth Tutorial With Examples](https://stackify.com/streams-guide-java-8/)

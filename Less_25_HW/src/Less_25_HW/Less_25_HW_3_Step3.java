package Less_25_HW;
/*
Задан массив случайных целых чисел
(от 1 до 300) длинной 1 млн элементов.

Найти максимальный элемент в массиве двумя способами:
- в одном потоке;
- при помощи 10 потоков.

Сравнить затраченное в обоих случаях время.

Пытаемся еще сильнее оптимизировать код применив,
готовый интерфейс Supplier - Поставщик. Интерфейс
ничего не принимает на вход, но выдает некий
требуемый результат, согласно прописанным условиям.
*/
import Less_25_HW.MyClasses.ArrayPerMillion;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Supplier;

import static Less_25_HW.MyClasses.ArraysFindMax.findMaxByOneThread;
import static Less_25_HW.MyClasses.ArraysFindMax.findMaxParallel;

public class Less_25_HW_3_Step3 {
    public static void main(String[] args) {

        int[] values = ArrayPerMillion.getArrayOfRandomElement();

        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        /*
        Мы точно знаем, что хотим получить значение максимального
        элемента массива, заполненного целочисленными переменными,
        поэтому наши Supplier параметризированы <Integer>, т.е.
        единственный метод *.get() данного интерфейса вернет int.

        Лямбда выражение вида () -> {содержит код возвращающий int}

        Наши статические методы функционального класса ArraysFindMax,
        возвращают именно то, что нам нужно int. Логика работы каждого
        из методов подобна той, что применялась в предыдущих трех
        примерах (Less_25_HW_3_Step 1 ÷ 3). Метод принимает на вход
        массив и возвращает искомое значение, либо принимает массив
        и пул потоков и тоже возвращает целочисленное значение.
        */
        Supplier<Integer> multiTreadFinder = () -> {
            try {
                return findMaxParallel(values, threadPool);
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
            return Integer.MIN_VALUE;
        };

        Supplier<Integer> oneTreadFinder = () -> findMaxByOneThread(values);

        timeMeasureMethod(oneTreadFinder, "oneTreadFinder");
        timeMeasureMethod(multiTreadFinder, "multiTreadFinder");

        threadPool.shutdownNow();
    }

    private static int timeMeasureMethod(Supplier<Integer> task, String nameOfMethod) {
        long startTime = System.nanoTime();
        int result = task.get();
        System.out.println("Отработал : " + nameOfMethod +
                           ", максимальное значение : " + result +
                           ", время поиска в наносекундах: " + (System.nanoTime() - startTime));
        return result;
    }
}
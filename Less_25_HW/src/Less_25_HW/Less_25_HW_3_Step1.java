package Less_25_HW;
/*
Задан массив случайных целых чисел
(от 1 до 300) длинной 1 млн элементов.

Найти максимальный элемент в массиве двумя способами:
- в одном потоке;
- при помощи 10 потоков.

Сравнить затраченное в обоих случаях время.

Задача решается сквозным кодом, кроме статического
метода *.getArrayOfRandomElement().

Комментарии расписывают, что происходит.
Для понимания и повторения см.
- InterfaceSupplier.txt
- InterfaceIntStream.txt
- FutureCallable.txt
- ClassOptional.txt
*/
import Less_25_HW.MyClasses.ArrayPerMillion;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class Less_25_HW_3_Step1 {
    public static void main(String[] args) {
        // Получили массив случайных чисел
        int[] values = ArrayPerMillion.getArrayOfRandomElement();
        // Получили возможность запускать 10 потоков
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        /*
        Запускаем метод нахождения MAX в массиве
        при помощи одного потока и замеряем время
        */
        long startTimeForOneThread = System.currentTimeMillis();
        /*
        static IntStream of(int... values) - Возвращает последовательный
        упорядоченный stream, элементами которого являются указанные
        значения values.
        */
        int resVolFromOneThread = IntStream.of(values)
                /*
                OptionalInt max() - Возвращает OptionalInt, описывающий
                максимальный элемент этого stream-а, или пустой
                необязательный (Optional) элемент, если этот stream пуст.
                */
                .max()
                /*
                Поскольку предыдущий метод возвращает (Optional) элемент,
                то к методам данного класса мы и обратимся.

                Метод - T orElse(T other) - возвращает значение, если оно
                присутствует, иначе возвращает другое (T other), заданное
                нами, в данном случае Integer.MIN_VALUE = -2147483648.

                Нам нужно, что-то вернуть в переменную resVolFromOneThread,
                Optional метод *.orElse(Integer.MIN_VALUE) этим и занят.
                */
                .orElse(Integer.MIN_VALUE);
        long finishTimeForOneThread = System.currentTimeMillis();
        System.out.print("Результат и время при работе одного потока -> ");
        System.out.println("MAX : " + resVolFromOneThread + " время поиска: " +
                          (finishTimeForOneThread - startTimeForOneThread));
        /*
        Запускаем метод нахождения MAX в массиве
        при помощи 10-и потоков и замеряем время
        */
        try {
            long startTimeForMultiThread = System.currentTimeMillis();
            /*
            Запускаем ExecutorService методом submit(Callable или Runnable),
            который принимает реализацию Callable (Runnable) и возвращает
            будущий объект или объект интерфейса Future. Объект Future можно
            использовать для проверки завершения выполнения Callable.

            Метод <V> get() - применяется для объекта Future, он ожидает
            (при необходимости) завершения задачи, после чего можно будет
            получить результат;
            */
            int resVolFromMultiThread = threadPool.submit(new Callable<Integer>() {
                        @Override
                        public Integer call() throws Exception {
                            // Как и в первом случае создаем stream из массива
                            return IntStream.of(values)
                                    // Распараллеливаем стрим
                                    .parallel()
                                    // Находим максимум
                                    .max()
                                    // Возвращаем его или если его нет, то мин. integer
                                    .orElse(Integer.MIN_VALUE);
                        }
                    })
                    .get(); // Метод Future объекта см. выше.
            long finishTimeForMultiThread = System.currentTimeMillis();
            System.out.print("Результат и время при работе 10 потоков -> ");
            System.out.println("MAX : " + resVolFromMultiThread + " время поиска: " +
                    (finishTimeForMultiThread - startTimeForMultiThread));
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        threadPool.shutdownNow();
    }
}

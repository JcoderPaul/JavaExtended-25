package Less_25_HW;
/*
Задан массив случайных целых чисел
(от 1 до 300) длинной 1 млн элементов.

Найти максимальный элемент в массиве двумя способами:
- в одном потоке;
- при помощи 10 потоков.

Сравнить затраченное в обоих случаях время.

В примере Less_25_HW_3_Step1 - наш код шел строка за
строкой без разбивки по методам, что плохо для
переработки программы в случае необходимости.

Попробуем внести изменения.

В старом коде, наши строки по замеру времени выполнения
задач в моно и мульти поточном режиме дублировались, хоть
и ссылались на разные переменные, надо это оптимизировать.

Так же поиск MAX значения массива в моно и мульти потоковом
режиме можно вынести в отдельные методы.

Получилось чуть лучше, но пришлось создать два класса и один
интерфейс.

Медленно подбираемся к интерфейсу Supplier, который еще сильнее
сократит код см. Less_25_HW_3_Step3
*/
import Less_25_HW.MyClasses.ArrayPerMillion;
import Less_25_HW.MyClasses.GetBackResult;
import Less_25_HW.MyClasses.MonoThreadFinder;
import Less_25_HW.MyClasses.MultiThreadFinder;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Less_25_HW_3_Step2 {

    public static void main(String[] args) {

        int[] values = ArrayPerMillion.getArrayOfRandomElement();

        ExecutorService threadPool = Executors.newFixedThreadPool(10);

        /*
        Передаем в наш замеритель времени наш класс, подписанный
        на GetBackResult и передаем в него наш массив. Данный класс
        работает в однопоточном режиме без распараллеливания stream-а.
        */
        timeMeasureMethod(new MonoThreadFinder(values));
        /*
        Передаем в наш замеритель времени наш класс, подписанный
        на GetBackResult и передаем в него наш массив. Данный класс
        работает в многопоточном режиме с распараллеливанием stream-а.
        */
        try {
            timeMeasureMethod(new MultiThreadFinder(values,threadPool));
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        threadPool.shutdownNow();
    }
    /*
    Создали отдельный метод для замера времени выполнения поиска MAX в массиве.
    Для работы данный метод принимает в качестве аргументов класс имплементирующий
    интерфейс GetBackResult.
    */
    private static void timeMeasureMethod(GetBackResult task) {
        long startTime = System.nanoTime(); // Стартовое время в наносекундах
        task.getRes();
        long endTime = System.nanoTime(); // Финишное время в наносекундах
        System.out.println("Время работы '" + task.getNameOfAlgo() +
                           "' алгоритма:" + (endTime - startTime) + " наносекунд.");
    }
}

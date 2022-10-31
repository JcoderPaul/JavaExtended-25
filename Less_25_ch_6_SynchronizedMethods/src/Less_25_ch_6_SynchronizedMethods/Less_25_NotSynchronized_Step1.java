package Less_25_ch_6_SynchronizedMethods;

import Less_25_ch_6_SynchronizedMethods.MyClasses.Runner;

public class Less_25_NotSynchronized_Step1 {
    public static void main(String[] args) {
        // Создаем объект от нашего класса подписанного на интерфейс Runnable
        Runner run_count = new Runner(4);
        // Создаем три потока из нашего объекта, так можно
        Thread tr_1 = new Thread(run_count);
        Thread tr_2 = new Thread(run_count);
        Thread tr_3 = new Thread(run_count);
        // Стартуем их
        tr_1.start();
        tr_2.start();
        tr_3.start();
        /*
        Смотрим на экран, задали limit = 4, запустим программу 4-и раза и глянем на результат:
        1 -> 1 3 4 5 0 6 7 8 2 9 10 11
        2 -> 2 3 4 5 1 6 7 8 0 9 10 11
        3 -> 1 2 3 4 0 5 6 7 0 8 9 10
        4 -> 1 3 4 5 2 0 7 8 9 6 10 11

        Теоретически, если предположить, что потоки не конкурируют, и работают последовательно
        один за другим, то на экране можно было бы ожидать следующую последовательность:
        0 1 2 3 4 5 6 7 8 9 10 11. Но конкуренция есть, и каждый поток работает как единоличник,
        отсюда разброс в выходных данных.
        */
    }
}

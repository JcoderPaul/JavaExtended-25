package Less_25_HW;
/*
Написать программу, бесконечно считывающую пользовательские числа
из консоли. Эти числа представляют собой количество секунд - N. При
каждом вводе числа, должна создаваться задача, которая засыпает
на введённое число секунд и затем выводит "Я спал N секунд".
При вводе отрицательного числа программа должна завершать свою работу.

Все задачи должны выполняться в нескольких потоках (в пуле потоков)
Посчитать кол-во обработанных задач каждым потоком.
*/

import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Less_25_HW_2 {
    public static void main(String[] args) {
        System.out.println("******* Программа запущена *******");
        ExecutorService threadExecutor = Executors.newFixedThreadPool(2);
        /*
        Данный класс предоставляет локальные переменные потока. Эти переменные
        отличаются от своих обычных аналогов тем, что каждый поток, обращающийся
        к ним (через свой метод get или set), имеет свою собственную, независимо
        инициализированную копию переменной. (См. ThreadLocal.txt)
        */
        ThreadLocal<Integer> threadLocal = new ThreadLocal<>();

        System.out.println("Для запуска потока введите положительное число \n" +
                           "(для выхода из программы отрицательное число): ");
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextInt()) {
            int seconds = scanner.nextInt();
            if (seconds < 0) {
                break;
            }
            // В ExecutorService передаем задание, а он уже раскидает все по потокам.
            threadExecutor.submit(new Callable<Integer>() {
                /*
                V call () throws Exception - вычисляет результат или выдает
                исключение, если это невозможно.

                В данном варианте решения задачи мы применяем интерфейс Callable.
                Хотя, нам ничего не надо возвращать и мы могли бы использовать и
                Runnable, но Callable пробрасывает исключение, а мы в его теле
                используем метод *.sleep(), который в свою очередь может выкинуть
                исключение, т.е. чисто из удобства.
                */
                @Override
                public Integer call() throws Exception {
                    // Активируем threadLocal переменную. (См. ThreadLocal.txt)
                    Integer counter = threadLocal.get();
                    // Если она еще null, то присваиваем ей 1, если нет, увеличиваем на 1
                    threadLocal.set(counter == null ? 1 : ++counter);
                    // Выводим на экран счетчик выполненных заданий конкретным потоком
                    System.out.println(String.format("Поток `%s`, задач: `%d`",
                            Thread.currentThread().getName(), threadLocal.get()));
                    // Усыпляем его
                    Thread.sleep(seconds * 1000L);
                    System.out.println(String.format("Поток `%s` спал `%d` секунд",
                            Thread.currentThread().getName(), seconds));
                    System.out.println("Ведите число : ");
                    /*
                    Возвращаемое значение уйдет в пустоту,
                    можно указать и null, но это некрасиво.
                    */
                    return seconds;
                }
            });
        }

        threadExecutor.shutdown();
        try {
            threadExecutor.awaitTermination(10L, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("******* Программа закончена *******");
    }
}

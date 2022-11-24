package Less_25_HW;
/*
Написать программу, бесконечно считывающую пользовательские числа
из консоли. Эти числа представляют собой количество секунд - N. При
каждом вводе числа, должна создаваться задача, которая засыпает
на введённое число секунд и затем выводит "Я спал N секунд".

Однако нужно сделать так, чтобы все задачи выполнялись в одном и том
же потоке в порядке ввода. Т.е. в программе есть 2 потока: главный main
и поток для выполнения всех задач по очереди.

При вводе отрицательного числа программа должна завершать свою работу.
*/

import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Less_25_HW_1 {
    public static void main(String[] args) {
        System.out.println("******* Программа запущена *******");
        ExecutorService threadExecutor = Executors.newSingleThreadExecutor();

        System.out.println("Для запуска потока введите положительное число \n" +
                           "(для выхода из программы отрицательное число): ");
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextInt()) {
            int seconds = scanner.nextInt();
            if (seconds < 0) {
                break;
            }
            threadExecutor.submit(new Runnable() {
                @Override
                public void run() {
                    try {

                    Thread.sleep(seconds * 1000L);
                    System.out.printf("Поток `%s`, спал: `%d` секунду.",
                            Thread.currentThread().getName(), seconds);
                    System.out.println("\nВедите число : ");

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
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

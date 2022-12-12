package Less_25_HW_2.TimingTask;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Lass_25_HW_ThreeOrOne {
    public static void main(String[] args) {
        // Шаг 1 - создаем счетчик обратного отсчета на фиксацию 3-х событий
        CountDownLatch countDownLatch = new CountDownLatch(3);
        // Шаг 2 - создаем пул потоков на 3-и штуки
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        // Шаг 3.1 - создаем первое задание
        Runnable task1 = new Runnable() {
            @Override
            public void run() {
                long sum = 0;
                for (int i = 0; i < 1000000; i++) {
                    if (i % 2 == 0) {
                        sum += i;
                    }
                }
                System.out.println("Task 1 - (сумма всех четных чисел в диапазоне 0 - 1_000_000): " + sum);
                countDownLatch.countDown();
            }
        };
        // Шаг 3.2 - создаем второе задание
        Runnable task2 = new Runnable() {
            @Override
            public void run() {
                long sum = 0;
                for (int i = 0; i < 1000000; i++) {
                    if (i % 7 == 0) {
                        sum += i;
                    }
                }
                System.out.println("Task 2 - (сумма всех чисел в диапазоне 0 - 1_000_000 делящихся на 7 без остатка): " + sum);
                countDownLatch.countDown();
            }
        };
        // Шаг 3.3 - создаем третье задание
        Runnable task3 = new Runnable() {
            @Override
            public void run() {
                List<Integer> numbers = new ArrayList<>();
                for (int i = 0; i < 1000; i++) {
                    numbers.add((int)(Math.random() * 1000));
                }
                int count = 0;
                for (int i : numbers) {
                    if (i % 2 == 0) {
                        count++;
                    }
                }
                System.out.println("Task 3 (сумма всех случайных четных чисел в списке): " + count);
                countDownLatch.countDown();
            }
        };
        // Замеряем стартовое время
        long before = System.currentTimeMillis();
        /*
        Передаем наши задания шаг 3.1 - 3.3 в
        executorService на выполнение, т.к.
        пул из 3-х потоков, то наши 3-и задания
        сразу пойдут в работу.
        */
        executorService.execute(task1);
        executorService.execute(task2);
        executorService.execute(task3);
        // По завершении операций 'убиваем' executorService
        executorService.shutdown();
        try {
            // Дожидаемся когда наш счетчик будет - '0'
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Замеряем конечное время выполнения всех операций
        long after = System.currentTimeMillis();
        System.out.println("Time: " + (after - before));
    }
}

package Less_25_ch_14_InterfaceCallable;
/*
Задача в многопоточном режиме рассчитать сумму всех чисел от 1 до 1_000_000_000
*/
import Less_25_ch_14_InterfaceCallable.MyClasses.PartSum;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Less_25_FutureMethods_Step4 {
    // Задаем предел, сумму чисел которого нужно рассчитать
    private static long value_of_num = 1_000_000_000L;
    // Итоговая сумма на старте
    private static long big_sum = 0;

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        /*
        Получаем начальное время запуска программы (хотим рассчитать, сколько времени)
        займет весь расчет. Если в ExecutorService в методе newFixedThreadPool() поставить
        1 вместо 10, то мы получим один поток и легко сравним время параллельного и
        последовательного расчета.
        */
        long before = System.currentTimeMillis();

        ExecutorService es_for_sum = Executors.newFixedThreadPool(10);
        // Создаем список объектов Future<Long>
        List<Future<Long>> future_part_res = new ArrayList<>();
        // Мы решили, что на 10 потоков, будет создано 10 задач (task-ов)
        long value_of_num_dev_by_ten = value_of_num/10;
        for(int i = 0; i < 10; i++){
            /*
            Распределяем диапазоны расчетов по периодам и потокам. Например, если
            i = 0, то диапазоны расчетов распределятся (законы математики неизменны,
            скобочки приведены для наглядности):
            -> от (from) 100_000_000*0+1 = 1 до (to) 100_000_000+(0+1) = 100_000_000;
            -> от (from) (100_000_000*1)+1 = 100_000_001 до (to) 100_000_000+(1+1) = 200_000_000;
            ...
            i = 2.....9
            ...
            -> от (from) (100_000_000*9)+1 = 900_000_001 до (to) 100_000_000+(9+1) = 1000_000_000;
            */
            long from = value_of_num_dev_by_ten*i + 1;
            long to = value_of_num_dev_by_ten*(i + 1);
            /*
            Как только мы получаем значения from и to, то сразу создаем
            наши Callable объекты 'PartSum' частичный расчет.
            */
            PartSum task = new PartSum(from, to);
            // И ждем возвращаемого Future значения, которое отдаем переменной, а затем...
            Future<Long> futurePartSum = es_for_sum.submit(task);
            // Помещаем полученный результат в наш список Future объектов
            future_part_res.add(futurePartSum);
        }
        /*
        После окончания предыдущего цикла, наш список промежуточных результатов будет
        заполнен, и мы методом *.get() достанем их и просуммируем, получив итог расчетов.
        */
        for (Future<Long> res: future_part_res) {
              big_sum += res.get();
        }
        System.out.println("Big sum = " + big_sum);
        // Останавливаем наш ExecutorService
        es_for_sum.shutdown();
        // Фиксируем время завершения расчетов и вывода итогов на экран
        long after = System.currentTimeMillis();
        // Выводим на экран время работы программы с n-потоками (в текущем варианте 10)
        System.out.println("Operation time : " + (after - before) + " ms.");

    }
}

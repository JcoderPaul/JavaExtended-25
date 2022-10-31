package Less_25_ch_14_InterfaceCallable.MyClasses;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Callable;

public class PartSum implements Callable<Long> {
    long from;
    long to;
    long localSum;
    // Форматируем вывод времени
    static SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss:SSS");

    public PartSum(Long from, Long to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public Long call() throws Exception {
        // Применяем формат к выходным данным
        String time_in_text = sdf.format(new Date());
        /*
        Запускаем цикл расчета суммы от стартовой точки до финишной, которые
        в свою очередь формируются и передаются в данный метод из основного кода.
        */
        for(Long i = from; i <= to; i++){
            localSum += i;
        }
        // Выводим время, диапазон расчетов, результат и поток, который над ним работал
        System.out.println(time_in_text + " - PartSum from " + from + " to " + to +
                           " -> " + localSum + " <- by " + Thread.currentThread().getName());
        /*
        Возвращаем результат расчетов, которые в основном коде попадут в наш List
        объектов Future<Long>, полученные текущим методом *.call().
        */
        return localSum;
    }
}

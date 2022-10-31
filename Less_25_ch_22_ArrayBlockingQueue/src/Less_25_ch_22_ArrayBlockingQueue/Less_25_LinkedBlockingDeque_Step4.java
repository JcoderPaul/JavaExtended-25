package Less_25_ch_22_ArrayBlockingQueue;
/*
В примере все сообщения, выводимые в консоль, и объекты (коллекция names, очередь deque)
для наглядности вынесены за пределы конструктора класса.

Сначала в конструкторе создается массив из элементов (месяцев) календаря и формируется
двунаправленная блокирующая очередь типа LinkedBlockingDeque с ограничением в 6 элементов.

После этого стартуют два потока:
- CalendarProducer для добавления элементов в очередь;
- CalendarConsumer для извлечения элемента с удалением.

Задержки после выполнения операций с очередью у потоков различны, CalendarProducer опережает
CalendarConsumer, т.е. очередь должна быстрее наполняться.

Поток CalendarProducer вставляет элементы из коллекции в начало очереди.
Поток CalendarConsumer извлекает из очереди элементы: если размер очереди нечетный, то элемент
извлекается из головы, в противном случае из хвоста очереди.

Все сообщения выводятся в консоль.
*/
import Less_25_ch_22_ArrayBlockingQueue.MyClasses.*;

import java.util.Calendar;
import java.util.Deque;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.LinkedBlockingDeque;

public class Less_25_LinkedBlockingDeque_Step4 {

    public final static String EXTRACT     = "Извлечение из map : %s%n"   ;
    public final static String INSERT      = "Добавление в очередь : %s%n";
    public final static String WAIT        = "... ожидание : %s%n"        ;
    public final static String SIZE        = "--- deque.size=%d ---%n"    ;
    public final static String REMOVE_HEAD = "\tremove head: %s%n"        ;
    public final static String REMOVE_TAIL = "\tremove tail: %s%n"        ;

    public static Map<String, Integer> names = null;
    public static Deque<String> deque = null;

    public Less_25_LinkedBlockingDeque_Step4()
    {
        Calendar now    = Calendar.getInstance();
        Locale locale = Locale.getDefault();

        names = now.getDisplayNames(Calendar.MONTH, Calendar.ALL_STYLES, locale);

        System.out.printf("Список коллекции : %s%n", names);

        deque = new LinkedBlockingDeque<String>(6);

        Thread producer = new Thread(new CalendarProducer());
        producer.start();

        (new Thread(new CalendarConsumer())).start();

        while (producer.isAlive()) {
            try {
                Thread.sleep(600);
            } catch (InterruptedException ignored) {}
        }
        System.exit(0);
    }

    public static void main(String args[])
    {
        new Less_25_LinkedBlockingDeque_Step4();
    }
}

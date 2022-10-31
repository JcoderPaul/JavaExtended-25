package Less_25_ch_22_ArrayBlockingQueue;
/*
Данный пример создан на основе примера Less_25_BlockingQueue_Step3.

В примере, как и раньше создаются два потока, которые работают с очередью SynchronousQueue.
Первый поток NewProducer вставляет элементы в очередь, а второй поток NewConsumer с
задержкой в 3 сек. извлекает из очереди элементы. Перед каждой и после каждой операции
вставки и чтения выводятся соответствующие сообщения.

Основная идея примера — проверка ожидания выполнения операций внесения и
извлечения в синхронной очереди.
*/
import Less_25_ch_22_ArrayBlockingQueue.MyClasses.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

public class Less_25_SynchQueues_Step5 {
    public static BlockingQueue<String> drop;
    public static final String  DONE = "done";

    public static final String[]  messages = {"Мама пошла готовить обед",
            "Мама позвала к столу",
            "Дети кушают молочную кашу",
            "А что ест папа?"};
    public static final String BEFORE_PUT = "*** %s before put message";
    public static final String AFTER_PUT  = "*** %s after put message\n";

    public static final String BEFORE_TAKE = "--- %s before take message";
    public static final String AFTER_TAKE  = "--- %s after take message ---\n";

    public static SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

    public Less_25_SynchQueues_Step5 ()
    {
        drop = new SynchronousQueue<String>();
        (new Thread(new NewProducer())).start();
        (new Thread(new NewConsumer())).start();
    }
    public static void printMessage(final String templ)
    {
        String text = String.format(templ, sdf.format(new Date()));
        System.out.println(text);
    }

    public static void main(String args[])
    {
        new Less_25_SynchQueues_Step5();
    }
}

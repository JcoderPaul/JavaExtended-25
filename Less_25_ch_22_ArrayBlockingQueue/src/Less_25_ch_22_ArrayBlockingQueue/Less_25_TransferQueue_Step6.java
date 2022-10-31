package Less_25_ch_22_ArrayBlockingQueue;
/*
Следующий пример демонстрирует применение очереди LinkedTransferQueue.

В примере создаются два потока, работающие с очередью TransferQueue<String>.
Поток производитель - MyProducer, размещает элемент в очереди с использованием
метода transfer. Поток потребитель - MyConsumer, извлекает элементы из очереди
и выводит их в консоль.

Перед получением элемента из очереди MyConsumer делает небольшую задержку,
по которой можно оценить, кто кого ожидает, т.е. какой поток блокируется
очередью.

Перед каждой операции с очередью и после выполнения операции потоки выводят
соответствующие сообщения в консоль с указанием времени.
*/
import Less_25_ch_22_ArrayBlockingQueue.MyClasses.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TransferQueue;

public class Less_25_TransferQueue_Step6 {
    public static TransferQueue<String> queue = null;
    public static String WAIT_producer = "Producer waiting to transfer : ";
    public static String TRANSFERED    = "Producer transfered :"       ;
    public static String WAIT_consumer = "Consumer waiting to consumed : ";
    public static String CONSUMED      = "Consumer consumed : "           ;
    public static SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
    public Less_25_TransferQueue_Step6()
    {
        queue = new LinkedTransferQueue<String>();

        new Thread(new MyProducer()).start();
        new Thread(new MyConsumer()).start();
    }
    public static void printMessage(final String msg)
    {
        String text = sdf.format(new Date()) + " " + msg;
        System.out.println(text);
    }

    public static void main(String args[])
    {
        new Less_25_TransferQueue_Step6();
    }
}

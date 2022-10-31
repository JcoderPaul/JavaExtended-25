package Less_25_ch_22_ArrayBlockingQueue;
/*
В примере BlockingQueueExample создается очередь drop типа ArrayBlockingQueue емкостью
в один элемент и с установленным флагом справедливости. После этого запускаются два потока.
Первый поток MatherProducer помещает в очередь сообщения из массива messages с использованием
метода put, а второй поток FamilyConsumer считывает из очереди сообщения методом take и
выводит их в консоль.
*/
import Less_25_ch_22_ArrayBlockingQueue.MyClasses.*;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Less_25_BlockingQueue_Step3 {
    public static BlockingQueue<String> drop;
    public static String DONE = "done";
    public static String[] messages = {
            "Мама пошла готовить обед",
            "Мама позвала к столу",
            "Дети кушают молочную кашу",
            "А что ест папа?"};

    public Less_25_BlockingQueue_Step3()
    {
        drop = new ArrayBlockingQueue<String>(1, true);
        (new Thread(new MatherProducer())).start();
        (new Thread(new FamilyConsumer())).start();
    }

    public static void main(String[] args)
    {
        new Less_25_BlockingQueue_Step3();
    }
}

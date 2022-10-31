package Less_25_ch_22_ArrayBlockingQueue;
/*
Пример работы разных методов добавления элементов в
коллекции типа ArrayBlockingQueue
*/
import java.util.concurrent.ArrayBlockingQueue;

public class Less_25_ArrayBlockingQueue_Step1 {
    public static void main(String[] args) {
        // При создании ArrayBlockingQueue мы должны указать размер коллекции
        ArrayBlockingQueue<Integer> myArrBlockQue = new ArrayBlockingQueue<>(5);

        myArrBlockQue.add(1);
        myArrBlockQue.add(2);
        myArrBlockQue.offer(3);
        myArrBlockQue.add(4);
        myArrBlockQue.add(5);

        System.out.println(myArrBlockQue);
        /*
        Если мы в очередь ограниченной длины попытаемся добавить еще один элемент
        сверх лимита:

        myArrBlockQue.add(6);

        То вылетит: 'Exception in thread "main" java.lang.IllegalStateException: Queue full'
        Однако нам никто не мешает попытаться добавить элемент используя:
        */
        myArrBlockQue.offer(6);
        /*
        Исключения не будет, но и элемент не добавится.
        */
        System.out.println("------- После попытки добавить элемент методом *.offer() -------");
        System.out.println(myArrBlockQue);
    }
}

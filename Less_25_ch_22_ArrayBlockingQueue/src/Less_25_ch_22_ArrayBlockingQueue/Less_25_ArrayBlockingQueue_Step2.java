package Less_25_ch_22_ArrayBlockingQueue;
/*
Пример работы двух потоков с ArrayBlockingQueue.
Один из которых добавляет элемент в хвост очереди, а
другой забирает элемент из головы очереди.
*/
import java.util.concurrent.ArrayBlockingQueue;

public class Less_25_ArrayBlockingQueue_Step2 {
    public static void main(String[] args) {
        // При создании ArrayBlockingQueue мы должны указать размер коллекции
        ArrayBlockingQueue<Integer> myArrBlockQue = new ArrayBlockingQueue<>(4);

        // Producer
        new Thread(() ->{
            int i = 0;
            while (true){
                try {
                    myArrBlockQue.put(++i);
                    System.out.println("Producer add element -> " + i);
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(myArrBlockQue);
            }
        }).start();

        // Consumer
        new Thread(() ->{
            while (true) {
                try {
                    Integer j = myArrBlockQue.take();
                    System.out.println("Consumer take element -> " + j);
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

    }
}

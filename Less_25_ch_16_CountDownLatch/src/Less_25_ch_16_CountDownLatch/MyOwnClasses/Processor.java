package Less_25_ch_16_CountDownLatch.MyOwnClasses;

import java.util.concurrent.CountDownLatch;

public class Processor implements Runnable{
    private int id;
    private CountDownLatch new_count_down;

    public Processor(int id, CountDownLatch new_count_down) {
        this.id = id;
        this.new_count_down = new_count_down;
    }

    @Override
    public void run() {

        System.out.println("Thread with id - " + id + " - is start.");

        try {
            System.out.println("Thread with id - " + id + " - going to sleep то 3 sec.");
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            new_count_down.await(); // Тут ждем пока таймер не уйдет в '0'
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Thread with id - " + id + " - is proceeded");

        for (int i = 0; i < 10; i++){
            System.out.println("Thread with id - " + id + " - is working -> " + i);
        }
    }
}

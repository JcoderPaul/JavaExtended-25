package Less_25_ch_4_SleepJoin.MyThreadMaker;

public class MyThread extends Thread{
    @Override
    public void run() {
        for (int i = 0 ; i <= 5; i++){
            System.out.println("Thread name'" + Thread.currentThread().getName() + "' выводит -> " + i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

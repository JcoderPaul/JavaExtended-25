package Less_25_ch_13_ThreadPool.MyClasses;

public class FirstRunner implements Runnable{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " -> start work.");
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(Thread.currentThread().getName() + " -> end work.");
    }
}

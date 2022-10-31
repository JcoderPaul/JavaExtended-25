package Less_25_ch_11_DaemonThread.MyThreadsClasses;

public class DaemonThread extends Thread{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " is Daemon -> " + isDaemon());
        for(int i = 1; i < 1000; i++){
            try {
                sleep(100);
                System.out.println(i);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

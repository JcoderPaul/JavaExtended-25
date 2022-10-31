package Less_25_ch_11_DaemonThread.MyThreadsClasses;

public class UserThread extends Thread{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " is Daemon -> " + isDaemon());
        for(char i = 'A'; i < 'J'; i++){
            try {
                sleep(300);
                System.out.println(i);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

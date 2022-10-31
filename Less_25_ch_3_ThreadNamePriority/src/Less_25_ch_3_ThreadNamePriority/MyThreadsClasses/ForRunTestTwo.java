package Less_25_ch_3_ThreadNamePriority.MyThreadsClasses;

public class ForRunTestTwo implements Runnable{

    @Override
    public void run() {
        System.out.println("Hello from ForRunTestTwo -> " + Thread.currentThread().getName());
    }
}

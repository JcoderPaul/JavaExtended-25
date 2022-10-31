package Less_25_ch_3_ThreadNamePriority.MyThreadsClasses;

public class ForRunTestOne implements Runnable{

    @Override
    public void run() {
        System.out.println("Hello from ForRunTestOne -> " + Thread.currentThread().getName());
    }
}

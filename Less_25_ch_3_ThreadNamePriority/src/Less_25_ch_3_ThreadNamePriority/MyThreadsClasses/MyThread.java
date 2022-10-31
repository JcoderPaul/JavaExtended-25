package Less_25_ch_3_ThreadNamePriority.MyThreadsClasses;

public class MyThread extends Thread{

    @Override
    public void run() {
        System.out.println("Hello from MyThread -> " + this.getName());
    }
}

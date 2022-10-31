package Less_25_ch_12_Interruption.MyThreadClasses;

public class MyThread implements Runnable {

    public void run(){

        System.out.printf("%s started... \n", Thread.currentThread().getName());
        int counter=1; // счетчик циклов
        while(!Thread.currentThread().isInterrupted()){

            System.out.println("Loop " + counter++);
        }
        System.out.printf("%s finished... \n", Thread.currentThread().getName());
    }
}

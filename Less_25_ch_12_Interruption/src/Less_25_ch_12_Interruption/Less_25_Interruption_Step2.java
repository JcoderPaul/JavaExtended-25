package Less_25_ch_12_Interruption;
/*
Пример напоминалка о том, что если основная функциональность заключена в классе,
который реализует интерфейс Runnable, то там можно проверять статус потока с
помощью метода Thread.currentThread().isInterrupted().
*/
import Less_25_ch_12_Interruption.MyThreadClasses.*;

public class Less_25_Interruption_Step2 {

    public static void main(String[] args) throws InterruptedException {

            System.out.println("Main thread started...");
            MyThread myThread = new MyThread();
            Thread t = new Thread(myThread,"MyThread");
            t.start();
            try{
                Thread.sleep(150);
                t.interrupt();

                Thread.sleep(150);
            }
            catch(InterruptedException e){
                System.out.println("Thread has been interrupted");
            }
            System.out.println("Main thread finished...");
    }
}

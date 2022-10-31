package Less_25_ch_12_Interruption;
/*
Пример напоминалка о том, что если основная функциональность заключена в классе,
который реализует интерфейс Runnable, то там можно проверять статус потока с
помощью метода Thread.currentThread().isInterrupted().

Так же нужно помнить, что при получении статуса потока с помощью метода isInterrupted()
нужно учитывать, что если мы обрабатываем в цикле исключение InterruptedException в
блоке catch, то при перехвате исключения статус потока автоматически сбрасывается с true
на false, и после этого isInterrupted будет возвращать false.
*/
import Less_25_ch_12_Interruption.MyThreadClasses.MyDoubleInterruptThread;

public class Less_25_Interruption_Step3 {

    public static void main(String[] args) throws InterruptedException {

            System.out.println("Main thread started...");
            MyDoubleInterruptThread myThread = new MyDoubleInterruptThread();
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

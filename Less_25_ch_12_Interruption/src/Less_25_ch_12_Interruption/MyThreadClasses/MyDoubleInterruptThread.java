package Less_25_ch_12_Interruption.MyThreadClasses;

public class MyDoubleInterruptThread implements Runnable {

    public void run(){

        System.out.printf("%s started... \n", Thread.currentThread().getName());
        int counter=1; // счетчик циклов
        while(!Thread.currentThread().isInterrupted()){

            System.out.println("Loop " + counter++);
            try{
                Thread.sleep(100);
            }
            catch(InterruptedException e){
                System.out.println(Thread.currentThread().getName() + " has been interrupted");
                System.out.println(Thread.currentThread().isInterrupted());    // false
                Thread.currentThread().interrupt();    // Повторно прерываем текущий поток
            }
        }
        System.out.printf("%s finished... \n", Thread.currentThread().getName());
    }
}

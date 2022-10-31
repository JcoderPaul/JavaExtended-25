package Less_25_ch_1_ClassThread.MyThreads;

public class ThreadWithSleep extends Thread{

    public ThreadWithSleep(String name_of_tread) {
        super(name_of_tread);
    }

    public void run(){
        /*
        Метод sleep усыпляет поток на котором был вызван на заданное количество милли секунд,
        он выбрасывает исключение и требует обработки.
        */
        System.out.printf("%s started... \n", Thread.currentThread().getName());
        try{
            Thread.sleep(500);
        }
        catch(InterruptedException e){
            System.out.println("Thread has been interrupted");
        }
        System.out.printf("%s finished... \n", Thread.currentThread().getName());
    }
}

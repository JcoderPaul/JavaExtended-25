package Less_25_ch_15_Semaphore.MyClasses;

import java.util.concurrent.Semaphore;

// Класс создан паттерном "Синглтон"
public class Connections {
    private static Connections connection = new Connections();
    /*
    Поскольку паттерн синглтон, то объект будет всегда в единственном
    экземпляре и нет смысла делать счетчик статическим, привязывать к
    классу.
     */
    private int connectionCount;
    private Semaphore current_semaphore = new Semaphore(5);

    public Connections() {

    }

    public static Connections getConnection(){
        return connection;
    }

    public void doWork() throws InterruptedException {
        current_semaphore.acquire();
        try {
            synchronized (this){
                System.out.println(Thread.currentThread().getName() + " инкремент.");
                connectionCount++;
                System.out.println(connectionCount);
            }
            System.out.println("--------------- Поток '" + Thread.currentThread().getName() +
                    "' засыпает на 5 сек ---------------");
            Thread.sleep(5000);
            System.out.println("--------------- Поток '" + Thread.currentThread().getName() +
                    "' проснулся перед декрементом ---------------");
            synchronized (this){
                System.out.println(Thread.currentThread().getName() + " декремент.");
                connectionCount--;
                System.out.println(connectionCount);
            }
        } finally {
            current_semaphore.release();
        }
    }
}

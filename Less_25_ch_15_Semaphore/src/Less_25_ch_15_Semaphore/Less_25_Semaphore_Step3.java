package Less_25_ch_15_Semaphore;

import Less_25_ch_15_Semaphore.MyClasses.Connections;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Less_25_Semaphore_Step3 {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService my_executor_service = Executors.newFixedThreadPool(10);
        Connections my_conection = Connections.getConnection();
        for(int i = 0; i < 10; i++){
            my_executor_service.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        my_conection.doWork();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        my_executor_service.shutdown();
        my_executor_service.awaitTermination(10, TimeUnit.SECONDS);
    }
}

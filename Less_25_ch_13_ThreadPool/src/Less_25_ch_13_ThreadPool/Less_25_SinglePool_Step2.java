package Less_25_ch_13_ThreadPool;
/*
Пример создания пула потоков через - Executors.newSingleThreadExecutor()
*/
import Less_25_ch_13_ThreadPool.MyClasses.FirstRunner;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Less_25_SinglePool_Step2 {
    public static void main(String[] args) throws InterruptedException {
        long before = System.currentTimeMillis();

        System.out.println("Main starts!");
        /*
        В данном случае мы создали пул потоков не с помощью NEW, а с помощью класса Executors и
        его 'фабричного' метода *.newSingleThreadExecutor, который генерирует только один поток.
        */
        ExecutorService my_executor = Executors.newSingleThreadExecutor();
        for(int i = 0; i < 10; i++){
            // Запускаем работу нашего ExecutorService
            my_executor.execute(new FirstRunner());
        }
        // !!! Обязательно завершаем работу нашего ExecutorService !!!
        my_executor.shutdown();
        /*
        boolean awaitTermination(long timeout, TimeUnit unit) - Метод блокирует текущий поток
        до тех пор, пока все задачи не завершат выполнение после запроса на завершение работы
        или пока не наступит тайм-аут или не будет прерван текущий поток, в зависимости от того,
        что произойдет раньше.

        ( В данном примере это некий аналог 'thread.join()' )
        */
        my_executor.awaitTermination(5, TimeUnit.SECONDS);

        long after = System.currentTimeMillis();
        System.out.println("Время выполнения операции : " + (after - before));

        System.out.println("Main finish!");
    }
}

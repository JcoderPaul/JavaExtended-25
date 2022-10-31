package Less_25_ch_13_ThreadPool;
/*
Пример создания пула потоков через - Executors.newScheduledThreadPool(Tread N)
и его метода *.scheduleWithFixedDelay(TimerTask task, long delay, long period)
*/
import Less_25_ch_13_ThreadPool.MyClasses.SecondRunner;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Less_25_ScheduledPool_Step4 {
    public static void main(String[] args) throws InterruptedException {
        long before = System.currentTimeMillis();

        System.out.println("Main starts!");
        /*
        В данном случае мы создали пул потоков не с помощью NEW, а с помощью класса Executors и
        его 'фабричного' метода *.newFixedThreadPool(Thread N), где N - количество создаваемых
        фабрикой потоков.
        */
        ScheduledExecutorService my_scheduled_executor = Executors.newScheduledThreadPool(1);
            /*
            В отличии от *.execute(), метод *.schedule() так же запускает потоки, но принимает
            три аргумента: задачу, задержку и промежуток времени задержки. Метод *.schedule()
            используется для планирования запуска задачи после фиксированной задержки.

            При использовании данного метода задание выполниться один раз и все.
            */
            my_scheduled_executor.schedule(new SecondRunner(), 3,TimeUnit.SECONDS);

            /*
            Метод *.scheduleWithFixedDelay(TimerTask task, long delay, long period) используется для
            планирования задачи после начальной задержки, а затем выполнения задач с фиксированной
            задержкой после завершения предыдущей задачи. В отличие от *.scheduleAtFixedRate() метода,
            тут:
            - task - выполняемое задание;
            - delay - задержка перед запуском задания;
            - period - периодичность запуска нашего задания, причем это разница по времени между
                       концом первого задания и началом второго (между концом n-го и началом
                       (n + 1) -го, ИМЕННО между концом одного задания и началом другого) ;
            */
            my_scheduled_executor.scheduleWithFixedDelay(new SecondRunner(),3,1,TimeUnit.SECONDS);

        /*
        Что бы увидеть, как работает методы *.scheduleAtFixedRate() и scheduleWithFixedDelay()
        немного усыпим поток MAIN, перед методом *.shutdown(), иначе, после первого выполнения
        задания, пул потоков будет остановлен, т.к. вроде бы заданий то больше нет, а про
        периодичность никто не говорил методу *.shutdown().
        */
        Thread.sleep(10000);
        // !!! Обязательно завершаем работу нашего ExecutorService !!!
        my_scheduled_executor.shutdown();
        /*
        boolean awaitTermination(long timeout, TimeUnit unit) - Метод блокирует текущий поток
        до тех пор, пока все задачи не завершат выполнение после запроса на завершение работы
        или пока не наступит тайм-аут или не будет прерван текущий поток, в зависимости от того,
        что произойдет раньше.

        ( В данном примере это некий аналог 'thread.join()' )
        */
        my_scheduled_executor.awaitTermination(5, TimeUnit.SECONDS);

        long after = System.currentTimeMillis();
        System.out.println("Время выполнения операции : " + (after - before));

        System.out.println("Main finish!");
    }
}

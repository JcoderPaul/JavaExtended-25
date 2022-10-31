package Less_25_ch_16_CountDownLatch;

import Less_25_ch_16_CountDownLatch.MyOwnClasses.Processor;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Less_25_CountDownLatch_Step3 {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("*** Main thread start! ***");

        /*
        «Защелки» CountDownLatch используются для того, чтобы раньше времени «не выскочить»
        на метод завершения выполнения *.shutdown(). Метод защелок *.await() тормозит этот
        выход, переводя программу в ожидание завершения работы потока. После того, как все
        задачи будут выполнены, пул закрывается и программа завершает свою работу.
        */
        CountDownLatch my_count_down = new CountDownLatch(3);
        ExecutorService my_executor = Executors.newFixedThreadPool(3);

        for (int i = 0; i < 3; i++){
            /*
            Наибольший интерес в интерфейсе ExecutorService представляет метод submit(), который
            ставит задачу в очередь на выполнение. В качестве входного параметра данный метод
            принимает объект типа Callable или Runnable, а возвращает параметризованный объект
            типа Future, который можно использовать для доступа к результату выполнения задачи.
            */
            my_executor.submit(new Processor(i, my_count_down));
        }
        /*
        Стоит обратить внимание на метод *.shutdown(), который выполняет остановку объекта ExecutorService.
        Поскольку потоки в объекте ExecutorService не останавливаются сами, как обычно, поэтому их необходимо
        явно остановить с помощью данного метода; при этом, если в ExecutorService находятся невыполненные
        задачи, то потоки будут остановлены только, когда завершится последняя задача.

        Вызов метода *.shutdown() очень важен. Если его не использовать, то программа не смогла бы завершиться,
        поскольку исполнитель оставался бы активным. В этом можно убедиться, закомментировав вызов метода
        *.shutdown().
        */
        my_executor.shutdown();

        for (int i = 0; i < 3; i++){
            Thread.sleep(1000);
            /*
            Метод «защелки» *.countDown() уменьшает счетчик. При достижении счетчиком порогового
            значения (COUNT) метод *.await() «снимает защелку». После того, как «защелки» всех
            потоков сняты, и они отработали, пул закрывается.

            Всякий раз, когда вызывается метод *.countDown(), отсчет, связанный с вызывающим объектом,
            уменьшается на единицу.
            */
            System.out.println(i + " - вызов метода обратного отсчета *.countDown()");
            my_count_down.countDown(); // Тут метод обратного отсчета вызывается 3-и раза в цикле.
        }
        my_executor.awaitTermination(1, TimeUnit.SECONDS);
        System.out.println("*** Main thread finish! ***");
    }
}

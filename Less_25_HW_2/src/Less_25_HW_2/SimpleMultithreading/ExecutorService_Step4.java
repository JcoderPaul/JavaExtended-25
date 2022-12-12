package Less_25_HW_2.SimpleMultithreading;
/*
Еще более изящное решение создания и запуска в работу нескольких
потоков это класс ExecutorService.

Более подробно и с другими примерами:
https://github.com/JcoderPaul/JavaExtended-25/tree/master/Less_25_ch_13_ThreadPool/src/Less_25_ch_13_ThreadPool
*/
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorService_Step4 {
    public static void main(String[] args) {
        // Создали планировщик задач
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            final int index = i;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("Start - " + index);
                    /*
                    Если убрать задержку, то фактически мы получим
                    нечто похожее на самый первый вариант, когда
                    потоки запускались и останавливались последовательно,
                    по крайней мере согласно отображению информации на
                    экране.
                    */
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Finish - " + index);
                    // Уменьшаем счетчик на единицу
                    countDownLatch.countDown();
                }
            });
        }
        /*
        Остановили работу планировщика задач.
        !!! Запущенный ExecutorService всегда нужно останавливать !!!
        */
        executorService.shutdown();
        try {
            /*
            Останавливаем работу основного потока, до тех
            пор пока значение счетчика countDownLatch не
            станет равно нулю.
            */
            countDownLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("All threads are terminated");
    }
}

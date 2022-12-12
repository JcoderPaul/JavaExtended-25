package Less_25_HW_2.SimpleMultithreading;
/*
Решение задачи при помощи списка интересно,
но громоздко. Можно сделать короче. При помощи
объекта класса CountDownLatch обратный счетчик.

Более подробно и с другими примерами:
https://github.com/JcoderPaul/JavaExtended-25/tree/master/Less_25_ch_16_CountDownLatch/src/Less_25_ch_16_CountDownLatch
*/
import java.util.concurrent.CountDownLatch;

public class CountDownLatch_Step3 {
    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            final int index = i;
            new Thread(new Runnable() {
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
            }).start();
        }
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

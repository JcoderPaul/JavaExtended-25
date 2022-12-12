package Less_25_HW_2.CyclicBarrier;
/*
Простой пример циклического барьера
*/
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrier_Step1 {
    public static void main(String[] args) {
        // Стартуем основной поток
        System.out.println("Program start");
        // Инициализируем циклический барьер
        CyclicBarrier cyclicBarrier = new CyclicBarrier(4);
        // Создаем цикл где будет запущено 20 итераций, в каждой по 1-му потоку
        for (int i = 0; i < 20; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    // Генерируем случайное число на которое уснет текущий поток (в мс.)
                    long millis = (long)(Math.random() * 5000 + 1000);
                    // Извлекаем имя текущего потока и передаем на экран (в консоль)
                    String name = Thread.currentThread().getName();
                    System.out.println(name + ": Data is being prepared");
                    try {
                        /*
                        Тут начинается самое интересное. Если сообщение о том,
                        что 'данные готовятся' вылетают в консоль подряд, то
                        начиная с этого момента, каждый из потоков засыпает на
                        свое случайно полученное время.
                        */
                        Thread.sleep(millis);
                        // После того как поток проснется, он выводит текущее сообщение...
                        System.out.println(name + ": Data is ready");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    try {
                        /*
                        ... и добирается до данной части кода, для наглядности счетчик
                        циклического барьера мы выводим на экран. Т.к. время пробуждения
                        у всех 20 потоков разное, то ...
                        */
                        int count = cyclicBarrier.getNumberWaiting();
                        System.out.println("Barrier counter -> " + count);
                        if (count == 3) {
                            System.out.println("-----------------------------------------------");
                        }
                        /*
                        ... до данной части кода они добираются кто раньше, кто позже, однако
                        каждый вновь прибывший увеличивает счетчик +1. В нашем случае 'барьер
                        ломается' при наборе 4-х потоков. Т.е. как только мы набираем критическую
                        массу потоков (у нас 4-и), работа каждого из них продолжается дальше и ...
                        */
                        cyclicBarrier.await();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    /*
                    ... барьер ломается, но только для этих 4-х потоков и каждый из них
                    добирается до этой части кода.

                    Далее происходит обнуление счетчика и следующий набор критической массы,
                    циклического барьера, для следующих 4-х проснувшихся потоков, при этом,
                    даже если проснулось их больше 4-х, скажем 6-ть, происходит банальное
                    'кто первый встал, того и тапки', первые 4-и прибывшие к барьеру, сломают
                    его и завершат работу и т.д.
                    */
                    System.out.println(name + ": Continue work and finish thread!");
                    int count = cyclicBarrier.getNumberWaiting();
                    System.out.println("Barrier counter -> " + count);
                }
            }).start();
        }

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Program finish");
    }
}

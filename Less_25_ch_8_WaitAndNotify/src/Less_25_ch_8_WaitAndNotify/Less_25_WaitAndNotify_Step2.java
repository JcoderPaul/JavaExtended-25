package Less_25_ch_8_WaitAndNotify;

import Less_25_ch_8_WaitAndNotify.MyClasses.ConsumerAndProducer;

/**
 * Код написанный ниже напоминает по функциональности код описанный в
 * Less_25_WaitAndNotify_Step1, конечно со своими дополнениями, но суть
 * работы не изменилась.
 *
 * (Правда тут мы имеем бесконечное заполнение очереди и ее очистку)
 *
 * Очередь не может переполниться, добавить элемент в очередь можно только
 * если в ней меньше заранее оговоренного количества элементов. Producer в
 * потоке генерирует элементы и добавляет в очередь, как только очередь
 * заполнилась поток 'засыпает' и передает управление очередью Consumer.
 * Который в свою очередь забирает из очереди ПЕРВЫЙ элемент (FiFo).
 *
 * Если в очереди нет элементов, то поток, в котором работает Consumer,
 * засыпает и передает управление Producer.
 *
 * Тут так же применен lock на объекте Object и естественно методы wait и notify
 * вызываются на объекте 'замка' - lock. Значит на нем же происходит и синхронизация
 * вынесенная теперь в synchronized блоки.
 */
public class Less_25_WaitAndNotify_Step2 {
    public static void main(String[] args) {
        /*
        Создаем объект из класса ConsumerAndProducer, где будет находиться наш 'lock Object'
        */
        ConsumerAndProducer my_CaP_bloc = new ConsumerAndProducer();

        // Создаем поток запускающий метод producer(), при помощи анонимного класса
        Thread my_first_thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    my_CaP_bloc.produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // Создаем поток запускающий метод consumer
        Thread my_second_thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    my_CaP_bloc.consumer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        /*
        Запускаем оба потока, методом *.start() без него созданные потоки не запустятся!!!
        Не забывай об этом!!!
        */
        my_first_thread.start();
        my_second_thread.start();

        try {
            // join() — Waits for this thread to die.
            my_first_thread.join();
            my_second_thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

package Less_25_ch_8_WaitAndNotify.MyClasses;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class ConsumerAndProducer {
    /*
    Создаем очередь условно безразмерную, однако, за счет ограничений, в
    программе будет введено ограничение на количество элементов очереди.
    */
    private final Queue<Integer> my_queue_test = new LinkedList<>();
    /*
    Согласно сигнатуре переменной QueueLimit мы создали константу,
    которая будет ограничивать количество элементов в очереди.
    */
    private final int QueueLimit = 10;
    /*
    Создали замок, на мониторе данного объекта будет происходить синхронизация
    */
    private final Object lock = new Object();
    private Random rnd_for_queue = new Random();
    // Метод производитель (он заполняет очередь случайными числами)
    public void produce() throws InterruptedException {
        while (true){
            // Начало синхронизированного блока
            synchronized (lock){
                // Если очередь полна, т.е. имеет 10 элементов, поток уходит в ожидание wait()
                while (my_queue_test.size() == QueueLimit){
                    // Поток отправляется в спячку до получения notify()
                    lock.wait();
                }
                // Помещаем в очередь случайную int переменную от 0 до 99
                my_queue_test.offer(rnd_for_queue.nextInt(100));
                /*
                Освобождаем монитор, метод здесь, ниже и выше
                вызывается на объекте 'замке' - lock
                */
                lock.notify();
            }
            // Усыпляем поток на неопределенное количество миллисекунд в пределах от 0 до 499
            Thread.sleep(rnd_for_queue.nextInt(500));
        }
    }
    // Метод потребитель (он освобождает очередь)
    public void consumer() throws InterruptedException {
        while (true){
            // Начало блока синхронизации
            synchronized (lock){
                // Если очередь пуста ждем
                while (my_queue_test.size() == 0){
                    lock.wait();
                }
                // Выводим на экран состояние элементов очереди
                System.out.println(my_queue_test);
                /*
                poll(): возвращает элемент из начала очереди с последующим его удалением.
                Если очередь пуста, возвращает значение null. Т.е. тут мы забрали один элемент
                из головы очереди и вывели на экран.
                */
                int value = my_queue_test.poll();
                System.out.println(value);
                // Отразили размер очереди
                System.out.println("Queue size is -> " + my_queue_test.size());
                // Оповестили другой поток, о том что монитор свободен.
                lock.notify();
            }
            // Усыпляем поток на неопределенный срок в пределах от 0 до 599 миллисекунды.
            Thread.sleep(rnd_for_queue.nextInt(600));
        }
    }
}

package Less_25_ch_3_ThreadNamePriority;
/*
Никогда не запускать поток через *.run, если нет в этом странной
необходимости, есть метод *.start()
*/
import Less_25_ch_3_ThreadNamePriority.MyThreadsClasses.ForRunTestOne;
import Less_25_ch_3_ThreadNamePriority.MyThreadsClasses.ForRunTestTwo;

public class Less_25_NeverRunLikeThis_Step3 {
    public static void main(String[] args) {
        System.out.println("-------------- Запуск потока через *.start() --------------");
        System.out.println("Hello from MAIN -> " + Thread.currentThread().getName());
        /*
        Создаем поток, запускаем метод *.start(), смотрим, что ушло на экран.
        */
        Thread mtrd_1 = new Thread(new ForRunTestOne());
        mtrd_1.start();
        System.out.println("-------------- Запуск потока через *.run() --------------");
        /*
        Создаем поток, запускаем метод *.run(), смотрим, что ушло на экран.
        */
        Thread mtrd_2 = new Thread(new ForRunTestTwo());
        mtrd_2.run();
        /*
        Отличия явные:
        - В первом случае 'mtrd_1' запущенный через *.start() показывает,
        что сформировался и заработал поток Thread-0.
        - Во втором случае запущенный *.run() метод на объекте 'mtrd_2'
        не запускает новых потоков, и продолжает работу поток MAIN
        */

    }
}

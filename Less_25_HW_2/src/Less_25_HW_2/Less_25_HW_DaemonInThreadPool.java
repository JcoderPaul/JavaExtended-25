package Less_25_HW_2;
/*
Как разместить поток - демон в ExecutorService.
*/
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class Less_25_HW_DaemonInThreadPool {
    public static void main(String[] args) {
        /*
        ШАГ 1 - Создаем ExecutorService из одного потока.

        Далее передаем в него фабрику потоков или ThreadFactory().
        В данном классе мы переопределяем метод 'новый поток',
        который принимает в качестве аргумента класс подписанный на
        Runnable.
        */

        ExecutorService executorService = Executors.newSingleThreadExecutor(new ThreadFactory() {
            @Override
            public Thread newThread(Runnable myRunnableFromMainCode) {
                /*
                Шаг 3 (!!! именно 3 !!!) - Получаем объект Runnable
                созданный на шаге 2, см. ниже и передаем его в новый
                поток, созданный на данном шаге.
                */
                Thread daemonThread = new Thread(myRunnableFromMainCode);
                // Шаг 4 - Объявляем созданный поток демоном
                daemonThread.setDaemon(true);
                // Шаг 5 - Возвращаем созданный поток
                return daemonThread;
            }
        });
        /*
        Шаг 2 - Передаем задачу в пул потоков, через метод
        *.execute(), объект подписанный на Runnable.
        */
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                int seconds = 0;
                try {
                    while (true) {
                        System.out.println(seconds);
                        Thread.sleep(1000);
                        seconds++;
                    }
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });
        // В основном MAIN потоке выводим в цикле '. . .' с задержкой 1 сек.
        for (int i = 0; i < 10; i++) {
            System.out.println(". . .");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }

        }
        System.out.println("All threads are terminated");
    }
}

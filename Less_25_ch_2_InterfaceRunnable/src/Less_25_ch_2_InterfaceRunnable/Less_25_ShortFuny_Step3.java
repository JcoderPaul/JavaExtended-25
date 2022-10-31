package Less_25_ch_2_InterfaceRunnable;

import Less_25_ch_2_InterfaceRunnable.MyThreads.MyRunner;

/**
 * Многопоточность в JAVA отнюдь не ограничена классом Thread.
 *
 * В контексте определённой задачи может быть выгоднее наследовать какой-то другой класс,
 * но множественное наследование в JAVA не поддерживается, выход: implements Runnable
 * Интерфейс Runnable имеет посредственное отношение к потокам - его следует расценивать
 * как передаваемую функцию, которая может быть выполнена где-то в другом месте
 * (поток, очередь, класс, метод и т.п.)
 *
 * Thread - это абстракция над физическим потоком.
 * Runnable - это абстракция над выполняемой задачей.
 *
 * Плюс использования Runnable состоит в том, что это позволяет логически отделить
 * выполнение задачи от логики управления потоками.
 **/
public class Less_25_ShortFuny_Step3 implements Runnable{

    public static void main(String[] args) {
        System.out.println("!!! Стартует метод MAIN !!!");
            /*
            Создаем объект класса Less_25_ShortFuny_Step3 подписанный на интерфейс Runnable.
            Создаем объект класса поток Thread, и в качестве аргумента передаем наш только
            что созданный объект. Т.е. отпачковали один поток от основного.
            Пока все как в прошлом примере...
            */
            Less_25_ShortFuny_Step3 my_first_runner = new Less_25_ShortFuny_Step3();
            Thread my_thread_1 = new Thread(my_first_runner);
            my_thread_1.start(); // И запускаем отдельный поток

            /*
            Внутри метода MAIN (ведь это отдельный поток) запускаем обратный цикл, как это было
            реализовано ранее в отдельном классе.
            */
            for (int i = 1000; i > 0; i--) {
            /*
            Метод *.sleep(время в миллисекундах) замораживает выполнение потока
            на заданное в методе время.
            */
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Hello from thread : '" + Thread.currentThread().getName() +
                                   "', значение i -> " + i);
        }

        System.out.println("!!! Метод MAIN закончил работу !!!");
        
        }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            /*
            Метод *.sleep(время в миллисекундах) замораживает выполнение потока
            на заданное в методе время.
            */
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Hello from thread : '" + Thread.currentThread().getName() + "', значение i -> " + i);
        }
    }
}

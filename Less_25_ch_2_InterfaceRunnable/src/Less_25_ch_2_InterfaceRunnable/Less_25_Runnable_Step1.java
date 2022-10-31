package Less_25_ch_2_InterfaceRunnable;

import Less_25_ch_2_InterfaceRunnable.MyThreads.FirstThread;
import Less_25_ch_2_InterfaceRunnable.MyThreads.SecondThread;

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
public class Less_25_Runnable_Step1 {
    public static void main(String[] args) {
        /*
        В только что созданный объект класса Thread, передаем в качестве аргумента
        объекты имплементирующие интерфейс Runnable. Можно сразу, можно и по классике,
        через ссылку.
        */
        Thread thread_first = new Thread(new FirstThread()); // Налету

        Runnable second_runnable_thread = new SecondThread(); // В два этапа
        Thread thread_second = new Thread(second_runnable_thread);

        thread_first.start(); // Стартуем оба потока
        thread_second.start();
    }
}

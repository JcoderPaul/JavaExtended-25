package Less_25_ch_2_InterfaceRunnable;

import Less_25_ch_2_InterfaceRunnable.MyThreads.*;

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
public class Less_25_Runnable_Step2 {
    public static void main(String[] args) {
        System.out.println("!!! Стартует метод MAIN !!!");
            /*
            Создаем объект класса MyRunner подписанный на интерфейс Runnable.
            Создаем объект класса поток Thread, и в качестве аргумента
            передаем наш только что созданный объект MyRunner.
            Пока все как в прошлом примере...
            */
            MyRunner my_first_runner = new MyRunner("FIRST");
            Thread my_thread_1 = new Thread(my_first_runner);
            /*
            Создаем второй объект нашего класса Thread, как и в первом случае,
            только чуть короче (как обычно, если есть возможность создать объект
            внутри создаваемого объекта делаем это).
            */
            Thread my_thread_2 = new Thread(new MyRunner("SECOND"));
            /*
            А вот и ништячек, создаем еще поток на базе уже созданной
            переменной my_first_runner на объект MyRunner.
            */
            Thread my_thread_3 = new Thread(my_first_runner);
            /*
            Стартуем все потоки, поскольку внутри метода *.run() класса MyRunner
            введена задержка *.sleep(), то потоки выводят информацию более менее равномерно.
            */
            my_thread_1.start();
            my_thread_2.start();
            my_thread_3.start();

        System.out.println("!!! Метод MAIN закончил работу !!!");

        }
}
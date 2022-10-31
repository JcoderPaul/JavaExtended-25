package Less_25_ch_1_ClassThread;

import Less_25_ch_1_ClassThread.MyThreads.UniversalThread;

public class Less_25_MakeThread_Step2 {
    public static void main(String[] args) {
        // Создаем объект нашего класса MyThread
        UniversalThread my_thread_1 = new UniversalThread("FIRST");
        // Создаем второй объект нашего класса MyThread
        UniversalThread my_thread_2 = new UniversalThread("SECOND");
        // Стартуем обы потока
        my_thread_1.start();
        my_thread_2.start();
        /*
        Из вывода в консоль видно, что потоки печатают свои сообщения
        не один за другим, а в перемешку - то первый, то второй
        и при каждом запуске программы результат вывода на экран разный.

        Порядок расположения команд *.start() не гарантирует первостепенность
        запуска, например my_thread_1, успел перехватить процессор работает,
        нет, ждет своей очереди.

        Конкуренция между потоками без дополнительных методов (сихронизации и т.д.)
        происходит постоянно.

        Но по факту работает не 2-а, а 3-и потока - основной MAIN + 2-а наших.
        */
        System.out.println("Hello from method MAIN!");
    }
}

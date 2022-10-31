package Less_25_ch_3_ThreadNamePriority;
/*
Игры с именем и приоритетом потоков.

- getName(): возвращает имя потока;
- getPriority(): возвращает приоритет потока;

Смотрим на имя и приоритет потоков.
*/
import Less_25_ch_3_ThreadNamePriority.MyThreadsClasses.MyThread;

public class Less_25_NamePriority_Step1 {
    public static void main(String[] args) {
        System.out.println("------------------- MAIN поток -------------------");
        System.out.println("*** Стартовал MAIN !!! ***");
        System.out.println("MAIN name -> " + Thread.currentThread().getName());
        System.out.println("Стартовал MAIN");
        System.out.println("MAIN priority -> " + Thread.currentThread().getPriority());

        System.out.println("------------------- Создали myThreadFirst -------------------");
        MyThread myThreadFirst = new MyThread();
        System.out.println("Name of thread -> " + myThreadFirst.getName());
        System.out.println("Priority of thread -> " + myThreadFirst.getPriority());
        System.out.println("------------------- Стартанули myThreadFirst -------------------");
        myThreadFirst.start();
        System.out.println("Name of thread -> " + myThreadFirst.getName());
        System.out.println("Priority of thread -> " + myThreadFirst.getPriority());

        System.out.println("------------------- Создали myThreadSecond -------------------");
        MyThread myThreadSecond = new MyThread();
        System.out.println("Name of thread -> " + myThreadSecond.getName());
        System.out.println("Priority of thread -> " + myThreadSecond.getPriority());
        System.out.println("------------------- Стартанули myThreadFirst -------------------");
        myThreadSecond.start();
        System.out.println("Name of thread -> " + myThreadSecond.getName());
        System.out.println("Priority of thread -> " + myThreadSecond.getPriority());

        System.out.println("*** MAIN thread completed !!! ***");
        /*
        Из вывода на экран мы видим, у наших потоков есть дефолтные имена и дефолтный приоритет.
        Приоритет от 1 до 10, где 1 - низший приоритет, 10 наивысший. В текущем примере, все потоки
        имеют средний приоритет по умолчанию, мы ничего не задавали.

        Самое странное, что никто не гарантирует, что имея наивысший приоритет поток запустится раньше,
        чем его собрат с более низким приоритетом. См. след. пример.
        */
    }
}

package Less_25_ch_3_ThreadNamePriority;
/*
Игры с именем и приоритетом потоков.

- setName(String name): устанавливает имя потока;
- setPriority(int priority): устанавливает приоритет потока;

Задаем имя и приоритет.

Приоритет можно задавать через готовый приоритет
(например *.setPriority(Thread.MAX_PRIORITY) см. ниже.
*/
import Less_25_ch_3_ThreadNamePriority.MyThreadsClasses.MyThread;

public class Less_25_NamePriority_Step2 {
    public static void main(String[] args) {
        System.out.println("------------------- MAIN поток -------------------");
        System.out.println("*** Стартовал MAIN !!! ***");
        System.out.println("MAIN name -> " + Thread.currentThread().getName());
        System.out.println("Стартовал MAIN");
        System.out.println("MAIN priority -> " + Thread.currentThread().getPriority());
        System.out.println();
//*******************************************************************************************************
        MyThread myThreadFirst = new MyThread();
        myThreadFirst.setName("myThreadFirst");
        myThreadFirst.setPriority(3);
        System.out.println("------------------- Стартанули myThreadFirst -------------------");
        myThreadFirst.start();
        System.out.println("Name of thread -> " + myThreadFirst.getName());
        System.out.println("Priority of '" + myThreadFirst.getName() +
                           "' -> " + myThreadFirst.getPriority());

//*******************************************************************************************************
        MyThread myThreadSecond = new MyThread();
        myThreadSecond.setName("myThreadSecond");
        myThreadSecond.setPriority(8);
        System.out.println("------------------- Стартанули myThreadFirst -------------------");
        myThreadSecond.start();
        System.out.println("Name of thread -> " + myThreadSecond.getName());
        System.out.println("Priority of '" + myThreadSecond.getName() +
                "' -> " + myThreadSecond.getPriority());

//*******************************************************************************************************
        MyThread myThreadThird = new MyThread();
        myThreadThird.setName("myThreadThird");
        myThreadThird.setPriority(Thread.MAX_PRIORITY);
        System.out.println("------------------- Стартанули myThreadThird -------------------");
        myThreadThird.start();
        System.out.println("Name of thread -> " + myThreadThird.getName());
        System.out.println("Priority of '" + myThreadThird.getName() +
                "' -> " + myThreadThird.getPriority());
        System.out.println();
        System.out.println("*** MAIN thread completed !!! ***");
        System.out.println("------------------- MAIN поток -------------------");
        /*
        Из вывода на экран мы видим, у наших потоков есть дефолтные имена и дефолтный приоритет.
        Приоритет от 1 до 10, где 1 - низший приоритет, 10 наивысший. В текущем примере, все потоки
        имеют средний приоритет по умолчанию, мы ничего не задавали.

        Самое странное, что никто не гарантирует, что имея наивысший приоритет поток запустится раньше,
        чем его собрат с более низким приоритетом.
        */
    }
}

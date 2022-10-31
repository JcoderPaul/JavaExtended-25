package Less_25_ch_4_SleepJoin;
/*
- sleep(): приостанавливает поток на заданное количество миллисекунд;
*/
import Less_25_ch_4_SleepJoin.MyThreadMaker.*;

public class Less_25_SleepThread_Step1 {
    public static void main(String[] args) {
        System.out.println("* * * MAIN START !!! * * * ");
        MyThread myThread_1 = new MyThread(); // Создали поток из наследника Thread
        Thread myThread_2 = new Thread(new MyRunner()); // Создали поток из подписчика на Runnable
        myThread_1.start(); // Стартуем оба потока
        myThread_2.start();
        /*
        В данной ситуации основной поток выделил два отдельных потока, запустил их и сам завершился.
        Запущение потоки продолжают свою работу.
        */
        System.out.println("* * * MAIN is FINISHED !!! * * * ");

    }
}

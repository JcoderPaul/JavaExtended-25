package Less_25_ch_4_SleepJoin;
/*
- public final void join(): Метод приостановит выполнение текущего потока (обычно main) до тех пор,
  пока другой поток не закончит свое выполнение. Если поток прерывается, бросается InterruptedException.
- public final synchronized void join(long millis): Метод приостановит выполнение текущего потока
  (обычно main) на указанное время в миллисекундах. Выполнение этого метода зависит от реализации ОС,
  поэтому Java не гарантирует, что текущий поток будет ждать указанное время.
*/

import Less_25_ch_4_SleepJoin.MyThreadMaker.MyRunner;
import Less_25_ch_4_SleepJoin.MyThreadMaker.MyThread;

public class Less_25_JoinThread_Step2 {
    public static void main(String[] args) {
        System.out.println("* * * MAIN START !!! * * * ");
        MyThread myThread_1 = new MyThread(); // Создали поток из наследника Thread
        Thread myThread_2 = new Thread(new MyRunner()); // Создали поток из подписчика на Runnable
        myThread_1.start(); // Стартуем оба потока
        myThread_2.start();
        /*
        В данной ситуации основной поток выделил два отдельных потока, запустил их, и далее получил
        директиву ожидать завершения потоков 'myThread_1' и 'myThread_2' через метод *.join(). Т.е.
        до завершения работы двух выделенных потока основной поток MAIN не завершит своей работы.

        Стоит обратить внимание, на последовательность:
        - в потоке MAIN создается и запускается два дополнительных потока;
        - в потоке MAIN на этих созданных и запущенных потоках вызывается метод *.join();

        Метод join() заставляет вызвавший поток (в данном случае Main thread) ожидать завершения
        вызываемого потока, для которого и применяется метод join (в данном случае 'myThread_1'
        и 'myThread_2').

        !!! Если в программе используется несколько дочерних потоков, и надо, чтобы Main thread
        завершался после дочерних, то для каждого дочернего потока надо вызвать метод join !!!
        */
        try {
            myThread_1.join();
            myThread_2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("* * * MAIN is FINISHED !!! * * * ");

    }
}

package Less_25_ch_1_ClassThread;

import Less_25_ch_1_ClassThread.MyThreads.*;

public class Less_25_MakeThread_Step3 {
    public static void main(String[] args) {
        /*
        Как и в предыдущем примере следует обратить внимание на то, что основной поток
        MAIN уже отработал и вывел сообщения на экран, хотя два других, вроде бы, не
        основных потока, продолжают работу, это нормально.

        Помним, работает не 2-а созданных нами вручную, а 3-и потока: основной MAIN + 2-а наших.
        */
        System.out.println("!!! MAIN thread started... !!!");
        /*
        В классе наследнике Thread реализован метод *.Run(), но запускается поток с помощью
        метода *.start().
        */
        new ThreadWithSleep("First sleeping").start();
        new ThreadWithSleep("Second sleeping").start();
        System.out.println("!!! MAIN thread finished... !!!");
    }
}

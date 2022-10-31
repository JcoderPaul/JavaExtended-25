package Less_25_ch_4_SleepJoin;
/*
- join(): ожидает завершение потока;
*/

import Less_25_ch_4_SleepJoin.MyThreadMaker.MyRunner;
import Less_25_ch_4_SleepJoin.MyThreadMaker.MySleepingThread;
import Less_25_ch_4_SleepJoin.MyThreadMaker.MyThread;

public class Less_25_JoinParametr_Step3 {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("* * * '" + Thread.currentThread().getName().toUpperCase() + "' is START !!! * * * ");
        MySleepingThread myThread_1 = new MySleepingThread();
        myThread_1.setTime_of_sleep(2500);
        myThread_1.start();

        MySleepingThread myThread_2 = new MySleepingThread();
        myThread_2.setTime_of_sleep(4000);
        myThread_2.start();
        /*
        В данном примере в методе *.join(Params) задан временной параметр, который говорит,
        что основной поток должен ожидать два варианта исхода ситуации:
        - либо дождаться окончания потока на котором вызван метод join(Params);
        - либо дождаться истечения временной задержки 'Params' установленной у метода join(Params);
        Основной поток (либо поток, внутри которого запущен другой поток) продолжит свою работу, по
        наступлении одного из исходов (условий).
        */

        /*
        Тут играя параметром 'Params(millis) - the time to wait in milliseconds' мы можем наблюдать
        за тем, как взаимодействуют три потока, кто и когда стартовал и финишировал.
        */
        myThread_1.join(500);
        myThread_2.join(2000);

        System.out.println("* * * '" + Thread.currentThread().getName().toUpperCase() + "' is FINISHED !!! * * * ");

    }
}

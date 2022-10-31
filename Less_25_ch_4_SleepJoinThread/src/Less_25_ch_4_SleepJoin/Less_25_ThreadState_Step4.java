package Less_25_ch_4_SleepJoin;
/*
- join(): ожидает завершение потока;
*/

import Less_25_ch_4_SleepJoin.MyThreadMaker.MySleepingThread;

public class Less_25_ThreadState_Step4 {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("* * * '" + Thread.currentThread().getName().toUpperCase() + "' is START !!! * * * ");
        MySleepingThread myThread_1 = new MySleepingThread();
        myThread_1.setTime_of_sleep(2500);

        System.out.println("Before start -> '" + myThread_1.getName() + "' is " + myThread_1.getState());
        myThread_1.start();

        System.out.println("After start -> '" + myThread_1.getName() + " is " + myThread_1.getState());
        myThread_1.join(500);

        System.out.println("After join -> '" + myThread_1.getName() + " is " + myThread_1.getState());
        System.out.println("* * * '" + Thread.currentThread().getName().toUpperCase() + "' is FINISHED !!! * * * ");
        System.out.println("After end of MAIN -> '" + myThread_1.getName() + " is " + myThread_1.getState());

    }
}

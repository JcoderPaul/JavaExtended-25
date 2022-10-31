package Less_25_ch_4_SleepJoin.MyThreadMaker;

public class MySleepingThread extends Thread{

    private int time_of_sleep;

    @Override
    public void run() {
        System.out.println("--- '" + Thread.currentThread().getName() + "' is start ---");
        try {
            Thread.sleep(time_of_sleep);
            System.out.println(Thread.currentThread().getName() + " засыпает на " + time_of_sleep);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("--- '"+ Thread.currentThread().getName() + "' is finished ---");
    }

    public void setTime_of_sleep(int time_of_sleep) {
        this.time_of_sleep = time_of_sleep;
    }
}

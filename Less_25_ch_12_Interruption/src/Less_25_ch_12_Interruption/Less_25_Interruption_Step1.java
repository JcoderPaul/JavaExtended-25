package Less_25_ch_12_Interruption;

import Less_25_ch_12_Interruption.MyThreadClasses.InterruptedThread;
import Less_25_ch_12_Interruption.MyThreadClasses.NotInterruptedThread;
import Less_25_ch_12_Interruption.MyThreadClasses.SleepInterruptThread;

public class Less_25_Interruption_Step1 {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("MAIN Thread starts\n");
        NotInterruptedThread non_inter_trd = new NotInterruptedThread();
        InterruptedThread trd_for_interrupted = new InterruptedThread();
        SleepInterruptThread slp_trd_error = new SleepInterruptThread();
        slp_trd_error.start();
        trd_for_interrupted.start();
        non_inter_trd.start();
        Thread.sleep(2000);
        /*
        Это не прерывание потока, это только желание основного потока прервать
        поток 'inter_trd'. Для фактического завершения потока или для продолжения
        его работы мы пишем соответствующий код и делаем необходимые проверки перед
        этим.
        */
        non_inter_trd.interrupt();
        trd_for_interrupted.interrupt();
        slp_trd_error.interrupt();

        try {
            non_inter_trd.join();
            trd_for_interrupted.join();
            slp_trd_error.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("\nMAIN Thread finish");
    }
}

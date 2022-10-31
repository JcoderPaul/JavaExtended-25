package Less_25_ch_12_Interruption.MyThreadClasses;

public class NotInterruptedThread extends Thread{
    double sqrtSum = 0;

    @Override
    public void run() {
        for(int i = 1; i < 1_000_000_000; i++){

            sqrtSum += Math.sqrt(i);
        }

        if(isInterrupted()){
            System.out.println("Поток '" + Thread.currentThread().getName() +
                               "' пытаются прервать, чихали мы на это!");
        }

        System.out.println("Итоговый результаты расчетов '" + Thread.currentThread().getName() +
                           "' потока: " + sqrtSum);
    }
}

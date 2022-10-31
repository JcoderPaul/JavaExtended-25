package Less_25_ch_12_Interruption.MyThreadClasses;

public class InterruptedThread extends Thread{
    double sqrtSum = 0;

    @Override
    public void run() {
        for(int i = 1; i < 1_000_000_000; i++){
            if(isInterrupted()){
                System.out.println("Поток '" + Thread.currentThread().getName() +
                                   "' пытаются прервать, подчиняемся!");
                System.out.println("Результаты расчетов '" + Thread.currentThread().getName() +
                                   "' потока на момент остановки: " + sqrtSum);
                return;
            }
            sqrtSum += Math.sqrt(i);
        }
        System.out.println("Результаты расчетов '" + Thread.currentThread().getName() + "' потока: " + sqrtSum);
    }
}

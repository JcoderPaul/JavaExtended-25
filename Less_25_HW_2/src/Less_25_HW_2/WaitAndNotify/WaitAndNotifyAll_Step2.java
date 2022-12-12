package Less_25_HW_2.WaitAndNotify;
/*
Решение текущей задачи меньшим кодом,
логика работы методов и синхронизация
потоков аналогична WaitAndNotifyAll_Step1.
Однако для работы применяется более
технологичный пул потоков. Весь
повторяющийся код вынесен в отдельный
метод.
*/
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WaitAndNotifyAll_Step2 {

    private static final String strForFirstThread = "Квас для вас";
    private static final String strForSecondThread = " и нас, ";
    private static final String strForThirdThread = "ядреный, аж слезы";
    private static final String strForFourthThread = " из глаз! ";
    private static final Object monitor = new Object();
    private static String nextSubString = strForFirstThread;
    private static ExecutorService executorService = Executors.newFixedThreadPool(4);

    public static void main(String[] args) {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                outputOnDisplay(strForFirstThread,strForSecondThread);
            }
        });
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                outputOnDisplay(strForSecondThread,strForThirdThread);
            }
        });
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                outputOnDisplay(strForThirdThread,strForFourthThread);
            }
        });
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                outputOnDisplay(strForFourthThread,strForFirstThread);
            }
        });

        executorService.shutdown();
    }

    private static void outputOnDisplay(String prevSbStr, String nextSbStr) {
        synchronized (monitor) {
            for (int i = 0; i < 5; i++) {
                try{
                    while (!nextSubString.equals(prevSbStr)){
                        monitor.wait();
                    }
                } catch (InterruptedException ex){
                    ex.printStackTrace();
                }
                System.out.print(prevSbStr);
                if(prevSbStr.equals(strForFourthThread)){
                    System.out.println();
                }
                nextSubString = nextSbStr;
                monitor.notifyAll();
            }
        }
    }
}

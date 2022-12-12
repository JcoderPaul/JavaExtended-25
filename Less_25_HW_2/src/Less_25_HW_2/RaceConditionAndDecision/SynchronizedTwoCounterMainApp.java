package Less_25_HW_2.RaceConditionAndDecision;
/*
В двух потоках одновременно и увеличиваем и уменьшаем
счетчик в цикле. При этом метод счетчик оперирует двумя
независимыми переменными для подсчета. И они
синхронизированы на двух разных мониторах и выполняют
работу параллельно, т.е. фактически работают 4-и потока
по 2-а на каждый счетчик.

В данном примере разъясняется для чего существует, как
минимум две теоретически равносильные синтаксические
конструкции:
-----------------------------------------------------
 public void inc() {
        synchronized(this){
            value++;
        }
 }
-----------------------------------------------------
и
-----------------------------------------------------
public synchronized void inc() {
        value++;
}
-----------------------------------------------------
С точки зрения работы - очень похоже, с точки зрения
возможностей синхронизированный блок дает более широкое
поле для маневра, как показано в текущем примере.
*/
import Less_25_HW_2.RaceConditionAndDecision.Counter.SynchronizedCounterOn2Object_Step2;

public class SynchronizedTwoCounterMainApp {
    public static void main(String[] args) {
        SynchronizedCounterOn2Object_Step2 counter = new SynchronizedCounterOn2Object_Step2();
        long before = System.currentTimeMillis();
        int barrier = 100_000_000;
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < barrier; i++) {
                    counter.incOne();
                }
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < barrier; i++) {
                    counter.decOne();
                }
            }
        });
        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < barrier; i++) {
                    counter.incTwo();
                }
            }
        });
        Thread thread4 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < barrier; i++) {
                    counter.decTwo();
                }
            }
        });
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        try {
            thread1.join();
            thread2.join();
            thread3.join();
            thread4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(counter.getValueOne());
        System.out.println(counter.getValueTwo());
        long after = System.currentTimeMillis();
        System.out.println("Время работы программы: " + (after - before));
    }
}

package Less_25_HW_2.RaceConditionAndDecision;

import Less_25_HW_2.RaceConditionAndDecision.Counter.SynchronizedCounter_Step1;

/*
В двух потоках одновременно и увеличиваем
и уменьшаем счетчик в цикле. По идее должно
получиться в конце - 0. И поскольку, теперь
мы используем синхронизацию на объекте
'счетчик', наши потоки работают поочереди
уменьшая и увеличивая счетчик, то каждый
поток работает с актуальной версией счетчика
и результат совпал с ожиданием. А время
затраченное на работу программы выросло.
*/
public class SyncCounterMainApp {
    public static void main(String[] args) {
        SynchronizedCounter_Step1 sync_counter = new SynchronizedCounter_Step1();
        long before = System.currentTimeMillis();
        int barrier = 100_000_000;
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < barrier; i++) {sync_counter.inc();
                }
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < barrier; i++) {
                    sync_counter.dec();
                }
            }
        });
        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Данные счетчика: " + sync_counter.getValue());
        long after = System.currentTimeMillis();
        System.out.println("Время расчета: " + (after - before));
    }
}

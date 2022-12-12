package Less_25_HW_2.RaceConditionAndDecision;

import Less_25_HW_2.RaceConditionAndDecision.Counter.NotSynchronizedCounter_Step0;

/*
В двух потоках одновременно и увеличиваем
и уменьшаем счетчик в цикле. По идее должно
получиться в конце - 0. Но однако, одна рука
не ведает что творит другая. При этом ни тот
ни другой поток не ждут, когда его коллега
отпустит монитор и с задором увеличивают и
уменьшают счетчик, отсюда меньшее затраченное
время на выполнение программы.
*/
public class NonSyncCounterMainApp {
    public static void main(String[] args) {
        NotSynchronizedCounter_Step0 not_sync_counter = new NotSynchronizedCounter_Step0();
        long before = System.currentTimeMillis();
        int barrier = 100_000_000;
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < barrier; i++) {
                    not_sync_counter.inc();
                }
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < barrier; i++) {
                    not_sync_counter.dec();
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
        System.out.println("Данные счетчика: " + not_sync_counter.getValue());
        long after = System.currentTimeMillis();
        System.out.println("Время расчета: " + (after - before));
    }
}

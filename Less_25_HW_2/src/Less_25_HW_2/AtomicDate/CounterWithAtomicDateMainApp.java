package Less_25_HW_2.AtomicDate;

/*
Более подробно работа с Атомиками и их методами рассмотрена:
https://github.com/JcoderPaul/JavaExtended-25/tree/master/Less_25_ch_18_AtomicInteger
*/

public class CounterWithAtomicDateMainApp {
    public static void main(String[] args) {
        CounterWithAtomic atomic_counter = new CounterWithAtomic();
        long before = System.currentTimeMillis();
        int barrier = 100_000_000;
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < barrier; i++) {
                    atomic_counter.inc();
                }
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < barrier; i++) {
                    atomic_counter.dec();
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
        System.out.println("Данные счетчика: " + atomic_counter.getValue());
        long after = System.currentTimeMillis();
        System.out.println("Время расчета: " + (after - before));
    }
}

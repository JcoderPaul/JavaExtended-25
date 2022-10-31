package Less_25_ch_18_AtomicInteger;
/*
Применяем к предыдущему примеру AtomicInteger.
При каждом последующем запуске программы мы будем получать неизменный результат.
*/
import Less_25_ch_18_AtomicInteger.MyClasses.MyAtomicRunner;
import java.util.concurrent.atomic.AtomicInteger;

public class Less_25_AtomicInteger_Step2 {
    // Применяем AtomicInteger
    public static AtomicInteger counter = new AtomicInteger(0);
    public static AtomicInteger counterAdd = new AtomicInteger(0);
    public static AtomicInteger backCounter = new AtomicInteger(0);

    public static void main(String[] args) {
        Thread trd_1 = new Thread(new MyAtomicRunner());
        Thread trd_2 = new Thread(new MyAtomicRunner());
        trd_1.start();
        trd_2.start();

        try {
            trd_1.join();
            trd_2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(counter);
        System.out.println(counterAdd);
        System.out.println(backCounter);
    }

    public static void incrementMeth(){
        counter.incrementAndGet();
    }

    public static void incrementAddAndGetMeth(){
        counterAdd.addAndGet(5);
    }
    public static void decrementAndGetMeth(){
        backCounter.decrementAndGet();
    }
}
package Less_25_ch_18_AtomicInteger;
/*
Это еще не AtomicInteger, а только подготовка к нему.

Стандартный пример, где показана гонка данных без должной синхронизации
два потока увеличивают статический счетчик и на экране мы видим
непредсказуемый результат.

Но ситуация измениться если применить AtomicInteger.
*/
import Less_25_ch_18_AtomicInteger.MyClasses.MyRunner;

public class Less_25_NotAtomicInteger_Step1 {
    public static int counter;

    public static void main(String[] args) {
        Thread trd_1 = new Thread(new MyRunner());
        Thread trd_2 = new Thread(new MyRunner());
        trd_1.start();
        trd_2.start();


        try {
            trd_1.join();
            trd_2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(counter);
    }

    public static void incrementMeth(){
        counter++;
    }
}

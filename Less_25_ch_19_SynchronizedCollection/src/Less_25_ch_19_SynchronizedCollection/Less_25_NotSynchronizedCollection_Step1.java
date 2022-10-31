package Less_25_ch_19_SynchronizedCollection;
/*
Это еще не синхронизированная коллекция и не ее пример,
тут мы можем увидеть, что будет с итоговой коллекцией,
заполняемой из двух разных потоков.
*/
import java.util.ArrayList;

public class Less_25_NotSynchronizedCollection_Step1 {
    public static void main(String[] args) {
        ArrayList<Integer> data_source = new ArrayList<>();
        for (int i = 0; i < 5; i++){
            data_source.add(i);
        }

        ArrayList<Integer> target_for_data = new ArrayList<>();
        Runnable myRunner = () -> target_for_data.addAll(data_source);

        Thread trd_1 = new Thread(myRunner);
        Thread trd_2 = new Thread(myRunner);

        trd_1.start();
        trd_2.start();

        try {
            trd_1.join();
            trd_2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(target_for_data);
    }
}

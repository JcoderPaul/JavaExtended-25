package Less_25_ch_19_SynchronizedCollection;
/*
Это еще не синхронизированная коллекция и не ее пример,
тут мы можем увидеть, что будет с итоговой коллекцией,
заполняемой из двух разных потоков.
*/
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Less_25_SynchronizedCollection_Step3 {
    public static void main(String[] args) {
        ArrayList<Integer> data_source = new ArrayList<>();
        for (int i = 0; i < 1000; i++){
            data_source.add(i);
        }

        List<Integer> mySyncList = Collections.synchronizedList(data_source);

        Runnable myRunIterator = () -> {
            /*
            Структура Итератор подвержена сбоям в конкурентных средах, может произойти
            ситуация когда в момент работы одного потока с коллекцией, другой поток внес
            в нее некие изменения, и тогда Итератор просто не будет знать что делать.
            Вылетит исключение об конкурентной модификации объекта.

            Для этого приходится применять дополнительную синхронизацию, в данном случае
            на самом синхронизированном списке.
            */
            synchronized (mySyncList) {
                Iterator<Integer> myIterator = mySyncList.iterator();
                while (myIterator.hasNext()) {
                    System.out.print(myIterator.next() + " ");
                }
            }
        };

        Runnable myRunRemover = () -> {
            for (int i = 0; i <= mySyncList.size(); i += 5){
                mySyncList.remove(i);
            }
        };

        Thread trd_1 = new Thread(myRunIterator);
        Thread trd_2 = new Thread(myRunRemover);

        trd_1.start();
        trd_2.start();

        try {
            trd_1.join();
            trd_2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("\n" + data_source);
    }
}

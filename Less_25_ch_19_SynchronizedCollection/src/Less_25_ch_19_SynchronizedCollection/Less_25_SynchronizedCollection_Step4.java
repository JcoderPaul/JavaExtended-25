package Less_25_ch_19_SynchronizedCollection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class Less_25_SynchronizedCollection_Step4 {
    public static void main(String[] args) {

        Collection syncCollection = Collections.synchronizedCollection(new ArrayList<>());

        Runnable listOperations = () -> {
            syncCollection.addAll(Arrays.asList(1, 2, 3, 4, 5, 6));
        };

        Thread thread1 = new Thread(listOperations);
        Thread thread2 = new Thread(listOperations);
        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(syncCollection.size() == (12));
        System.out.println(syncCollection);
    }
}

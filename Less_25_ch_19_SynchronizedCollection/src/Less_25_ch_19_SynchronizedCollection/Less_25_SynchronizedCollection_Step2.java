package Less_25_ch_19_SynchronizedCollection;
/*
Collections.synchronizedList - это обертка для обычной коллекции.
В данном примере пока один поток работает с коллекцией, второй,
ждет когда освободится лок и только за тем делает свою работу.
*/
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Less_25_SynchronizedCollection_Step2 {
    public static void main(String[] args) {
        ArrayList<Integer> data_source = new ArrayList<>();
        for (int i = 0; i < 5; i++){
            data_source.add(i);
        }

        List<Integer> targetSyncList = Collections.synchronizedList(new ArrayList<>());
        Runnable myRunner = () -> targetSyncList.addAll(data_source);

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

        System.out.println(targetSyncList);
    }
}

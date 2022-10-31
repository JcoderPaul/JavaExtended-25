package Less_25_ch_22_ArrayBlockingQueue.MyClasses;

import static Less_25_ch_22_ArrayBlockingQueue.Less_25_BlockingQueue_Step3.DONE;
import static Less_25_ch_22_ArrayBlockingQueue.Less_25_BlockingQueue_Step3.drop;

public class FamilyConsumer implements Runnable
{
    public void run() {
        try {
            String msg = null;
            while (!((msg = drop.take()).equals(DONE)))
                System.out.println(msg);
        } catch (InterruptedException e) {
            System.err.println("Interrupted! " + e.getMessage());
        }
    }
}

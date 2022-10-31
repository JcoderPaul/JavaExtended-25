package Less_25_ch_22_ArrayBlockingQueue.MyClasses;

import static Less_25_ch_22_ArrayBlockingQueue.Less_25_SynchQueues_Step5.*;

public class NewConsumer implements Runnable
{
    public void run() {
        try {
            String msg = null;
            while (true) {
                printMessage(BEFORE_TAKE);
                Thread.sleep(3000);
                if (!((msg = drop.take()).equals(DONE))) {
                    System.out.println(msg);
                } else
                    break;
                printMessage(AFTER_TAKE);
            }
        } catch (InterruptedException e) {
            System.err.println("Interrupted! " + e.getMessage());
        }
    }
}

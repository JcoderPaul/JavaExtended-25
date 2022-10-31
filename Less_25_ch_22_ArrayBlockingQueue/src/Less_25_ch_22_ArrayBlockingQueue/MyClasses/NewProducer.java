package Less_25_ch_22_ArrayBlockingQueue.MyClasses;

import static Less_25_ch_22_ArrayBlockingQueue.Less_25_SynchQueues_Step5.*;

public class NewProducer implements Runnable
{
    public void run() {
        try {
            for (int i = 0; i < messages.length; i++) {
                printMessage(BEFORE_PUT);
                drop.put(messages[i]);
                printMessage(AFTER_PUT);
            }
            drop.put(DONE);
        } catch (InterruptedException e) {
            System.err.println("Interrupted! " + e.getMessage());
        }
    }
}

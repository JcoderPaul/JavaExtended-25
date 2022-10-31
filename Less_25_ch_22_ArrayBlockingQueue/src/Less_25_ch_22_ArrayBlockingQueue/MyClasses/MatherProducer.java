package Less_25_ch_22_ArrayBlockingQueue.MyClasses;

import static Less_25_ch_22_ArrayBlockingQueue.Less_25_BlockingQueue_Step3.*;

public class MatherProducer implements Runnable
{
    public void run() {
        try {
            int cnt = 0;
            for (int i = 0; i < messages.length; i++) {
                drop.put(messages[i]);
                if (++cnt < 3)
                    Thread.sleep(2000);
            }
            drop.put(DONE);
        } catch (InterruptedException e) {
            System.err.println("Interrupted! " + e.getMessage());
        }
    }
}

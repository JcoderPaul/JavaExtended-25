package Less_25_ch_22_ArrayBlockingQueue.MyClasses;

import static Less_25_ch_22_ArrayBlockingQueue.Less_25_TransferQueue_Step6.*;

public class MyProducer implements Runnable{
    @Override
    public void run() {
        for(int i = 0; i < 2; i++){
            try{
                printMessage(WAIT_producer + i);
                queue.transfer("'producer-" + i + "'");
                printMessage(TRANSFERED + i + "\n");
            } catch(Exception e){
                e.printStackTrace();
            }
        }
    }

}
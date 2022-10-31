package Less_25_ch_22_ArrayBlockingQueue.MyClasses;

import static Less_25_ch_22_ArrayBlockingQueue.Less_25_TransferQueue_Step6.*;

public class MyConsumer implements Runnable{
    @Override
    public void run() {
        for(int i = 0; i < 2; i++){
            try{
                Thread.sleep(2000);
                printMessage(WAIT_consumer + i);
                String element = queue.take();
                printMessage(CONSUMED + element);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
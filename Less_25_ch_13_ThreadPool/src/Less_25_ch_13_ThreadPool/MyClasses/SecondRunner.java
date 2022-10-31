package Less_25_ch_13_ThreadPool.MyClasses;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SecondRunner implements Runnable{
    static SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss:SSS");

    @Override
    public void run() {
        String time_in_text = sdf.format(new Date());

        System.out.println(time_in_text + " - " + Thread.currentThread().getName() + " -> start work.");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(time_in_text + " - " + Thread.currentThread().getName() + " -> end work.");
    }
}

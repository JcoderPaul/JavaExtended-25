package Less_25_ch_10_ReentrantLock;

import Less_25_ch_10_ReentrantLock.MyTryLockClasses.*;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Less_25_tryLock_Step3 {
    public static int test_count;
    public static String resource = " Hello, World! -> ";
    static SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

    public static void main(String[] args) {
        Thread thread1 = new Thread(
                new LockedClass(" first "," Первый поток в работе -> "));
        Thread thread2 = new Thread(
                new LockedClass(" second "," Второй поток в работе -> "));

        thread1.start();
        thread2.start();

        printMessage(null);

        while (thread1.isAlive() || thread2.isAlive()) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("\nОбщий итог работы потоков: " + test_count);
        System.out.println("\nЗавершение работы примера.");
        System.exit(0);
    }

    public static void printMessage(final String msg) {
        String text = sdf.format(new Date());
        if (msg == null)
            text += resource + Thread.currentThread().getName();
        else
            text += msg + Thread.currentThread().getName();
        System.out.println(text);
    }
}

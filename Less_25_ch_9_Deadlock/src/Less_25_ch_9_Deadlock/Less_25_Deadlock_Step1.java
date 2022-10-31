package Less_25_ch_9_Deadlock;
/*
Симуляция проблемы ДедЛоков.

В данном примере один друг, должен поклониться в ответ на поклон другого.
Но метод ответного поклона, завязан на метод первого поклона. Т.е. для
выполнения одного метода, нужен другой. А тут еще объектов не один, да,
и потоков тоже ...
*/
import Less_25_ch_9_Deadlock.MyDeadLoskClasses.*;

public class Less_25_Deadlock_Step1 {
    public static void main(String[] args) {
        final Friend alphonse = new Friend("Alphonse");
        final Friend gaston = new Friend("Gaston");

        Thread first_tread = new Thread(new Runnable() {
            @Override
            public void run() {
                  alphonse.bow(gaston);
             }
        });

        Thread second_thread = new Thread(new Runnable() {
            @Override
            public void run() {
                  gaston.bow(alphonse);
            }
        });

        first_tread.start();
        second_thread.start();

        try {
            second_thread.join();
            first_tread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Программа завершилась!");
    }
}

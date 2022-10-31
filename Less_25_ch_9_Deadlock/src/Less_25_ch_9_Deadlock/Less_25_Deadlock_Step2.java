package Less_25_ch_9_Deadlock;

import Less_25_ch_9_Deadlock.MyDeadLoskClasses.*;

/**
 * В данной программе мы симулируем "ДэдЛок", когда при работе с приватной
 * переменной двух разных объектов мы создаем два Лока, для избежания эффекта
 * гонки потоков. Но в итоге, если локи активируются в разных порядках в разных потоках,
 * происходит их взаимное блокирование.
 **/


public class Less_25_Deadlock_Step2 {
    public static void main(String[] args) {
        Runner my_runer_test = new Runner();

        Thread tread_first = new Thread(new Runnable() {
            @Override
            public void run() {
                my_runer_test.firstTread();
            }
        });

        Thread tread_second = new Thread(new Runnable() {
            @Override
            public void run() {
                my_runer_test.secondTread();
            }
        });

        tread_first.start();
        tread_second.start();

        try {
            tread_first.join();
            tread_second.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        my_runer_test.treadFinished();
    }
}

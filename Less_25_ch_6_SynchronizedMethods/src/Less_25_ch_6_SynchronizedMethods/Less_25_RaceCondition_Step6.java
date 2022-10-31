package Less_25_ch_6_SynchronizedMethods;

import Less_25_ch_6_SynchronizedMethods.MyBankClasses.*;

/**
 * В данной программе мы симулируем "состояние гонки", когда при
 * работе с приватной переменной двух разных объектов мы получаем
 * эффект при котором один поток, изменяя переменную не знает, что
 * второй поток проводит похожую операцию.
 *
 * В итоге мы получаем, когда часть переводов между переменными теряется
 * или не фиксируется потоками.
 *
 * !!! Деньги из воздуха !!!"
 *
 **/
public class Less_25_RaceCondition_Step6 {
    public static void main(String[] args) {
        BankRunnerOperation my_bank_runer_operation = new BankRunnerOperation();

        Thread tread_first = new Thread(new Runnable() {
            @Override
            public void run() {
                my_bank_runer_operation.firstTread();
            }
        });

        Thread tread_second = new Thread(new Runnable() {
            @Override
            public void run() {
                my_bank_runer_operation.secondTread();
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

        my_bank_runer_operation.treadFinished();
    }
}

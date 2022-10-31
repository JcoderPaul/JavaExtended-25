package Less_25_ch_7_SynchronizedBlocks;
/*
Вся работа (по синхронизации) проведена в классе Runner().

Каждый поток инкрементирует статическую переменную на 1, с учетом своего
предельного значения, т.е. например один инкрементирует до 11300, второй
до 12100, итого общий инкремент синхронизированного счетчика 23400.

Параллельно похожую работу мы пытаемся провести с НЕсихронизированной статической
переменной, а вернее с переменной, вне синхронизированного блока, и естественно имеем,
непредсказуемый результат.
*/
import Less_25_ch_7_SynchronizedBlocks.MyClasses.*;

public class Less_25_GoodSynchronized_Step3 {
    public static void main(String[] args) {
        System.out.println("*** Start MAIN ***");
        GoodRunner rn_1 = new GoodRunner(11300);
        GoodRunner rn_2 = new GoodRunner(12100);
        rn_1.start();
        rn_2.start();

        try {
            rn_1.join();
            rn_2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Данные synchronized счетчика -> " + SyncCounter.sync_count);
        System.out.println("Данные NOT synchronized счетчика -> " + NotSyncCounter.not_sync_count);

        System.out.println("*** MAIN Finish ***");
    }
}

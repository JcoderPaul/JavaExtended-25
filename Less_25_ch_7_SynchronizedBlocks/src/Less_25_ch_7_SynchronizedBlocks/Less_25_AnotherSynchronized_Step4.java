package Less_25_ch_7_SynchronizedBlocks;
/*
Вся работа (по синхронизации) проведена в классе JastRunner().

Каждый поток инкрементирует статическую переменную на 1, с учетом своего
предельного значения, т.е. в текущем примере оба потока инкрементируют до
15000, итого общий инкремент синхронизированного счетчика 30000.

Параллельно похожую работу мы пытаемся провести с НЕсихронизированной статической
переменной, а вернее с переменной, вне синхронизированного блока, и естественно имеем,
непредсказуемый результат.
*/

import Less_25_ch_7_SynchronizedBlocks.MyClasses.*;

public class Less_25_AnotherSynchronized_Step4 {
    public static void main(String[] args) {
        System.out.println("*** Start MAIN ***");
        /*
        Создаем объект подписанный на интерфейс Runnable, в итоге именно
        на нем и будет происходить синхронизация, т.е. монитор именно этого
        объекта будет блокировать и разблокировать потоки.
        */
        JastRunner jerry = new JastRunner(15000);
        // Создаем от него два потока, которые и будут заниматься инкрементом
        Thread jrn_1 = new Thread(jerry);
        Thread jrn_2 = new Thread(jerry);
        jrn_1.start();
        jrn_2.start();

        try {
            jrn_1.join();
            jrn_2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Данные synchronized счетчика -> " + JastRunner.getSync_count());
        System.out.println("Данные NOT synchronized счетчика -> " + JastRunner.getNot_sync_count());

        System.out.println("*** MAIN Finish ***");
    }
}
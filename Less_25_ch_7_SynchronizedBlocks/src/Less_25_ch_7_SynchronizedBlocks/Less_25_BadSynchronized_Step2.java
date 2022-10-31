package Less_25_ch_7_SynchronizedBlocks;
/*
Отсутствие синхронизации на одном конкретном объекте.

Каждый поток инкрементирует статическую переменную на 1, с учетом своего предельного
значения, т.е. например один инкрементирует до 11300, второй до 12100, итого общий
инкремент синхронизированного счетчика должен быть 23400.

!!! Однако при неверной настройке synchronized блока можно получить весьма интересный результат !!!

Все что происходит с НЕсихронизированной статической переменной, а вернее с переменной,
вне синхронизированного блока, естественно имеет непредсказуемый результат.
*/

import Less_25_ch_7_SynchronizedBlocks.MyClasses.*;

public class Less_25_BadSynchronized_Step2 {
    public static void main(String[] args) {
        System.out.println("*** Start MAIN ***");
        BadRunner brn_1 = new BadRunner(11300);
        BadRunner brn_2 = new BadRunner(12100);
        brn_1.start();
        brn_2.start();

        try {
            brn_1.join();
            brn_2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Данные bad synchronized счетчика -> " + BadRunner.getBad_counter());
        System.out.println("Данные bad NON synchronized счетчика -> " + BadRunner.getBad_non_sync_counter());
        System.out.println("*** MAIN Finish ***");
    }
}

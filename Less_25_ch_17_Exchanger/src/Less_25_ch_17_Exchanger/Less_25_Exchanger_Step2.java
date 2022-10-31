package Less_25_ch_17_Exchanger;
/*
Имитация работы 'ПОЧТА РОССИИ'.
*/
import Less_25_ch_17_Exchanger.MyClasses.Truck;

import java.util.concurrent.Exchanger;

public class Less_25_Exchanger_Step2 {
    //Создаем обменник, который будет обмениваться типом String
    public static final Exchanger<String> EXCHANGER = new Exchanger<>();

    public static void main(String[] args) throws InterruptedException {
        //Формируем груз для 1-го грузовика
        String[] parcels_for_first_track = new String[]{"{посылка A->D}", "{посылка A->C}"};
        //Формируем груз для 2-го грузовика
        String[] parcels_for_second_track = new String[]{"{посылка B->C}", "{посылка B->D}"};
        //Отправляем 1-й грузовик из А в D
        new Thread(new Truck(1, "A", "D", parcels_for_first_track)).start();
        Thread.sleep(100);
        //Отправляем 2-й грузовик из В в С
        new Thread(new Truck(2, "B", "C", parcels_for_second_track)).start();
    }
}

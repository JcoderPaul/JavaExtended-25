package Less_25_ch_6_SynchronizedMethods;
/*
Данный пример отличается от предыдущего чуть большей наглядностью
при выводе на экран. В данном и предыдущем примере показаны характерные
ошибки при Data Racing.
*/
import Less_25_ch_6_SynchronizedMethods.MyClasses.InfoRunner;

public class Less_25_NotSynchronized_Step2 {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("**** Поток MAIN старт ****");
        // Создаем объект от нашего класса подписанного на интерфейс Runnable
        InfoRunner run_count = new InfoRunner(4);
        // Создаем три потока из нашего объекта, так можно
        Thread tr_1 = new Thread(run_count);
        Thread tr_2 = new Thread(run_count);
        Thread tr_3 = new Thread(run_count);
        // Стартуем их
        tr_1.start();
        tr_2.start();
        tr_3.start();

        tr_1.join();
        tr_2.join();
        tr_3.join();

        System.out.println("**** Поток MAIN финиш ****");
    }
}

package Less_25_ch_17_Exchanger.MyClasses;

import static Less_25_ch_17_Exchanger.Less_25_Exchanger_Step2.EXCHANGER;

public class Truck implements Runnable {
    private int number;
    private String dep;
    private String dest;
    // Массив посылок
    private String[] parcels;

    public Truck(int number, String departure, String destination, String[] parcels) {
        this.number = number; // Номер машины
        this.dep = departure; // Точка отправления (депортации)
        this.dest = destination; // Точка назначения
        this.parcels = parcels; // Массив посылок
    }

    @Override
    public void run() {
        try {
            System.out.printf("В грузовик №%d погрузили: %s и %s.\n", number, parcels[0], parcels[1]);
            System.out.printf("Грузовик №%d выехал из пункта %s в пункт %s.\n", number, dep, dest);
            System.out.println("----------------------------------------------------------------------");
            Thread.sleep(1000 + (long) Math.random() * 5000);
            System.out.printf("Грузовик №%d приехал в пункт Е.\n", number);
            /*
            При вызове *.exchange() поток блокируется и ждет пока другой поток
            вызовет *.exchange(), после этого произойдет обмен посылками, между
            потоками.

            И тут очень тонкий момент, по крайней мере для меня, (!!! с этими потоками !!!)
            в данном случае от класса Track, который подписан на интерфейс Runnable будет
            порождено 2 - а объекта с разными переменными, но абсолютно одинаковым методом
            *.run(). И когда от этих объектов запустятся потоки, они будут выполнять абсолютно
            одинаковый набор действий...

            Наконец, кто-то из этих потоков первым доберется до строки:
            << parcels[1] = EXCHANGER.exchange(parcels[1]); >>

            Причем, независимо от потока, что у того, что у другого, эта строка идентична -
            метод *.run() у обоих объектов один и тот же. Первый кто добрался до метода
            синхронизатора, притормаживается в ожидании, пока второй поток тоже доберется
            до точно такой же строки у себя в методе *.run().

            В данной реализации кода, происходит замена второго элемента массива - parcels[1],
            одного потока (Track X), на второй элемент массива - parcels[1], другого потока
            (Track Y).

            Не важно, кто первым из потоков активировал метод *.exchange(), второй его тоже
            активирует. И произойдет взаимный обмен вторыми элементами каждого из массивов,
            каждого из объектов.

            В качестве эксперимента можно поиграться с этой строкой, что бы наглядно увидеть,
            что и как работает.
            */
            parcels[1] = EXCHANGER.exchange(parcels[1]);

            System.out.printf("В грузовик №%d переместили посылку для пункта %s.\n", number, dest);
            Thread.sleep(1000 + (long) Math.random() * 5000);
            System.out.printf("Грузовик №%d приехал в %s и доставил: %s и %s.\n", number, dest, parcels[0], parcels[1]);
        } catch (InterruptedException e) {
        }
    }
}

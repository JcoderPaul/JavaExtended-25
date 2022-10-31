package Less_25_ch_7_SynchronizedBlocks;

import Less_25_ch_7_SynchronizedBlocks.MyClasses.AnotherWorker;

public class Less_25_SynchronizedWithLock_Step6 {
    public static void main(String[] args) {
        /*
        Создаем объект AnotherWorker и сразу вызываем его метод *.main_for_go().
        */
        new AnotherWorker().main_for_go();
    }
}

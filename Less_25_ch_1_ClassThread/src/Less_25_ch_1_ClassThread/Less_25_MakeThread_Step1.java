package Less_25_ch_1_ClassThread;

import Less_25_ch_1_ClassThread.MyThreads.*;

public class Less_25_MakeThread_Step1 {
    public static void main(String[] args) {
        FirstThread ft_1 = new FirstThread(); // Создаем потоковые объекты
        SecondThread st_1 = new SecondThread();

        ft_1.start(); // Запускаем оба потока
        st_1.start();
        /*
        Но не обязательно создавать два специальных класса наследника Thread,
        для создания нескольких потоков, если их функционал похож. См. следующие
        два примера.
        */

    }
}

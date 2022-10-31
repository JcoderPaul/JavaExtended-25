package Less_25_ch_6_SynchronizedMethods;
/*
Перепишем наш класс Runner в SynchroRunner и добавим
сигнатуру 'synchronized' в метод 'increase'
*/
import Less_25_ch_6_SynchronizedMethods.MyClasses.Runner;
import Less_25_ch_6_SynchronizedMethods.MyClasses.SynchroRunner;

public class Less_25_Synchronized_Step5 {
    public static void main(String[] args) {
        // Создаем объект от нашего нового класса SynchroRunner с synchronized методом
        SynchroRunner run_count = new SynchroRunner(4);
        // Создаем три потока из нашего объекта
        Thread tr_1 = new Thread(run_count);
        Thread tr_2 = new Thread(run_count);
        Thread tr_3 = new Thread(run_count);
        // Стартуем их
        tr_1.start();
        tr_2.start();
        tr_3.start();
        /*
        На экране последовательность: 0 1 2 3 4 5 6 7 8 9 10 11.
        Как мы и хотели в первых двух примерах.
        В данном случае, как уже было сказано, один поток работает,
        два других ждут своей очереди.
        */
    }
}

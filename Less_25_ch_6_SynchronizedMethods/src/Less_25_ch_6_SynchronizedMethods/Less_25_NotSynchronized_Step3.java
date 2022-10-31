package Less_25_ch_6_SynchronizedMethods;

/**
 * В программе приведен пример несинхронизированности потоков
 * при обращении к переменной 'counter'. В итоге, результат вывода на экран
 * будет различным. Т.к. два потока не знают о том, что один уже
 * изменил переменную и каждый работает со своим значением этой переменной.
 * Это состояние гонки данных Data Race (самое интересное, что разные преподаватели,
 * трактуют понятие 'гонки данных' и 'состояние гонки' по-разному и часто путают).
 **/

public class Less_25_NotSynchronized_Step3 {
    private int counter;

    public static void main(String[] args) throws InterruptedException {
        Less_25_NotSynchronized_Step3 my_test = new Less_25_NotSynchronized_Step3();
        my_test.doWork();
    }

    public void doWork() throws InterruptedException {

        Thread my_thread_1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i =0; i < 10000; i++){
                    counter = counter + 1;
                }
            }
        });

        Thread my_thread_2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i =0; i < 10000; i++){
                    counter = counter + 1;
                }
            }
        });

        my_thread_1.start();
        my_thread_2.start();
        /*
        !!! Повторение !!!
        Метод final void 'join()' throws InetrruptedException приведенный ниже
        ожидает завершения того потока исполнения, для которого он вызван.
        Его имя отражает следующий принцип: вызывающий поток ожидает, когда указанный
        поток присоединится к нему. Метод jоin() перегружен и позволяет указывать
        максимальный промежуток времени, в течение которого требуется ожидать завершения
        указанного потока исполнения.
        */

        /**
         * There are three overloaded join functions.
         *
         * join(): It will put the current thread on wait until the thread on which it is called is dead.
         * If thread is interrupted then it will throw InterruptedException.
         *
         * public final void join()
         * join(long millis) :It will put the current thread on wait until the thread on which it is called
         * is dead or wait for specified time (milliseconds).
         *
         * public final synchronized void join(long millis)
         * join(long millis, int nanos): It will put the current thread on wait until the thread on which
         * it is called is dead or wait for specified time (milliseconds + nanos).
         **/
        my_thread_1.join(); // В нашем случае поток main будет ждать завершения потока my_thread_1
        my_thread_2.join(); // и потока my_thread_2 соответственно и только затем выведет результат на экран.

        System.out.println("Счетчик : " + counter);
    }
}

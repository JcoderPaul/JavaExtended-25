package Less_25_ch_5_Volatile.MyTreads;

public class ThreadRunner implements Runnable {
    /**
     * Переменная 'stop_do_it' имеет сигнатуру 'volatile' для того чтобы
     * избежать КОГЕРЕНТНОСТЬ КЕШЕЙ ПРОЦЕССОРА, т.е. когда значение переменной
     * захвачено одним из потоков, изменено и об этом изменении не известно
     * другому использующему эту переменную потоку.
     * Т.е. переменная не кэширует в процессоре, а в общей памяти и доступ к ней
     * происходит последовательно каждым потоком.
     **/
    private volatile boolean stop_do_it = true;
    private String name_of_tread;
    // Конструктор метода TreadTest
    public ThreadRunner(String name_of_tread) {
        this.name_of_tread = name_of_tread;
    }
    /*
    Метод для запуска потока относящийся к классу TreadTest, который наследует класс Thread.
    */
    public void run() {
        int count = 0;
        while (stop_do_it) {
            count++;
        }
        System.out.println("Hello from thread '" + name_of_tread + "', счетчик -> " + count);
    }

    public void setStop_do_it(boolean stop_do_it) {
        this.stop_do_it = stop_do_it;
    }

    public void setName_of_tread(String name_of_tread) {
        this.name_of_tread = name_of_tread;
    }
}

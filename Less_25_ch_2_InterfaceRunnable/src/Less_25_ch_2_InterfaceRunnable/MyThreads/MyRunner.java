package Less_25_ch_2_InterfaceRunnable.MyThreads;

public class MyRunner implements Runnable {
    String name_of_tread;

    public MyRunner(String name_of_tread) {
        this.name_of_tread = name_of_tread;
    }

    public void run() {
        for (int i = 0; i < 1000; i++) {
            /*
            Метод *.sleep(время в миллисекундах) замораживает выполнение потока
            на заданное в методе время.
            */
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Hello from thread : " + name_of_tread + " значение i -> " + i);
        }
    }
}

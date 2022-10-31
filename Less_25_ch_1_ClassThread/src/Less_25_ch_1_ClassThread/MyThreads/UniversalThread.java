package Less_25_ch_1_ClassThread.MyThreads;

public class UniversalThread extends Thread {
    String name_of_tread;

    public UniversalThread(String name_of_tread) {
        this.name_of_tread = name_of_tread;
    }

    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println("Hello from thread : " + name_of_tread + " значение i текущего потока " + i);
        }
    }
}

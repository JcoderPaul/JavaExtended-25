package Less_25_ch_6_SynchronizedMethods.MyClasses;

public class InfoRunner implements Runnable{
    private static int count = 0; // Статический счетчик
    private int limit; // Сколько раз будем увеличивать счетчик на единицу

    public InfoRunner(int limit) {
        this.limit = limit; // Передаем счетчик через конструктор
    }
    // Метод запускающий наш счетчик в потоке
    @Override
    public void run() {
        increase(this.getLimit());
    }
    // Метод для увеличения счетчика
    private static void increase(int limit){
        for (int i = 0; i < limit; i++) {
            System.out.println(Thread.currentThread().getName() + " -> " + count++);
        }

    }

    public int getLimit() {
        return limit;
    }

}

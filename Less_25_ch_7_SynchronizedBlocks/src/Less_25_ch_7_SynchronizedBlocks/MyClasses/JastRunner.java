package Less_25_ch_7_SynchronizedBlocks.MyClasses;

public class JastRunner implements Runnable{

    private int limit = 0;
    private static int sync_count = 0;
    private static int not_sync_count = 0;

    public JastRunner(int limit) {
        this.limit = limit;
    }

    @Override
    public void run() {
        for (int i = 0; i < this.limit; i++){
            syncCounter();
            notSyncCounter();
        }
    }
    /*
    Внутри метода организован синхронизированный с текущим объектом
    блок и уже внутри него происходит увеличение статической переменной.
    Синхронизация тут есть - должны получить 'красоту', каждый раз одну и ту же.
    */
    public int syncCounter(){
        synchronized (this){
            sync_count++;
        }
        return sync_count;
    }
    /*
    В отличие от своего синхронного собрата, этот метод, призван показать,
    разницу в работе при синхронизации на текущем объекте 'this' и при её отсутсвии.
    !!! Тут синхронизации нет - получим неизвестный результат, каждый раз разный !!!
    */
    public int notSyncCounter(){
        return not_sync_count++;
    }

    public int getLimit() {
        return limit;
    }

    public static int getSync_count() {
        return sync_count;
    }

    public static int getNot_sync_count() {
        return not_sync_count;
    }
}

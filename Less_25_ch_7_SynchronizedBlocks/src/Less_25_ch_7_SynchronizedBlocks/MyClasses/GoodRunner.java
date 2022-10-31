package Less_25_ch_7_SynchronizedBlocks.MyClasses;

public class GoodRunner extends Thread{
    private int limit = 0;
    public GoodRunner(int limit) {
        this.limit = limit;
    }

    @Override
    public void run() {
        for (int i = 0; i < this.limit; i++) {
            doWork();
        }
        System.out.println("Предел инкремента '" + Thread.currentThread().getName() +
                           "' составляет -> " + this.limit);
    }
    /*
    Как написано в литературе синхронизация идет на конкретном объекте.
    Однако, я могу ошибаться, но нигде не акцентируется, внимание изучающего,
    на том, что будет если у нас есть два объекта на которых идет синхронизация
    внутри одной программы при работе с одной и той же не статичной переменной.

    см. класс 'BadRunner' и 'Less_25_BadSynchronized_Step2'
    */
    private void doWork(){

        NotSyncCounter.not_sync_count ++;
        /*
        Пример синхронизации блока кода на объекте SyncCounter,
        содержащем статическую переменную sync_count.
        */
        synchronized (SyncCounter.class){
            SyncCounter.sync_count++;
        }
    }
}

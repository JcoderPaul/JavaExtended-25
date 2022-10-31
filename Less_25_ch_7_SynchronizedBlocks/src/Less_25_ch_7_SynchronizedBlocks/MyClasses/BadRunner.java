package Less_25_ch_7_SynchronizedBlocks.MyClasses;

public class BadRunner extends Thread{
    private int limit = 0;
    private static int bad_counter = 0;
    private static int bad_non_sync_counter = 0;

    public BadRunner(int limit) {
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
    внутри одной программы при работе с одной и той же статичной переменной.
    */
    private void doWork(){
        /*
        Пример того, как в одном методе может находиться асинхронный код и
        блок с синхронизированным набором строк.
        */

        bad_non_sync_counter++;

        /*
        Пример НЕправильной синхронизации блока кода на экземпляре объекта BadRunner,
        содержащем статическую переменную sync_count, это применение synchronized(this).

        Естественно никакой синхронизации не будет, вернее она будет, но, подозреваю,
        в пределах каждого самостоятельного объекта этого класса.

        При таком способе применения блока synchronized, мы не сможем получить полное значение
        счетчика, при работе нескольких (вроде как синхронизированных) потоков, снова будет
        гонка данных.

        Что бы получить верный результат необходимо синхронизироваться на классе BadRunner,
        т.е. верным будет применение synchronized(BadRunner.class), см. так же GoodRunner и
        Less_25_GoodSynchronized_Step3, где эта ошибка исправлена.

        Либо можно просто поменять 'synchronized (this)' на 'synchronized (BadRunner.class)'
        в строке под комментариями. И посмотреть, как работает программа в том и другом исполнении.
        */
        synchronized (this){
            bad_counter++;
        }
    }

    public int getLimit() {
        return limit;
    }

    public static int getBad_counter() {
        return bad_counter;
    }

    public static int getBad_non_sync_counter() {
        return bad_non_sync_counter;
    }
}

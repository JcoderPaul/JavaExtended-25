package Less_25_HW_2.RaceConditionAndDecision.Counter;

public class SynchronizedCounter_Step1 {
    private int value;
    /*
    Данный синтаксис равносилен написанию:
    --------------------------------------
    public void inc() {
        synchronized(this){
            value++;
        }
    }
    --------------------------------------
    В текущем примере мы синхронизируемся на
    мониторе текущего объекта, что в блоке
    --------------------------------------
    synchronized(this){
            value++;
        }
    --------------------------------------
    что в методе написанном ниже и работающим
    на данный момент.

    Но мы можем занимать мониторы разных объектов,
    что рассмотрено в следующем примере.
    */
    public synchronized void inc() {
            value++;
    }

    public synchronized void dec() {
            value--;
    }

    public int getValue() {
        return value;
    }
}

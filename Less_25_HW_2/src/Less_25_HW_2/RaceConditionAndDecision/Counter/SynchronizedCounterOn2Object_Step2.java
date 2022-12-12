package Less_25_HW_2.RaceConditionAndDecision.Counter;

public class SynchronizedCounterOn2Object_Step2 {
    // Два счетчика внутри одного класса
    private int value;
    private int valueTwo;
    // Два объекта чьи мониторы будут применяться для синхронизации
    Object monitorOne = new Object();
    Object monitorTwo = new Object();

    public void incOne() {
        synchronized (monitorOne){
        value++;
        }
    }

    public void decOne() {
        synchronized (monitorOne) {
            value--;
        }
    }

    public int getValueOne() {
        return value;
    }

    public void incTwo() {
        synchronized (monitorTwo) {
            valueTwo++;
        }
    }

    public void decTwo() {
        synchronized (monitorTwo) {
            valueTwo--;
        }
    }

    public int getValueTwo() {
        return valueTwo;
    }
}

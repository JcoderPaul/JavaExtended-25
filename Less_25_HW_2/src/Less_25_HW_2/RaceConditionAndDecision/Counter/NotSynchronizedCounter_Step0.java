package Less_25_HW_2.RaceConditionAndDecision.Counter;

public class NotSynchronizedCounter_Step0 {
    private int value;

    public void inc() {
            value++;
    }

    public void dec() {
            value--;
    }

    public int getValue() {
        return value;
    }
}

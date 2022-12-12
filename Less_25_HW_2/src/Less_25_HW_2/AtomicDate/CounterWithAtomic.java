package Less_25_HW_2.AtomicDate;
/*
Более подробно работа с Атомиками и их методами рассмотрена:
https://github.com/JcoderPaul/JavaExtended-25/tree/master/Less_25_ch_18_AtomicInteger
*/
import java.util.concurrent.atomic.AtomicInteger;

public class CounterWithAtomic {
    private AtomicInteger value = new AtomicInteger();

    public void inc() {
            value.getAndIncrement();
    }

    public void dec() {
            value.getAndDecrement();
    }

    public int getValue() {
        return value.intValue();
    }
}

package Less_25_ch_18_AtomicInteger.MyClasses;

import static Less_25_ch_18_AtomicInteger.Less_25_NotAtomicInteger_Step1.incrementMeth;

public class MyRunner implements Runnable{

    @Override
    public void run() {
        for(int i = 0; i < 100; i++){
            incrementMeth();
        }
    }
}

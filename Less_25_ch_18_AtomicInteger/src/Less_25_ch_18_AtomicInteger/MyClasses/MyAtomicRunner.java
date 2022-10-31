package Less_25_ch_18_AtomicInteger.MyClasses;

import static Less_25_ch_18_AtomicInteger.Less_25_AtomicInteger_Step2.*;

public class MyAtomicRunner implements Runnable{

    @Override
    public void run() {
        for(int i = 0; i < 100; i++){
            incrementMeth();
            incrementAddAndGetMeth();
            decrementAndGetMeth();
        }
    }
}

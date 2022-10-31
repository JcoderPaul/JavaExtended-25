package Less_25_ch_7_SynchronizedBlocks.MyPhoneClasses;

public class PhoneRunner implements Runnable{
    @Override
    public void run() {
        try {
            new CallProgram().phoneCall();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

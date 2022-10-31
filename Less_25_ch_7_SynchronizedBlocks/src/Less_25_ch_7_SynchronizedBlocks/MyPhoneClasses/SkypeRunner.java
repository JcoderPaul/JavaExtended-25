package Less_25_ch_7_SynchronizedBlocks.MyPhoneClasses;

public class SkypeRunner implements Runnable{
    @Override
    public void run() {
        try {
            new CallProgram().skypeCall();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

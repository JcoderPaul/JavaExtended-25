package Less_25_ch_7_SynchronizedBlocks.MyPhoneClasses;

public class WhatsAppRunner implements Runnable{
    @Override
    public void run() {
        try {
            new CallProgram().whatsAppCall();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

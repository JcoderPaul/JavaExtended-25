package Less_25_ch_10_ReentrantLock;

import Less_25_ch_10_ReentrantLock.MyClasses.IncomingCall;

public class Less_25_ReentrantLock_Step1 {
    public static void main(String[] args) {

        System.out.println("Phone turned on...");

        IncomingCall incomingCall = new IncomingCall();

        Thread thread_first = new Thread(new Runnable() {
            @Override
            public void run() {
                incomingCall.mobileCall();
            }
        });

        Thread thread_second = new Thread(new Runnable() {
            @Override
            public void run() {
                incomingCall.skypeCall();
            }
        });

        Thread thread_third = new Thread(new Runnable() {
            @Override
            public void run() {
                incomingCall.whatsAppCall();
            }
        });

        thread_first.start();
        thread_second.start();
        thread_third.start();


        try {
            thread_first.join();
            thread_second.join();
            thread_third.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Phone turned off...");
    }
}

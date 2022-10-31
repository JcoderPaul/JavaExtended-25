package Less_25_ch_10_ReentrantLock.MyClasses;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class IncomingCall {
    private Lock myLock = new ReentrantLock();

    Random rnd_time_of_call = new Random();

    public void mobileCall(){
        // Получаем лок на объекте
        myLock.lock();
            try {
                System.out.println("Mobile call starts");
                Thread.sleep(rnd_time_of_call.nextInt(5000));
                System.out.println("Mobile call finish");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            finally {
            // Отдаем лок, разблокируем монитор объекта
            myLock.unlock();
            }
    }

    public void skypeCall(){
        // Получаем лок на объекте
        myLock.lock();
        try {
            System.out.println("Skype call starts");
            Thread.sleep(rnd_time_of_call.nextInt(5000));
            System.out.println("Skype call finish");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        finally {
            // Отдаем лок, разблокируем монитор объекта
            myLock.unlock();
        }
    }

    public void whatsAppCall(){
        // Получаем лок на объекте
        myLock.lock();
        try {
            System.out.println("WhatsApp call starts");
            Thread.sleep(rnd_time_of_call.nextInt(5000));
            System.out.println("WhatsApp call finish");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        finally {
            // Отдаем лок, разблокируем монитор объекта
            myLock.unlock();
        }
    }
}

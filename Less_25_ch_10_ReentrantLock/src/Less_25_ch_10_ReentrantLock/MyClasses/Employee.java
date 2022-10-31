package Less_25_ch_10_ReentrantLock.MyClasses;

import java.util.Random;
import java.util.concurrent.locks.Lock;

public class Employee extends Thread{
    private String name;
    private Lock lock;

    Random rnd_using_atm = new Random();

    public Employee(String name, Lock lock) {
        this.name = name;
        this.lock = lock;
        /*
        Поток запускается сразу после создания объекта,
        обращения к конструктору.
        */
        this.start();
    }

    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName() + " -> '" +
                    name + "' ждет доступа к банкомату ...");
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " -> '" +
                    name + "' получил(а) доступ к банкомату...");
            Thread.sleep(rnd_using_atm.nextInt(1000));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        finally {
            System.out.println(Thread.currentThread().getName() + " -> '" +
                    name + "' закончил(а) использовать банкомат.");
            lock.unlock();
        }

    }
}

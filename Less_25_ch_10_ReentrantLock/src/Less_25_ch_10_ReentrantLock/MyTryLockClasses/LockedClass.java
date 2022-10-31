package Less_25_ch_10_ReentrantLock.MyTryLockClasses;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static Less_25_ch_10_ReentrantLock.Less_25_tryLock_Step3.printMessage;
import static Less_25_ch_10_ReentrantLock.Less_25_tryLock_Step3.test_count;
import static Less_25_ch_10_ReentrantLock.Less_25_tryLock_Step3.resource;

public class LockedClass implements Runnable {
    protected String name_of_thread;
    protected String thread_massage;
    // Можно поиграть временем, чтобы получить разную выдачу в консоль.
    protected final int TIME_WAIT = 800; // Исходная - 7000
    protected final int TIME_SLEEP = 500; // Исходная - 5000
    // Лок завязанный на классе LockedClass и объектов созданных из него.
    static Lock locked_class_lock = new ReentrantLock();

    public LockedClass(String name_of_thread, String thread_massage) {
        this.name_of_thread = name_of_thread;
        this.thread_massage = thread_massage;
    }

    @Override
    public void run() {
        boolean locked = false;
        try {
            /*
            Попытка получение блокировки в течение TIME_WAIT, ответ true
            если блокировка получена и false если нет.
            */
            locked = locked_class_lock.tryLock(TIME_WAIT, TimeUnit.MILLISECONDS);
            if (locked) {
                System.out.println(Thread.currentThread().getName());
                resource = thread_massage;
                printMessage(null);
                for (int i = 0; i < 100000; i++){
                    test_count++;
                }
                System.out.println(test_count);
            }
            Thread.sleep(TIME_SLEEP);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            /*
            Блок finally выполняется в любом случае после 'try - catch'
            Убираем блокировку принудительно по условию если lock все еще у потока
            */
            String text = name_of_thread + " : завершил работу -> ";
            printMessage(text);
            // Если все еще заблокировано, разблокируем
            if (locked)
                locked_class_lock.unlock();
        }
    }
}
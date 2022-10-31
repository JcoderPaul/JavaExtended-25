package Less_25_ch_9_Deadlock.MyDeadLoskClasses;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Внимание!!! В данной программе происходит "мерцающая эмуляция"
 * эффекта взаимного запирания ключей, для этого и создано два лока:
 * 'lock_first_account' и 'lock_second_account', хотя можно было обойтись
 * одним локом на всех. При этом даже тут есть масса допущений, т.к. оба лока
 * будут созданы в двух объектах Runner и запираться и отпираться
 * в случайном порядке.
 * Поэтому, кстати и "мерцание", то произошел ДэдЛок, то нет.
 **/

public class Runner {
    private BankAccount account_first = new BankAccount();
    private BankAccount account_second = new BankAccount();
    private Random random_transfer_amount = new Random();

    private Lock lock_first_account = new ReentrantLock();
    private Lock lock_second_account = new ReentrantLock();

    // Метод позволяющий избежать ДэдЛока
    private void takeLock(Lock lock_1, Lock lock_2){
        boolean firstLockTaken = false;
        boolean secondLockTaken = false;
        while (true){
            try {
                /*
                Пытаемся захватить Локи, при этом если не получается метод *.tryLock()
                выкидывает false, если метод захватывает контроль над Локом он
                выкидывает true.
                */
                firstLockTaken = lock_1.tryLock();
                secondLockTaken = lock_2.tryLock();
            } finally {
                // Если удалось захватить оба Лока одновременно, то выходим из цикла while
                if(firstLockTaken && secondLockTaken){
                    System.out.println(Thread.currentThread().getName() + " я забрал оба 'лока' себе!");
                    return;
                }
                /*
                Поскольку мы не знаем какой Лок был захвачен, мы проверяем какой и разблокируем его,
                чтобы другой поток в котором, работает тот же модуль анализа захвата Локов мог забрать
                себе оба лока если успеет.
                */
                if(firstLockTaken)
                    lock_1.unlock();
                if (secondLockTaken)
                    lock_2.unlock();
            }
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void firstTread(){
        takeLock(lock_first_account, lock_second_account);
        try {
            for(int i = 0; i < 100000; i++)
                account_first.transfer_money(account_second, random_transfer_amount.nextInt(100));
        } finally{
            lock_first_account.unlock();
            lock_second_account.unlock();
        }
    }
    public void secondTread(){
        takeLock(lock_second_account, lock_first_account);
        try {
            for(int i = 0; i < 100000; i++)
                account_second.transfer_money(account_first, random_transfer_amount.nextInt(100));
        } finally{
            lock_first_account.unlock();
            lock_second_account.unlock();
        }
    }
    public void treadFinished(){
        System.out.println(account_first.getAccount_balance());
        System.out.println(account_second.getAccount_balance());
        System.out.println("Общий баланс двух счетов : "
                + (account_first.getAccount_balance() + account_second.getAccount_balance()));
    }
}

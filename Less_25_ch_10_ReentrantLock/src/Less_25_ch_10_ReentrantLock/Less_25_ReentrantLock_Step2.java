package Less_25_ch_10_ReentrantLock;

import Less_25_ch_10_ReentrantLock.MyClasses.Employee;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Less_25_ReentrantLock_Step2 {

    public static void main(String[] args) {
        Lock atm_lock = new ReentrantLock();

        new Employee("Ариам",atm_lock);
        new Employee("Кафаир",atm_lock);
        new Employee("Анушка",atm_lock);
        new Employee("Далима",atm_lock);
        new Employee("Костас",atm_lock);

    }

}

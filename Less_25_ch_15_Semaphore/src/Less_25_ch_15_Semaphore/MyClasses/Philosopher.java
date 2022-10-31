package Less_25_ch_15_Semaphore.MyClasses;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Semaphore;

// Создаем класс наследник класса Thread
public class Philosopher extends Thread {

    static SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
    // Создаем переменную класса Semaphore
    private Semaphore sem;
    // Создаем переменную флаг (поел или нет философ)
    private boolean already_ate = false;
    private String philosopher_name; // Имя философа
    // Конструктор класса
    public Philosopher(Semaphore sem, String name) {
        this.sem=sem;
        this.philosopher_name =name;
    }
    // Метод 'run' для класса наследника Thread
    public void run()
    {
        try
        {
            // если философ еще не ел
            if (!already_ate) {
                /*
                Запрашиваем у семафора разрешение на выполнение
                Методы acquire() и release() класса Semaphore управляют его счетчиком разрешений.
                Метод acquire() запрашивает разрешение на доступ к ресурсу у семафора.
                Если счетчик > 0, разрешение предоставляется, а счетчик уменьшается на 1.
                */
                sem.acquire();
                System.out.println (sdf.format(new Date()) + " " + philosopher_name + " садится за стол.");
                sleep(3000); // Философ ест!!!
                System.out.println(sdf.format(new Date()) + " " + philosopher_name + " кушает!");
                already_ate = true;
                System.out.println (sdf.format(new Date()) + " " + philosopher_name + " поел! Он выходит из-за стола.");
                /*
                Метод release() «освобождает» выданное ранее разрешение и возвращает его в счетчик
                (увеличивает счетчик разрешений семафора на 1).
                */
                sem.release();
                System.out.println (sdf.format(new Date()) + " " + philosopher_name + " отошел от стола.");
                // Философ вышел из-за стола, освободив место другим
                sleep(3000);
            }
        }
        catch(InterruptedException e) {
            System.out.println ("Что-то пошло не так!");
        }
    }
}
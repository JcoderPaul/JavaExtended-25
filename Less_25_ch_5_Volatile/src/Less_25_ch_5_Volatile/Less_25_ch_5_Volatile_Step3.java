package Less_25_ch_5_Volatile;
/*
В данном примере напоминалке, мы используем модификатора volatile у
ключевой переменной и видим нормальное завершение программы.

Важно помнить две вещи о volatile переменных:
- Операции чтения/записи volatile переменной являются атомарными.
- Результат операции записи значения в volatile переменную одним потоком,
становится виден всем другим потокам, которые используют эту переменную
для чтения из нее значения.

В идеале, в программе должен существовать только один поток, который меняет
значение volatile переменной, и множество других потоков, которые эту
переменную только читают.

В данном примере видно, что хотя значение переменной stop_do_it изменено
в одном потоке и он остановлен, второй продолжает работать, т.к. не знает

*/
import Less_25_ch_5_Volatile.MyTreads.*;

import java.util.Scanner;

import static java.lang.Thread.sleep;

public class Less_25_ch_5_Volatile_Step3 {
    public static void main(String[] args) throws InterruptedException {
            System.out.println("*** Start program!!! ***");
            // Создаем объект нашего класса MyThread
            ThreadRunner tr_1 = new ThreadRunner("FIRST");
            Thread my_thread_1 = new Thread(tr_1);
            // Создаем второй объект нашего класса MyThread
            ThreadRunner tr_2 = new ThreadRunner("SECOND");
            Thread my_thread_2 = new Thread(tr_2);
            // Стартуем обы потока
            my_thread_1.start();
            my_thread_2.start();
            /*
            Создаем сканер, который на вход ждет некое сообщение.
            Нажатие Enter или простой перевод каретки т.е. чтение следующей строки
            и будет тем сообщением *.nextLine()
            */
            sleep(3000);
            System.out.println("Three seconds are up time to wake up!!!");

            Scanner press_stop = new Scanner(System.in);
            press_stop.nextLine();
            /*
            По нажатию Enter останавливаем потоки и выводим сообщение.
            Если бы переменная 'stop_do_it' не имела бы модификатор volatile,
            на кнопку можно было бы жать очень долго.
            */
            tr_1.setStop_do_it(false);
            press_stop.nextLine();
            tr_2.setStop_do_it(false);
            press_stop.close();

            try {
                my_thread_1.join();
                my_thread_2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Все потоки закрыты, программа завершена!");
    }
}

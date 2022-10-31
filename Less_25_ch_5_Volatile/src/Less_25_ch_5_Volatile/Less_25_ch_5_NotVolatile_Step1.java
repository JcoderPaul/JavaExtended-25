package Less_25_ch_5_Volatile;
/*
В данном примере напоминалке, нет модификатора volatile у ключевой переменной, и мы можем
получить почти бесконечный цикл, см. ReadMe.txt где разъясняется похожая ситуация.
*/
import Less_25_ch_5_Volatile.MyTreads.*;

public class Less_25_ch_5_NotVolatile_Step1 {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("*** Start program!!! ***");
        // Cоздаем поток внутри потока MAIN
        NotVolatileExample test_tread = new NotVolatileExample();
        // Запускаем его
        test_tread.start();
        // Усыпляем поток MAIN
        Thread.sleep(3000);
        // Этот текст выводится после возврата MAIN к работе с 3-сек. задержкой.
        System.out.println("Three seconds are up time to wake up!!!");
        // Меняем флаг переменной end_of_cycle, чтобы остановить цикл
        test_tread.setEnd_of_cycle(false);
        // Просим основной поток подождать завершения нашего тестового потока.
        test_tread.join();
        // Теоретически, мы должны увидеть на экране эту надпись.
        System.out.println("*** End of program!!! ***");
        /*
        !!! Однако в данной ситуации мы можем ждать окончания программы сколь угодно долго,
        т.к. у нас, в данном случае, скорее всего процесс работы программы распараллелен, и
        изменения, сделанные нами в методе test_tread.setEnd_of_cycle(false); зафиксированы
        в основном потоке, а вернее в кэше основного потока, но не добрались до кэша второго
        потока, т.е. второй поток не знает об этих изменениях !!!

        Каждый процессор выполняющий отдельный поток, хранит свое значение 'end_of_cycle',
        и оно не обязано быть одинаковым.
        */
    }
}

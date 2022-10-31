package Less_25_ch_15_Semaphore;
/*
Пример задачи Less_25_Semaphore_Step1 и Less_25_DiningPhilosophers_Step2
практически повторяют друг друга, только частично изменен код реализации
действующих объектов наследующих Thread.
*/

/*
Имитация ситуации, когда 5-ть человек пришли к 2-м телефонным будкам
и всем надо позвонить (люди приличные никто не дерется, а там еще
есть вахтер-семафор-распределитель оказывается).
*/
import Less_25_ch_15_Semaphore.MyClasses.ManWantsToCall;

import java.util.concurrent.Semaphore;

public class Less_25_Semaphore_Step1 {
    public static void main(String[] args) {
        /*
        Создаем наш семафор и задаем сколько объектов одновременно
        могут пользоваться нашим ресурсом 'пара телефонных будок'
        */
        Semaphore phone_booth = new Semaphore(2);
        // Создаем наши потоки и передаем в качестве аргумента семафор
        new ManWantsToCall("Малкольм Стоун", phone_booth);
        new ManWantsToCall("Санара Куэста", phone_booth);
        new ManWantsToCall("Дуглас Линд", phone_booth);
        new ManWantsToCall("Говард Аддингтон", phone_booth);
        new ManWantsToCall("Таймус Роддерик", phone_booth);

    }
}

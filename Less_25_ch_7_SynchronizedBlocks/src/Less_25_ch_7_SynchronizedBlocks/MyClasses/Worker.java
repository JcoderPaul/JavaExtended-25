package Less_25_ch_7_SynchronizedBlocks.MyClasses;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Worker {
    // Создаем ссылку на объект класса Random
    Random random_element = new Random();
    private List<Integer> my_list_1 = new ArrayList<>();
    private List<Integer> my_list_2 = new ArrayList<>();

    /*
    Метод добавляет один случайный элемент в массив с именем 'name_list'.
    В данном случае мы сделали внутри метода addToMyList синхронизированный блок
    с фиксацией на текущем объекте. Это значит, что к нему в процессе работы
    сможет обратиться только один поток, а значит время выполнения
    всего кода увеличится по сравнению с вариантом распараллеливания потоков.
    */
    public void addToMyList(List name_list){
        /*
        Мы могли поставить модификатор 'synchronized' на текущий метод, как в предыдущих
        примерах, см. Less_25_ch_6_SynchronizedMethods. Но для разнообразия применим другой
        вариант - синхронизация на блоке. Если вдуматься, то для данного примера, ничего
        не изменилось, т.к. синхронизация идет на объекте которому принадлежит метод, т.е.
        на объекте класса 'Worker'.

        С таким же успехом мы могли синхронизировать метод *.work() или обернуть в
        синхронизированный блок код внутри него.

        Однако у синхронизации некоего блока кода, есть свои преимущества. Т.к. метод,
        к которому обращаются несколько потоков, может содержать другие методы или
        блоки кода, которые легко можно запустить одновременно в нескольких потоках без
        возникновения казусных ситуаций.
        */
        synchronized(this) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // Добавляем в наши массивы случайно сгенерированные элементы в пределах от 0 до 99.
            name_list.add(random_element.nextInt(100));
        }
    }
    /*
    Метод в цикле обращается 50 раз к методу 'addToList' и заполняет наши списки.
    Не сложно заметить, что фактически к методу 'addToList' происходит
    двойное обращение: сначала заполняется ячейка одного списка, затем другого.

    При этом метод 'work' запускается одновременно в двух разных потоках,
    а следовательно и метод 'addToList', к которому мы обращаемся в цикле.

    Т.е. наш цикл в методе 'work' в идеале (при должной синхронизации) отработает
    два раза (по количеству задействованных потоков) и мы получим 2 * 50 или
    каждый список станет длинной 100.

    Если не синхронизировать процесс работы, то размер списков будет непредсказуемым
    в пределах до 100. Поскольку идет обращение к единственной переменной i от двух
    потоков, то один из них может быстрее другого назначить i = 50 и циклы в обоих
    потоках закончатся, при этом, например один из потоков отработал его от 0 - 30,
    а другой от 30 до 50. Хотя на практике, из-за конкуренции потоков все будет ужаснее,
    т.к. i может принять одно и то же значение, например 23 или 34, два раза и отсчет
    продолжится, а за тем вдруг перескочит.
    */
    public void work(){
        for (int i = 0; i < 50; i++){
            addToMyList(my_list_1);
            addToMyList(my_list_2);
        }
    }

    public void main(){
        long before = System.currentTimeMillis(); // Замеряем стартовое время
        // Генерируем поток
        Thread my_thread_1 = new Thread(new Runnable() {
            @Override
            public void run() {
                work(); // Запускаем метод work() в потоке, т.е. заполняем наши List случайными числами
            }
        });
        // Генерируем еще поток и проделываем тоже что и в первом потоке.
        Thread my_thread_2 = new Thread(new Runnable() {
            @Override
            public void run() {
                work();
            }
        });
        // Стартуем потоки
        my_thread_1.start();
        my_thread_2.start();

        try {
            my_thread_1.join();
            my_thread_2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Замеряем конечное время
        long after = System.currentTimeMillis();
        System.out.println("Время выполнения операции : " + (after - before));

        System.out.println("Размер my_list_1 : " + my_list_1.size());
        System.out.println(my_list_1);
        System.out.println("Размер my_list_2 : " + my_list_2.size());
        System.out.println(my_list_2);
    }
}
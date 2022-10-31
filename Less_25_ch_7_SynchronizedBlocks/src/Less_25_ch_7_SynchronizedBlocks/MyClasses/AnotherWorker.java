package Less_25_ch_7_SynchronizedBlocks.MyClasses;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AnotherWorker {
    // Создаем ссылку на случайно сгенерированный элемент
    Random rnd_element = new Random();

    /*
    Создаем объекты, которые будем использовать для фиксации на них монитора при синхронизации.
    */
    Object lock_1 = new Object();
    Object lock_2 = new Object();

    private List<Integer> my_list_first = new ArrayList<>();
    private List<Integer> my_list_second = new ArrayList<>();

    // Метод добавляет один случайный элемент в массив с именем name_list.
    public void addToList(List name_list, Object my_lock){
        /*
        Вместо синхронизации на методе addToList, мы синхронизируемся на блоке кода
        внутри этого метода, помеченного переменной 'lock' ссылающегося на некий объект
        и теперь его монитор будет зафиксирован на одном потоке, а монитор объекта с другим
        'lock' будет зафиксирован на другом потоке.
        Мы получаем распараллеливание потоков и время обработки всего кода уменьшается.
        */
        synchronized (my_lock) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // Добавляем в наши массивы случайно сгенерированные элементы в пределах от 0 до 99.
            name_list.add(rnd_element.nextInt(100));
        }
    }
    // Метод в цикле обращается 1000 раз к методу addToList и заполняет наши List
    public void work_with_lock(){
        for (int i = 0; i < 1000; i++){
            // Добавляем данные массивов и 'ключей слежения' на которые будут завязаны разные потоки.
            addToList(my_list_first,lock_1);
            addToList(my_list_second,lock_2);
        }
    }

    public void main_for_go(){
        long before = System.currentTimeMillis(); // Замеряем стартовое время
        // Генерируем поток
        Thread my_thread_one = new Thread(new Runnable() {
            @Override
            public void run() {
                /*
                Запускаем метод work_with_lock() в потоке, т.е. заполняем наши List случайными числами
                при этом один поток будет обрабатывать один массив, в то время как другой поток будет
                обрабатывать другой массив.
                */
                work_with_lock();
            }
        });
        // Генерируем еще поток и проделываем тоже что и в первом потоке.
        Thread my_thread_two = new Thread(new Runnable() {
            @Override
            public void run() {
                work_with_lock();
            }
        });
        // Стартуем потоки
        my_thread_one.start();
        my_thread_two.start();

        try {
            my_thread_one.join();
            my_thread_two.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Замеряем конечное время
        long after = System.currentTimeMillis();
        System.out.println("Время выполнения операции : " + (after - before));

        System.out.println("Размер my_list_first : " + my_list_first.size());
        System.out.println(my_list_first);
        System.out.println("Размер my_list_second : " + my_list_second.size());
        System.out.println(my_list_second);
    }
}

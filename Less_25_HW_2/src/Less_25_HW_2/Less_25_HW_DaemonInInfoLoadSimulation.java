package Less_25_HW_2;
/*
Потоки - демоны обычно являются сервисными или
обслуживающими, т.е. мы не видим их работу, а
лишь пользуемся результатами их работы.

В данном случае мы имитируем загрузку данных из
некого очень медленного источника, чтобы получить
некую информацию - имя и возраст.

Причем сам результат работы наших демоном будет
получен с некой отсрочкой, но возвращен именно из
потока.

Т.е. внешний метод запросивший эту информацию будет
находиться в процессе ожидания, когда поток-демон из
пула потоков завершит работу и вернет данные.

Чуть более подробно об интерфейсах Callable и Future можно
посмотреть тут:
https://github.com/JcoderPaul/JavaExtended-25/tree/master/Less_25_ch_14_InterfaceCallable/src/Less_25_ch_14_InterfaceCallable

!!! Есть ситуации, когда поток отработал и ничего не вернул, и
нас эта ситуация устраивает. Но очень часто нам нужен не просто
вывод на экран, а данные - результат работы потока и тогда в
работу идут интерфейсы Future и Callable !!!
*/
import java.util.concurrent.*;

public class Less_25_HW_DaemonInInfoLoadSimulation {
    public static void main(String[] args) {
        /*
        Шаг 1 - создаем пул потоков и 3 - х и
        передаем в качестве аргумента фабрику
        потоков
        */
        ExecutorService executorService = Executors.newFixedThreadPool(3, new ThreadFactory() {
            @Override
            public Thread newThread(Runnable myRunnableForWork) {
                /*
                Шаг 3 - Текущий метод ожидает объект Runnable,
                который в свою очередь поступает во вновь
                созданный поток.
                */
                Thread thread = new Thread(myRunnableForWork);
                // Шаг 4 - Объявляем поток демоном
                thread.setDaemon(true);
                // Шаг 5 - Отдаем демона пулу потоков
                return thread;
            }
        });
        /*
        Шаг 2.1 - Через метод *.execute() передаем в пул
                  анонимный Runnable(), который выводит на
                  экран '.' имитируя процесс загрузки.
        */
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        // Выводим точки на экран каждые 300 мс.
                        System.out.print(".");
                        Thread.sleep(300);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        /*
        Шаг 2.2 - Если метод *.execute() пула потоков принимает объект
                  Runnable и ничего не возвращает, то метод *.submit()
                  принимает параметризированный объект Callable, который
                  в свою очередь возвращает объект Future.

                  И Callable и Future параметризированны одним и тем же
                  классом, в нашем примере это String, т.е. объект Callable
                  возвращает из потока объект String, а объект Future его
                  принимает и далее мы получаем из него нужную там информацию.
        */
        Future<String> futureName = executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                /*
                Поскольку это симуляция, то в данном методе нет
                серьезного кода, но зато явно показано, что он
                возвращает после небольшой задержки. Объект String.
                */
                Thread.sleep(5000);
                return "John";
            }
        });
        /*
        Шаг 2.3 - Данный объект Future, как и прошлый, получает результаты
                  работы метода *.submit(). Однако, на этот раз, оба класса
                  параметризированны Integer, что согласуется с документацией
                  по этим классам - они используют Generic-и.
        */
        Future<Integer> futureAge = executorService.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                /*
                Если в первом случае мы получили после задержки
                имя или String, то тут в похожей ситуации метод
                *.call() возвращаем Integer.
                */
                Thread.sleep(4000);
                return 25;
            }
        });
        /*
        Шаг 6 - Получаем из наших объектов Future данные возвращенные методом *.call().
                Все то время пока, потоки работали методы *.get() класса Future ожидали
                результата работы потоков.

                Мы всегда ходим под страхом, что наши потоки будут неожиданно прерваны,
                поэтому многие методы связанные с ожиданием результатов работы потоков
                приходится оборачивать в блок try-catch-finally.
        */
        try {
            String name = futureName.get();
            int age = futureAge.get();
            System.out.println("\nName: " + name + " Age: " + age);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } finally {
            // Всегда закрывайте все потоки и пулы.
            executorService.shutdown();
        }
    }
}
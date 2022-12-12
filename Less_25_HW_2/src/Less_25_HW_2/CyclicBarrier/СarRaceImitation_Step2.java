package Less_25_HW_2.CyclicBarrier;
/*
Данная программа имитирует автомобильную гонку.
Мы имеем 3-и этапа гонки и 10-ть машин, которым
еще надо подготовиться к гонке.

На каждом участке гонки у каждой машины будет своя
случайная скорость (задержка потока). Это означает,
что не зависимо от того, как гонка была начата, она
может закончиться непредсказуемым результатом.
*/
import java.util.Map;
import java.util.concurrent.*;

public class СarRaceImitation_Step2 {
        /*
        На трассе есть участок - 'тоннель', и по нему могут проехать
        только три машины одновременно, естественно, кто доберется
        первым, т.е. по три машины за раз.
        */
        private static final int CARS_COUNT_IN_TUNNEL = 3;
        // Всего машин в гонке
        private static final int CARS_COUNT = 10;
        /*
        Объект класса 'семафор' - Semaphore, в нашем случае
        он отсчитывает, сколько машин заехало в тоннель.
        Как работает 'семафор' можно посмотреть и почитать тут:
        https://github.com/JcoderPaul/JavaExtended-25/tree/master/Less_25_ch_15_Semaphore/src/Less_25_ch_15_Semaphore
        */
        private static final Semaphore tunnelSemaphore = new Semaphore(CARS_COUNT_IN_TUNNEL);
        // Инициализируем пул потоков
        private static final ExecutorService executorService = Executors.newCachedThreadPool();
        // Задаем циклический барьер, в данном случае на старте гонки, когда все будут готовы
        private static final CyclicBarrier cyclicBarrier = new CyclicBarrier(CARS_COUNT);
        // Map для хранения данных об участниках соревнования в формате <'номер машины', 'время на гонку'>
        private static final Map<Integer, Long> score = new ConcurrentHashMap<>();
        // Считаем, все ли добрались до финиша
        private static final CountDownLatch countDownLatch = new CountDownLatch(CARS_COUNT);
        // Начальный индекс победителя
        private static int winnerIndex = -1;
        // Создаем объект на чьем 'мониторе' будет синхронизация
        private static final Object monitor = new Object();

        public static void main(String[] args) {
            for (int i = 0; i < CARS_COUNT; i++) {
                final int index = i;
                // Передаем в цикле нашему ExecutorService 10-ть задач
                executorService.execute(new Runnable() {
                    @Override
                    public void run() {
                        // Запускаем этап подготовки
                        prepare(index);
                        try {
                            // Циклический барьер ждет когда все машины будут готовы
                            if (cyclicBarrier.getNumberWaiting() == 9){
                                System.out.println("---------- Все машины готовы к гонке ----------");
                            }
                            cyclicBarrier.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (BrokenBarrierException e) {
                            e.printStackTrace();
                        }
                        // Замеряем стартовое время текущей машины - 'текущего потока'
                        long before = System.currentTimeMillis();
                        roadFirst(index); // Первый этап
                        tunnel(index); // Тоннель
                        roadSecond(index); // Второй этап
                        /*
                        Тут интересный момент, это синхронизированный блок, на
                        специально созданном для этого объекте. Тот поток, который
                        обратится к монитору первым, тот и будет победителем гонки.

                        Такое возможно, только в случае самой минимальной задержки
                        на всех 3-х 'этапах гонки' или самое меньшее время затраченное
                        на выполнение методов: roadFirst(index), tunnel(index) и
                        roadSecond(index).

                        Как только кто-то из потоков первым обратиться к монитору, он
                        сменит значение 'winnerIndex', другие уже не смогут этого сделать.
                        */
                        synchronized (monitor) {
                            if (winnerIndex == -1) {
                                winnerIndex = index;
                            }
                        }
                        /*
                        Замер времени выполнения кода текущим потоком
                        или 'время затраченное машиной на прохождение
                        дистанции'.
                        */
                        long after = System.currentTimeMillis();
                        // Заносим данные в Map (это потокобезопасная Map)
                        score.put(index, after - before);
                        // Отщелкиваем CountDownLatch
                        countDownLatch.countDown();
                    }
                });
            }
            try {
                // Как только счетчик дойдет до нуля, гонка закончена, все потоки отработали
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // Выводим результаты гонки
            System.out.println("------------------ Результаты гонки ------------------");
            for (int key : score.keySet()) {
                System.out.println(key + " " + score.get(key));
            }
            System.out.println("Winner: " + winnerIndex + " Time: " + score.get(winnerIndex));
            // Закрываем пул потоков
            executorService.shutdown();
        }
        /*
        Метод генерирует задержку на всех этапах
        гонки, в том числе и на этапе подготовки.
        */
        private static void sleepRandomTime() {
            long millis = (long) (Math.random() * 5000 + 1000);
            try {
                Thread.sleep(millis);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // Имитируем этап подготовки
        private static void prepare(int index) {
            System.out.println(index + " started preparing");
            sleepRandomTime(); // Усыпляем поток, имитируем скорость подготовки к гонке
            System.out.println(index + " finished preparing");
        }
        // Имитируем первый этап гонки
        private static void roadFirst(int index) {
            System.out.println(index + " started roadFirst");
            sleepRandomTime(); // Усыпляем поток, имитируем скорость прохождения этапа
            System.out.println(index + " finished roadFirst");
        }
        // Имитируем второй этап гонки
        private static void roadSecond(int index) {
            System.out.println(index + " started roadSecond");
            sleepRandomTime(); // Усыпляем поток, имитируем скорость прохождения этапа
            System.out.println(index + " finished roadSecond");
        }
        // Имитируем тоннель
        private static void tunnel(int index) {
            try {
                /*
                В тоннеле могут находиться одновременно 3-и машины,
                естественно, в нашем случае один поток, одна машина.
                Т.е. данный метод *.acquire() это запрос потока на
                продолжение работы по коду...
                */
                tunnelSemaphore.acquire();
                /*
                ... если разрешение не получено, то поток блокируется.
                Если разрешение есть, то на экран выходят две нижние
                строки со случайной задержкой между выводами.
                */
                System.out.println(index + " started tunnel");
                sleepRandomTime();
                System.out.println(index + " finished tunnel");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                /*
                После выполнения работы - 'прохождения тоннеля' - поток
                должен вернуть семафору полученное разрешение, для того,
                чтобы другие потоки могли отработать свою часть кода.

                На случай если произойдет ошибка, метод *.release() размещен
                в блоке finally, что гарантирует его выполнение.
                */
                tunnelSemaphore.release();
            }
        }
}

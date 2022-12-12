package Less_25_HW_2;
/*
Программа делает некий сложный расчет (работу) и
заполняет массив результатами этого расчета.

При этом в первом случае, массив заполняется в одном
потоке, а во втором - работу делят между собой два
потока.

По результатам работы программы легко заметить, что
два потока справляются с задачей быстрее, чем один
поток.

При расчетах сделаны некие допущения, замер времени
производится только на сам процесс заполнения массива и
расчеты значения элементов. Вся предварительная работа
вынесена 'за скобки'.
*/
public class Less_25_HW_WhichIsFasterOneThreadOrMany {
    private static final int SIZE = 50_000_000;
    private static final int HALF = SIZE / 2;

    public static void main(String[] args) {
        withConcurrency();
        withoutConcurrency();
    }
    private static float[] withoutConcurrency() {
        // Создаем массив и заполняем его '1' для предварительных расчетов
        float[] arrayInSingleThread = new float[SIZE];
        for (int i = 0; i < SIZE; i++) {
            arrayInSingleThread[i] = 1f;
        }
        long before = System.currentTimeMillis(); // Старт замера
        writInFloatArray(arrayInSingleThread, SIZE); // Расчет и заполнение массива
        long after = System.currentTimeMillis(); // Финиш замера
        System.out.println("withoutConcurrency: " + (after - before));
        return arrayInSingleThread;
    }

    private static float[] withConcurrency() {
        // Создаем массив и заполняем его '1' для предварительных расчетов
        float[] arrayInMultiThreading = new float[SIZE];
        for (int i = 0; i < SIZE; i++) {
            arrayInMultiThreading[i] = 1f;
        }
        // Создаем массивы для работы в разных потоках
        float[] firstHalf = new float[HALF];
        float[] secondHalf = new float[HALF];
        // Заполняем их данными из первого массива
        System.arraycopy(arrayInMultiThreading, 0, firstHalf, 0, HALF);
        System.arraycopy(arrayInMultiThreading, HALF, secondHalf, 0, HALF);

        long before = System.currentTimeMillis();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                writInFloatArray(firstHalf,HALF);
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                writInFloatArray(secondHalf,HALF);
            }
        });
        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        /*
        Перезаполняем наш массив из массивов
        рассчитанных в двух разных потоках.
        */
        System.arraycopy(firstHalf, 0, arrayInMultiThreading, 0, HALF);
        System.arraycopy(secondHalf, 0, arrayInMultiThreading, HALF, HALF);
        long after = System.currentTimeMillis();

        System.out.println("withConcurrency: " + (after - before));
        return arrayInMultiThreading;
    }
    private static void writInFloatArray(float[] to_write_in, int size){
        for (int i = 0; i < size; i++) {
            float f = (float) i;
            to_write_in[i] = (float) (to_write_in[i] * Math.sin(0.2f + f / 5) *
                                                       Math.cos(0.2f + f / 5) *
                                                       Math.cos(0.4f + f / 2));
        }
    }
}
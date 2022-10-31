package Less_25_ch_6_SynchronizedMethods;

/**
 * В программе приведен пример синхронизации потоков при обращении к
 * переменной 'counter' в итоге резульат вывода на экран будет одним и тем же,
 * в отличие от асинхронного примера (см. Less_25_NotSynchronized_Step3).
 *
 * В данном примере, оба потока поочередно обращаются к переменной за счет того,
 * что метод *.increment() имеет сигнатуру 'synchronized'. Т.е. в данном случае,
 * только один поток может обращаться к синхронизированному методу и работать с ним,
 * реализуя код записанный в этом методе (в нашем случае это инкремент). Как только один
 * поток "отпускает" метод, другой начинает/продолжает свою работу с этим методом.
 *
 * Естественно в сихронизируемом методе может быть реализован более сложный код.
 *
 * Синхронизация происходит строго на объекте на котором вызвана синхронизация,
 * в нашем коде это 'my_test' класса 'Less_25_Synchronized_Step4()'
 *
 * Мьютекс — это специальный объект для синхронизации потоков. Он «прикреплен» к каждому объекту в Java.
 * Задача мьютекса — обеспечить такой механизм, чтобы доступ к объекту в определенное время был только
 * у одного потока.
 *
 * Во-первых, у мьютекса возможны только два состояния — «свободен» и «занят».
 * Во-вторых, состояниями мьютекса нельзя управлять напрямую, в JAVA нет такого механизма.
 *
 * Монитор — это дополнительная «надстройка» над мьютексом.
 * Фактически монитор — это «невидимый» для программиста кусок кода.
 *
 * В блоке кода, который помечен словом synchronized, происходит захват мьютекса нашего объекта obj.
 * **/

public class Less_25_Synchronized_Step4 {
    private int counter;

    public static void main(String[] args) throws InterruptedException {
        Less_25_Synchronized_Step4 my_test = new Less_25_Synchronized_Step4();
        my_test.doWork();
    }
    /*
    Данный метод помечен как 'synchronized', т.е. это означает то, что в каждый конкретный
    момент времени к данному методу может обратиться, и работать с ним, только один поток.
    */
    public synchronized void increment(){
        counter++;
    }

    public void doWork() throws InterruptedException {
        Thread my_thread_1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i =0; i < 10000; i++){
                    increment();
                }
            }
        });

        Thread my_thread_2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i =0; i < 10000; i++){
                    increment();
                }
            }
        });

        my_thread_1.start();
        my_thread_2.start();
        /*
        Метод final void 'join()' throws InetrruptedException приведенный ниже
        ожидает завершения того потока исполнения, для которого он вызван.
        Его имя отражает следующий принцип: вызывающий поток ожидает, когда указанный
        поток присоединится к нему. Метод jоin() перегружен и позволяет указывать
        максимальный промежуток времени, в течение которого требуется ожидать завершения
        указанного потока исполнения.
        */

        /**
         * There are three overloaded join functions.
         *
         * join(): It will put the current thread on wait until the thread on which it is called is dead.
         * If thread is interrupted then it will throw InterruptedException.
         *
         * public final void join()
         * join(long millis) :It will put the current thread on wait until the thread on which it is called
         * is dead or wait for specified time (milliseconds).
         *
         * public final synchronized void join(long millis)
         * join(long millis, int nanos): It will put the current thread on wait until the thread on which
         * it is called is dead or wait for specified time (milliseconds + nanos).
         **/
        my_thread_1.join();
        my_thread_2.join();

        System.out.println("Счетчик : " + counter);
    }
}

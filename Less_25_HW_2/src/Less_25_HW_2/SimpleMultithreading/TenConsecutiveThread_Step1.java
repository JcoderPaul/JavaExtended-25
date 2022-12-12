package Less_25_HW_2.SimpleMultithreading;
/*
В данном примере мы в цикле создаем потоки и
сразу же запускаем их и пытаемся заставить
основной поток дождаться завершения их
выполнения.

Нам бы хотелось, что бы все 10 потоков стартовали
одновременно. Однако при такой конструкции кода,
каждый поток, будет стартовать и финишировать в
пределах своей циклической итерации. Т.е. мы
получим запуск потока, его финиш и запуск следующего
и т.д.

В следующем примере приведен вариант кода решающий
нашу задачу Less_25_HW_ArrayOfThread_Step2.
*/

public class TenConsecutiveThread_Step1 {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            final int index = i;
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("Start - " + index);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Finish - " + index);
                }
            });
            thread.start();
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("All threads are terminated");
    }
}

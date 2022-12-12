package Less_25_HW_2.SimpleMultithreading;
/*
В данном примере мы создаем пустой список потоков.
Далее создаем поток и передаем в него анонимный класс
с интерфейсом Runnable. В методе *.run() выводим
сведения о запуске потока, усыпляем его на 1 сек., а
затем выводим сообщение о его завершении.

А теперь самое интересное, по крайней мере для меня,
мы в цикле заполняем наш список созданными объектами
thread и тут же их запускаем - *.start(). Т.е. все
десять созданных потока запускаются.

Теперь, чтобы основной поток дождался окончания
выполнения всех потоков, мы в цикле вызываем наш список
и на каждом элементе списка вызываем *.join().
*/
import java.util.ArrayList;
import java.util.List;

public class ArrayOfThread_Step2 {
    public static void main(String[] args) {
        List<Thread> listOfThreads = new ArrayList<>();
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
            listOfThreads.add(thread);
            thread.start();
        }
        for (Thread thread: listOfThreads){
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("All threads are terminated");
    }
}

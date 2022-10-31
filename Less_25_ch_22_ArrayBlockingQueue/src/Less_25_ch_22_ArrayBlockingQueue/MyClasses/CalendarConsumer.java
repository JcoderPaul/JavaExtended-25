package Less_25_ch_22_ArrayBlockingQueue.MyClasses;

import static Less_25_ch_22_ArrayBlockingQueue.Less_25_LinkedBlockingDeque_Step4.*;

public class CalendarConsumer implements Runnable
{
    public CalendarConsumer() {}

    public void run() {
        while (true) {
            if ((deque.size() % 2 == 1))
                // удаление из начала
                System.out.printf(REMOVE_HEAD, deque.pollFirst());
            else
                // удаление из конца
                System.out.printf(REMOVE_TAIL, deque.pollLast());
            try {
                // пауза между циклами
                Thread.sleep(500);
            } catch (InterruptedException e) {}
        }
    }
}

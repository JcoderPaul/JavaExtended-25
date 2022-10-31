package Less_25_ch_22_ArrayBlockingQueue.MyClasses;

import static Less_25_ch_22_ArrayBlockingQueue.Less_25_LinkedBlockingDeque_Step4.*;
import java.util.Iterator;
import java.util.Set;

public class CalendarProducer implements Runnable {
    public CalendarProducer() {}

    public void run() {
        Set<String> keys = names.keySet();
        Iterator<String> iter = keys.iterator();
        String element = null;
        while ((iter.hasNext()) || (element != null)) {
            if (element == null) {
                element = iter.next();
                System.out.printf(EXTRACT, element);
            }
            // Добавление элемента в начало
            if (deque.offerFirst(element)) {
                System.out.printf(INSERT, element);
                iter.remove();
                element = null;
            } else {
                System.out.printf(WAIT, element);
                try {
                    Thread.sleep(250);
                } catch (InterruptedException ignored) {}
            }
            System.out.printf(SIZE, deque.size());
        }
        try {
            Thread.sleep(3500);
        } catch (InterruptedException ignored) {}
    }
}

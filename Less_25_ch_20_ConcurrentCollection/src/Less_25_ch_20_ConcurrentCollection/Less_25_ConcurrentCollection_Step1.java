package Less_25_ch_20_ConcurrentCollection;

import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

public class Less_25_ConcurrentCollection_Step1 {
    public static void main(String[] args) {
        /*
        Используем потокобезопасный ConcurrentHashMap вместо HashMap
        с null в качестве своих элементов не работает. Для проверки
        того как данный код работает с обычным HashMap можно использовать:

        HashMap<Integer, String> myMap = new HashMap<>();

        вместо

        ConcurrentHashMap<Integer, String> myMap = new ConcurrentHashMap<>();
        */

        ConcurrentHashMap<Integer, String> myMap = new ConcurrentHashMap<>();
        myMap.put(1, "Малкольм");
        myMap.put(2, "Дуглас");
        myMap.put(3, "Санара");
        myMap.put(4, "Таймус");
        myMap.put(5, "Говард");
        myMap.put(6, "Сергей");

        System.out.println(myMap);
        System.out.println("--------------------------------------------------------------");

        Runnable readRunner = () -> {
            Iterator<Integer> myIterator = myMap.keySet().iterator();
            while (myIterator.hasNext()){
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                Integer i = myIterator.next();
                System.out.println(i + " : " + myMap.get(i));

            }
        };

        Runnable addRunner = () -> {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            myMap.put(7,"Лин");
        };

        Thread trd_1 = new Thread(readRunner);
        Thread trd_2 = new Thread(addRunner);

        trd_2.start();
        trd_1.start();

        try {
            trd_1.join();
            trd_2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("--------------------------------------------------------------");
        System.out.println(myMap);
    }
}

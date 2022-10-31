package Less_25_ch_21_CopyOnWriteArrayList;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class Less_25_CopyOnWriteArrayList_Step1 {
    public static void main(String[] args) {
        /*
        Что бы увидеть ошибку при выполнении кода можно просто поменять местами
        строки кода, по созданию List-ов.

        ArrayList<String> myList = new ArrayList<>();
        */

        CopyOnWriteArrayList<String> myList = new CopyOnWriteArrayList<>();
        myList.add("Малкольм");
        myList.add("Дуглас");
        myList.add("Санара");
        myList.add("Таймус");
        myList.add("Говард");
        myList.add("Сергей");

        System.out.println(myList);
        System.out.println("--------------------------------------------------------------");

        Runnable readRunner = () -> {
            Iterator<String> myIterator = myList.iterator();
            while (myIterator.hasNext()){
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(myIterator.next());

            }
        };

        Runnable addRunner = () -> {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            myList.remove(3);
            myList.add("Лин");
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
        System.out.println(myList);
    }
}

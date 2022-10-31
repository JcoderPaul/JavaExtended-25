package Less_25_ch_15_Semaphore.MyClasses;

import java.util.concurrent.Semaphore;
/*
Пусть наши люди, желающие позвонить будут потоками,
захватывающими ограниченный ресурс - телефонные будки.
*/
public class ManWantsToCall extends Thread{
    private String man_name;
    private Semaphore phoneBooth; // Переменная класса семафор

    public ManWantsToCall(String name, Semaphore phoneBooth) {
        this.man_name = name;
        this.phoneBooth = phoneBooth;
        this.start(); // !!! После создания объекта его поток сразу запустится !!!
    }

    @Override
    public void run() {

        try {
            System.out.println(man_name + " ждет своей очереди...");
            phoneBooth.acquire();
            System.out.println(man_name + " пользуется телефонной будкой");
            sleep(2000);
            System.out.println(man_name + " освободил(а) телефонную будку");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        finally {
            phoneBooth.release();
        }
    }

    public String getManName() {
        return man_name;
    }

    public Semaphore getPhoneBooth() {
        return phoneBooth;
    }
}

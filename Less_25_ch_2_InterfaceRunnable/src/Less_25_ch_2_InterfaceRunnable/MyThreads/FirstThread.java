package Less_25_ch_2_InterfaceRunnable.MyThreads;
/*
Для создания нового потока мы можем создать новый класс, либо наследуя его от класса Thread.
*/
public class FirstThread implements Runnable{
    public void run(){
        for (int i = 0; i < 1000; i++){
            System.out.println("Вывод из FirstThread -> " + i);
        }
    }
}

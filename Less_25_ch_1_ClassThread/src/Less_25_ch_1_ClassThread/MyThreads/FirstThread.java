package Less_25_ch_1_ClassThread.MyThreads;
/*
Для создания нового потока мы можем создать новый класс, либо наследуя его от класса Thread.
*/
public class FirstThread extends Thread{
    public void run(){
        for (int i = 0; i < 1000; i++){
            System.out.println("Вывод из FirstThread -> " + i);
        }
    }
}

package Less_25_ch_1_ClassThread.MyThreads;
/*
Для создания нового потока мы можем создать новый класс, либо наследуя его от класса Thread.
*/
public class SecondThread extends Thread{
    public void run(){
        for (int i = 1000; i > 0; i--){
            System.out.println("Вывод из SecondThread -> " + i);
        }
    }
}

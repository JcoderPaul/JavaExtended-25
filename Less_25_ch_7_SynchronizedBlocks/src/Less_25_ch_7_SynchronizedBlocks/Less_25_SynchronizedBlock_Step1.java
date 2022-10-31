package Less_25_ch_7_SynchronizedBlocks;
/*
Вся работа (по синхронизации) проведена в классе Worker(), там же основной код программы.
*/
import Less_25_ch_7_SynchronizedBlocks.MyClasses.Worker;

public class Less_25_SynchronizedBlock_Step1 {
    public static void main(String[] args) {
            /*
            Создаем новый объект Worker, при этом синтаксис выглядит именно так
            название класса из которого создается объект, а затем '()'
            после идет '.' а затем метод *.main() класса Worker.
            */
            new Worker().main();
    }
}

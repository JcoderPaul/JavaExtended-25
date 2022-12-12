package Less_25_HW_2;
/*
Простой пример в котором повторяем особенности работы
потоков. В данном случае в одном потоке список из
целочисленных элементов заполняется, а в другом потоке
мы пытаемся получить эту информацию.

Ожидаемо, эталонная информация будет расходиться с
получаемой, по причине того, что потоки запускаются
не в порядке их старта и естественно информация одним
потоком может быть получена, когда другой поток еще не
завершил работу свою.

Для получения полного совпадения исходных данных и
фактических необходимо потоку, который получает данные,
дождаться завершения выполнения работы потоком, который
заполняет список.

Как это не странно, но вариант применения метода *.join()
обычно происходит в основном потоке, чтобы заставить того
дождаться завершения остальных, запущенных в нем. Но метод
универсальный и применяется к любому потоку, поэтому мы
можем применить его несколько раз в разных местах программы,
как в основном потоке, так и в 'сервисном'
*/
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Less_25_HW_JoinInOtherThread {
    public static void main(String[] args) {
        System.out.println("Start program");

        Random rnd = new Random();
        int realSizeOfList = rnd.nextInt(1000) + 10;
        System.out.println("Ожидаемый размер списка: " + realSizeOfList);
        List<Integer> listToTest = new ArrayList<>();

        Thread threadToAddedList = new Thread(new Runnable() {
            @Override
            public void run() {
                 for (int i = 0; i < realSizeOfList; i++){
                     listToTest.add(i);
                 }
            }
        });

        Thread threadToGetSizeOfList = new Thread(new Runnable() {
            @Override
            public void run() {
                /*
                Мы просим текущий поток threadToGetSizeOfList дождаться окончания
                работы потока threadToAddedList и только затем провести замеры размера
                списка. Если убрать метод *.join() из данного блока кода, то получим
                некое расхождение между фактом и замером.
                */
                try {
                    threadToAddedList.join();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Данные из второго потока о размере списка: " + listToTest.size());
            }
        });
        // Стартуем потоки
        threadToAddedList.start();
        threadToGetSizeOfList.start();
        /*
        А тут мы просим основной поток MAIN дождаться
        окончания всех потоков в программе.
        */
        try {
            threadToAddedList.join();
            threadToGetSizeOfList.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Finish program");
    }
}

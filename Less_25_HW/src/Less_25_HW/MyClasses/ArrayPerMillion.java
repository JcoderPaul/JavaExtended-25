package Less_25_HW.MyClasses;
/*
Функциональный класс с методом создающим массив
на 1000000 элементов и заполняющим его случайными
числами от 1 до 300.
*/
import java.util.Random;

public class ArrayPerMillion {
    public static int[] getArrayOfRandomElement(){
        // Создали массив
        int[] values = new int[1_000_000];
        // Заполнили его случайными числами
        Random random = new Random();
        for (int i = 0; i < values.length; i++) {
            /*
            Нужны числа в диапазоне от 1 до 300,
            данная строка, как раз и решает
            эту задачу, т.к. random.nextInt(300)
            генерирует числа в диапазоне от 0 до 299
            */
            values[i] = random.nextInt(300) + 1;
        }
        return values;
    }
}

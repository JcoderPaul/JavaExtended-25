package Less_25_HW.MyClasses;

import java.util.stream.IntStream;

public class MonoThreadFinder implements GetBackResult{
    int[] values;
    int max;
    private final String nameOfAlgo= "MonoThreadFinder";

    public MonoThreadFinder(int[] values) {
        this.values = values;
        this.max = IntStream.of(values)
                            .max()
                            .orElse(Integer.MIN_VALUE);
    }

    @Override
    public void getRes() {
        System.out.println("Максимальный элемент массива: " + max);
    }

    public String getNameOfAlgo() {
        return nameOfAlgo;
    }
}

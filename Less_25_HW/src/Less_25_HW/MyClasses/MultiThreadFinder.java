package Less_25_HW.MyClasses;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.stream.IntStream;

public class MultiThreadFinder implements GetBackResult{
    int[] values;
    ExecutorService executorService;
    int max;
    private final String nameOfAlgo= "MultiThreadFinder";

    public MultiThreadFinder(int[] values, ExecutorService executorService)
                                        throws ExecutionException, InterruptedException {
        this.values = values;
        this.executorService = executorService;
        max = executorService.submit(() -> IntStream.of(values)
                        .parallel()
                        .max()
                        .orElse(Integer.MIN_VALUE)).get();
    }

    @Override
    public void getRes() {
        System.out.println("Максимальный элемент массива: " + max);
    }

    public String getNameOfAlgo() {
        return nameOfAlgo;
    }
}

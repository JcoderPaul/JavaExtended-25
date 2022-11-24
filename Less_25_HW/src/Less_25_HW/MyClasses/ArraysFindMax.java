package Less_25_HW.MyClasses;
/*
Функциональный класс, который содержит только
два статических метода для поиска максимального
значения среди всех элементов массива.
*/
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.stream.IntStream;

public class ArraysFindMax {
    // Однопоточный режим поиска
    public static int findMaxByOneThread(int[] values) {
        return IntStream.of(values)
                        .max()
                        .orElse(Integer.MIN_VALUE);
    }
    // Многопоточный режим поиска в распараллельненном stream-е
    public static int findMaxParallel(int[] values, ExecutorService executorService)
                                                       throws ExecutionException, InterruptedException
    {
        return executorService.submit(() -> IntStream.of(values)
                                                     .parallel()
                                                     .max()
                                                     .orElse(Integer.MIN_VALUE))
                              .get();
    }
}

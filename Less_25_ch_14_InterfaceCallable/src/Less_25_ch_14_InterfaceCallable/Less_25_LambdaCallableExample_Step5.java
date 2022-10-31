package Less_25_ch_14_InterfaceCallable;
/*
Применим Лямбда выражение для генерации задания нашему ExecutorService,
созданному из *.newFixedThreadPool(1), но в данном случае запускающего
только один поток.
*/
import java.util.Random;
import java.util.concurrent.*;

public class Less_25_LambdaCallableExample_Step5 {
    public static void main(String[] args) {
        System.out.println("Main start.");
        Random rnd_for_out = new Random();

        ExecutorService executorServiceTest = Executors.newFixedThreadPool(1);

        Future<Integer> futureRes = executorServiceTest.submit(() -> {
            System.out.println("Tread start.");
            Thread.sleep(3000);
            System.out.println("Tread finish.");
            int limit_of_generator = 10;
            int bad_limit = limit_of_generator/2;
            int rnd_volume = rnd_for_out.nextInt(limit_of_generator);
            if(rnd_volume < bad_limit){
                throw new Exception("Variable 'rnd_volume < " + bad_limit + "' and it is BAD!");
            } else {
                System.out.println("Variable 'rnd_volume > " + bad_limit + "' and it is GOOD!");
                return rnd_volume;
            }
        });

        try {
            int tread_work_result = futureRes.get();
            System.out.println("Resul of random generator -> " + tread_work_result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            Throwable ex = e.getCause();
            System.out.println(ex.getMessage());
        }

        executorServiceTest.shutdown();
        System.out.println("Main finish.");
    }
}

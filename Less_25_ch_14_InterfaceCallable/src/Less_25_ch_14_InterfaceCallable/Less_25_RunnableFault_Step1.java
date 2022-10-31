package Less_25_ch_14_InterfaceCallable;
/*
Как обычно, это еще не Callable это подготовка к нему,
что бы понять зачем же вдруг понадобился этот интерфейс
когда есть такой прекрасный Runnable.
*/
import Less_25_ch_14_InterfaceCallable.MyClasses.FactorialRunner;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Less_25_RunnableFault_Step1 {
    public static int factorialResult;

    public static void main(String[] args) throws InterruptedException {
        // Создаем ExecutorService, через метод единственного потока
        ExecutorService my_es = Executors.newSingleThreadExecutor();
        int find_f_limit = 5;
        /*
        Создаем наш объект подписанный на Runnable, по факту это
        наше задание для потока, созданного экзекутер сервисом.
        */
        FactorialRunner f_runner = new FactorialRunner(find_f_limit);
        // Запускаем поток и передаем в него задание
        my_es.execute(f_runner);
        // Останавливаем ExecutorService, и естественно поток, после выполнения задания
        my_es.shutdown();
        /*
        Просим основной поток подождать 10 секунд, что бы дать
        ExecutorService потоку проделать работу и вернуть результат.
        Недостаток интерфейса Runnable в том, что его метод *.run(),
        void и ничего не возвращает, так же этот метод не выбрасывает
        исключения, если вдруг это понадобится.

        Но об этом в следующем примере, метод интерфейса Callable
        решает эту проблему.
        */
        my_es.awaitTermination(10, TimeUnit.SECONDS);
        System.out.println("Факториал от " + find_f_limit + " равен -> " + factorialResult);
    }

}

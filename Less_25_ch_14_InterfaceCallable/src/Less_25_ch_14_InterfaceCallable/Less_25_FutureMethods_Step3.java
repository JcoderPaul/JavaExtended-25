package Less_25_ch_14_InterfaceCallable;
/*
Что такое Future, почему так называется и его методы.
*/

import Less_25_ch_14_InterfaceCallable.MyClasses.FactorialCallable;
import Less_25_ch_14_InterfaceCallable.MyClasses.FactorialForFuture;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Less_25_FutureMethods_Step3 {
    public static int factorialResult;

    public static void main(String[] args) throws InterruptedException {
        // Создаем ExecutorService, через метод единственного потока
        ExecutorService my_es = Executors.newSingleThreadExecutor();
        int find_f_limit = 5;
        // Создаем наш объект подписанный на Callable
        FactorialForFuture f_callable = new FactorialForFuture(find_f_limit);
        /*
        Методы *.execute() и *.submit() являются способами подачи задачи в пул потоков.
        !!! НО между ними ЕСТЬ разница !!!
        - execute(Runnable command) определён в интерфейсе Executor и принимает ТОЛЬКО
        объект Runnable, он выполняет поданную задачу и ничего не возвращает.
        - submit() – перегруженный метод (может принять и Runnable и !!! САМОЕ ГЛАВНОЕ !!!
        Callable), он определён в интерфейсе ExecutorService. Способен принимать задачи
        (добавлять наши tasks для выполнения в созданные ExecutorService потоки) причем
        обоих типов (см. выше и Callable и Runnable). Метод возвращает объект Future,
        который можно использовать для контроля и управления процессом выполнения,
        получения его результата.
        */

        /*
        В данном случае метод *.submit() не сразу возвращает результат, поскольку задание
        может еще выполнятся. Это как бы обещание результата в будущем, когда задание (наш
        task, поступивший в ExecutorService выполнится и *.submit() вернет результат).
        */
        Future<Integer> my_future_obj = my_es.submit(f_callable);
        /*
        Далее, мы, все же, хотим получить этот результат и вызываем метод *.get(). Он в свою
        очередь блокирует работу потока, из которого был вызван (в нашем случае MAIN), что бы
        дождаться выполнения задания (task-a) и получить требуемый результат.

        Метод .isDone() позволяет проверить завершена ли задача.
        */
        try {
            // На текущей проверке задание еще не выполнено и будет - false
            System.out.println("Задача завершена -> " + my_future_obj.isDone());
            System.out.println("We want to get results");
            // Метод *.get() вернет результат, только когда расчет закончится.
            factorialResult = my_future_obj.get();
            System.out.println("Result received!");
            // На текущей проверке задание уже выполнено и будет - true
            System.out.println("Задача завершена -> " + my_future_obj.isDone());
        } catch (ExecutionException e) {
            System.out.println(e.getCause());
        }
        /*
        Для остановки работы ExecutorService, !!! обязательно !!! в блоке выполняющемся
        при любом раскладе обращаемся к методу *.shutdown()
        */
        finally {
            my_es.shutdown();
        }
        /*
        В данной ситуации мы не используем метод *.awaitTermination(), потому что поток
        работу завершил, результат получен и передан в factorialResult.
        */
        System.out.println("Факториал от " + find_f_limit + " равен -> " + factorialResult);
    }
}

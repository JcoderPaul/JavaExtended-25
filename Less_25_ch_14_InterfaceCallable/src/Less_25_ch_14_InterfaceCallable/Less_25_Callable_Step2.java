package Less_25_ch_14_InterfaceCallable;
/*
Пример применения Callable и чем он отличается от Runnable.
*/
import Less_25_ch_14_InterfaceCallable.MyClasses.FactorialCallable;

import java.util.concurrent.*;

public class Less_25_Callable_Step2 {
    public static int factorialResult;

    public static void main(String[] args) throws InterruptedException {
        // Создаем ExecutorService, через метод единственного потока
        ExecutorService my_es = Executors.newSingleThreadExecutor();
        int find_f_limit = 5;
        // Создаем наш объект подписанный на Callable
        FactorialCallable f_callable = new FactorialCallable(find_f_limit);
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
        Поскольку на выходе метода *.submit() будет объект типа Future, причем мы сами
        параметризировали его Integer, нам нужно куда-то положить этот объект. Повторюсь,
        мы уже знаем, что на выходе НАШЕГО потока, будет объект типа Integer, поэтому и
        ссылка на Future результат делаем типа Integer.
        */
        Future<Integer> my_future_obj = my_es.submit(f_callable);

        /*
        Поскольку у нас объект Callable мы применяем метод *.submit(), а он в свою очередь
        возвращает объект Future, который нужен нам. Для его получения используем метод
        *.get() и извлекаем его из переменной куда сами же и положили на прошлом шаге.
        Метод *.get() способен выбросить исключение, т.к. метод *.call() объекта Callable
        способен выкинуть этот фокус. Поэтому заворачиваем метод в try-catch.
        */
        try {
            factorialResult = my_future_obj.get();
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
        System.out.println("Факториал от " + find_f_limit + " равен -> " + factorialResult);
    }
}

package Less_25_ch_14_InterfaceCallable.MyClasses;

import java.util.concurrent.Callable;

// Имплементируем интерфейс Callable и сразу задаем, что будет возвращать наш класс
public class FactorialForFuture implements Callable<Integer> {
    int f;

    public FactorialForFuture(int f) {
        this.f = f;
    }

    @Override
    public Integer call() throws Exception{
        int result = 1;
        /*
        Проверяем правильность введенного числа, оно должно
        быть больше или равно 0, но если это не так, программа
        выбрасывает исключение.
        */
        if(f <= 0) {
            throw new Exception("You cannot enter 0 or less!");
        } else {
            for(int i = 1; i <= f; i++){
            result *= i;
            // Для демонстрации работы с Future Obj введем задержку на поток 1 сек.
            Thread.sleep(1000);
            }
        }
        /*
        Ну, и в данном случае, вместо присваивания, как в прошлом
        примере с Runnable мы просто возвращаем искомое значение
        (объект) в нужном формате.
        */
        return result;
    }
}

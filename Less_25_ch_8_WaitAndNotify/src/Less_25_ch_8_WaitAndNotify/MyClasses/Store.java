package Less_25_ch_8_WaitAndNotify.MyClasses;

/**
 * Итак, у нас есть класс магазин, потребитель и производитель.
 *
 * - Производитель в своем методе run() добавляет в объект Store с помощью
 * его метода *.put_in_my_store() 10 товаров.
 * - Потребитель в своем методе run() в цикле обращается к методу *.get_from_my_store()
 * объекта Store для получения этих товаров.
 *
 * Оба метода Store - put... и get... являются синхронизированными (на объекте Store).
 *
 * Для отслеживания наличия товаров в классе Store проверяем значение переменной 'product'.
 * По умолчанию товара нет, поэтому переменная равна 0.
 * Метод *.get_from_my_store() - получение товара должен срабатывать только при наличии
 * хотя бы одного товара 'на полке'. Поэтому в методе *.get_from_my_store проверяем,
 * отсутствует ли товар: 'while (product < 1)'
 *
 * Если товар отсутствует, вызывается метод wait(). Этот метод освобождает монитор объекта Store
 * и блокирует выполнение метода *.get_from_my_store(), пока для этого же монитора не будет вызван
 * метод notify().
 *
 * Когда в методе *.put_in_my_store() добавляется товар и вызывается notify(), метод *.get_from_my_store()
 * получает монитор и выходит из конструкции 'while (product < 1)', так как товар добавлен.
 *
 * Метод *.get_from_my_store() сработает при значении 'product >= 1', т.е. наличии товара в магазине.
 * Он не будет ждать наполнения магазина, а просто сработает. Имитируется получение покупателем товара.
 * Для этого выводится сообщение, и уменьшается значение product: 'product--'.
 * И далее вызов метода notify() дает сигнал методу *.put_in_my_store() продолжить работу.
 *
 * В методе *.put_in_my_store() работает похожая логика, только теперь метод *.put_in_my_store()
 * должен срабатывать, если в магазине не более трех товаров. Поэтому в цикле проверяется наличие
 * товара, и если товар уже есть, то освобождаем монитор с помощью wait() и ждем вызова
 * notify() в методе *.get_from_my_store().
 **/
public class Store {

    private int product = 0;
    /*
    Синхронизированный метод 'взять'. К его переменным может в одну
    единицу времени обратиться только один поток.
    */
    public synchronized void get_from_my_store() {
        while (product < 1) {
            try {
                wait();
            }
            catch (InterruptedException e) {
            }
        }
        product--;
        System.out.print("Покупатель купил 1 товар -> ");
        System.out.println("Товаров на складе: " + product);
        notify();
    }

    /*
    Синхронизированный метод 'положить'. К его переменным может в одну единицу
    времени обратиться только один поток, как и в случае 'взять'.
    */
    public synchronized void put_in_my_store() {
        while (product >= 3) {
            try {
                wait();
            }
            catch (InterruptedException e) {
            }
        }
        product++;
        System.out.print("Производитель добавил 1 товар -> ");
        System.out.println("Товаров на складе: " + product);
        notify();
    }
}
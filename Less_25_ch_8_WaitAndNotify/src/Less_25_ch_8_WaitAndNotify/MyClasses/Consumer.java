package Less_25_ch_8_WaitAndNotify.MyClasses;

// Класс Потребитель
public class Consumer implements Runnable{
    Store store;
    /*
    Для демонстрации работы методов wait и notify сделаем нашего покупателя
    фанатом того же магазина куда поставляет товар наш производитель.
    Сведения о магазине передаются в качестве аргументов в конструктор
    покупателя.
    */
    public Consumer(Store store){
        this.store=store;
    }

    public void run(){
        for (int i = 1; i < 10; i++) {
            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            store.get_from_my_store();
            /*
            Consumer реализует единственный метод *.run() в котором в цикле обращается к
            методу *.get_from_my_store() класса Store. Этот метод просто декрементирует
            переменную 'product' класса Store, естественно после соблюдения определенных
            условий (product > 1). Т.е. если на полке есть хотя бы одна единица товара -
            забирает ее.
            */
        }
    }
}

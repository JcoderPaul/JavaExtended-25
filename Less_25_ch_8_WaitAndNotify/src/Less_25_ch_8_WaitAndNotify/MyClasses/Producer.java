package Less_25_ch_8_WaitAndNotify.MyClasses;

// класс Производитель
public class Producer implements Runnable{
    Store store;
    /*
    Условно, данный производитель привязан к определенному магазину,
    сведения о котором передаются в качестве аргументов в конструктор
    производителя.
    */
    public Producer(Store store){
        this.store=store;
    }

    public void run(){
        for (int i = 1; i < 10; i++) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            store.put_in_my_store();
            /*
            Producer реализует единственный метод *.run() в котором в цикле обращается к
            методу *.put_in_my_store() класса Store. Этот метод просто инкрементирует
            переменную 'product' класса Store, естественно после соблюдения определенных
            условий (product <= 3). Т.е. если на полке менее трех единиц товара - загружает
            полку до предела (в данном примере = 3).
            */
        }
    }
}
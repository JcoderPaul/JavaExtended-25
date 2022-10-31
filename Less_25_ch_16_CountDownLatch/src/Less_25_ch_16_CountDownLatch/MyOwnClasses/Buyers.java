package Less_25_ch_16_CountDownLatch.MyOwnClasses;
/*
Имитируем приличного покупателя который строго дожидается окончания отсчета
через метод *.await() без параметров.
*/
import java.util.concurrent.CountDownLatch;

public class Buyers extends Thread{
    private String b_name;
    private CountDownLatch countDownLatch;
    private Supermarket supermarket;

    public Buyers(String b_name, Supermarket supermarket, CountDownLatch countDownLatch) {
        this.b_name = b_name;
        this.supermarket = supermarket;
        this.countDownLatch = countDownLatch;
        this.start();
    }

    @Override
    public void run() {
        try {
            countDownLatch.await();
            sleep(100);
            System.out.println(b_name + " прорвался в магазин '"+ supermarket.getName_of_market() +
                                        "' и хватает с полок все подряд...");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

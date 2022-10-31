package Less_25_ch_16_CountDownLatch.MyOwnClasses;
/*
Имитируем нетерпеливого покупателя который дожидается окончания
времени ожидания либо окончания отсчета, что раньше наступит.
Через метод *.await(long timeout, TimeUnit unit) с параметрами,
где timeout - время ожидания, unit - единица времени
(наносекунды, миллисекунды, секунды...)
*/
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class EagerBuyers extends Thread{
    private String b_name;
    private CountDownLatch countDownLatch;
    private Supermarket supermarket;

    public EagerBuyers(String b_name, Supermarket supermarket, CountDownLatch countDownLatch) {
        this.b_name = b_name;
        this.supermarket = supermarket;
        this.countDownLatch = countDownLatch;
        this.start();
    }

    @Override
    public void run() {
        try {
            countDownLatch.await(100, TimeUnit.MILLISECONDS);
            System.out.println(b_name + " примчался в магазин '" + supermarket.getName_of_market() +
                                        "' и шурует в нем до открытия ...");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

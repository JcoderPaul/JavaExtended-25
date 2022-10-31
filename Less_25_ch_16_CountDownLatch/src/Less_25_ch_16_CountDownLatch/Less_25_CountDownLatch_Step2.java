package Less_25_ch_16_CountDownLatch;
/*
Имитируем нетерпеливого покупателя который дожидается окончания
времени ожидания либо окончания отсчета, что раньше наступит.
Через метод *.await(long timeout, TimeUnit unit) с параметрами,
где timeout - время ожидания, unit - единица времени
(наносекунды, миллисекунды, секунды...)

В текущем примере время ожидания timeout в методе *.await класса
EagerBuyers задано предельно малое, и превышает время задержки
на совершение соответствующих методов, класса Supermarket, которые
в свою очередь декрементируют счетчик CountDownLatch.

Т.е. все произойдет быстрее, чем отработает CountDownLatch.
*/
import Less_25_ch_16_CountDownLatch.MyOwnClasses.*;

public class Less_25_CountDownLatch_Step2 {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Раннее утро черной пятницы ...\n");

        Supermarket ikea = new Supermarket("IKEA");

        new EagerBuyers("Часовой", ikea, ikea.getCountDownLatch_of_this_Market());
        new EagerBuyers("Супер-Прайм", ikea, ikea.getCountDownLatch_of_this_Market());
        new EagerBuyers("Серебряный Сёрфер", ikea, ikea.getCountDownLatch_of_this_Market());
        new EagerBuyers("Флэш", ikea, ikea.getCountDownLatch_of_this_Market());
        new EagerBuyers("Чёрный Гонщик",ikea, ikea.getCountDownLatch_of_this_Market());

        ikea.supermarketStaffAtWork();
        ikea.everythingIsReadyForTheOpeningOfTheStore();
        ikea.openSupermarket();

        Thread.sleep(1000);
        System.out.println("\nРаннее утро субботы ...");
    }
}

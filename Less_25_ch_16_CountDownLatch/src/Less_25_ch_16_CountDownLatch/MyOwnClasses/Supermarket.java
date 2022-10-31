package Less_25_ch_16_CountDownLatch.MyOwnClasses;

import java.util.concurrent.CountDownLatch;

public class Supermarket {
    private String name_of_market;
    private CountDownLatch countDownLatch_of_this_Market = new CountDownLatch(3);

    public Supermarket(String name_of_market) {
        this.name_of_market = name_of_market;
    }

    public CountDownLatch supermarketStaffAtWork() throws InterruptedException {
        Thread.sleep(2000);
        System.out.println("Supermarket employees come to work!");
        countDownLatch_of_this_Market.countDown();
        System.out.println("Текущее состояние CountDownLatch -> " + countDownLatch_of_this_Market.getCount());
        return countDownLatch_of_this_Market;
    }

    public CountDownLatch everythingIsReadyForTheOpeningOfTheStore() throws InterruptedException {
        Thread.sleep(5000);
        System.out.println("Everything is ready for the opening of the store!");
        countDownLatch_of_this_Market.countDown();
        System.out.println("Текущее состояние CountDownLatch -> " + countDownLatch_of_this_Market.getCount());
        return countDownLatch_of_this_Market;
    }

    public CountDownLatch openSupermarket() throws InterruptedException {
        Thread.sleep(3000);
        System.out.println("The supermarket is open!");
        countDownLatch_of_this_Market.countDown();
        System.out.println("Текущее состояние CountDownLatch -> " + countDownLatch_of_this_Market.getCount());
        return countDownLatch_of_this_Market;
    }

    public CountDownLatch getCountDownLatch_of_this_Market() {
        return countDownLatch_of_this_Market;
    }

    public String getName_of_market() {
        return name_of_market;
    }
}

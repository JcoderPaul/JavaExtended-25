package Less_25_ch_16_CountDownLatch;
/*
Имитируем приличного покупателя который строго дожидается окончания отсчета
через метод *.await() без параметров.
*/
import Less_25_ch_16_CountDownLatch.MyOwnClasses.*;

public class Less_25_CountDownLatch_Step1 {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Раннее утро черной пятницы ...\n");

        Supermarket ashan = new Supermarket("АШАН");

        new Buyers("Гендольф", ashan, ashan.getCountDownLatch_of_this_Market());
        new Buyers("Фродо", ashan, ashan.getCountDownLatch_of_this_Market());
        new Buyers("Саурон", ashan, ashan.getCountDownLatch_of_this_Market());
        new Buyers("Бильбо", ashan, ashan.getCountDownLatch_of_this_Market());
        new Buyers("Король орков",ashan, ashan.getCountDownLatch_of_this_Market());
        new Buyers("Леголас", ashan, ashan.getCountDownLatch_of_this_Market());

        ashan.supermarketStaffAtWork();
        ashan.everythingIsReadyForTheOpeningOfTheStore();
        ashan.openSupermarket();


        Thread.sleep(1000);
        System.out.println("\nРаннее утро субботы ...");
    }
}

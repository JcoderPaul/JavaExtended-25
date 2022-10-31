package Less_25_ch_17_Exchanger.MyClasses;

import java.util.Random;

public class BlackBox {

    public HandFigure nextMoveOfPlayer(){
        HandFigure playerStrike;
        Random rnd = new Random();
        int luckyHand = rnd.nextInt(3);
        if( luckyHand == 0) {
            return HandFigure.ROCK;
        } else if (luckyHand == 1) {
            return HandFigure.PAPER;
        } else {
            return HandFigure.SCISSORS;
        }
    }
}

package Less_25_ch_5_Volatile.MyTreads;

public class NotVolatileExample extends Thread{

    private boolean end_of_cycle = true;

    @Override
    public void run() {
        long counter = 0;
        while (end_of_cycle){
            counter++;
        }
        System.out.println("Loop is finished, counter = " + counter);
    }

    public void setEnd_of_cycle(boolean end_of_cycle) {
        this.end_of_cycle = end_of_cycle;
    }
}

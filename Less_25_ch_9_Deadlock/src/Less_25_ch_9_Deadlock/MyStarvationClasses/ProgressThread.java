package Less_25_ch_9_Deadlock.MyStarvationClasses;

import Less_25_ch_9_Deadlock.Less_25_Starvation_Step4;

import javax.swing.*;

public class ProgressThread extends Thread {
    JProgressBar progressBar;

    public ProgressThread() {
        progressBar = new JProgressBar();
        progressBar.setString(this.getName());
        progressBar.setStringPainted(true);
    }

    public JComponent getProgressComponent() {
        return progressBar;
    }

    @Override
    public void run () {

        int c = 0;
        while (true) {
            synchronized (Less_25_Starvation_Step4.sharedObj) {
                if (c == 100) {
                    c = 0;
                }
                progressBar.setValue(++c);
                try {
                    //sleep the thread to simulate long running task
                    Thread.sleep(100);
                    /*
                    !!! НО !!!
                    Если заменить 'Thread.sleep(100);' на 'sharedObj.wait(100);'
                    распределение нагрузки на потоки выровняется.
                    */
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

package Less_25_ch_9_Deadlock;
/*
Из документации ORACLE:
Голодание описывает ситуацию, когда поток (потоки) не может получить доступ
к общим ресурсам и не может выполнять свою работу. Это происходит, когда общие
ресурсы становятся недоступными в течение длительного времени из-за «жадных»
потоков (например, потоков с высоким приоритетом).

Например, предположим, что объект предоставляет синхронизированный метод, возврат
которого часто занимает много времени. Если один поток часто вызывает этот метод,
другие потоки, которым также требуется синхронизированный доступ к тому же объекту,
просто будут заблокированы.

Из данного примера видно, что не все потоки имеют равные шансы иметь одинаковое
процессорное время. Некоторые потоки долго не могут получить доступ к выполнению
работы.
*/

import Less_25_ch_9_Deadlock.MyStarvationClasses.*;

import javax.swing.*;
import java.awt.*;

public class Less_25_Starvation_Step4 {

    public static Object sharedObj = new Object();

    public static void main (String[] args) {
        JFrame frame = createFrame();
        frame.setLayout(new FlowLayout(FlowLayout.LEFT));

        for (int i = 0; i < 5; i++) {
            ProgressThread progressThread = new ProgressThread();
            frame.add(progressThread.getProgressComponent());
            progressThread.start();
        }

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static JFrame createFrame () {
        JFrame frame = new JFrame("Starvation Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(300, 200));
        return frame;
    }
}

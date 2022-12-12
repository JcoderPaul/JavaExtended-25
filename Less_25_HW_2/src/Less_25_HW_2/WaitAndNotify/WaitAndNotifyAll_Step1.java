package Less_25_HW_2.WaitAndNotify;
/*
Решение задачи 'в лоб' создать 4-и отдельных
потока и запускать и останавливать их при
соблюдении определенных условий.
*/
public class WaitAndNotifyAll_Step1 {
    // Задаем значения четырех подстрок
    private static final String strForFirstThread = "Квас для вас";
    private static final String strForSecondThread = " и нас, ";
    private static final String strForThirdThread = "ядреный, аж слезы";
    private static final String strForFourthThread = " из глаз! ";
    // Создаем монитор
    private static final Object monitor = new Object();
    // Задаем значение 'контрольной' подстроки
    private static String nextSubString = strForFirstThread;

    public static void main(String[] args) {
        // Создаем поток
        new Thread(new Runnable() {
            @Override
            public void run() {
                // Создаем блок синхронизации на мониторе
                synchronized (monitor) {
                    // В цикле ...
                    for (int i = 0; i < 5; i++) {
                        try{
                            // Проверяем чему соответствует 'контрольная' подстрока
                            while (!nextSubString.equals(strForFirstThread)){
                                /*
                                Если не первой подстроке, усыпляем текущий
                                поток или переводим в режим ожидания.
                                */
                                monitor.wait();
                            }
                        } catch (InterruptedException ex){
                            ex.printStackTrace();
                        }
                        /*
                        Если поток в режиме ожидания, то он
                        не выполняет последние три строки,
                        т.е. вывод на экран подстроки, смена
                        контрольной подстроки и оповещение
                        всем потокам, что можно проснуться.

                        Если проверка пройдена, то поток не
                        засыпает и данные строки идут в работу.

                        Такое возможно если поток не спал или
                        был разбужен из другого потока методом
                        *.notifyAll() и условия были выполнены.

                        Метод *.notify() тут не подходит, т.к.
                        не понятно, какой из 4-х потоков будет
                        разбужен и в итоге 'все мило повиснут в
                        ожидании чуда' ...

                        Можно заменить методы во всех потоках и
                        изучить полученный эффект.
                        */
                        System.out.print(strForFirstThread);
                        nextSubString = strForSecondThread;
                        monitor.notifyAll();
                    }
                }
            }
        }).start();
        /*
        Работа в текущем потоке подобна работе
        описанной выше, только меняются вводные
        переменные.
        */
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (monitor) {
                    for (int i = 0; i < 5; i++) {
                        try{
                            while (!nextSubString.equals(strForSecondThread)){
                                monitor.wait();
                            }
                        } catch (InterruptedException ex){
                            ex.printStackTrace();
                        }
                        System.out.print(strForSecondThread);
                        nextSubString = strForThirdThread;
                        monitor.notifyAll();
                    }
                }
            }
        }).start();
        /*
        Работа в текущем потоке подобна работе
        описанной выше, только меняются вводные
        переменные.
        */
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (monitor) {
                    for (int i = 0; i < 5; i++) {
                        try{
                            while (!nextSubString.equals(strForThirdThread)){
                                monitor.wait();
                            }
                        } catch (InterruptedException ex){
                            ex.printStackTrace();
                        }
                        System.out.print(strForThirdThread);
                        nextSubString = strForFourthThread;
                        monitor.notifyAll();
                    }
                }
            }
        }).start();
        /*
        Работа в текущем потоке подобна работе
        описанной выше, только меняются вводные
        переменные.
        */
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (monitor) {
                    for (int i = 0; i < 5; i++) {
                        try{
                            while (!nextSubString.equals(strForFourthThread)){
                                monitor.wait();
                            }
                        } catch (InterruptedException ex){
                            ex.printStackTrace();
                        }
                        System.out.print(strForFourthThread);
                        System.out.println();
                        nextSubString = strForFirstThread;
                        monitor.notifyAll();

                    }
                }
            }
        }).start();
    }
}

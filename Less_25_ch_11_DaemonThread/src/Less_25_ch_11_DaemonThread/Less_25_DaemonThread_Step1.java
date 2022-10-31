package Less_25_ch_11_DaemonThread;
/*
Из вывода на экран видно, что после того как пользовательский поток завершил свою
работу и вывел на экран алфавит от 'A' до 'J'. Основная программа грубо завершила
свою работу не дожидаясь завершения всех операций демон потока. (Из всего цикла
от 1 до 1000 на экран попали значения i не больше 30).

Естественно наш демон поток, не проводит сервисных операций, но в данном примере
хотелось показать, как несправедливо до сих пор относятся к демонам, пусть они
даже только потоки...
*/
import Less_25_ch_11_DaemonThread.MyThreadsClasses.DaemonThread;
import Less_25_ch_11_DaemonThread.MyThreadsClasses.UserThread;

public class Less_25_DaemonThread_Step1 {
    public static void main(String[] args) {
        System.out.println("MAIN Thread starts");
        UserThread user_trd = new UserThread();
        user_trd.setName("USER THREAD");
        DaemonThread daemon_trd = new DaemonThread();
        daemon_trd.setName("DAEMON THREAD");
        /*
        Обязательно до метода *.start() объявляем нашего демона демоном, если это сделать
        после вылетит: Exception in thread "main" java.lang.IllegalThreadStateException
        */
        daemon_trd.setDaemon(true);

        user_trd.start();
        daemon_trd.start();

        try {
            user_trd.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("MAIN Thread finish");
    }
}

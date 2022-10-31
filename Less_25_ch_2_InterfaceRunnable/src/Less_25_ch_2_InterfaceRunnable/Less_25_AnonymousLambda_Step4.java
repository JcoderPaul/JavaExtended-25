package Less_25_ch_2_InterfaceRunnable;

/**
 * Многопоточность в JAVA отнюдь не ограничена классом Thread.
 *
 * В контексте определённой задачи может быть выгоднее наследовать какой-то другой класс,
 * но множественное наследование в JAVA не поддерживается, выход: implements Runnable
 * Интерфейс Runnable имеет посредственное отношение к потокам - его следует расценивать
 * как передаваемую функцию, которая может быть выполнена где-то в другом месте
 * (поток, очередь, класс, метод и т.п.)
 *
 * Thread - это абстракция над физическим потоком.
 * Runnable - это абстракция над выполняемой задачей.
 *
 * Плюс использования Runnable состоит в том, что это позволяет логически отделить
 * выполнение задачи от логики управления потоками.
 **/
public class Less_25_AnonymousLambda_Step4 {

    public static void main(String[] args) {

        System.out.println("!!! Стартует метод MAIN !!!");
        /*
        Реализация потока через анонимный класс, подписанный на интерфейс Runnable
        */
        new Thread(new Runnable() {

            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Hello from thread : " + Thread.currentThread().getName() + " значение i -> " + i);
                }
            }
        }).start(); // Не забываем запустить наш поток !!!

        /*
        Там где маячит анонимный класс, существует потенциальная возможность переписать
        код в лямбду, хотя в данном примере, это не сильно сократило количество строк.
        */
        new Thread(() -> {
            for (int i = 1000; i > 0; i--) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Hello from thread : " + Thread.currentThread().getName() +
                                   " значение i -> " + i);
            }
        }).start(); // Не забываем запустить наш поток !!!

        System.out.println("!!! Метод MAIN закончил работу !!!");
        
        }
}


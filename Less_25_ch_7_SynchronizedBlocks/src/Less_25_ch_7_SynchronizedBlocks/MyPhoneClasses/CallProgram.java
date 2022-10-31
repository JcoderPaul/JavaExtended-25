package Less_25_ch_7_SynchronizedBlocks.MyPhoneClasses;
/*
Класс отвечающий за методы звонков мз различных источников.
*/
public class CallProgram {
    /*
    В данном случае модификаторы 'static final' у класса 'CommunicationСhannelBlocker'
    обязательны для правильной синхронизации всех потоков при работе с методами класса
    'CallProgram'.
    */
    static final CommunicationСhannelBlocker blocker = new CommunicationСhannelBlocker();
    /*
    Метод имитирует звонок по телефонной линии, внутри находится synchronize блок, завязанный
    на объект 'blocker'. Все остальные методы данного 'CallProgram' класса так же содержат
    synchronize блоки и завязаны на этот же объект 'blocker'. Это позволяет при
    многопоточном режиме работы запустить и дождаться окончания работы каждого конкретного
    метода.

    В основном коде MAIN, происходит имитация одновременного поступления сигналов на телефон
    по всем трем каналам связи. Без синхронизации все три метода запустятся один за другим и
    так же завершаться один за другим после паузы, что имитирует ситуацию, когда на одном
    устройстве мы вдруг смогли одновременно ответить по всем трем входящим звонкам.

    Для того что бы избежать подобной ситуации введен класс 'CommunicationСhannelBlocker'.
    На объекте этого класса будет происходить синхронизация всех трех методов, что
    имитирует ситуацию, когда входящий вызов по одному из каналов блокирует возможность
    общения по другим каналам, до окончания текущего разговора (остальные в режиме ожидания).

    Для синхронизации мы придумали класс, хотя обычно применяют следующий вариант 'замка':
    static final Object lock = new Object();
    */
    public void phoneCall() throws InterruptedException {
        synchronized (blocker){
            System.out.println("Mobile call start.");
            Thread.sleep(300);
            System.out.println("Mobile call ends.");
        }
    }

    public void skypeCall() throws InterruptedException {
        synchronized (blocker) {
            System.out.println("Skype call start.");
            Thread.sleep(500);
            System.out.println("Skype call ends.");
        }
    }

    public void whatsAppCall() throws InterruptedException {
        synchronized (blocker){
            System.out.println("WhatsApp call start.");
            Thread.sleep(700);
            System.out.println("WhatsApp call ends.");
        }
    }

}

package Less_25_ch_7_SynchronizedBlocks;

/*
Программа имитирует звонки по трем линиям связи телефон, скайп, ватсап.
Происходит имитация одновременного поступления сигналов на телефон
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

import Less_25_ch_7_SynchronizedBlocks.MyPhoneClasses.*;

public class Less_25_SynchronizedBlock_Step5 {

    public static void main(String[] args) {
        System.out.println("*** Turned on the phone ***");

        Thread phone_tr = new Thread(new PhoneRunner());
        Thread skype_tr = new Thread(new SkypeRunner());
        Thread whats_app_tr = new Thread(new WhatsAppRunner());

        phone_tr.start();
        skype_tr.start();
        whats_app_tr.start();

        try {
            phone_tr.join();
            skype_tr.join();
            whats_app_tr.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("*** Turned off the phone ***");
    }
}

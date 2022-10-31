package Less_25_ch_9_Deadlock;

/*
* Из документации ORACLE о состоянии Livelock:
* Процесс когда один поток действует в ответ на действие другого потока.
* Если действие некоего потока также является ответом на действие другого
* потока, может возникнуть активная блокировка.

* Как и в случае взаимоблокировкой (DeadLock), заблокированные в реальном
* времени потоки не могут двигаться дальше. Однако потоки не заблокированы
* — они просто слишком заняты реагированием друг на друга, чтобы возобновить
* полезную работу.
*
* Текущий пример:
* Приведена ситуацию, когда два потока хотят получить доступ к общему ресурсу
* через объект Worker. Но когда один из них видит, что Worker вызывается в другом
* потоке, он пытается передать ресурс этому worker и ждет. При этом в другом потоке
* похожая ситуация. Если изначально мы сделаем обоих worker активными, они попадут
* в livelock.
*/

import Less_25_ch_9_Deadlock.MyLiveLockClasses.*;

public class Less_25_Livelock_Step3 {

    public static void main (String[] args) {
        final Worker worker1 = new Worker("Worker 1 ", true);
        final Worker worker2 = new Worker("Worker 2", true);

        final CommonResource s = new CommonResource(worker1);

        new Thread(() -> {
            worker1.work(s, worker2);
        }).start();

        new Thread(() -> {
            worker2.work(s, worker1);
        }).start();
    }
}

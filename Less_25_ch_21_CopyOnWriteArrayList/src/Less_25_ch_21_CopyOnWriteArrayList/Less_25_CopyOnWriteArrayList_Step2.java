package Less_25_ch_21_CopyOnWriteArrayList;
/*
Удаление и итерация над CopyOnWriteArrayList :
Итератор CopyOnWriteArrayList не поддерживает операцию удаления ().
Любая попытка сделать это приведет к исключению UnsupportedOperationException:
*/
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

public class Less_25_CopyOnWriteArrayList_Step2 {
    public static void main(String[] args) {
        CopyOnWriteArrayList<String> list
                = new CopyOnWriteArrayList<>(new String[]{"A", "B", "D", "E", "F"});

        Iterator<String> itr = list.iterator();
        while (itr.hasNext()) {
            itr.remove();
        }
    }
}

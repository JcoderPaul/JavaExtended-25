package Less_25_ch_17_Exchanger.MyClasses;
/*
Как это ни странно, но пример Less_25_Exchanger_Step2 более простой
для понимания работы синхронизатора Exchanger. (Боюсь, в дальнейшем,
понимание многих вещей будет упираться в правильно названные переменные
и методы.)
*/
import java.util.List;
import java.util.concurrent.Exchanger;

public class Player extends Thread{
    /*
    У нас есть игрок, у него есть имя, и в данной реализации симулятора в его
    списке мы храним некую серию выбросов рук на "Камень-Ножици-Бумага". Так же
    при создании объекта игрока (потока игрока), через конструктор мы передадим
    объект синхронизатор.

    Пока, все более-менее, по классике...
    */
    private String player_name;
    private List<HandFigure> listOfPlayerAction;
    private Exchanger<HandFigure> play_exchanger;

    public Player(String player_name,
                  List<HandFigure> playerAction,
                  Exchanger<HandFigure> play_exchanger) {
        this.player_name = player_name;
        this.listOfPlayerAction = playerAction;
        this.play_exchanger = play_exchanger;
    }
    /*
    Метод, который определяет кто из игроков выиграл. Если игрок выиграл мы увидим его имя,
    если нет, ничего не произойдет, если будет ничья, на экране появится надпись, причем
    два раза - сначала напечатанная из одного потока, затем из другого.

    Данный метод активируется на текущем объекте (в текущем потоке), по этому если условия
    соблюдены мы просто выводим имя победителя (и ничего у проигравшего).
    */
    private void whoWins(HandFigure firstPlayerFigure, HandFigure secondPlayerFigure){
        if((firstPlayerFigure == HandFigure.ROCK && secondPlayerFigure == HandFigure.SCISSORS) ||
           (firstPlayerFigure == HandFigure.SCISSORS && secondPlayerFigure == HandFigure.PAPER) ||
           (firstPlayerFigure == HandFigure.PAPER && secondPlayerFigure == HandFigure.ROCK)) {
            System.out.println(player_name + " ВЫИГРАЛ!");
        } else if ((firstPlayerFigure == HandFigure.ROCK && secondPlayerFigure == HandFigure.ROCK) ||
                   (firstPlayerFigure == HandFigure.SCISSORS && secondPlayerFigure == HandFigure.SCISSORS) ||
                   (firstPlayerFigure == HandFigure.PAPER && secondPlayerFigure == HandFigure.PAPER)) {
            System.out.println("НИЧЬЯ!");
        } else {
        }
    }

    @Override
    public void run() {
        /*
        В обмене данными между потоками, как я понимаю есть много нюансов, но в данном
        примере все упрощено до предела. И все же чуть мутновато, если без подготовки
        подходить к вопросу.

        Помним, что в основном коде (методе MAIN) мы создаем два объекта (два потока),
        отличающиеся только переменными. Реализация метода *.run() у них будет одинаковой,
        а значит действовать они будут по шаблону !!! И В ЗЕРКАЛЬНОЙ ПРОТИВОПОЛОЖНОСТИ !!!
        Чего хочет (делает) один поток (объект - игрок), тоже делает его условный оппонент,
        !!! с точки зрения кода !!! ПРОСТО С НЕБОЛЬШОЙ ЗАДЕРЖКОЙ.
        */

        //В данную переменную мы поместим (КАМЕНЬ, НОЖНИЦЫ, БУМАГУ) оппонента текущего потока
        HandFigure opponent_hand_figure;
        // И тот и другой поток запустят этот цикл, просто кто-то из них раньше и будет ждать 'соперника'
        for(HandFigure current_player_hand_figure: listOfPlayerAction){
            try {
                /*
                !!! И вот он тонкий момент !!! Вызов метода *.exchange('запрашиваемые данные') на
                текущем потоке, показывает, какие данные мы хотим получить для текущего потока
                из соседнего потока. И пока вопрошающий их не получит, он будет ждать ответа.
                Причем в данном примере ответа зеркального и такого же зеркального запроса к себе.

                Поэтому в переменную 'opponent_hand_figure' будет помещен ответ (содержимое ячейки
                списка) соседнего потока, полученные через метод *.exchange(current_player_hand_figure)
                из списка 'private List<HandFigure> listOfPlayerAction' того же потока.

                А текущий поток, в свою очередь, отдаст такие же данные, в поток оппонента, из своего
                списка 'listOfPlayerAction', запросы то синтаксически одинаковы.
                */
				
                opponent_hand_figure = play_exchanger.exchange(current_player_hand_figure);
                
				/*
                Поскольку идет взаимный обмен данными то схема этого процесса будет следующей:

                 Данные полученные           Синхронизатор           Данные отданные
                из соседнего потока              обмена             в соседний поток
                         V                         V                        V
                opponent_hand_figure = play_exchanger.exchange(current_player_hand_figure);

                Имея данные из n - ого элемента списка 'List<HandFigure> listOfPlayerAction'
                текущего потока (объекта), и получив соответствующие сведения об n - ом же
                элементе списка оппонента, мы можем их сравнить и узнать "кто победил"

                Точно такая же работа будет проведена в другом потоке. Метод *.run() все таки
                работает в обоих потоках. По этому то в случае ничьей, сообщение об этом
                всплывает дважды.
                */
                whoWins(current_player_hand_figure, opponent_hand_figure);
                sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
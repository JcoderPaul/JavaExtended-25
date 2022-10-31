package Less_25_ch_17_Exchanger;

import Less_25_ch_17_Exchanger.MyClasses.BlackBox;
import Less_25_ch_17_Exchanger.MyClasses.HandFigure;
import Less_25_ch_17_Exchanger.MyClasses.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;

/*
Имитация игры "Камень-Ножницы-Бумага"
Игра "Камень-Ножницы-Бумага-Ящерица-Спок" реализуется так же.
*/
public class Less_25_Exchanger_Step1 {
    public static void main(String[] args) {
        // Создаем синхронизатор класса Exchanger
        Exchanger<HandFigure> game_exchanger = new Exchanger<>();
        // Создаем игроков, их списки выкинутых рукой фигур (Камень, Ножницы, Бумага)
        String firstPlayer = "Джон Кеннеди";
        System.out.println("--------------- " + firstPlayer + " ---------------");
        List<HandFigure> firstPlayerList = new ArrayList<>();
        /*
        Заполняем списки 'фигурами пальцев' в случайном порядке.
        Класс BlackBox и его метод 'nextMoveOfPlayer', позволяют
        выкинуть Камень, Ножницы и бумагу
        */
        firstPlayerList.add(new BlackBox().nextMoveOfPlayer());
        firstPlayerList.add(new BlackBox().nextMoveOfPlayer());
        firstPlayerList.add(new BlackBox().nextMoveOfPlayer());
        // Просто для наглядности, выведем на экран.
        for (HandFigure first_list: firstPlayerList) {
            System.out.print(first_list + " ");
        }
        String secondPlayer = "Никита Хрущев";
        System.out.println("\n--------------- " + secondPlayer + " ---------------");
        List<HandFigure> secondPlayerList = new ArrayList<>();
        secondPlayerList.add(new BlackBox().nextMoveOfPlayer());
        secondPlayerList.add(new BlackBox().nextMoveOfPlayer());
        secondPlayerList.add(new BlackBox().nextMoveOfPlayer());
        for (HandFigure second_list: secondPlayerList) {
            System.out.print(second_list + " ");
        }
        System.out.println("\n--------------- Game result ---------------");
        /*
        Создаем игроков, передаем им все аргументы и стартуем потоки.

        Далее, в процессе взаимодействия потоков и синхронизатора Exchanger,
        будут перебраны и сопоставлены соответствующие элементы списков
        выброшенных игроками фигур (ROCK, PAPER, SCISSORS) и найден
        победитель, каждого из раундов игры.

        Т.к. элементов в списках три, то и раундов игры в одной серии
        (при одном запуске программы) будет тоже три.
        */
        new Player(firstPlayer, firstPlayerList, game_exchanger).start();
        new Player(secondPlayer, secondPlayerList, game_exchanger).start();

    }
}

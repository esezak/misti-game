import java.io.FileWriter;
import java.nio.file.Paths;
import java.util.Formatter;
import java.util.Scanner;

public class Main {

    public static void main(String[] args){
      Game game = new Game(args);
        //number of players, point file name, name and expertise level of each player, and verboseness
        //level
        for(int i=0; i<48/game.playerCount();i++){//changes based on how many players are in the game

                game.start();

            }
        game.boardCleanup();
        game.debug();
        BotExpert.printPlayedCards();
        Player player = game.getWinner();
        System.out.println("Winner is: "+ player.getName()+" with "+player.getPoint()+" points!");

        //-------------- high score list--------------
        LeaderBoard leaderBoard = new LeaderBoard(player);



    }
}

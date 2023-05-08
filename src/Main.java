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

        Scanner reader = null;
        Formatter formatter = null;
        FileWriter writer = null;
        String[] names = new String[10];
        String[] scores = new String[10];


        ////////////-----------------Read
        try {
            reader = new Scanner(Paths.get("scores.txt"));
            int counter = 0;
            while (reader.hasNextLine()) {
                String[] info = reader.nextLine().split(",");
                names[counter] = info[0].trim();
                scores[counter] = info[1].trim();
                counter++;
            }
        } catch (Exception e) {
            System.out.println("It seems this is the first time playing.");
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
        String point = String.valueOf(player.getPoint());



        for (int i = 0; i < names.length; i++) {
            if (scores[i] != null) {
                if (Integer.parseInt(scores[i]) < player.getPoint()) {
                    for (int j = names.length - 1; j > i; j--) {//make place for new value
                        names[j] = names[j - 1];
                        scores[j] = scores[j - 1];
                    }
                    names[i] = player.getName();// add values
                    scores[i] = point;
                    break;
                }
            } else if (scores[i] == null) {// if empty add the value
                scores[i] = point;
                names[i] = player.getName();
                break;
            }
        }



        try {
            writer = new FileWriter("scores.txt", false);
            for(int i=0; i< 10;i++){
                formatter = new Formatter(writer);
                if(scores[i]!=null) {
                    formatter.format("%s,%s\n", names[i], scores[i]);
                }
            }
        } catch (Exception e) {
            System.out.println("Something went wrong.");
        }finally{
            if(formatter!=null)
                formatter.close();
        }
        System.out.println();
        System.out.println("----------Hi Scores----------");
        for (int i = 0; i < names.length; i++) {
            if (names[i] == null) {
                System.out.println((i+1)+"# Name:        Score:      ");
            } else {
                System.out.println((i+1)+"# Name: " + names[i] + "   Score: " + scores[i]);
            }
        }



    }
}

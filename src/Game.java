import java.lang.reflect.Array;
import java.util.*;


public class Game {
    private Deck deck = new Deck();
    private Board board = new Board(deck);
    

    public Game(String[] args) {
        //Parameters include "2 point.txt Can Human Ege Novice verbose"
        System.out.println("There are "+args[0]+" players playing");
        Random r = new Random();
        deck.shuffle();
        deck.cut(r.nextInt(52));
        ArrayList<Player> players = new ArrayList<Player>();
        for(int i = 0; i<args.length;i++){
            String arg = args[i];
            switch(arg){
                case "Human":
                    players.add(new Human(args[i-1]));
                    break;
                case "Novice":
                    players.add(new BotNovice(args[i-1]));
                    break;
                case "Normal":
                    players.add(new BotNormal(args[i-1]));
                    break;
                case "Expert":
                    //players.add((new BotExpert(args[i-1]));
                    break;
                default:
                    break;

            }
        }
        for(int i = 0; i<4;i++){//deal cards
            for(Player p : players){
                p.addCard(deck);
            }
        }
    }
    public void round(){
        //round
    }
}
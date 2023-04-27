<<<<<<< Updated upstream
=======
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

>>>>>>> Stashed changes
public class Test {
        ArrayList<Player> players = new ArrayList<Player>();
    public static void main(String[] args){//class for testing methods
        System.out.println("Welcome to the mişti game!");
<<<<<<< Updated upstream
=======
        Scanner sc = new Scanner(System.in);
        System.out.println("There are how many players in this game ?");
        int number = sc.nextInt();
        Random r = new Random();
>>>>>>> Stashed changes
        //test codes
        Deck deck = new Deck();
        deck.see();
        System.out.println();
        deck.cut(10);
        deck.see();System.out.println();
        Board board = new Board(deck);
        deck.see();System.out.println();

        for(int i=0; i<number; i++) {

        }
        



        

        // board.info();
        // board.flushBoard();
        // board.info();
        board.getTopCard();


    }
}

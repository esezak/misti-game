import java.util.*;

public class Game {
    Deck deck = new Deck();
    Board board = new Board();
    

    public Game() {
        Random r = new Random();
        deck.see();
        System.out.println();
        deck.shuffle();
        System.out.println("Shuffled deck: ");
        deck.see();
        System.out.println();
        deck.cut(r.nextInt(52));
        System.out.println("Shuffled and cutted deck: ");
        deck.see();

        // to see the top card (string)
        System.out.println("Top card: ");
        System.out.println(deck.seeTopCard());

        // to see the card we want (string)
        System.out.println("Fifth card: ");
        System.out.println(deck.getCard(4));

        // adding cards to board array
        board.addCard(deck);
        System.out.println("Top card on board: ");
        System.out.println(board.getTopCard());
    }
}
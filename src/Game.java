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
        
        // to see the top card (object)
        Card topCard = deck.seeTopcardObject();
        System.out.println("Top card");

        if(topCard != null) {
            System.out.println(topCard.getCard());
        }
        else {
            System.out.println("Error ! ");
        }

        // to see the card we want (object) 
        Card card = deck.getCardObject(4);
        System.out.println("Fifth card: ");
        System.out.println(card.getCard());
    }
}
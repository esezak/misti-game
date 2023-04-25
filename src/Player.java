/*
Play Card
Get Card
*/


import java.util.ArrayList;

public class Player {
    ArrayList<Card> hand = new ArrayList<Card>();
    ArrayList<Card> cardsTaken = new ArrayList<Card>();

    public ArrayList<Card> getHand() {
        return hand;
    }

    public ArrayList<Card> getCardsTaken() {
        return cardsTaken;
    }
    public void addCard(Card card){
        hand.add(card);
    }
    public void takeCard(Card card){
        cardsTaken.add(card);
    }
}

/*
Play Card
Get Card
*/


import java.util.ArrayList;

public class Player {
    ArrayList<Card> hand = new ArrayList<Card>();
    ArrayList<Card> cardsTaken = new ArrayList<Card>();
    public void see(){
        if(hand.size()!=0){
            for(int i = 0; i<hand.size();i++){
                System.out.print("("+(i+1)+") "+hand.get(i).getCard());
            }
        }
    }
    public Card getCard(int index){
        return hand.get(index);
    }
    public void removeCard(int index){
        hand.remove(index);
        hand.trimToSize();
    }
    public int selectedCardNum(int index){//for comparison with the card on deck
        return hand.get(index).getNumber();
    }
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

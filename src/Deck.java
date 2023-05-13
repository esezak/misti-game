import java.util.ArrayList;
import java.util.Collections;
public class Deck {
    private ArrayList<Card> deck = new ArrayList<Card>(52);//a deck of card
    private boolean isVerbose;
    public void see(){
        System.out.print("\nDeck :  ");
        for(Card c : deck){
            System.out.print(c.getCard()+"("+c.getPoint()+"), ");//see what is inside the deck

        }


    }//mainly for debug

    public Deck(boolean isVerbose){//creates a deck
        create();
        this.isVerbose = isVerbose;
    }

    public void create(){
        String[] numbers = {"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
        String[] symbols = {"♠","♥","♦","♣"};
        for(String symbol : symbols){
            for(String number : numbers){
                deck.add(new Card(number,symbol,1));
            }
        }
    }//create an unshuffled deck

    public void cut(int cutLocation){
        ArrayList<Card> tempDeck = new ArrayList<Card>(52);

        for(int i=cutLocation; i< deck.size(); i++ ){
            tempDeck.add(deck.get(i));//take 52-cutLocation cards move them to front
        }
        for(int i = 0; i < cutLocation; i++){
            tempDeck.add(deck.get(i));//insert the remaining cards to where we left off
        }
        deck = (ArrayList<Card>) tempDeck.clone();
    }
    public void shuffle(){
        Collections.shuffle(deck);
    }


    public Card seeTopCard(){
        if(deck.size() > 0){
            return deck.get(deck.size()-1);
        }
        else{
            return new Card("0","0",0);//will return this if there are no cards remain
        }
    }
    public int getSize(){
        return deck.size();
    }
    public ArrayList<Card> getDeck(){
        return deck;
    }
    public Card transferCard(){
        if(deck.size() > 0){
        Card temp = seeTopCard();
        deck.remove(deck.size()-1);
        return temp;
        } else{
            return new Card("0","0",0);//empty card meaning the cards have finished
            //should not suppose to go here but just in case
        }
    }
    public void showChangedCards(){
        System.out.print("\nCards with changed points: ");
        for(Card c : deck){
            if(c.getPoint() != 1){
                System.out.print(c.getCard()+"("+c.getPoint()+") ");
            }
        }
    }


}

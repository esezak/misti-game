import java.util.*;
public class Deck {
    private ArrayList<Card> deck = new ArrayList<Card>(52);//a deck of card
    public void see(){
        for(Card c: deck){
            System.out.print(c.getCard()+", ");//see what is inside the deck
        }
    }//see what is inside the deck (for debug)

    public Deck(){//create an shuffled deck
        create();
        shuffle();
    }

    public void create(){
        int[] numbers = {1,2,3,4,5,6,7,8,9,10,11,12,13};
        String[] symbols = {"♠","♥","♦","♣"};
        for(String symbol : symbols){
            for(int number : numbers){
                deck.add(new Card(number,symbol,1));
            }
        }
    }//create an unshuffled deck

    public void cut(int cutLocation){
        ArrayList<Card> tempdeck = new ArrayList<Card>(52);
        int j=0;    // cutlocation variable to store where we last are
        for(int i=cutLocation; i< deck.size();i++ ){
            tempdeck.add(j,deck.get(i));//take 52-cutlocation cards move them to front
            j++;
        }
        for(int i=0;i<cutLocation;i++){
            tempdeck.add(j,deck.get(i));//insert the remaining cards to where we left off
            j++;
        }
        deck = (ArrayList<Card>) tempdeck.clone();
    }
    public void shuffle(){
        Collections.shuffle(deck);
    }



    public Card getCard(int index){return deck.get(index);}
    public Card seeTopcard(){
        return deck.get(deck.size()-1);
    }
    public ArrayList<Card> getDeck(){
        return deck;
    }
    public Card transferCard(){
        Card temp = seeTopcard();
        deck.remove(deck.size()-1);
        return temp;
    }

}

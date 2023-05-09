import java.util.*;
public class Deck {
    private ArrayList<Card> deck = new ArrayList<Card>(52);//a deck of card
    private boolean isVerbose;
    public void see(){
        System.out.print("\nDeck :  ");
        for(Card c: deck){
            System.out.print(c.getCard()+", ");//see what is inside the deck
        }System.out.print("\nPoints: ");
        if(isVerbose){//wanted to align points with cards but gave up
            for(Card c: deck){
                if(c.getNumber().equals("9")||c.getNumber().equals("10")){
                    System.out.print(c.getPoint()+"   ");
                }
                else {
                    System.out.print(c.getPoint() + "   ");
                }
            }
        }

    }//mainly for debug

    public Deck(boolean isVerbose){//creates a deck
        create();
        this.isVerbose=isVerbose;
    }

    public void create(){
        String[] numbers = {"1","2","3","4","5","6","7","8","9","10","J","Q","K"};
        String[] symbols = {"♠","♥","♦","♣"};
        for(String symbol : symbols){
            for(String number : numbers){
                deck.add(new Card(number,symbol,1));
            }
        }
    }//create an unshuffled deck

    public void cut(int cutLocation){
        ArrayList<Card> tempDeck = new ArrayList<Card>(52);
        int j=0;    // cutLocation variable to store where we last are
        for(int i=cutLocation; i< deck.size();i++ ){
            tempDeck.add(j,deck.get(i));//take 52-cutLocation cards move them to front
            j++;
        }
        for(int i=0;i<cutLocation;i++){
            tempDeck.add(j,deck.get(i));//insert the remaining cards to where we left off
            j++;
        }
        deck = (ArrayList<Card>) tempDeck.clone();
    }
    public void shuffle(){
        Collections.shuffle(deck);
    }

    //public Card getCard(int index){return deck.get(index);}   //wasn't used
    public Card seeTopCard(){
        if(deck.size()>0){
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
        if(deck.size()>0){
        Card temp = seeTopCard();
        deck.remove(deck.size()-1);
        return temp;
        } else{
            return new Card("0","0",0);//empty card meaning the cards have finished
            //should not suppose to go here but just in case
        }
    }

}

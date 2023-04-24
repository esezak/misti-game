import java.util.*;
public class Deck {
    private ArrayList<Card> deck = new ArrayList<Card>(52);//a deck of cards
    private int topcard;
    public void see(){
        for(Card c: deck){
            System.out.print(c.getCard()+", ");//see what is inside the deck
        }
    }//see what is inside the deck (for debug)

    public Deck(){//create an unshuffled deck
        topcard =0;
        for(int i = 0; i<52;i++){
            Card temp = new Card(); // temp object for assigning to Card[]
            if(i<13){//first 13
                temp.setNumber(i+1);
                temp.setSymbol("♠");
                temp.setPoint(1);
                deck.add(i,temp);
            }
            else if(i<26){//second 13
                temp.setNumber((i%13)+1);
                temp.setSymbol("♥");
                temp.setPoint(1);
                deck.add(i,temp);
            }
            else if(i<39){//third 13
                temp.setNumber((i%13)+1);
                temp.setSymbol("♦");
                temp.setPoint(1);
                deck.add(i,temp);
            }
            else{//forth 13
                temp.setNumber((i%13)+1);
                temp.setSymbol("♣");
                temp.setPoint(1);
                deck.add(i,temp);
            }
        }
        Card temp2 = new Card();
        temp2.setNumber(10);
        temp2.setSymbol("♦");
        temp2.setPoint(3);
        deck.add(35,temp2);
        Card temp3 = new Card();
        temp3.setNumber(2);
        temp3.setSymbol("♣");
        temp3.setPoint(2);
        deck.add(40,temp3);


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
    public void addToTopCard(int add){topcard = topcard + add;}
    public int getTopcard(){return topcard;}
    public Card getCard(int index){return deck.get(index);}
    public String seeTopcard(){return deck.get(topcard).getCard();}
}

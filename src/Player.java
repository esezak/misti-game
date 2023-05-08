/*
Play Card
Get Card
*/


import java.util.ArrayList;

public class Player implements Playable{
    private ArrayList<Card> hand = new ArrayList<Card>();
    private String name;
    private int point;
    private boolean lastTake=false;//indicates who last took cards from the board
    public void see(){//mainly for human player and debug
        System.out.print("Hand: ");
        if(hand.size()!=0){
            for(int i = 0; i<hand.size();i++){
                System.out.print("("+(i+1)+")"+hand.get(i).getCard()+" ");
            }
            System.out.println();
        }
    }
    public void addCard(Deck deck){
        Card card = deck.transferCard();
        hand.add(card);
    }
    public void addPoint(int point){
        this.point+=point;
    }
    public int getPoint(){
        return point;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Card getCard(int index){
        return hand.get(index);
    }
    public void removeCard(int index){
        hand.remove(index);
        hand.trimToSize();
    }
    public ArrayList<Card> getHand() {
        return hand;
    }

    public int simulatePoint(Board board, Card card){//simulates the amount of point gained/lost from playing the card
        if(board.getSize()==1){
            return (board.getBoardPoint()+ card.getPoint())*5;
        }
        return board.getBoardPoint()+card.getPoint();
    }
    @Override
    public int play(Board board) {
        return 0;
    }
    public void resetLastTake(){//reset this property
        lastTake = false;
    }
    public void lastTookCards(){//find if it took cards last
        lastTake = true;
    }
    public boolean isLastTake() {
        return lastTake;
    }
    public static int parseNumber(String number){
        if(number.equals("J")){
            return 11;
        }else if(number.equals("Q")){
            return 12;
        }else if(number.equals("K")){
            return 13;
        }else{
            return Integer.parseInt(number);
        }
    }
}

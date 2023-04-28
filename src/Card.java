//Card object
public class Card {
    public Card(String number, String symbol, int point){
        this.number=number;
        this.symbol=symbol;
        this.point=point;
    }
    public Card(){
        this.point=1;
    }
    private String number;
    private String symbol;
    private int point;
    public void setPoint(int a){point=a;}
    public int getPoint(){return point;}
    public void setNumber(String a){number=a;}
    public void setSymbol(String a){symbol=a;}
    public String getNumber(){return number;}
    public String getSymbol(){return symbol;}
    public String getCard(){
        return getNumber()+getSymbol();
    }
    public boolean equals(Card card){
        if(number == card.getNumber()&& symbol.equals(card.getSymbol())&&point== card.getPoint()){
            return true;
        }
        return false;
    }

}

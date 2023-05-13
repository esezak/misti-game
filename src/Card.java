//Card object
public class Card {
    public Card(String number, String symbol, int point){
        this.number = number;
        this.symbol = symbol;
        this.point = point;
    }
    private String number;
    private String symbol;
    private int point;
    public void setPoint(int a){point = a;}
    public int getPoint(){return point;}
    public String getNumber(){return number;}
    public String getSymbol(){return symbol;}
    public String getCard(){
        return getNumber() + getSymbol();
    }

}

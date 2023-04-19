//Card object
public class Card {
    private int number;
    private String symbol;
    private int point;
    public void setPoint(int a){point=a;}
    public int getPoint(){return point;}
    public void setNumber(int a){number=a;}
    public void setSymbol(String a){symbol=a;}
    public int getNumber(){return number;}
    public String getSymbol(){return symbol;}
    public String getCard(){
        if(getNumber()==1){
            return "A"+getSymbol();
        }
        else if(getNumber() == 11){
            return "J"+getSymbol();
        }
        else if(getNumber() == 12){
            return "Q"+getSymbol();
        }
        else if(getNumber() == 13){
            return "K"+getSymbol();
        }
        else{
            return getNumber()+getSymbol();
        }
    }

}

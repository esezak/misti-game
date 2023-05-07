public class BotExpert extends Player implements Playable{
    public BotExpert(String name){
        setName(name+" (Expert)");
    }
    private static int[] playedCardNums = new int[14];
    @Override
    public int play(Board board) {
        //AI
        //check if we can take the board
        //Check if board has positive points after we played the card (if we can take)
        boolean hasJack=false;
        boolean hasSelected =false;
        boolean[] okToPlay = {true,true,true,true};
        int jackIndex=0;
        int selectedIndex=0;
        int timesNumberPlayed;
        if(getHand().size()==1){//shortcut when we have 1 card left at hand
            return selectedIndex;
        }
        {
            int counter=0;//local variable
            for (Card c : getHand()) {//if it can take the board it will take it (if board has positive points after played card)
                if (board.getTopCard().getNumber().equals(c.getNumber()) && !c.getNumber().equals("J")) {
                    /*
                    We can take the board but if we play we get negative points,
                    we should avoid playing this card
                    */
                    if(simulatePoint(board,c)<0){
                        okToPlay[counter]=false;
                    }else{
                        selectedIndex=counter;
                        hasSelected=true;
                    }
                } else if (c.getNumber().equals("J")&& simulatePoint(board, c) > 0) {
                    if(simulatePoint(board,c)<0){
                        okToPlay[counter]=false;
                    }else{
                        hasJack=true;
                        jackIndex=counter;//backup plan
                    }
                }
                counter++;
            }
            if(hasSelected){
                System.out.println("Has board taken?");//for debug
                return selectedIndex;
            }else if(hasJack){
                System.out.println("Has board taken?");//for debug
                return jackIndex;
            }




        }





        return selectedIndex;
    }
    public int simulatePoint(Board board, Card card){
        if(board.getSize()==1){
            return (board.getBoardPoint()+ card.getPoint())*5;
        }
        return board.getBoardPoint()+card.getPoint();
    }
    public void cardTracker(int playedCardNum){
        playedCardNums[playedCardNum]++;
    }
}

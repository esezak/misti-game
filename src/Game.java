import java.util.*;


public class Game {
    private Deck deck;
    private Board board;
    private ArrayList<Player> players = new ArrayList<Player>();
    private Card bufferCard; // a card for temprorarily store a card
    private int roundctr; // for keeping track of when to deal cards
    private boolean isVerbose=false; // check if verbose mode is enabled

    public Game(String[] args) {
        //Parameters include "2 point.txt Can Human Ege Novice verbose"
        setVerbose(args);
        deck = new Deck(isVerbose);
        //points are here
        

        Random r = new Random();
        deck.see();
        deck.shuffle();
        //deck.see();
        deck.cut(r.nextInt(52));
        //deck.see();
        board = new Board(deck);
        roundctr=0;
        for(int i = 0; i<args.length;i++){//adds users based on arguments
            switch(args[i]){
                case "Human":
                    players.add(new Human(args[i-1]));
                    break;
                case "Novice":
                    players.add(new BotNovice(args[i-1]));
                    break;
                case "Normal":
                    players.add(new BotNormal(args[i-1]));// not finished
                    break;
                case "Expert":
                    players.add(new BotExpert(args[i-1])); // needs to be tested
                    break;
                default:
                    break;
            }
        }
        System.out.println("\nThere are "+ playerCount()+" players playing");
        dealCards();

    }
    public void setVerbose(String[] args){//sets verbose mode based on the last argument name
        if(args[args.length-1].equals("verbose")){
            isVerbose = true;
            System.out.println("Verbose mode is enabled");
        }
        else{
            isVerbose = false;
            System.out.println("Verbose mode is not enabled");
        }
    }
    public void debug(){// shows deck size, player hands, scores and the state of the board
        System.out.println("Deck size: "+getDeckSize());
        for(int i=0;i<players.size();i++){
            System.out.print("Player "+ (i+1) +" "); players.get(i).see();
            //System.out.print("\nPlayer "+(i+1)+ "Taken Cards: "); players.get(i).seeCardsTaken();//not yet implemented
            System.out.print("Player "+ (i+1) +" Point: "+players.get(i).getPoint()+"\n");
        }
        board.seeBoard();
    }
    public void start(){
        round();
        roundctr++;
        if(roundctr==4){
            dealCards();
            roundctr=0;
        }
    }
    public int playerCount(){
        return players.size();
    }
    public void dealCards(){
        if(isVerbose){
            System.out.println("Dealing cards...");
        }
        for(int i = 0; i<4;i++){//deal cards
            for(Player p : players){
                p.addCard(deck);
            }
        }
    }
    public void round(){//makes every player play once
        if(isVerbose){
            System.out.println("------New Round------");
        }
        for(Player player: players){
            int play = player.play(board);
            message(player,play);
            boardUpdate(player,play);
            if(isVerbose)
                board.seeBoard();
        }
    }
    public int getDeckSize(){
        return deck.getSize();
    }
    public void message(Player player, int play){
        System.out.println("Player "+player.getName()+" played: "+player.getCard(play).getCard());
    }
    public void boardUpdate(Player player, int play){//handles cards and updates the board
        bufferCard = player.getCard(play);
        player.removeCard(play);
        boardCheck(player);
    }
    public void boardCheck(Player player){//actually updates the board states
        String boardCardNum = board.getTopCard().getNumber();
        String playedCardNum = bufferCard.getNumber();
        int boardCardPoint = board.getTopCard().getPoint();
        int playedCardPoint = bufferCard.getPoint();
        if(board.getSize()==1 && boardCardNum.equals(playedCardNum)){//mişti check and take board
            int pointToAdd = (boardCardPoint+playedCardPoint)*5;
            player.addPoint(pointToAdd);
            resetPlayerTakes();//sets players take value to false
            player.lastTookCards();//sets the player who took the cards true
            board.addCard(bufferCard);
            board.flushBoard();//empties board and board points
            System.out.println("Miştiiii!");
        }else if(boardCardNum.equals(playedCardNum) | (playedCardNum.equals("J")&&board.getSize()!=0)){//take board normal
            board.addCard(bufferCard);
            player.addPoint(board.getBoardPoint());
            resetPlayerTakes();
            player.lastTookCards();
            board.flushBoard();
        }else{
            board.addCard(bufferCard);//adds played card
        }
    }
    public void resetPlayerTakes(){
        for(Player p: players){
            p.resetLastTake();
        }
    }
    public void boardCleanup(){//depending on who last took the cards gets the remaining cards on the board
        for(Player p: players){
            if(p.isLastTake()){
                p.addPoint(board.getBoardPoint());
                board.flushBoard();
            }
        }
    }
    public Player getWinner(){
        int highestScore=0;
        int playerIndex=0;
        for(int i = 0; i<playerCount();i++){
            if(highestScore<players.get(i).getPoint()){
                highestScore = players.get(i).getPoint();
                playerIndex=i;
            }
        }
        return players.get(playerIndex);
    }
}
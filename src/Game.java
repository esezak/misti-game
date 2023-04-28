import java.lang.reflect.Array;
import java.util.*;


public class Game {
    private Deck deck;
    private Board board;
    private ArrayList<Player> players = new ArrayList<Player>();
    private Card bufferCard;
    private int roundctr;

    public Game(String[] args) {
        //Parameters include "2 point.txt Can Human Ege Novice verbose"

        deck = new Deck();
        Random r = new Random();
        deck.shuffle();
        deck.cut(r.nextInt(52));
        deck.see();
        board = new Board(deck);
        roundctr=0;
        for(int i = 0; i<args.length;i++){
            String arg = args[i];
            switch(arg){
                case "Human":
                    players.add(new Human(args[i-1]));
                    break;
                case "Novice":
                    players.add(new BotNovice(args[i-1]));
                    break;
                case "Normal":
                    players.add(new BotNormal(args[i-1]));
                    break;
                case "Expert":
                    //players.add((new BotExpert(args[i-1]));
                    break;
                default:
                    break;
            }
        }
        System.out.println("\nThere are "+ playerCount()+" players playing");
        dealCards();

    }
    public void debug(){
        System.out.println("Deck size: "+getDeckSize());
        for(int i=0;i<players.size();i++){
            System.out.print("Player "+ (i+1) +" Hand: "); players.get(i).see();
            System.out.print("\nPlayer "+(i+1)+ "Taken Cards: "); players.get(i).seeCardsTaken();
            System.out.print("\nPlayer "+ (i+1) +" Point: "+players.get(i).getPoint()+"\n");
            board.seeBoard();
        }
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
        for(int i = 0; i<4;i++){//deal cards
            for(Player p : players){
                p.addCard(deck);
            }
        }
    }
    public void round(){
        //round
        //for every player
        for(Player player: players){
            //board.seeBoard();
            int play = player.play(board);
            message(player,play);
            boardUpdate(player,play);
        }
    }
    public int getDeckSize(){
        return deck.getSize();
    }
    public void message(Player player, int play){
        System.out.println("Player "+player.getName()+" played: "+player.getCard(play).getCard());
    }
    public void boardUpdate(Player player, int play){
        bufferCard = player.getCard(play);
        player.removeCard(play);
        boardCheck(player);
    }
    public void boardCheck(Player player){
        int boardCardNum = board.getTopCard().getNumber();
        int playedCardNum = bufferCard.getNumber();
        int boardCardPoint = board.getTopCard().getPoint();
        int playedCardPoint = bufferCard.getPoint();
        if(board.getBoard().size()==1&&boardCardNum==playedCardNum){//mişti check and take board
            int pointToAdd = (boardCardPoint+playedCardPoint)*5;
            player.addPoint(pointToAdd);
            resetPlayerTakes();
            player.lastTookCards();
            board.flushBoard();
        }else if(boardCardNum==playedCardNum | playedCardNum==11){//take board normal
            board.addCard(bufferCard);
            player.addPoint(board.getBoardPoint());
            resetPlayerTakes();
            player.lastTookCards();
            board.flushBoard();
        }else{
            board.addCard(bufferCard);
        }
    }
    public void resetPlayerTakes(){
        for(Player p: players){
            p.resetLastTake();
        }
    }
    public void boardCleanup(){
        for(Player p: players){
            if(p.isLastTake()){
                p.addPoint(board.getBoardPoint());
                board.flushBoard();
                board.flushBoard();
            }
        }
    }
}
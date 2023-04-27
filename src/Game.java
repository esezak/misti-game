import java.lang.reflect.Array;
import java.util.*;


public class Game {
    private Deck deck = new Deck();
    private Board board = new Board(deck);
    private ArrayList<Player> players = new ArrayList<Player>();
    private Card bufferCard;

    public Game(String[] args) {
        //Parameters include "2 point.txt Can Human Ege Novice verbose"
        System.out.println("There are "+args[0]+" players playing");
        Random r = new Random();
        deck.shuffle();
        deck.cut(r.nextInt(52));

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
            dealCards();
        }

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
        System.out.println("Player "+player.getName()+" played: "+player.getCard(play));
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
        if(board.getBoard().size()==1&&boardCardNum==playedCardNum){//mişti check
            int pointToAdd = (boardCardPoint+playedCardPoint)*5;
            player.addPoint(pointToAdd);
            board.flushBoard();
        }else if(boardCardNum==playedCardNum || playedCardNum==11){//take board
            board.addCard(bufferCard);
            player.addPoint(board.getBoardPoint());
            board.flushBoard();
        }else{
            board.addCard(bufferCard);
        }
    }
}
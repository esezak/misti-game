import java.util.*;
import java.util.Scanner;

public class Game {
<<<<<<< Updated upstream
    Deck deck = new Deck();
    Board board = new Board();
    

    public Game() {
=======
    private Deck deck = new Deck();
    private Board board = new Board(deck);
    private ArrayList<Player> players = new ArrayList<Player>();
    private Card bufferCard;
    private int number;

    public Game(String[] args) {
        //Parameters include "2 point.txt Can Human Ege Novice verbose"
        // System.out.println("There are "+args[0]+" players playing");
        Scanner sc = new Scanner(System.in);
        System.out.println("There are how many players ?");
        number = sc.nextInt();
        System.out.println(number + " players are in the game... ");

        for(int i = 0; i<number;i++){

            if(number == 2) {
                players.add(new Human("Can"));
                players.add(new BotNovice("Ege"));
            }

            else if(number == 3) {
                players.add(new Human("Can"));
                players.add(new BotNovice("Ege"));
                players.add(new BotNormal("Yusuf"));
            }

            else if(number == 4) {
                players.add(new Human("Can"));
                players.add(new BotNovice("Ege"));
                players.add(new BotNormal("Yusuf"));
                players.add(new BotExpert("Mehmet"));
            }

            else {
                System.out.println("Player capacity is 4...");
            }
        } 

>>>>>>> Stashed changes
        Random r = new Random();
        deck.see();
        System.out.println();
        deck.shuffle();
        System.out.println("Shuffled deck: ");
        deck.see();
        System.out.println();
        deck.cut(r.nextInt(52));
        System.out.println("Shuffled and cutted deck: ");
        deck.see();

<<<<<<< Updated upstream
        // to see the top card (string)
        System.out.println("Top card: ");
        System.out.println(deck.seeTopCard());

        // to see the card we want (string)
        System.out.println("Fifth card: ");
        System.out.println(deck.getCard(4));
        
        // to see the top card (object)
        Card topCard = deck.seeTopcardObject();
        System.out.println("Top card");

        if(topCard != null) {
            System.out.println(topCard.getCard());
        }
        else {
            System.out.println("Error ! ");
        }

        // to see the card we want (object) 
        Card card = deck.getCardObject(4);
        System.out.println("Fifth card: ");
        System.out.println(card.getCard());
=======
        /* for(int i = 0; i<args.length;i++){
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
            } */
            
            for(int i=0; i<4; i++) {
                dealCards(); 
            }

            round();
        }

    public void dealCards(){
        for(int i=0; i<number; i++){ //deal cards
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
        if(board.getBoard().size()==1&&boardCardNum==playedCardNum){//mişti check
            int pointToAdd = (boardCardPoint+playedCardPoint)*5;
            player.addPoint(pointToAdd);
            board.flushBoard();
        }
        
        else if(boardCardNum==playedCardNum || playedCardNum==11){//take board
            board.addCard(bufferCard);
            player.addPoint(board.getBoardPoint());
            board.flushBoard();
        }
        
        else{
            board.addCard(bufferCard);
        }
>>>>>>> Stashed changes
    }
}
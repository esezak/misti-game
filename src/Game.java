import java.util.*;


public class Game {
    private Deck deck;
    private Board board;
    private ArrayList<Player> players = new ArrayList<Player>();
    private Card bufferCard; // a card for temporarily store a card
    private int roundCounter; // for keeping track of when to deal cards
    private int roundNumber;  //for showing round number
    private int numberOfHumans=0;   // for keeping track of how many human players exist
    private boolean isVerbose=false; // check if verbose mode is enabled
    private FileHandling fileHandling; // class for reading points file and managing leaderboard
    private String[] args;  // for storing the arguments given to the program

    public Game(String[] args) {
        roundNumber = 1;
        roundCounter = 0;
        this.args = args;
        //Example arguments (2 point.txt Can Human Ege Novice verbose)
        setVerbose(args);   //check if verbose mode is enabled

        deck = new Deck(isVerbose); // create deck

        fileHandling = new FileHandling(args[1], deck.getDeck());
        fileHandling.pointReading();        //read points file

        System.out.println("----------Welcome to Mişti----------");
        System.out.println("Created by: Ege Sezak, Defne Yılmaz and Can Özer");
        deck.showChangedCards();

        ///--------Ready the deck-------------
        Random r = new Random();
        deck.shuffle();
        deck.cut(r.nextInt(52));

        board = new Board(deck); // creates board with 4 cards

        validateArgs(checkArgs()); //arg verification
        System.out.println("\nThere are "+ playerCount()+" players playing");
        dealCards();

    }
    public void validateArgs(boolean argsOk){//exits code if arguments are not right
        if(!argsOk){
            System.err.println("Argument format should be: <number of players> <point file name> <p1 name> <p1 type> <p2 name> <p2 type> ... <p4 type> <verbose> (if needed)");
            System.exit(0);
        }
    }

    public boolean checkArgs(){
        for(int i = 3; i<args.length; i++){//adds users based on arguments
            String arg = args[i].toLowerCase();//for compatibility with Uppercase characters
            switch(arg){
                case "human":
                    players.add(new Human(args[i-1]));
                    numberOfHumans++;
                    break;
                case "novice":
                    players.add(new BotNovice(args[i-1]));
                    break;
                case "normal":
                    players.add(new BotNormal(args[i-1]));
                    break;
                case "expert":
                    players.add(new BotExpert(args[i-1]));
                    break;
                default:
                    break;
            }
        }
        int numplayer;
        //check number of players
        try{
            numplayer = Integer.parseInt(args[0]);
        }catch(NumberFormatException numex){
            System.err.println("\nFirst argument must be the number of players.");
            return false;
        }
        if(numberOfHumans > 1){
            System.err.println("\nThere can only be 1 human player!");
            return false;
        }
        if(numplayer > 4 || numplayer < 2){
            System.err.println("\nNumber of player must be 2,3 or 4.");
            return false;
        }
        if(numplayer != players.size()){
            System.err.println("\nNumber of players given in the arguments does not match!");
            return false;
        }
        return true;
    }
    public void setVerbose(String[] args){//sets verbose mode based on the last argument name
        String arg = args[args.length-1].toLowerCase();
        if(arg.equals("verbose")){
            isVerbose = true;
            System.out.println("Verbose mode is enabled.");
        }
        else{
            isVerbose = false;
            System.out.println("Verbose mode is not enabled.");
        }
    }
    public void debug(){// shows deck size, player hands, scores and the state of the board at the end of the game
        System.out.println("Deck size: "+getDeckSize());
        for(int i = 0; i < players.size(); i++){
            System.out.print("Player "+ (i+1) +" "); players.get(i).see();
            System.out.print("Player "+ (i+1) +" Point: "+players.get(i).getPoint()+"\n");
        }
        board.seeBoard();
    }
    public void start(){// plays 1 round
        round();
        roundCounter++;
        if(roundCounter == 4){
            dealCards();
            roundCounter = 0;
        }
    }
    public int playerCount(){
        return players.size();
    }
    public void dealCards(){//deals 4 cards each player (round-robin)
        if(isVerbose){
            System.out.println("Dealing cards...");
        }
        for(int i = 0; i < 4; i++){//deal cards
            for(Player p : players){
                p.addCard(deck);
            }
        }
    }
    public void round(){//makes every player play once
            System.out.println("------Round " + (roundNumber++)+"------");
        for(Player player: players){
            if(isVerbose){
                System.out.println("Player "+player.getName()+" is playing.");
                player.see();//shows the hand of the playing player
            }

            int play = player.play(board);//returns the index played by the player
            message(player,play);
            boardUpdate(player,play);//card is played here
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
    public void boardCheck(Player player){//actually updates the board states and gives points to players if needed

        String boardCardNum = board.getTopCard().getNumber();
        int boardCardPoint = board.getTopCard().getPoint();

        String playedCardNum = bufferCard.getNumber();
        int playedCardPoint = bufferCard.getPoint();

        if(board.getSize() == 1 && boardCardNum.equals(playedCardNum)){//mişti check and take board
            int pointToAdd = (boardCardPoint + playedCardPoint) * 5;
            player.addPoint(pointToAdd);
            resetPlayerTakes();         //sets players take value to false
            player.lastTookCards();     //sets the player who took the cards true
            board.addCard(bufferCard);
            board.flushBoard();//empties board and board points
            System.out.println("Miştiiii!");
        }else if(boardCardNum.equals(playedCardNum) || (playedCardNum.equals("J") && board.getSize() != 0)){//take board normal
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
        for(Player p : players){
            p.resetLastTake();
        }
    }
    public void boardCleanup(){//depending on who last took the cards gets the remaining cards on the board
        for(Player p : players){
            if(p.isLastTake()){
                p.addPoint(board.getBoardPoint());
                board.flushBoard();
            }
        }
    }
    public Player getWinner(){
        int highestScore = 0;
        int playerIndex = 0;
        for(int i = 0; i < playerCount(); i++){
            if(highestScore<players.get(i).getPoint()){
                highestScore = players.get(i).getPoint();
                playerIndex = i;
            }
        }
        return players.get(playerIndex);
    }
    public void showLeaderBoard(){
        fileHandling.addPlayer(getWinner());
        fileHandling.printList();
    }
}
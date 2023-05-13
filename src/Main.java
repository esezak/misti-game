public class Main {

    public static void main(String[] args){
      Game game = new Game(args);
        //number of players, point file name, name and expertise level of each player, and verboseness level

        for(int i=0; i<48/game.playerCount();i++){//changes based on how many players are in the game
                game.start();
            }

        game.boardCleanup();//gives remaining cards on the board to the player who last took the board
        //game.debug();
        //BotExpert.printPlayedCards();//for debug
        Player player = game.getWinner();
        System.out.println("Winner is: "+ player.getName()+" with "+player.getPoint()+" points!");

        //-------------- high score list--------------
        game.showLeaderBoard();

    }
}

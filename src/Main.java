public class Main {

    public static void main(String[] args){
      Game game = new Game(args);
        //number of players, point file name, name and expertise level of each player, and verboseness
        //level
        for(int i=0; i<48/game.playerCount();i++){//changes based on how many players are in the game

                game.start();

            }
        game.boardCleanup();
        game.debug();
        BotExpert.printPlayedCards();








        /*
          Game game = new Game();
          //sets up game
          1. create deck
          2. shuffle deck
          3. cut deck

          for/while loop içinde
            game.round();
            1. players play the game (bots and humans)

            puan hesabı
            sonuçlar
         */
    }
}

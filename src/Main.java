public class Main {
    public static void main(String[] args){
      Game game = new Game(args);
        //number of players, point file name, name and expertise level of each player, and verboseness
        //level
        do{
        game.round();
        }while(game.getDeckSize()>0);








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

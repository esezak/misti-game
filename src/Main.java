public class Main {
    public static void main(String[] args){
        System.out.println("Welcome to the mişti game!");
        //test codes
        Deck deck = new Deck();
        Board board = new Board();
        deck.see();
        System.out.println();

        deck.shuffle();
        deck.see();
        System.out.println();
        deck.cut(10);
        deck.see();
        System.out.println(deck.seeTopcard().getCard());
        deck.transferTopCard(board.getBoard());
        System.out.println(board.getCard());
        System.out.println();
        deck.see();
        //test codes end
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

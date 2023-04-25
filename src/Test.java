public class Test {
    public static void main(String[] args){//class for testing methods
        System.out.println("Welcome to the mişti game!");
        //test codes
        Deck deck = new Deck();
        deck.see();
        System.out.println();
        deck.cut(10);
        deck.see();System.out.println();
        Board board = new Board(deck);
        deck.see();System.out.println();

        board.info();
        board.flushBoard();
        board.info();
        board.getTopCard();


    }
}

public class Main {
    public static void main(String[] args){
        System.out.println("Welcome to the mişti game!");
        Deck deck = new Deck();
        deck.see();
        System.out.println();
        deck.cut(10);

        deck.see();
    }
}

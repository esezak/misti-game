import java.nio.file.Paths;
import java.util.Random;
public class Test {
    public static void main(String[] args){//class for testing methods
        //for testing do not commit here
        Deck deck = new Deck(true);
        FileHandling fileRead = new FileHandling();
        Paths.get("points.txt");
        fileRead.fileReading("/src/points.txt", deck.getDeck());
        deck.see();
    }
}

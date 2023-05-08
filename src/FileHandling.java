import javax.swing.plaf.ButtonUI;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
public class FileHandling {
    private boolean check(String fileName) {
        return false;
        //splitlendikten sonra size 2 değilse yanlış format
    }

    public void fileReading(String fileName, ArrayList<Card> Deck) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(fileName));
            String line;
            while((line = reader.readLine()) != null) {
                String[] components = line.split(" ");

                char symbol = components[0].charAt(0);
                String number = components[0].substring(1);
                int point = Integer.parseInt(components[1]);

                String s = Character.toString(symbol);

                if (s.equals("H")||s.equals("h")) {
                    s = "♥";
                } else if (s.equals("S")||s.equals("s")) {
                    s = "♠";
                } else if (s.equals("C")||s.equals("c")) {
                    s = "♣";
                } else if (s.equals("D")||s.equals("d")){
                    s = "♦";
                }

                for (Card card : Deck) {
                    if (card.getNumber().equals(number) && card.getSymbol().equals(s) && card.getPoint() == 1) {
                        card.setPoint(point);
                        break;
                    } else if (s.equals("*") && card.getNumber().equals(number)) {
                        card.setPoint(point);
                    } else if (number.equals("*") && card.getSymbol().equals(s)) {
                        card.setPoint(point);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

class Test2 {
    public static void main(String[]args) {
        Deck deck = new Deck(true);
        FileHandling fileRead = new FileHandling();
        fileRead.fileReading("/Users/defne/IdeaProjects/misti-game/src/points.txt", deck.getDeck());
    }
}

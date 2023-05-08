import javax.swing.plaf.ButtonUI;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.nio.file.Paths;
import java.util.Scanner;
public class FileHandling {

    public void fileReading(String fileName, ArrayList<Card> Deck) {
        Scanner reader = null;
        try {
            reader = new Scanner(fileName);

            while(reader.hasNextLine()) {
                String[] components = reader.nextLine().split(" ");

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
                    } else if (s.equals("*") && card.getNumber().equals(number) && card.getPoint() == 1) {
                        card.setPoint(point);
                    } else if (number.equals("*") && card.getSymbol().equals(s) && card.getPoint() == 1) {
                        card.setPoint(point);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
    }
}

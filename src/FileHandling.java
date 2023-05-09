import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.nio.file.Paths;
import java.util.Formatter;
import java.util.Scanner;
public class FileHandling {
    private Scanner reader = null;
    private Formatter formatter = null;
    private FileWriter writer = null;
    private String[] names = new String[10];
    private String[] scores = new String[10];
    private Player player;
    private String point;
    private String fileName;
    private ArrayList<Card> deck;
    public FileHandling(String pointFileName,ArrayList<Card> deck){
        fileName = pointFileName;
        this.deck=deck;
    }

    // ------------------------ Reading Point File ------------------------
    public void pointReading() {
        try {
            reader = new Scanner(Paths.get(fileName));

            while(reader.hasNextLine()) {
                String[] components = reader.nextLine().split(" ");

                char symbol = components[0].charAt(0);
                String number = components[0].substring(1);
                int point = Integer.parseInt(components[1]);//can throw error   INFO

                String s = Character.toString(symbol);

                if (s.equals("H")||s.equals("h")) {// converts symbol to unicode counterparts
                    s = "♥";
                } else if (s.equals("S")||s.equals("s")) {
                    s = "♠";
                } else if (s.equals("C")||s.equals("c")) {
                    s = "♣";
                } else if (s.equals("D")||s.equals("d")){
                    s = "♦";
                }

                for (Card card : deck) {
                    if (card.getNumber().equals(number) && card.getSymbol().equals(s) && card.getPoint() == 1) {
                        card.setPoint(point);
                        break;
                    } else if (s.equals("*") && card.getNumber().equals(number) && card.getPoint() == 1) {
                        card.setPoint(point);
                    } else if (number.equals("*") && card.getSymbol().equals(s) && card.getPoint() == 1) {
                        card.setPoint(point);
                    } else if (number.equals("*") && s.equals("*") && card.getPoint()==1){
                        card.setPoint(point);
                    }
                }
            }
        } catch (IOException noFile) {
            System.out.println("File not found using default values");
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
    }

    //-------------------- LeaderBoard --------------------------

    public void addPlayer(Player player){
        this.player = player;
        point = String.valueOf(player.getPoint());
        read();
        writeToFile();
    }
    private void read() {
        try {
            reader = new Scanner(Paths.get("scores.txt"));
            int counter = 0;
            while (reader.hasNextLine()) {
                String[] info = reader.nextLine().split(",");
                scores[counter] = info[1].trim();
                names[counter] = info[0].trim();
                counter++;
            }
        } catch (IOException e) {
            System.out.println("File not found, is this your first time playing?");
        } catch(ArrayIndexOutOfBoundsException array){
            System.out.println("Unexpected file format");
        } catch(Exception err){
            System.out.println("An error has occurred!");
        }
        finally {
            if (reader != null) {
                reader.close();
            }
        }
        for (int i = 0; i < names.length; i++) {
            try {
                if (scores[i] != null) {
                    if (Integer.parseInt(scores[i]) < player.getPoint()) {
                        for (int j = names.length - 1; j > i; j--) {//make place for new value
                            names[j] = names[j - 1];
                            scores[j] = scores[j - 1];
                        }
                        names[i] = player.getName();// add values
                        scores[i] = point;
                        break;
                    }
                } else if (scores[i] == null) {// if empty add the value
                    scores[i] = point;
                    names[i] = player.getName();
                    break;
                }
            } catch(NumberFormatException number){
                System.out.println("Incorrect score type, removing entry");
                names[i] = null;
                scores[i] = null;
            }
        }
    }
    private void writeToFile(){
        try {
            writer = new FileWriter("scores.txt", false);
            for(int i=0; i< 10;i++){
                formatter = new Formatter(writer);
                if(scores[i]!=null) {
                    formatter.format("%s,%s\n", names[i], scores[i]);
                }
            }
        } catch (Exception e) {
            System.out.println("Something went wrong.");
        }finally {
            if (formatter != null){
                formatter.close();
            }
        }
    }
    public void printList(){
        System.out.println();
        System.out.println("----------Hi Scores----------");
        int place=1;
        for (int i = 0; i < names.length; i++) {
            if (names[i] != null) {
                System.out.println((place++)+"# Name: " + names[i] + "   Score: " + scores[i]);
            }
        }
    }
}

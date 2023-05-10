import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.nio.file.Paths;
import java.util.Formatter;
import java.util.Scanner;
public class FileHandling {
    private Scanner reader = null;//both
    private Formatter formatter = null;//leaderboard
    private FileWriter writer = null;//leaderboard
    private ArrayList<String> names = new ArrayList<>();//leaderboard
    private ArrayList<String> scores = new ArrayList<>();//leaderboard
    private Player player;//leaderboard
    private String point;//leaderboard
    private String fileName;//points
    private ArrayList<Card> deck;//points
    private int listSize;
    public FileHandling(String pointFileName,ArrayList<Card> deck){
        fileName = pointFileName;
        this.deck=deck;
    }

    // ------------------------ Reading Point File ------------------------
    public void pointReading() {
        try {
            reader = new Scanner(Paths.get(fileName));
            while(reader.hasNextLine()) {
                String line = reader.nextLine();
                String[] components;
                String s;
                try{
                    if(!line.equals("")) {
                        components = line.split(" ");
                        char symbol = components[0].charAt(0);
                        String number = components[0].substring(1);
                        int point = Integer.parseInt(components[1]);
                        s = Character.toString(symbol);

                        if (s.equals("H") || s.equals("h")) {// converts symbol to unicode counterparts
                            s = "♥";
                        } else if (s.equals("S") || s.equals("s")) {
                            s = "♠";
                        } else if (s.equals("C") || s.equals("c")) {
                            s = "♣";
                        } else if (s.equals("D") || s.equals("d")) {
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
                        } else if (number.equals("*") && s.equals("*") && card.getPoint() == 1) {
                            card.setPoint(point);
                        }
                    }
                }
                }catch(NumberFormatException numex){
                    System.err.println("Please use the format: <symbol><number> <point value>");
                }

            }
        } catch (IOException noFile) {
            System.err.println("File not found, using default values...");
        } catch(NumberFormatException numberFormatException){
            System.err.println("number format error on reading point");
        }
        finally {
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
        boolean hasBeenAdded = false;
        try {
            reader = new Scanner(Paths.get("scores.txt"));
            while (reader.hasNextLine()) {
                try {
                    String line = reader.nextLine();
                    String[] info = line.split(",");
                    if (player.getPoint() > Integer.parseInt(info[1].trim()) && !hasBeenAdded) {
                        scores.add(point);
                        names.add(player.getName());
                        hasBeenAdded = true;
                    }
                    scores.add(info[1].trim());
                    names.add(info[0].trim());
                }catch(NumberFormatException numerr){
                    System.err.println("Incorrect point type! Removing entry...");
                }catch(ArrayIndexOutOfBoundsException array){
                    System.out.println("Skipping empty or broken line...");
                }
            }
        } catch (IOException e) {
            System.out.println("File not found, is this your first time playing?");
        } catch(Exception err){
            System.err.println("A read error has occurred!");
        }
        finally {
            if (reader != null) {
                reader.close();
            }
        }
        if(!hasBeenAdded){
            scores.add(point);
            names.add(player.getName());
        }
    }
    private void setListSize(){//sets the list size to 10 if list size is bigger than 10
        listSize = Math.min(names.size(), 10);
    }
    private void writeToFile(){
        setListSize();
        try {
            writer = new FileWriter("scores.txt", false);
            for(int i=0; i< listSize;i++){
                formatter = new Formatter(writer);
                //if(scores.get(i)!=null) {
                    formatter.format("%s,%s\n", names.get(i), scores.get(i));
                //}
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
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
        for (int i = 0; i < listSize; i++) {
                System.out.println((place++)+"# Name: " + names.get(i) + "   Score: " + scores.get(i));
        }
    }
}

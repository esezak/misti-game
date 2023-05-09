import java.io.FileWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;

public class LeaderBoard {
    private Scanner reader = null;
    private Formatter formatter = null;
    private FileWriter writer = null;
    private String[] names = new String[10];
    private String[] scores = new String[10];
    private Player player;
    private String point;
    public LeaderBoard(Player player){
        this.player=player;
        point = String.valueOf(player.getPoint());
        read();
        writeToFile();
        printList();
    }
    private void read() {
        try {
            reader = new Scanner(Paths.get("scores.txt"));
            int counter = 0;
            while (reader.hasNextLine()) {
                String[] info = reader.nextLine().split(",");
                names[counter] = info[0].trim();
                scores[counter] = info[1].trim();
                counter++;
            }
        } catch (Exception e) {
            System.out.println("It seems this is the first time playing.");
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
        for (int i = 0; i < names.length; i++) {
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
        for (int i = 0; i < names.length; i++) {
            if (names[i] != null) {
                System.out.println((i+1)+"# Name: " + names[i] + "   Score: " + scores[i]);
            }
        }
    }

}

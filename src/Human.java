import java.util.Scanner;
public class Human extends Player implements Playable {
    public Human(String name){
        setName(name);
    }

    @Override
    public int play(Board board) {
        Scanner sc = new Scanner(System.in);
        int input;
        board.seeBoard();
        System.out.println();
        see();//sees hand
        try{
            System.out.println("Input: ");
            return Integer.parseInt(sc.next());
        }catch (Exception e){
            System.out.println("Error has occurred");
        }
        System.out.println();
        return 0;
    }
}

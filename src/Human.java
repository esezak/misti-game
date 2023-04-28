import java.util.Scanner;
public class Human extends Player implements Playable {
    public Human(String name){
        setName(name+" (Human)");
    }

    @Override
    public int play(Board board) {
        Scanner sc = new Scanner(System.in);
        int input;
        board.seeBoard();
        System.out.print("Hand: ");
        see();//sees hand
        System.out.println();
        try{
            System.out.print("Input: ");
            return Integer.parseInt(sc.next())-1;
        }catch (Exception e){
            System.out.println("Error has occurred");
        }
        System.out.println();
        return 0;
    }
}

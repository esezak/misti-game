import java.util.Scanner;
public class Human extends Player implements Playable {
    public Human(String name){
        setName(name+"_(Human)");
    }

    @Override
    public int play(Board board) {
        Scanner sc = new Scanner(System.in);
        boolean check = true;
        int input=0;
        while(check){
            try{
                board.seeBoard();
                see();//sees hand
                System.out.print("Input: ");
                input = Integer.parseInt(sc.next())-1;
                getCard(input);//will fail if no card exists
                return input;
            }catch (NumberFormatException numberFormatException){
                System.out.println("Please enter a number between 1-4");
            }catch(IndexOutOfBoundsException outOfBoundsException){
                System.out.println("Value "+(input+1)+" is empty");
            }
        }
        System.out.println();
        return 0;
    }
}

/** CS 110
 *  Jason Lobell
 *  Driver Class
 */
import java.util.Scanner;

public class BattleShipDriver {
    public static void main(String[] args){
        //Create Game
        Game game = new Game();
        //UI
        System.out.println("Welcome to Battleship! \n");
        //Decide who goes first
        double rand = Math.random();
        //Create keyboard Scanner
        Scanner keyboard = new Scanner(System.in);
            //Make correct move and determine turn order
            String first = "";
                if (rand >= 0.5) {
                    first = "c";
                    System.out.println("The computer won the coin toss and gets to go first.\n");
                    String[] compmove = game.makeComputerMove();
                    System.out.println("Computers Move was : " + compmove[0] + "\n" + compmove[1]);
                    System.out.println("Your Turn. Please input move : ");
                    String move = keyboard.next();
                    System.out.println(game.makePlayerMove((move)));
                    System.out.println(game);
                } else {
                    first = "u";
                    System.out.println("Your Turn. Please input move : ");
                    String move = keyboard.next();
                    System.out.println(game.makePlayerMove(move));
                    String[] compmove = game.makeComputerMove();
                    System.out.println(game);
                    System.out.println("Computers Move was : " + compmove[0] + "\n" + compmove[1]);
                }
                //While neither side has lost

                while (!game.computerDefeated() && !game.userDefeated()) {
                    if (first.equals("u")) {
                        System.out.println("Your Turn. Please input move : ");
                        System.out.println(game.makePlayerMove(keyboard.next()));
                        String[] compmove = game.makeComputerMove();
                        System.out.println(game);
                        System.out.println("Computers move was : " + compmove[0] + "\n" + compmove[1]);
                    } else if (first.equals("c")) {
                        String[] compmove = game.makeComputerMove();
                        System.out.println("Computers move was : " + compmove[0] + "\n" + compmove[1]);
                        System.out.println("Your Turn. Please input move : ");
                        System.out.println(game.makePlayerMove(keyboard.next()));
                        System.out.println(game);
                    }

                }
                //When game finishes
                if (game.userDefeated()) {
                    System.out.println("Game Over!");
                    System.out.println("Computer Wins!");
                } else if (game.computerDefeated()) {
                    System.out.println("Game Over!");
                    System.out.println("User Wins!");
                }

    }
}

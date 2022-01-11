/** CS 110
 *  Jason Lobell
 *  Move Class
 */

import java.util.Scanner;

public class Move {
    // Initialize x,y
    private int row = 0;
    private int col = 0;

    /** Move constructor
     * takes user move and puts it in row, col
     * @param row
     * @param col
     */
    public Move(int row, int col){
        this.row = row-1;
        this.col = col-1;
    }

    /** Move Constructor
     * parses character into correlating column, subtracts one from row and sets row and col to said values
     * @param string
     */
    public Move(String string){

            Character character = string.charAt(0);
            String number = string.substring(1);
            try {
                int x = Integer.parseInt(number);
                this.row = x-1;
            }
            catch (NumberFormatException n){
                Scanner keyboard = new Scanner(System.in);
                System.out.println("Invalid move format, please try again : ");
                string = keyboard.next();
                Move move = new Move(string);
            }
            catch (IndexOutOfBoundsException i){
                Scanner keyboard = new Scanner(System.in);
                System.out.println("Index out of bounds, please try again : ");
                string = keyboard.next();
                Move move = new Move(string);
            }
            switch (character){
                case 'A','a':
                    this.col = 0;
                    break;
                case 'B','b':
                    this.col = 1;
                    break;
                case 'C','c':
                    this.col = 2;
                    break;
                case 'D','d':
                    this.col = 3;
                    break;
                case 'E','e':
                    this.col = 4;
                    break;
                case 'F','f':
                    this.col = 5;
                    break;
                case 'G','g':
                    this.col = 6;
                    break;
                case 'H','h':
                    this.col = 7;
                    break;
                case 'I','i':
                    this.col = 8;
                    break;
                case 'J','j':
                    this.col = 9;
                    break;
            }

        }

    /** row method
     *  return row
     * @return int
     */
    public int row(){
        return row;
    }

    /** col method
     *  return col
     * @return int
     */
    public int col(){
        return col;
    }

    /** toString method
     *  return string representation of Move object
     * @return String
     */
    @Override
    public String toString() {
        String column = "";
        switch (col){
            case 0:
                column = "A";
                break;
            case 1:
                column = "B";
                break;
            case 2:
                column = "C";
                break;
            case 3:
                column = "D";
                break;
            case 4:
                column = "E";
                break;
            case 5:
                column = "F";
                break;
            case 6:
                column = "G";
                break;
            case 7:
                column = "H";
                break;
            case 8:
                column = "I";
                break;
            case 9:
                column = "J";
                break;
        }
        return column + (row+1);
    }
}

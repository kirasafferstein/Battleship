/** CS 100
 *  Jason Lobell
 *  UserBoard Class
 */

import java.util.ArrayList;
import java.util.Random;

public class UserBoard extends Board {
    //Makes arraylist of 100 Moves
    private ArrayList<Move> moves = new ArrayList<>(100);
    //Creates Random r
    private Random r;

    /** UserBoard constructor
     * fills moves with new Moves
     * @param string
     */
    public UserBoard(String string){
        super(string);
        for (int i = 1; i <= 10; i++){
            for (int j = 1; j < 10; j ++) {
                //Creates new move for i and j
                Move move = new Move(i, j);
                //adds move to moves
                moves.add(move);
            }
        }
        //Initializes Random r
        r = new Random(1);
    }

    /** makeComputerMove method
     *  Subtracts move from moves, makes that move
     * @return String[]
     */
    public String[] makeComputerMove(){
        //remove move from moves
        Move move = moves.remove(r.nextInt(moves.size()));
        //initialize String[] for return
        String[] string = new String[]{"", ""};
        CellStatus status = applyMoveToLayout(move);

            switch (status){
                case AIRCRAFT_CARRIER_SUNK:
                    string[1] = "Your Aircraft Carrier was sunk!";
                    break;
                case BATTLESHIP_SUNK:
                    string[1] = "Your Battleship was sunk!";
                    break;
                case CRUISER_SUNK:
                    string[1] = "Your Cruiser was sunk!";
                    break;
                case DESTROYER_SUNK:
                    string[1] = "Your Destroyer was sunk!";
                    break;
                case SUB_SUNK:
                    string[1] = "Your Submarine was sunk!";
                    break;
                case AIRCRAFT_CARRIER, BATTLESHIP, SUB, CRUISER, DESTROYER, AIRCRAFT_CARRIER_HIT, BATTLESHIP_HIT, CRUISER_HIT,
                        DESTROYER_HIT,NOTHING,NOTHING_HIT:
                    string[1] = "";
                    break;
            }
            Integer row = move.row() + 1;
            Integer col = move.col();
            char c = ' ';
            switch (col){
                case 0:
                    c = 'A';
                    break;
                case 1:
                    c = 'B';
                    break;
                case 2:
                    c = 'C';
                    break;
                case 3:
                    c = 'D';
                    break;
                case 4:
                    c = 'E';
                    break;
                case 5:
                    c = 'F';
                    break;
                case 6:
                    c = 'G';
                    break;
                case 7:
                    c = 'H';
                    break;
                case 8:
                    c = 'I';
                    break;
                case 9:
                    c = 'J';
                    break;

            }
            String str = c + row.toString();
            string[0] = str;
        return string;
    }

    /** toString method
     *  returns String format of board
     * @return String
     */
    @Override
    public String toString(){
        String board = "";
        int rowNum = 65;
        ArrayList<ArrayList<CellStatus>> layout = getLayout();
        for (ArrayList<CellStatus> row : layout){
            char rowName = (char) rowNum;
            board += rowName + " ";
            rowNum++;
            for (CellStatus status : row){
                String s = status.toString();
                board  += " " + s.substring(1) + " ";
                }
            board += "\n";
            }
        return board;
    }
}

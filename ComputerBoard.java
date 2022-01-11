/** CS 110
 *  Jason Lobell
 *  ComputerBoard class
 */

import java.util.ArrayList;
import java.util.Scanner;

public class ComputerBoard extends Board {
    /** ComputerBoard Constructor
     *  Uses super constructor for the Board object
     * @param string
     */
    public ComputerBoard(String string){
        super(string);
    }

    /** makePlayerMove method
     *  Takes in move object and checks if the move is taken. If not, uses switch statement to test cell for each base
     *  shipType enum value. After
     * @param move
     * @return String
     */
    public String makePlayerMove(Move move){
        //Initi
        String str = "";
        if (!moveTaken(move)){
            Fleet fleet = getFleet();
            CellStatus cellStatus = applyMoveToLayout(move);
            switch (cellStatus){
                case AIRCRAFT_CARRIER:
                    boolean acsunk = fleet.updateFleet(ShipType.ST_AIRCRAFT_CARRIER);
                    if (acsunk){
                        str=("Computer : You sank my Aircraft Carrier!");
                    }
                    break;
                case BATTLESHIP:
                    boolean bsunk =  fleet.updateFleet(ShipType.ST_BATTLESHIP);
                    if (bsunk){
                        str=("Computer : You sank my Battleship!");
                    }
                    break;
                case CRUISER:
                    boolean csunk =  fleet.updateFleet(ShipType.ST_CRUISER);
                    if (csunk){
                        str=("Computer : You sank my Cruiser!");
                    }
                    break;
                case DESTROYER:
                    boolean dsunk =   fleet.updateFleet(ShipType.ST_DESTROYER);
                    if (dsunk){
                        str=("Computer : You sank my Destroyer!");
                    }
                    break;
                case SUB:
                    boolean ssunk =  fleet.updateFleet(ShipType.ST_SUB);
                    if (ssunk){
                        str=("Computer : You sank my Sub!");
                    }
                    break;
            }

        }
        else {
            Scanner tempkeyboard = new Scanner(System.in);
            try {
                str=("Please input valid Move : ");
                makePlayerMove(new Move(tempkeyboard.next()));
            }
            catch (IndexOutOfBoundsException i){
                str=("Move out of bounds, please try again : ");
                makePlayerMove(new Move(tempkeyboard.next()));
            }
            catch (NumberFormatException n){
                str=("Incorrect Move format, please try again : ");
                makePlayerMove(new Move(tempkeyboard.next()));
            }
            catch (Exception e){
                str=("Unresolved Error : ");
                e.printStackTrace();
            }
        }
        return str;

    }

    /** toString Method
     *  prints computer board with letters along the rows
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
                board += " " + s.substring(0,1) + " ";

            }
            board += "\n";
        }

        return board;
    }
}

/** CS 110
 ** Jason Lobell
 *  Board Class
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Board {
    //Matrix of Cells
    private ArrayList<ArrayList<CellStatus>> layout;
    //Fleet Object initialization
    private Fleet fleet = new Fleet();
    //Board size
    private final int SIZE = 10;

    /** Board Constructor
     * Fills matrix of CellStatuses with initial values of NOTHING after assigning each row a maximum length and a maximum number of rows
     * @param filename
     */
    public Board(String filename) {

        layout = new ArrayList<>(SIZE);
        int cols = 0;
        while (cols < SIZE) {
            ArrayList<CellStatus> row = new ArrayList<>(SIZE);
            for (int i = 0; i < SIZE; i++) {
                row.add(i, CellStatus.NOTHING);
            }
            layout.add(cols, row);
            cols++;
        }
        loadFromFile(filename);
    }

    /** applyMoveToLayout Method
     * Checks to see if move is taken, if not then update Cellstatus to new enumerated value. If all Ship cells of that
     * type are hit, change all cells to sunk. Uses recursion with the move being taken as the base case
     * @param move
     * @return Cellstatus
     */
    public CellStatus applyMoveToLayout(Move move){
        if (moveTaken(move)){
            System.out.println("Move taken, please try again : ");
            Scanner tempkey = new Scanner(System.in);
            applyMoveToLayout(new Move(tempkey.next()));
        }
        else {
            if (layout.get(move.col()).get(move.row()) == CellStatus.AIRCRAFT_CARRIER){
                layout.get(move.col()).set(move.row(), CellStatus.AIRCRAFT_CARRIER_HIT);
                ShipType shipType = ShipType.ST_AIRCRAFT_CARRIER;
                if (fleet.updateFleet(shipType)){
                    for (int i = 0; i < SIZE; i++) {
                        for (int j = 0; j < SIZE; j++){
                            if ((layout.get(i).get(j) == CellStatus.AIRCRAFT_CARRIER_HIT)){
                                layout.get(i).set(j, CellStatus.AIRCRAFT_CARRIER_SUNK);
                            }
                        }
                    }
                }
            }
            else if (layout.get(move.col()).get(move.row()) == CellStatus.BATTLESHIP){
                layout.get(move.col()).set(move.row(), CellStatus.BATTLESHIP_HIT);
                ShipType shipType = ShipType.ST_BATTLESHIP;
                if (fleet.updateFleet(shipType)){
                    for (int i = 0; i < SIZE; i++) {
                        for (int j = 0; j < SIZE; j++){
                            if ((layout.get(i).get(j) == CellStatus.BATTLESHIP_HIT)){
                                layout.get(i).set(j, CellStatus.BATTLESHIP_SUNK);
                            }
                        }
                    }
                }
            }
            else if (layout.get(move.col()).get(move.row()) == CellStatus.CRUISER){
                layout.get(move.col()).set(move.row(), CellStatus.CRUISER_HIT);
                ShipType shipType = ShipType.ST_CRUISER;
                if (fleet.updateFleet(shipType)){
                    for (int i = 0; i < SIZE; i++) {
                        for (int j = 0; j < SIZE; j++){
                            if ((layout.get(i).get(j) == CellStatus.CRUISER_HIT)){
                                layout.get(i).set(j, CellStatus.CRUISER_SUNK);
                            }
                        }
                    }
                }
            }
            else if (layout.get(move.col()).get(move.row()) == CellStatus.DESTROYER){
                layout.get(move.col()).set(move.row(), CellStatus.DESTROYER_HIT);
                ShipType shipType = ShipType.ST_DESTROYER;
                if (fleet.updateFleet(shipType)){
                    for (int i = 0; i < SIZE; i++) {
                        for (int j = 0; j < SIZE; j++){
                            if ((layout.get(i).get(j) == CellStatus.DESTROYER_HIT)){
                                layout.get(i).set(j, CellStatus.DESTROYER_SUNK);
                            }
                        }
                    }
                }
            }
            else if (layout.get(move.col()).get(move.row()) == CellStatus.SUB){
                layout.get(move.col()).set(move.row(), CellStatus.SUB_HIT);
                ShipType shipType = ShipType.ST_SUB;
                if (fleet.updateFleet(shipType)){
                    for (int i = 0; i < SIZE; i++) {
                        for (int j = 0; j < SIZE; j++){
                            if ((layout.get(i).get(j) == CellStatus.SUB_HIT)){
                                layout.get(i).set(j, CellStatus.SUB_SUNK);
                            }
                        }
                    }
                }
            }
            else if (layout.get(move.col()).get(move.row()) == CellStatus.NOTHING){
                layout.get(move.col()).set(move.row(), CellStatus.NOTHING_HIT);
            }
        }
        return layout.get(move.row()).get(move.col());
    }

    /** MoveTaken Method
     * tests move against cellstatus in layout to see if cellstatus is one of the base types. if not, move is taken
     * @param move
     * @return boolean
     */
    public boolean moveTaken(Move move){
        return  layout.get(move.row()).get(move.col()) != CellStatus.AIRCRAFT_CARRIER &&
                layout.get(move.row()).get(move.col()) != CellStatus.BATTLESHIP &&
                layout.get(move.row()).get(move.col()) != CellStatus.CRUISER &&
                layout.get(move.row()).get(move.col()) != CellStatus.DESTROYER &&
                layout.get(move.row()).get(move.col()) != CellStatus.SUB &&
                layout.get(move.row()).get(move.col()) != CellStatus.NOTHING;
    }

    /** gameOver method
     * returns true if all ships in fleet are sunk
     * @return boolean
     */
    public boolean gameOver(){
        return fleet.gameOver();
    }

    /** getLayout method
     *  returns Matrix of CellStatus objects
     * @return ArrayList<ArrayList<CellStatus>>
     */
    public ArrayList<ArrayList<CellStatus>> getLayout() {
        return layout;
    }

    /** getFleet method
     * returns fleet
     * @return Fleet
     */
    public Fleet getFleet() {
        return fleet;
    }

    /** loadFromFile method
     * Creates scanner object using filepath, reads each line and turns the content of that line into an Array of
     * Strings using the space bar as the delimiter. Scans the [0] value for ship type, [1] for ship starting location,
     * and [2] for ship ending location. After, corresponding celltypes are added to the cells on the board Matrix.
     * @param filepath
     */
    public void loadFromFile(String filepath)  {
            //Takes in file path as initial variable for Scanner object
        Scanner moveScanner = null;
        try {
            moveScanner = new Scanner(new File(filepath));
        }
        catch (FileNotFoundException f){
            Scanner keys = new Scanner(System.in);
            System.out.println("Please input valid filepath");
            String file = keys.next();
            loadFromFile(filepath);
        }

            //While file has ships
            while (moveScanner.hasNextLine()) {
                //Grab type, start, and end
                String shipLocation = moveScanner.nextLine();
                //Turn line into array of strings, use space as delimiter
                String[] parts = shipLocation.split(" ");
                //Starting Location is second String in parts
                String shipStart = parts[1];
                //Initialize corresponding row value
                int rowStart = 0;
                //Second character is the numeric value
                String startRow = shipStart.substring(1);
                try {
                    //Parse string into int
                    int r = Integer.parseInt(startRow);
                    //Assign r to rowstart
                    rowStart = r-1;
                } catch (NumberFormatException n) {
                    return;
                }
                //Initialize corresponding column value
                int colStart = 0;
                //First character is the alphabetical value
                String startCol = shipStart.substring(0, 1);
                //Switch statement assigning numeric values to colStart variable
                switch (startCol) {
                    case "A", "a":
                        colStart = 0;
                        break;
                    case "B", "b":
                        colStart = 1;
                        break;
                    case "C", "c":
                        colStart = 2;
                        break;
                    case "D", "d":
                        colStart = 3;
                        break;
                    case "E", "e":
                        colStart = 4;
                        break;
                    case "F", "f":
                        colStart = 5;
                        break;
                    case "G", "g":
                        colStart = 6;
                        break;
                    case "H", "h":
                        colStart = 7;
                        break;
                    case "I", "i":
                        colStart = 8;
                        break;
                    case "J", "j":
                        colStart = 9;
                        break;
                }
                //Third String from parts is the end location
                String shipEnd = parts[2];
                //Initialize row end value
                int rowEnd = 0;
                //Second character is the numeric value
                String endRow = shipEnd.substring(1);
                try {
                    //Parse integer
                    int row = Integer.parseInt(endRow);
                    //Assign to rowEnd
                    rowEnd = row-1;
                } catch (NumberFormatException n) {

                }
                //Initialize col end value
                int colEnd = 0;
                //First character is the alphabetical value
                String endCol = shipEnd.substring(0, 1);
                //Switch statement assigning numeric values to colEnd variable
                switch (endCol) {
                    case "A", "a":
                        colEnd = 0;
                        break;
                    case "B", "b":
                        colEnd = 1;
                        break;
                    case "C", "c":
                        colEnd = 2;
                        break;
                    case "D", "d":
                        colEnd = 3;
                        break;
                    case "E", "e":
                        colEnd = 4;
                        break;
                    case "F", "f":
                        colEnd = 5;
                        break;
                    case "G", "g":
                        colEnd = 6;
                        break;
                    case "H", "h":
                        colEnd = 7;
                        break;
                    case "I", "i":
                        colEnd = 8;
                        break;
                    case "J", "j":
                        colEnd = 9;
                        break;
                }
                //First String in parts is the ship type
                char[] shipType = parts[0].toCharArray();
                //Switch statement on shipType, adds corresponding CellStatus values to the already existing matrix of CellStatuses
                //using colStart, colEnd, rowStart, rowEnd, and shipType[0] (A,B,C,D,S)
                switch (shipType[0]) {
                    case 'A', 'a':
                        if (rowEnd - rowStart != 0 && colEnd - colStart != 0) {
                            System.out.println("Error : Invalid Ship Placement");
                        }
                        layout.get(colStart).set(rowStart, CellStatus.AIRCRAFT_CARRIER);
                        if (colStart == colEnd) {
                            for (int i = rowStart + 1; i <= rowEnd; i++) {
                                layout.get(colStart).set(i, CellStatus.AIRCRAFT_CARRIER);
                            }
                        } else {
                            for (int i = colStart + 1; i <= colEnd; i++) {
                                layout.get(i).set(rowStart, CellStatus.AIRCRAFT_CARRIER);
                            }
                        }
                        break;
                    case 'B', 'b':
                        if (rowEnd - rowStart != 0 && colEnd - colStart != 0) {
                            System.out.println("Error : Invalid Ship Placement");
                        }
                        layout.get(colStart).set(rowStart, CellStatus.BATTLESHIP);
                        if (colStart == colEnd) {
                            for (int i = rowStart + 1; i <= rowEnd; i++) {
                                layout.get(colStart).set(i, CellStatus.BATTLESHIP);
                            }
                        } else {
                            for (int i = colStart + 1; i <= colEnd; i++) {
                                layout.get(i).set(rowStart, CellStatus.BATTLESHIP);
                            }
                        }
                        break;
                    case 'C', 'c':
                        if (rowEnd - rowStart != 0 && colEnd - colStart != 0) {
                            System.out.println("Error : Invalid Ship Placement");
                        }
                        layout.get(colStart).set(rowStart, CellStatus.CRUISER);
                        if (colStart == colEnd) {
                            for (int i = rowStart + 1; i <= rowEnd; i++) {
                                layout.get(colStart).set(i, CellStatus.CRUISER);
                            }
                        } else {
                            for (int i = colStart + 1; i <= colEnd; i++) {
                                layout.get(i).set(rowStart, CellStatus.CRUISER);
                            }
                        }
                        break;
                    case 'D', 'd':
                        if (rowEnd - rowStart != 0 && colEnd - colStart != 0) {
                            System.out.println("Error : Invalid Ship Placement");
                        }
                        layout.get(colStart).set(rowStart, CellStatus.DESTROYER);
                        if (colStart == colEnd) {
                            for (int i = rowStart + 1; i <= rowEnd; i++) {
                                layout.get(colStart).set(i, CellStatus.DESTROYER);
                            }
                        } else {
                            for (int i = colStart + 1; i <= colEnd; i++) {
                                layout.get(i).set(rowStart, CellStatus.DESTROYER);
                            }
                        }
                        break;
                    case 'S', 's':
                        if (rowEnd - rowStart != 0 && colEnd - colStart != 0) {
                            System.out.println("Error : Invalid Ship Placement");
                        }
                        layout.get(colStart).set(rowStart, CellStatus.SUB);
                        if (colStart == colEnd) {
                            for (int i = rowStart + 1; i <= rowEnd; i++) {
                                layout.get(colStart).set(i, CellStatus.SUB);
                            }
                        } else {
                            for (int i = colStart + 1; i <= colEnd; i++) {
                                layout.get(i).set(rowStart, CellStatus.SUB);
                            }
                        }
                        break;
                }
            }




    }
}

    


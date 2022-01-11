/** CS 110
 *  Jason Lobell
 *  Game Class
 */
public class Game {
    //Initialize boards
    private ComputerBoard computerBoard;
    private UserBoard userBoard;
    //Final path Strings
    public final String USER_PATH = "C:\\Users\\Jason\\IdeaProjects\\fucking work please\\src\\userFleet";
    public final String COMP_PATH = "C:\\Users\\Jason\\IdeaProjects\\fucking work please\\src\\compFleet";

    /** Game Constructor
     * Game constructor initializes boards
     */
    public Game(){
        userBoard = new UserBoard(USER_PATH);
        computerBoard = new ComputerBoard(COMP_PATH);
    }

    /** makeComputerMove method
     * calls the UserBoards makeComputerMove
     * @return String[]
     */
    public String[] makeComputerMove(){
        return userBoard.makeComputerMove();
    }

    /** makePlayerMove method
     *  calls ComputerBoards makePLayerMove method
     * @param string
     * @return String
     */
    public String makePlayerMove (String string){
        return computerBoard.makePlayerMove(new Move(string));
    }

    /** userDefeated method
     * returns true if users fleet is sunk
     * @return boolean
     */
    public boolean userDefeated(){
        return userBoard.gameOver();
    }

    /** computerDefeated method
     *  returns true if computers fleet is sunk
     * @return boolean
     */
    public boolean computerDefeated(){
        return computerBoard.gameOver();
    }

    /** toString method
     *  returns String value of UserBoard and ComputerBoard objects with some filler
     * @return String
     */
    @Override
    public String toString(){
        return      "USER\n   1  2  3  4  5  6  7  8  9  10\n" + userBoard.toString() + "\n" +
                "COMPUTER\n   1  2  3  4  5  6  7  8  9  10\n" + computerBoard.toString() + "\n";
    }
}

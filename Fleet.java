/** CS 110
 *  Jason Lobell
 *  Fleet Class
 */
public class Fleet {
    //initialize Ship objects
    private Battleship battleship;
    private Destroyer destroyer;
    private Criuser criuser;
    private Sub sub;
    private AircraftCarrier aircraftCarrier;

    /**Fleet Constructor
     * Initializes Ship objects
     */
    public Fleet(){
        battleship = new Battleship();
        destroyer = new Destroyer();
        criuser = new Criuser();
        sub = new Sub();
        aircraftCarrier = new AircraftCarrier();
    }

    /** updateFleet method
     *  Updates ship of corresponding shiptype, if that hit sinks that ship, return true
     * @param shipType
     * @return boolean
     */
    public boolean updateFleet(ShipType shipType){
        boolean b = false;
        switch (shipType){
            case ST_SUB :
                sub.hit();
                if (sub.getSunk()){
                    b = true;
                }
                break;
            case ST_CRUISER:
                criuser.hit();
                if (criuser.getSunk()){
                    b = true;
                }
                break;
            case ST_DESTROYER:
                destroyer.hit();
                if (destroyer.getSunk()){
                    b = true;
                }
                break;
            case ST_BATTLESHIP:
                battleship.hit();
                if (battleship.getSunk()){
                    b = true;
                }
                break;
            case ST_AIRCRAFT_CARRIER:
                aircraftCarrier.hit();
                if (aircraftCarrier.getSunk()){
                    b = true;
                }
                break;
        }
        return b;
    }

    /** gameOver method
     *  return true if all ships are sunk
     * @return boolean
     */
    public boolean gameOver(){
        return sub.getSunk() && criuser.getSunk()
                && destroyer.getSunk() && battleship.getSunk()
                && aircraftCarrier.getSunk();
    }
}

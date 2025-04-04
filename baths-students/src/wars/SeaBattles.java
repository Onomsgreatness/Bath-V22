package wars;

import java.util.*;
import java.io.*;
import wars.Ship;
/**
 * This class implements the behaviour expected from the BATHS
 system as required for 5COM2007 Cwk1B BATHS - Feb 2025
 * 
 * @author A.A.Marczyk 
 * @version 16/02/25
 */

public class SeaBattles implements BATHS 
{
    // may have one HashMap and select on stat

    private String admiral;
    private double warChest;
    
    private Map<String, Ship> reserveFleet = new HashMap<>();
    private Map<String, Ship> squadron = new HashMap<>();
    private Map<Integer, Encounter> encounters = new HashMap<>();



//**************** BATHS ************************** 
    /** Constructor requires the name of the admiral
     * @param adm the name of the admiral
     */  
    public SeaBattles(String adm)
    {
       this.admiral = adm;
       this.warChest = 1000;
        
       setupShips();
       setupEncounters();
    }
    
    /** Constructor requires the name of the admiral and the
     * name of the file storing encounters
     * @param admir the name of the admiral
     * @param filename name of file storing encounters
     */  
    public SeaBattles(String admir, String filename)  //Task 3
    {
      
        
       setupShips();
       setupEncounters();
       // uncomment for testing Task 
       // readEncounters(filename);
    }
    
    
    /**Returns a String representation of the state of the game,including the name of the 
     * admiral, state of the warChest,whether defeated or not, and the ships currently in 
     * the squadron,(or, "No ships" if squadron is empty), ships in the reserve fleet
     * @return a String representation of the state of the game,including the name of the 
     * admiral, state of the warChest,whether defeated or not, and the ships currently in 
     * the squadron,(or, "No ships" if squadron is empty), ships in the reserve fleet
     **/
    public String toString()
    {
        // Using StringBuilder for efficient string concatenation
        String s = "";

        // Add admiral and war chest info
        s += "Admiral: "+ admiral + "\n";  // Name of the admiral
        s += "War Chest: " + warChest + "\n";  // State of the war chest
        s += "Admiral State: " + isStringDefeated()  + "\n";  // Whether defeated or not

        // Add ships currently in the squadron
        String squadronShips = getSquadron();
        if (squadronShips.equals("No ships \n")) {
            s += "No ships"; //No ships currently in Squadron
        } else {
            s += "Ships currently in the squadron:\n" + squadronShips;
        }

        // Add ships in the reserve fleet
        String reserveFleetShips = getReserveFleet();
        if (reserveFleetShips.equals("No ships")) {
            s += "\nNo reserve fleet.\n";
        } else {
            s += "\nReserve Fleet:\n" + reserveFleetShips;
        }

        return s;  
    }

    
    /** returns true if War Chest <=0 and the admiral's squadron has no ships which 
     * can be retired. 
     * @returns true if War Chest <=0 and the admiral's fleet has no ships 
     * which can be retired. 
     */
    public boolean isDefeated()
    {
       return warChest <= 0 && 
           squadron.values().stream().allMatch(Ship::isSunk);
    }
    
    private String isStringDefeated()
    {
       if (isDefeated())
       {
           return "Is Not Ok";
       }
       else {return "Is OK";}
    }
    
    /** returns the amount of money in the War Chest
     * @returns the amount of money in the War Chest
     */
    public double getWarChest()
    {
        return warChest;
    }
    
        private double addWarChest(double moneyAdded)
    {
        return warChest = warChest + moneyAdded;
    }
    
    private double subtractWarChest(double moneySubtract)
    {
        return warChest = warChest + moneySubtract;
    }
    
    
    /**Returns a String representation of all ships in the reserve fleet
     * @return a String representation of all ships in the reserve fleet
     **/
    public String getReserveFleet()
    {   //assumes reserves is a Hashmap
    if (reserveFleet.isEmpty()) {
        return "No ships";
    }
    
    StringBuilder sb = new StringBuilder();
    for (Ship ship : reserveFleet.values()) {
        sb.append(ship.toString()).append("\n");
    }
    return sb.toString();
    }
    
    /**Returns a String representation of the ships in the admiral's squadron
     * or the message "No ships commissioned"
     * @return a String representation of the ships in the admiral's fleet
     **/
    public String getSquadron()
    {
        // If the squadron is empty, return "No ships"
        if (squadron.isEmpty()) {
            return "No ships";
        } else {
            StringBuilder sb = new StringBuilder();
            for (Map.Entry<String, Ship> entry : squadron.entrySet()) {
                sb.append(entry.getKey()).append(": ").append(entry.getValue().toString()).append("\n");
            }
            return sb.toString();  // Return the squadron details
        }
    }

    /**Returns a String representation of the ships sunk (or "no ships sunk yet")
     * @return a String representation of the ships sunk
     **/
    public String getSunkShips()
    {
    StringBuilder sunkShips = new StringBuilder();
    
    // Check squadron
    for (Ship ship : squadron.values()) {
        if (ship.isSunk()) {
            if (!sunkShips.isEmpty()) {
                sunkShips.append(", ");
            }
            sunkShips.append(ship.getShipName());
        }
    }
    
    // Check reserve fleet
    for (Ship ship : reserveFleet.values()) {
        if (ship.isSunk()) {
            if (!sunkShips.isEmpty()) {
                sunkShips.append(", ");
            }
            sunkShips.append(ship.getShipName());
        }
    }
    
    return sunkShips.isEmpty() 
        ? "No ships sunk yet." 
        : "Sunk ships: " + sunkShips.toString();
}
    
    /**Returns a String representation of the all ships in the game
     * including their status
     * @return a String representation of the ships in the game
     **/
    public String getAllShips()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("Reserve Fleet:\n");
        for (Map.Entry<String, Ship> entry : reserveFleet.entrySet()) {
            sb.append(entry.getKey()).append(": ").append(entry.getValue().toString()).append("\n");
        }
        sb.append("\nSquadron:\n");
        for (Map.Entry<String, Ship> entry : squadron.entrySet()) {
            sb.append(entry.getKey()).append(": ").append(entry.getValue().toString()).append("\n");
        }
        return sb.toString();
    }
    
    
    /** Returns details of any ship with the given name
     * @return details of any ship with the given name
     **/
    public String getShipDetails(String nme)
    {
      if (reserveFleet.containsKey(nme)) {
        return reserveFleet.get(nme).toString();
    } else if (squadron.containsKey(nme)) {
        return squadron.get(nme).toString();
    } else {
        return "Ship not found.";
    }
    }     
 
    // ***************** Fleet Ships ************************   
    /** Allows a ship to be commissioned to the admiral's squadron, if there 
     * is enough money in the War Chest for the commission fee.The ship's 
     * state is set to "active"
     * @param nme represents the name of the ship
     * @return "Ship commissioned" if ship is commissioned, "Not found" if 
     * ship not found, "Not available" if ship is not in the reserve fleet, "Not 
     * enough money" if not enough money in the warChest
     **/        
    public String commissionShip(String nme)
    {
        
    if(squadron.containsKey(nme) && !reserveFleet.containsKey(nme)){
        return "Not available";
        }
  
     // 1. Check if ship exists in reserve
    if (!reserveFleet.containsKey(nme)) {
        return "Not found";
        }
     // 2. Check if ship is already in squadron
        Ship ship = reserveFleet.get(nme);

        // 3. Check war chest balance
    if (warChest < ship.getCommissionFee()) {
            return "Not enough money";
        }
    

        warChest -= ship.getCommissionFee();
        ship.setState(ShipState.ACTIVE);
        reserveFleet.remove(nme);
        squadron.put(nme, ship);

        return "Ship commissioned";
        
    }

    /** Returns true if the ship with the name is in the admiral's squadron, false otherwise.
     * @param nme is the name of the ship
     * @return returns true if the ship with the name is in the admiral's squadron, false otherwise.
     **/
    public boolean isInSquadron(String nme)
    {
        if (squadron.containsKey(nme)){
            return true;
        }
        else{
        return false;
        }
    }
    
    /** Decommissions a ship from the squadron to the reserve fleet (if they are in the squadron)
     * pre-condition: isInSquadron(nme)
     * @param nme is the name of the ship
     * @return true if ship decommissioned, else false
     **/
    public boolean decommissionShip(String nme)
     {
         // 1. Check if ship is in squadron
    if (!squadron.containsKey(nme)) {
        return false;
    }
    
    Ship ship = squadron.get(nme);
    
    // 2. Can't decommission sunk ships
    if (ship.isSunk()) {
        return false;
    }
    
    // Perform decommissioning
    ship.setState(ShipState.RESERVE);
    squadron.remove(nme);
    reserveFleet.put(nme, ship);
    warChest += (ship.getCommissionFee() / 2); // Refund half fee
    return true;
    }
    
  
    /**Restores a ship to the squadron by setting their state to ACTIVE 
     * @param ref the name of the ship to be restored
     */
    public void restoreShip(String ref)
    {
    Ship ship = reserveFleet.get(ref);  // Only check reserve fleet
    if (ship != null && (ship.isResting() || ship.isInReserve())) {
        ship.setState(ShipState.ACTIVE);
        reserveFleet.remove(ref);
        squadron.put(ref, ship);
    }
    }
    
//**********************Encounters************************* 
    /** returns true if the number represents a encounter
     * @param num is the reference number of the encounter
     * @returns true if the reference number represents a encounter, else false
     **/
     public boolean isEncounter(int num)
     {
         if (encounters.containsKey(num)){
            return true;
        };
         return false;
     }
     
     
/** Retrieves the encounter represented by the encounter 
      * number.Finds a ship from the fleet which can fight the 
      * encounter.The results of fighting an encounter will be 
      * one of the following: 
      * 0-Encounter won by...(ship reference and name)-add prize money to War 
      * Chest and set ship's state to RESTING,  
      * 1-Encounter lost as no ship available - deduct prize from the War Chest,
      * 2-Encounter lost on battle skill and (ship name) sunk" - deduct prize 
      * from War Chest and set ship state to SUNK.
      * If an encounter is lost and admiral is completely defeated because there 
      * are no ships to decommission,add "You have been defeated " to message, 
      * -1 No such encounter
      * Ensure that the state of the war chest is also included in the return message.
      * @param encNo is the number of the encounter
      * @return a String showing the result of fighting the encounter
      */ 
    public String fightEncounter(int encNo)
    { 
        Encounter encounter = encounters.get(encNo);
        if (encounter == null){
             return "-1 - No such encounter. \nWar chest: " + warChest; // encounter not found
            }
             
        Ship chosenShip = null;
         
        for (Ship ship : squadron.values()){
             if (canFightEncounter(ship.getShipName(), encNo)){ //Returns first capable ship found
                 chosenShip = ship; 
                 break;
             }
        }
             
         if (chosenShip == null){
             warChest = warChest - encounter.getPrizeMoney();
             return "1 - Encounter lost as no ship available. \nWar Chest: " + warChest;
         }
         
         String chosenShipName = chosenShip.getShipName();
         if (shipIsStronger(chosenShipName, encNo)){
             warChest = warChest + encounter.getPrizeMoney();
             chosenShip.setState(ShipState.RESTING);
             squadron.remove(chosenShipName); 
             reserveFleet.put(chosenShipName, chosenShip); 
             return "0 - Encounter won by " + chosenShipName + ". \nWar Chest: " + warChest;
         }
         
         else {
                 warChest -= encounter.getPrizeMoney();
                 chosenShip.setState(ShipState.SUNK);
                 reserveFleet.put(chosenShipName, chosenShip);  // Move to reserve
                 squadron.remove(chosenShipName);
             
             if (squadron.isEmpty()){
                 return "2 - Encounter lost on battle skill and " + chosenShipName +
                         " sunk. You have been defeated. \nWar Chest: " + warChest;
             }
             return "2 - Encounter lost on battle skill and " + chosenShipName +
                         " sunk. \nWar Chest: " + warChest;
         }
    }
    
    
    public boolean canFightEncounter(String shipname, int eNo) {
        Ship ship = getShip(shipname);
        Encounter encounter = encounters.get(eNo);

        if(ship == null||encounter == null){
            return false; // Ship or encounter does not exist
        }

        if(!ship.isActive()){
            return false; // Ship must be active to fight
        }
        
        EncounterType encounterType = encounter.getEncounterType();
        if(encounterType == EncounterType.BLOCKADE){
            return ship instanceof ManOWar || (ship instanceof Frigate && ((Frigate) ship).getPinnanceOrDoctor());
        }
        if(encounterType == EncounterType.BATTLE){
            return (ship instanceof ManOWar || ship instanceof Frigate ||ship instanceof Sloop);
        }
        if(encounterType == EncounterType.SKIRMISH){
            return ship instanceof Frigate || ship instanceof Sloop;
        }

        return false;
    }

    
    private boolean shipIsStronger(String shipnme, int encNo){
        Ship ship = getShip(shipnme);
        if (ship == null){
            return false; // ship not found
        }
        Encounter encounter = encounters.get(encNo);
        
        if (encounter == null) {
            return false;
        }
        return ship.getBattleSkill() >= encounter.getSkillRequired();
    }

    
    private Ship getShip(String shipName) {
        if (reserveFleet.containsKey(shipName)) {
            return reserveFleet.get(shipName);
        }
        return squadron.get(shipName); // Returns null if not found
    }

    /** Provides a String representation of an encounter given by 
     * the encounter number
     * @param num the number of the encounter
     * @return returns a String representation of a encounter given by 
     * the encounter number
     **/
    public String getEncounter(int num)
    {
        if (encounters.containsKey(num)){
            return encounters.get(num).toString();
        }
        else {
            return "No such encounter";
        }

    }
    
    /** Provides a String representation of all encounters 
     * @return returns a String representation of all encounters
     **/
    public String getAllEncounters()
    {
    if (encounters.isEmpty()) {
        return "No encounters";
    }

    StringBuilder sb = new StringBuilder();
    for (Encounter e : encounters.values()) {
        List<String> parts = List.of(
            String.valueOf(e.getEncounterNumber()),
            e.getEncounterType().toString(),
            e.getLocation(),
            String.valueOf(e.getSkillRequired()),
            String.valueOf(e.getPrizeMoney())
        );
        sb.append(String.join(", ", parts)).append("\n");
    }
    return sb.toString().trim();
    }
    

    //****************** private methods for Task 4 functionality*******************
    //*******************************************************************************
     private void setupShips()
     {
        reserveFleet.put("Victory", new ManOWar("Victory", "Alan Aikin", 3, 500, 3, 30));
        reserveFleet.put("Sophie", new Frigate("Sophie", "Ben Baggins", 8, 160, 16, true));
        reserveFleet.put("Endeavour", new ManOWar("Endeavour", "Col Cannon", 4, 300, 2, 20));
        reserveFleet.put("Arrow", new Sloop("Arrow", "Dan Dare", 5, 150, true));
        reserveFleet.put("Belerophon", new ManOWar("Belerophon", "Ed Evans", 8, 500, 3, 50));
        reserveFleet.put("Surprise", new Frigate("Surprise", "Fred Fox", 6, 100, 10, false));
        reserveFleet.put("Jupiter", new Frigate("Jupiter", "Gil Gamage", 7, 200, 20, false));
        reserveFleet.put("Paris", new Sloop("Paris", "Hal Henry", 5, 200, true));
        reserveFleet.put("Beast", new Sloop("Beast", "Ian Idle", 5, 400, false));
        reserveFleet.put("Athena", new Sloop("Athena", "John Jones", 5, 100, true));

     }
     
    private void setupEncounters()
    {
        encounters.put(1, new Encounter(1, EncounterType.BATTLE, "Trafalgar", 3, 300));
        encounters.put(2, new Encounter(2, EncounterType.SKIRMISH, "Belle Isle", 3, 120));
        encounters.put(3, new Encounter(3, EncounterType.BLOCKADE, "Brest", 3, 150));
        encounters.put(4, new Encounter(4, EncounterType.BATTLE, "St Malo", 9, 200));
        encounters.put(5, new Encounter(5, EncounterType.BLOCKADE, "Dieppe", 7, 90));
        encounters.put(6, new Encounter(6, EncounterType.SKIRMISH, "Jersey", 8, 45));
        encounters.put(7, new Encounter(7, EncounterType.BLOCKADE, "Nantes", 6, 130));
        encounters.put(8, new Encounter(8, EncounterType.BATTLE, "Finisterre", 4, 100));
        encounters.put(9, new Encounter(9, EncounterType.SKIRMISH, "Biscay", 5, 200));
        encounters.put(10, new Encounter(10, EncounterType.BATTLE, "Cadiz", 1, 250));
  
    }
        
    // Useful private methods to "get" objects from collections/maps

    //*******************************************************************************
    //*******************************************************************************
  
    /************************ Task 3 ************************************************/

    
    //******************************** Task 3.5 **********************************
    /** reads data about encounters from a text file and stores in collection of 
     * encounters.Data in the file is editable
     * @param filename name of the file to be read
     */
    public void readEncounters(String filename) {
    BufferedReader reader = null;
    try {
        reader = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = reader.readLine()) != null) {
            line = line.trim();
            if (line.isEmpty() || line.startsWith("#")) {
                continue;
            }

            String[] parts = line.split("\\|");
            if (parts.length == 5) {
                int id = Integer.parseInt(parts[0].trim());
                EncounterType type = EncounterType.valueOf(parts[1].trim().toUpperCase());
                String name = parts[2].trim();
                int skill = Integer.parseInt(parts[3].trim());
                int prize = Integer.parseInt(parts[4].trim());

                encounters.put(id, new Encounter(id, type, name, skill, prize));
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        if (reader != null) {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
 
    // ***************   file write/read  *********************
    /** Writes whole game to the specified file
     * @param fname name of file storing requests
     */
public void saveGame(String fname) throws IOException {
    // Verify we can write to the file first
    File file = new File(fname);
    if (file.exists() && !file.canWrite()) {
        throw new IOException("Cannot write to file: " + fname);
    }

    try (ObjectOutputStream oos = new ObjectOutputStream(
            new FileOutputStream(file))) {
        oos.writeObject(this);
    }
}

    
    /** reads all information about the game from the specified file 
     * and returns 
     * @param fname name of file storing the game
     * @return the game (as an SeaBattles object)
     */
    // In SeaBattles.java:
    public SeaBattles loadGame(String fname) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(fname))) {
            return (SeaBattles) ois.readObject();
        }
    }
    
}   







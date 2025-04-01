package wars;

import java.util.*;
import java.io.*;
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
        
        return "null";
    }
    
    
    /** returns true if War Chest <=0 and the admiral's squadron has no ships which 
     * can be retired. 
     * @returns true if War Chest <=0 and the admiral's fleet has no ships 
     * which can be retired. 
     */
    public boolean isDefeated()
    {
        return false;
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
        if (reserveFleet.entrySet() != null) {  
            StringBuilder sb = new StringBuilder();
            for (Map.Entry<String, Ship> entry : reserveFleet.entrySet()) {
                sb.append(entry.getKey()).append(": ").append(entry.getValue().toString()).append("\n");
            }
            return sb.toString();
        }
        else {
            return "Ship not found";
        }
 
    
    }
    
    /**Returns a String representation of the ships in the admiral's squadron
     * or the message "No ships commissioned"
     * @return a String representation of the ships in the admiral's fleet
     **/
    public String getSquadron()
    {
        if (squadron.entrySet() != null){
            StringBuilder sb = new StringBuilder();
            for (Map.Entry<String, Ship> entry : squadron.entrySet()) {
                sb.append(entry.getKey()).append(": ").append(entry.getValue().toString()).append("\n");
            }
            return sb.toString();
        }
        else {
            return "Ship not found";
        }
    }
    
    /**Returns a String representation of the ships sunk (or "no ships sunk yet")
     * @return a String representation of the ships sunk
     **/
    public String getSunkShips()
    {
       
        return "No ships";
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
     //1. Check if ship exists in reserve fleet
    if (!reserveFleet.containsKey(nme)) {
        return "Not found";
    } 
    
    // Get the ship instance from reserve fleet
    Ship shipToCommission = reserveFleet.get(nme);
    
    //2. Check if ship is already in squadron
    if (squadron.containsKey(nme)) {
        return "Not available";
    }
    // 3. Check if already active
    if (shipToCommission.isActive()) {
        return "Not available - already active";
    }
    //4. Check if there's enough money to commission the ship
    if (warChest < shipToCommission.getCommissionFee()) {
        return "Not enough money";
    }
        // Deduct commission fee from war chest
        warChest -= shipToCommission.getCommissionFee();
        
        // Change ship status to active
        shipToCommission.setStatus(Ship.ACTIVE);
        
        // Move ship from reserve to squadron
        reserveFleet.remove(nme);
        squadron.put(nme, shipToCommission);
        
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
        Ship shipToDecommission = reserveFleet.get(nme);
        
    // 1. Check if ship is in active squadron
    if (shipToDecommission.isActive()) {
        return "Not available - already active";
    
    // 2. Get the ship instance
    Ship ship = squadron.get(shipName);
    
    // 3. Perform decommissioning
    ship.setStatus(Ship.DEACTIVATED);    // Set to deactivated
    squadron.remove(shipName);           // Remove from active
    reserveFleet.put(shipName, ship);    // Add to reserves
    warChest += (ship.getCommissionFee() / 2);  // Refund half fee
    
    return true;
}
    }
    
  
    /**Restores a ship to the squadron by setting their state to ACTIVE 
     * @param ref the name of the ship to be restored
     */
    public void restoreShip(String ref)
    {
  
        
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
       
            
        return "Not done";
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
        StringBuilder sb = new StringBuilder();
        sb.append("\nEncounters: \n");
        for (Map.Entry<Integer, Encounter> entry : encounters.entrySet()) {
            sb.append(entry.getKey()).append(": ").append(entry.getValue().toString()).append("\n");
        }
        return "No encounters";
    }
    
    

    //****************** private methods for Task 4 functionality*******************
    //*******************************************************************************
     private void setupShips()
     {
        reserveFleet.put("Victory", new ManOWar("Victory", "Alan Aikin", 3, 500, 3, 30));
        reserveFleet.put("Sophie", new Frigate("Sophie", "Ben Baggins", 8, 160, 16, true));
        reserveFleet.put("Endeavour", new ManOWar("Endeavour", "Col Cannon", 4, 500, 2, 20));
        reserveFleet.put("Arrow", new Sloop("Arrow", "Dan Dare", 5, 150, true));
        reserveFleet.put("Belerophon", new ManOWar("Belerophon", "Ed Evans", 8, 900, 3, 50));
        reserveFleet.put("Surprise", new Frigate("Surprise", "Fred Fox", 6, 300, 10, false));
        reserveFleet.put("Jupiter", new Frigate("Jupiter", "Gil Gamage", 7, 30, 20, false));
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
    public void readEncounters(String filename)
    { 
      
        
        
    }   
 
    
    // ***************   file write/read  *********************
    /** Writes whole game to the specified file
     * @param fname name of file storing requests
     */
    public void saveGame(String fname)
    {   // uses object serialisation 
           
    }
    
    /** reads all information about the game from the specified file 
     * and returns 
     * @param fname name of file storing the game
     * @return the game (as an SeaBattles object)
     */
    public SeaBattles loadGame(String fname)
    {   // uses object serialisation 
       
        return null;
    } 
    
 
}




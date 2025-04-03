package wars;

import java.io.*;
import java.util.*;
/**
 * Task 2 - provide command line interface
 * 
 * @author A.A.Marczyk
 * @version 16/02/25
 */
public class GameUI
{
    private BATHS myBattles ;
    private Scanner myIn = new Scanner(System.in);

    public void doMain() throws IOException, ClassNotFoundException
    {
        int choice;
        System.out.println("Enter admiral's name");
        String name = myIn.nextLine();
        myBattles = new SeaBattles(name); // create
        
        choice = 100;
        while (choice != 0 )
        {
            choice = getMenuItem();
            if (choice == 1)
            {
                System.out.println(myBattles.getReserveFleet());
            }
            else if (choice == 2)
            {
                System.out.println(myBattles.getSquadron());
            }
            else if (choice == 3)
            {
                System.out.println("Enter Ship name");
                myIn.nextLine();
                String ref = (myIn.nextLine()).trim();
                System.out.println(myBattles.getShipDetails(ref));
            } 
            else if (choice == 4)
            {
            System.out.println("Enter Ship name to commission:");
            myIn.nextLine(); 
            String shipName = (myIn.nextLine()).trim();
            String result = myBattles.commissionShip(shipName);
            System.out.println(result);
    
            // Show updated war chest and squadron
            System.out.println("\nUpdated Status:");
            System.out.println("War Chest: " + myBattles.getWarChest());
            System.out.println("Squadron: \n" + myBattles.getSquadron());
            }
            
            else if (choice == 5)
            {
       	       //write your code here
                System.out.println("Enter encounter number you wish to fight");
                int encounterNo = myIn.nextInt();
                System.out.println(myBattles.fightEncounter(encounterNo));
                  
            }
            else if (choice ==6)
            {
            System.out.println("\nEnter ship name to restore to active status:");
            myIn.nextLine();
            String shipName = myIn.nextLine().trim();

            myBattles.restoreShip(shipName);
            System.out.println("Successfully restored ");
            }

            else if (choice == 7)
            {
            System.out.println("Enter Ship name to decommission:");
            myIn.nextLine(); 
            String shipName = (myIn.nextLine()).trim();
            
            boolean success = myBattles.decommissionShip(shipName);
            System.out.println(success);
            }
            
            else if (choice == 9) // Task 7 only
            {
                System.out.println("Write to file");
                myBattles.saveGame("olenka.dat");
            }
            else if (choice == 10) // Task 7 only
            {
                System.out.println("Recommission from file");
                SeaBattles myBattles2=null;
                myBattles2 = myBattles.loadGame("olenka.dat");
                System.out.println(myBattles2.toString());               
            }  
        }
        System.out.println("Thank-you");
    }
    
    private int getMenuItem()
    {   int choice = 100;  
        System.out.println("Main Menu");
        System.out.println("0. Quit");
        System.out.println("1. List ships in the reserve fleet");
        System.out.println("2. List ships in admirals squadron"); 
        System.out.println("3. View a ship");
        System.out.println("4. Commission a ship into admiral's squadron");
        System.out.println("5. Fight an encounter");
        System.out.println("6. Restore a ship");
        System.out.println("7. Decommission a ship");
        System.out.println("8. View admiral's state");
        System.out.println("9. Save this game");
        System.out.println("10. Restore a game");
       
        
        while (choice < 0 || choice  > 10)
        {
            System.out.println("Enter the number of your choice");
            choice =  myIn.nextInt();
        }
        return choice;        
    } 
    
    public static void main(String[] args) throws IOException, ClassNotFoundException
    {
        GameUI xxx = new GameUI();
        xxx.doMain();
    }
}
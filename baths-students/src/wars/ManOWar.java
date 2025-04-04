/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package wars;
import java.io.Serializable;
/**
 *
 * @author Pasha
 */

public class ManOWar extends Ship implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int marines;
    private int decks;
    
    public ManOWar (String name, String captain, int battleSkill, int commissionFee, int decks, int marines){
        super(name, captain, battleSkill, commissionFee);
        this.decks = decks;
        this.marines = marines;
    }
    
    public int getDecks(){
        return decks;
    }
    
    public int getMarines(){
        return marines;
    }
    
    @Override
    public String toString(){
        return super.toString() +
            " | Decks = " + decks +
            " | Marines = " + marines +
            " | Type = ManOWar";
    }
    
    
    
    
    
}

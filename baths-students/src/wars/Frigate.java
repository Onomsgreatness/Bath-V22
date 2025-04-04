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

public class Frigate extends Ship implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private boolean pinnanceOrDoctor;
    private int cannons;
    
    public Frigate (String name, String captain, int battleSkill, int commissionFee, int cannons, boolean pinnanceOrDoctor){
        super(name, captain, battleSkill, commissionFee);
        this.pinnanceOrDoctor = pinnanceOrDoctor;
        this.cannons = cannons;
}
    
    public boolean getPinnanceOrDoctor(){
        return pinnanceOrDoctor;
    }
    
    public int getCannon(){
        return cannons;
    }
    
    @Override
    public String toString(){
        return super.toString() +
            " | Cannons = " + cannons +
            " | Pinnance/Doctor = " + pinnanceOrDoctor +
            " | Type = Frigate";
    }
    
    
    
    
}

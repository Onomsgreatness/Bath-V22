/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package wars;
import java.io.*;
/**
 *
 * @author Pasha
 */
public class Sloop extends Ship{
    
    private boolean pinnanceOrDoctor;
    
    public Sloop (String name, String captain, int battleSkill, int commissionFee, boolean pinnanceOrDoctor){
        super(name, captain, battleSkill, commissionFee);
        this.pinnanceOrDoctor = pinnanceOrDoctor;
        
}
    
    public boolean getPinnanceOrDoctor(){
        return pinnanceOrDoctor;
    }
    
    @Override
    public String toString(){
        return super.toString() +
            " | Pinnance/Doctor = " + pinnanceOrDoctor +
            " | Type = Sloop";
    } 
    
    
    
    
    
    
    
    
}

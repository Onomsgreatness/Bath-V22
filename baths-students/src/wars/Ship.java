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
public class Ship {
    private String name;
    private String captainName;
    private int battleSkill;
    private int commissionFee;
    
    public Ship (String name, String captain, int battleSkill, int commissionFee){
        this.name = name;
        this.captainName = captain;
        this.battleSkill = battleSkill;
        this.commissionFee = commissionFee;
    }
    
    public String getShipName(){
       return name;
    }
    
    public String getCaptainName(){
        return captainName;
    }
    
    public int getBattleSkill(){
        return battleSkill;
    }
    
    public int getCommissionFee(){
        return commissionFee;
    }
    
    
    public String toString(){
        return 
            "Ship Name = " + name +
            " | Captain Name = " + captainName +
            " | Battle Skill = " + battleSkill +
            " | Commission Fee = " + commissionFee;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
}



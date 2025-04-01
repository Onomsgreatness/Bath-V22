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
public abstract class Ship {
    private String name;
    private String captain;
    private int battleSkill;
    private int commissionFee;
    
    public Ship (String name, String captain, int battleSkill, int commissionFee){
        this.name = name;
        this.captain = captain;
        this.battleSkill = battleSkill;
        this.commissionFee = commissionFee;
    }
    
    public String getShipName(){
       return name;
    }
    
    public String getCaptainName(){
        return captain;
    }
    
    public int getBattleSkill(){
        return battleSkill;
    }
    
    public int getCommissionFee(){
        return commissionFee;
    }
    
    //Abstract method to be implemented by subclasses
    //public abstract void attack(); //Each shipType may implement differently
    
    public String toString(){
        return 
            "Ship Name = " + name +
            " | Captain Name = " + captain +
            " | Battle Skill = " + battleSkill +
            " | Commission Fee = " + commissionFee;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
}



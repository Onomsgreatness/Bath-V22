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
    private String captain;
    private int battleSkill;
    private int commissionFee;
    private boolean ShipStatus;
    private ShipState state;
    
    public static final boolean ACTIVE = true;
    public static final boolean DEACTIVATED = false;
    
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
    
    public void setState(ShipState newState) {
        this.state = newState;
    }
    
    public boolean isActive() {
        return state == ShipState.ACTIVE;
    }
    
    public boolean isInReserve() {
        return state == ShipState.RESERVE;
    }
    
    public boolean isResting() {
        return state == ShipState.RESTING;
    }
    
    public boolean isSunk() {
        return state == ShipState.SUNK;
    }
    
    
   public String toString(){
        return 
            "Ship Name = " + name +
            " | Captain Name = " + captain +
            " | Battle Skill = " + battleSkill +
            " | Commission Fee = " + commissionFee;
    }


    
}



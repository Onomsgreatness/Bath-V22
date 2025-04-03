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
public class Encounter {
    
    private int encounterNumber;
    private EncounterType type;
    private String location;
    private int skillRequired;
    private int prizeMoney;
    
    public Encounter(int encounterNumber, EncounterType type, String location, int skillRequired, int prizeMoney){
        this.encounterNumber = encounterNumber;
        this.type = type;
        this.location = location; 
        this.skillRequired = skillRequired;
        this.prizeMoney = prizeMoney;
    }
    
    public int getEncounterNumber(){
        return encounterNumber;
    }
    
    public EncounterType getEncounterType() {
        return type;
    }
    
    public String getLocation(){
        return location;
    }
    
    public int getSkillRequired(){
        return skillRequired;
    }
    
    public int getPrizeMoney(){
        return prizeMoney;
    }
    
    public String toString(){
        return
            "Encounter Number = " + encounterNumber +
            " |Encounter Type = " + type +
            " | Location = " + location +
            " | SKill Required = " + skillRequired +
            " | Prize Money = " + prizeMoney;
    }
    
    
    
    
}

package wars; 


/**
 * Details of your team
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Teamwork
{
    private String[] details = new String[12];
    
    public Teamwork()
    {   // in each line replace the contents of the String 
        // with the details of your team member
        // Please list the member details alphabetically by surname 
        // i.e. the surname of member1 should come alphabetically 
        // before the surname of member 2...etc
        details[0] = "team number";
        
        details[1] = "Abuku";
        details[2] = "Onome";
        details[3] = "21092431";

        details[4] = "Adeniyi";
        details[5] = "Adebola";
        details[6] = "22086761";

        details[7] = "Animashaun";
        details[8] = "Ayanfeoluwa";
        details[9] = "22102944";


        details[10] = "Hosseini";
        details[11] = "Pasha";
        details[12] = "22069104";

	
	   // only if applicable
        details[13] = "surname of member5";
        details[14] = "first name of member5";
        details[15] = "SRN of member5";


    }
    
    public String[] getTeamDetails()
    {
        return details;
    }
    
    public void displayDetails()
    {
        for(String temp:details)
        {
            System.out.println(temp.toString());
        }
    }
}
        

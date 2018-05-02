package application;
import java.io.*;
import java.util.*;

///////////////////////////////////////////////////////////////////////////////
//
//Class File:       SetUp.java
//Semester:         Spring 2018
//
//Author:           Yaakov Levin, Anthony Leung, Sharon Lin, Ben Lewis
//Credits:          none
//
/////////////////////////////////////////////////////////////////////////////////

/*
 *  This class manages the basic set up of the program. It reads the input file, get all the team
 *  names and ordered the teams based on their ranks 
 * 
 */
public class SetUp {
    // Name of the file
    private String filename;
    // Ordered team array based on their ranks
    protected Team[] team; 
    
    /**
     * The constructor instantiates a SetUp instance. It reads file and order the team.
     * 
     * @param: filename is the name of the file 
     **/
    public SetUp(String filename) {
        this.filename = filename;
        String[] names = readFile();
        team = createTeams(names);
        // This is the file ordered Team array 
        team = orderTeams(team);
    }
    
    /**
     * This methods read the input team file. It gets all the team on each line and ranks them 
     * in terms of their order.
     * 
     **/
    public String[] readFile() {
        File file = null;
        Scanner scnr = null;
        ArrayList<String> teamNameList = null;
        String[] nameArr = new String[] {};
        
        try {
            // Open and read the file
            file = new File(this.filename);
            scnr = new Scanner(file);
            teamNameList = new ArrayList<String>();
            
            while(scnr.hasNextLine()) {
                String team = scnr.nextLine();
                // Put each team name (string) into the string ArrayList
                teamNameList.add(team);
            }
            
            // Check if the number of team is legal (i.e. 0,1,2,4,8,16)
            if (!legitSize(teamNameList.size())) {
                throw new IllegalArgumentException();
            }
            // Convert the ArrayList into array
            nameArr = teamNameList.toArray(nameArr);
        }
        catch (Exception e) {
            if (e instanceof FileNotFoundException) {
                System.out.println("File" + filename + " is not found.");            
            }
            else if (e instanceof IllegalArgumentException) {
                System.out.println("Number of team has to be 0,2,4,8 or 16");
            }
            else { 
                e.printStackTrace();
            }
            System.exit(0);

        }
        finally {
            scnr.close();
        }
        return nameArr;
    }
    
    /**
     *  This method instantiates team objects using the team name in nameArr generated from
     *  the last method.
     * 
     * @param nameArr
     * @return Team[] is the array of Team objects 
     */
    public Team[] createTeams(String[] nameArr) {
        ArrayList<Team> teamList = new ArrayList<Team>();
        Team[] teamArr = new Team[] {};
        
        for(int i = 0; i < nameArr.length; i++) {
            // Instantiate a new Team object and put them in the unordered team array
            teamList.add(new Team(i,nameArr[i]));
        }
        // Convert the team array into an ArrayList
        teamArr = teamList.toArray(teamArr);
        
        return teamArr;
    }
    
    /**
     *  This method orders the team base on their rank and seed them in the correct order. 
     * 
     * @param teamArr is the ordered team array generated from the last method.
     * @return
     */
    public Team[] orderTeams(Team[] teamArr) {
        // This is the match order 
        int[] seedFour = new int[] {1, 4, 2, 3};
        int[] seedEight = new int[] {1, 8, 4, 5, 2, 7, 3, 6};
        int[] seedSixteen = new int[] {1, 16, 8, 9, 4, 13, 5, 12, 2, 15, 7, 10, 3, 14, 6, 11};
        Team[] ordered = new Team[teamArr.length];
        
        if (team.length == 0) { return new Team[] {}; }
        
        if (team.length == 1 || team.length == 2) { return teamArr;}
        
        if (team.length == 4){
            for (int i = 0; i < seedFour.length; i++) {
                ordered[i] = teamArr[seedFour[i]-1];
            }
            return ordered;
        }       
        if (team.length == 8){
            for (int i = 0; i < seedEight.length; i++) {
                ordered[i] = teamArr[seedEight[i]-1];
            }
            return ordered;
        } 
        else {
            for (int i = 0; i < seedSixteen.length; i++) {
                ordered[i] = teamArr[seedSixteen[i]-1];
            }
            return ordered;
        } 
    }
    /**
     * The method checks if the size is legal (i.e. 0,1,2,4,8,16)
     * 
     * @param size
     * @return true if legal, false if otherwise
     */
    private boolean legitSize(int size) {
        Set<Integer> legitTeamSize = new HashSet<Integer>();
        legitTeamSize.add(0);
        legitTeamSize.add(1);
        legitTeamSize.add(2);
        legitTeamSize.add(4);
        legitTeamSize.add(8);
        legitTeamSize.add(16);
        
        return legitTeamSize.contains(size);
    }
    /**
     * This method returns the ordered team array for the main class.
     * 
     * @return team is the ordered Team array
     */
    public Team[] getTeam() {
        return team;
    }  
}

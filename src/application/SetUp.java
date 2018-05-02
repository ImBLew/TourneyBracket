package application;
import java.io.*;
import java.util.*;


public class SetUp {
    private String name;
    protected Team[] team; 
    
    public SetUp(String name) {
        this.name = name;
        String[] names = readFile();
        team = createTeams(names);
        team = orderTeams(team);
    }
    
    public String[] readFile() {
        File file = null;
        Scanner scnr = null;
        ArrayList<String> teamNameList = null;
     
        String[] nameArr = new String[] {};
        try {
            file = new File(this.name);
            scnr = new Scanner(file);
            teamNameList = new ArrayList<String>();
            
            while(scnr.hasNextLine()) {
                String team = scnr.nextLine();
                teamNameList.add(team);
            }
            
            if (!legitSize(teamNameList.size())) {
                throw new IllegalArgumentException();
            }
            nameArr = teamNameList.toArray(nameArr);
        }
        catch (Exception e) {
            if (e instanceof FileNotFoundException) {
                System.out.println("File" + name + " is not found.");            
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
    
    public Team[] createTeams(String[] nameArr) {
        ArrayList<Team> teamList = new ArrayList<Team>();
        Team[] teamArr = new Team[] {};
        
        for(int i = 0; i < nameArr.length; i++) {
            teamList.add(new Team(i,nameArr[i]));
        }
        teamArr = teamList.toArray(teamArr);
        
        return teamArr;
    }
    
    public Team[] orderTeams(Team[] teamArr) {
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
    
    public Team[] getTeam() {
        return team;
    }
    
    
}

package application;
///////////////////////////////////////////////////////////////////////////////
//
//Class File:       Team.java
//Semester:         Spring 2018
//
//Author:           Yaakov Levin, Anthony Leung, Sharon Lin, Ben Lewis
//Credits:          none
//
/////////////////////////////////////////////////////////////////////////////////

/**
 * 
 * The Team class represents each team in the tournament
 *
 */
public class Team {
    int seed = 0; 
    String name = "";
    int score = 0;
    boolean isEliminated = false;
    
    /** 
     * The constructor instantiates a Team object 
     * @param seed
     * @param name
     */
    public Team(int seed, String name) {
        this.seed = seed;
        this.name = name; 
    }
    
    public int getSeed() {
        return seed;
    }

    public void setSeed(int seed) {
        this.seed = seed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean isEliminated() {
        return isEliminated;
    }

    public void setEliminated(boolean isEliminated) {
        this.isEliminated = isEliminated;
    }  
}

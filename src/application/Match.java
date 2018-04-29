package application;

import javafx.scene.control.Label;

public class Match {

    
    private int x, y;
    private int width, height;
    private Team team1, team2;
    private boolean isLeft;
    
    private Label team1Label;
    private Label team2Label;
    
    public Match(int x, int y, int width, int height, boolean isLeft) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.isLeft = isLeft;
        team1 = null;
        Label team1Label = new Label("TBD");
        team2 = null;
        Label team2Label = new Label("TBD");
    }
    
    public Match(int x, int y, int width, int height, boolean isLeft, Team team1, Team team2) {
        this(x,y,width,height,isLeft);
        this.team1 = team1;
        team1Label.setText(team1.getName());
        this.team2 = team2;
        team2Label.setText(team2.getName());
    }
    
    
}

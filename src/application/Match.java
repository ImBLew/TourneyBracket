package application;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class Match{

    private int x, y;
    private int width, height;
    private Team team1, team2;
    private boolean isLeft;
    
    private Label team1Label;
    private Label team2Label;
    
    private TextField team1TextField;
    private TextField team2TextField;
    
    private Button scoreButton;
    
    public Match(int x, int y, int width, int height, boolean isLeft) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.isLeft = isLeft;
        
        /* ******************** TEAM 1 ******************** */
        team1 = null;
        
        team1Label = new Label("TBD");
        team1Label.setLayoutX(x);
        team1Label.setMaxWidth(width/2.0);
        team1Label.setLayoutY(y);
        team1Label.setMaxHeight(height/3.0);
        
        team1TextField = new TextField();
        team1TextField.setPromptText("Score");
        team1TextField.setLayoutX((x + width)/2.0);
        team1TextField.setMaxWidth(width/2.0);
        team1TextField.setLayoutY(y);
        team1TextField.setMaxHeight(height/3.0);
        /* ******************** TEAM 2 ******************** */
        team2 = null;
        
        team2Label = new Label("TBD");
        team2Label.setLayoutX(x);
        team2Label.setMaxWidth(width/2.0);
        team2Label.setLayoutY(y + (y * 2)/3.0);
        team2Label.setMaxHeight(height/3.0);
        
        team2TextField = new TextField();
        team2TextField.setPromptText("Score");
        team2TextField.setLayoutX((x + width)/2.0);
        team2TextField.setMaxWidth(width/2.0);
        team2TextField.setLayoutY(y + (y * 2)/3.0);
        team2TextField.setMaxHeight(height/3.0);
        
        
        /* ***************** SCORE BUTTON ***************** */
        scoreButton = new Button();
        scoreButton.setText("Final Score");
        scoreButton.setLayoutX(x + x/3.0);
        scoreButton.setMaxWidth(width/3.0);
        scoreButton.setLayoutY(y + y/3.0);
        scoreButton.setMaxHeight(height/3.0);
        
        /* *************** IS LEFT HANDLING *************** */
        if (!isLeft) {
            double tmp = team1Label.getLayoutX();
            team1Label.setLayoutX(team1TextField.getLayoutX());
            team1TextField.setLayoutX(tmp);
            
            tmp = team2Label.getLayoutX();
            team2Label.setLayoutX(team2TextField.getLayoutX());
            team2TextField.setLayoutX(tmp);
        }
    }
    
    public Match(int x, int y, int width, int height, boolean isLeft, Team team1, Team team2) {
        this(x,y,width,height,isLeft);
        
        this.team1 = team1;
        team1Label.setText(team1.getName());
        this.team2 = team2;
        team2Label.setText(team2.getName());
    }
    
    public void addToLayout(GridPane grid) {
        grid.add(team1Label, (int)team1Label.getLayoutY(), (int)team1Label.getLayoutX());
        grid.add(team2Label, (int)team2Label.getLayoutY(), (int)team2Label.getLayoutX());
        grid.add(team1TextField, (int)team1TextField.getLayoutX(), (int)team1TextField.getLayoutY());
        grid.add(team2TextField, (int)team2TextField.getLayoutX(), (int)team2TextField.getLayoutY());
        grid.add(scoreButton, (int)scoreButton.getLayoutX(), (int)scoreButton.getLayoutY());
    }
}

package application;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class Match {

    private int x, y;
    private int width, height;
    private Team team1, team2;
    private boolean isLeft;

    private Label team1Label;
    private Label team2Label;

    private TextField team1TextField;
    private TextField team2TextField;

    private Button scoreButton;
    private boolean isActive;

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
        team1Label.setLayoutY(y);

        team1TextField = new TextField();
        team1TextField.setPromptText("Score");
        team1TextField.setLayoutX(x + (width / 2.0));
        team1TextField.setLayoutY(y);
        team1TextField.setMaxSize(60,10);
        /* ******************** TEAM 2 ******************** */
        team2 = null;

        team2Label = new Label("TBD");
        team2Label.setLayoutX(x);
        team2Label.setLayoutY(y + (height * 2) / 3.0);

        team2TextField = new TextField();
        team2TextField.setPromptText("Score");
        team2TextField.setLayoutX(x + (width / 2.0));
        team2TextField.setLayoutY(y + (height * 2) / 3.0);
        team2TextField.setMaxSize(60, 10);


        /* ***************** SCORE BUTTON ***************** */
        scoreButton = new Button();
        scoreButton.setText("Final Score");
        scoreButton.setLayoutX(x);
        scoreButton.setLayoutY(y + height / 3.0);
        scoreButton.setMaxSize(90, 10);

        /* *************** IS LEFT HANDLING *************** */
        if (!isLeft) {
            double tmp = team1Label.getLayoutX();
            team1Label.setLayoutX(team1TextField.getLayoutX());
            team1TextField.setLayoutX(tmp);

            tmp = team2Label.getLayoutX();
            team2Label.setLayoutX(team2TextField.getLayoutX());
            team2TextField.setLayoutX(tmp);
            
            scoreButton.setLayoutX(x + 1);
        }
        
        /* ********************IS ACTIVE ****************** */
        this.setActive(false);
    }

    public Match(int x, int y, int width, int height, boolean isLeft, Team team1, Team team2) {
        this(x, y, width, height, isLeft);

        this.team1 = team1;
        team1Label.setText(team1.getName());
        this.team2 = team2;
        team2Label.setText(team2.getName());
        
        this.setActive(true);
    }

    public void addToLayout(GridPane grid) {
        grid.add(team1Label, (int) team1Label.getLayoutX(), (int) team1Label.getLayoutY());
        grid.add(team2Label, (int) team2Label.getLayoutX(), (int) team2Label.getLayoutY());
        grid.add(team1TextField, (int) team1TextField.getLayoutX(),
                        (int) team1TextField.getLayoutY());
        grid.add(team2TextField, (int) team2TextField.getLayoutX(),
                        (int) team2TextField.getLayoutY());
        grid.add(scoreButton, (int) scoreButton.getLayoutX(), (int) scoreButton.getLayoutY());

    }
    
    public void setTeam1(Team team1) {
        this.team1 = team1;
        team1Label.setText(team1.getName());
        if (team2 != null) {
            this.setActive(true);
        }
    }
    
    public void setTeam2(Team team2) {
        this.team2 = team2;
        team2Label.setText(team2.getName());
        if (team1 != null) {
            this.setActive(true);
        }
    }
    public void setActive(boolean isActive) {
        this.isActive = isActive;
        
        if (isActive == true) {
            team1TextField.setEditable(true);
            team2TextField.setEditable(true);
        }else {
            team1TextField.setEditable(false);
            team2TextField.setEditable(false);
        }
    }
    
    public boolean getIsActive() {
        return isActive;
    }
    
}

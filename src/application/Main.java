package application;

import javafx.scene.input.MouseEvent;
import javafx.scene.control.Button;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
//package cs400;

import java.util.ArrayList;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
///////////////////////////////////////////////////////////////////////////////
//
//Class File:       Team.java
//Semester:         Spring 2018
//
//Author:           Yaakov Levin, Anthony Leung, Sharon Lin, Ben Lewis
//Credits:          none
//
/////////////////////////////////////////////////////////////////////////////////
public class Main extends Application {
	
	static SetUp setup;
	
	static ArrayList<Match> roundOne;
	static ArrayList<Match> quarterFinals;
	static ArrayList<Match> semiFinals;
	
	static Label finalLabel1;
	static Label finalLabel2;
	static Label championLabel;
	static Label secondLabel;
	static Label thirdLabel;
	static TextField finalTextField1;
	static TextField finalTextField2;
	static Label champion;
	static Button finalsButton;
	
	/**
	 * This method finalizes the all the matches
	 * 
	 * @param m is a match
	 */
	public static void matchFinished(Match m) {
        for (int i = 0; i < roundOne.size(); i++) {
            if (roundOne.get(i).equals(m)) {
                if (i%2 == 0) {
                    quarterFinals.get((int)Math.floor((double)(i/2))).setTeam1(m.getWinningTeam());
                }else {
                    quarterFinals.get((int)Math.floor((double)(i/2))).setTeam2(m.getWinningTeam());
                }
            }
        }
        for (int i = 0; i < quarterFinals.size(); i++) {
            if (quarterFinals.get(i).equals(m)) {
                if (i%2 == 0) {
                    semiFinals.get((int)Math.floor((double)(i/2))).setTeam1(m.getWinningTeam());
                }else {
                    semiFinals.get((int)Math.floor((double)(i/2))).setTeam2(m.getWinningTeam());
                }
            }
        }
        for (int i = 0; i < semiFinals.size(); i++) {
            if (semiFinals.get(i).equals(m)) {
                if (i%2 == 0) {
                    finalLabel1.setText(m.getWinningTeam().getName());
                    if (!finalLabel2.getText().equals("TBD")) {
                        activateFinals();
                    }
                }else {
                    finalLabel2.setText(m.getWinningTeam().getName());
                    if (!finalLabel1.getText().equals("TBD")) {
                        activateFinals();
                    }
                }
            }
        }
    }
    
    /**
     * This sets up the match between the two final teams. Different from a normal match in 
     * that the two teams are lined up horizontally not vertically. 
     * @param gp the gridPane object we put everything in
     * @param numTeams 
     */
    private static void setupFinals(GridPane gp, int numTeams) {
        //final 2 teams
        finalLabel1 = new Label("TBD");
        finalLabel2 = new Label("TBD");
        championLabel = new Label("");
        secondLabel = new Label("");
        thirdLabel = new Label("");

        
        
        //finals score input field for left side winner
        finalTextField1 = new TextField();
        finalTextField1.setEditable(false);
        finalTextField1.setMaxSize(60, 10);
        finalTextField1.setPromptText("Score");
        
        //finals score input field for rigth side winner
        finalTextField2 = new TextField();
        finalTextField2.setEditable(false);
        finalTextField2.setMaxSize(60, 10);
        finalTextField2.setPromptText("Score");
        
        //button used to submit the scores
        finalsButton = new Button("Submit");
        finalsButton.setMaxSize(80, 10);
        
        //this figures out what to do when user hits "submit"
        finalsButton.addEventHandler(MouseEvent.MOUSE_CLICKED, 
                        (evt) -> {
                            try { 
                                
                                int score1 = Integer.parseInt(finalTextField1.getText());
                                int score2 = Integer.parseInt(finalTextField2.getText());
                                
                                //checks if inputs are legal
                                if (score1 < 0 || score2 < 0 || score1 == score2) {
                                    throw new IllegalArgumentException();
                                }
                                
                                String champ = score1 > score2 ? finalLabel1.getText() : finalLabel2.getText();
                                String second = score1 > score2 ? finalLabel2.getText() : finalLabel1.getText();
                                
                                int lostScore1 = semiFinals.get(0).lostTeam.getScore();
                                int lostScore2 = semiFinals.get(1).lostTeam.getScore();
                                String third = lostScore1 > lostScore2 ? semiFinals.get(0).lostTeam.getName() : semiFinals.get(1).lostTeam.getName();
                                champion.setText(champ);
                                
                                championLabel.setText("Champion: " + champ);
                                secondLabel.setText("Second: " + second);
                                thirdLabel.setText("Third: " + third);
                                                      
                                finalTextField1.setEditable(false);
                                finalTextField2.setEditable(false);
                                      
                            }
                            catch (Exception ex) {
                                if (ex instanceof IllegalArgumentException || ex instanceof NumberFormatException) {
                                    finalTextField1.clear();
                                    finalTextField2.clear();
                                    System.out.println("Please enter only valid Integers as scores.");
                                }                   
                            }
                        });
        
        champion = new Label("TBD");
        
        //add all labels and buttons to the gridPane
        gp.add(championLabel, 9, 16);
        gp.add(secondLabel, 9, 17);
        gp.add(thirdLabel, 9, 18);
        gp.add(finalLabel1, 7, 6);
        gp.add(finalLabel2, 12, 6);
        gp.add(finalTextField1, 7, 7);
        gp.add(finalTextField2, 12, 7);
        gp.add(champion, 9, 7);
        gp.add(finalsButton, 9, 6);
        
    }
    
    /**
     * Activates the text fields for final match
     */
    private static void activateFinals() {
        finalTextField1.setEditable(true);
        finalTextField2.setEditable(true);
    }
    
    
	/**
	 * This method set ups the stage and scene
	 */
	@Override
	public void start(Stage primaryStage) {
		try {
			//we are using GridPane to format our GUI
			GridPane gridPane = new GridPane();
			//this gives us spacing between cells in GridPane
			gridPane.setVgap(20);
			gridPane.setHgap(20);
			
			Team[] teams = setup.getTeam();
			
			// Set up all labels needed
			int numTeam = teams.length;
			roundOne = new ArrayList<Match>();
            quarterFinals = new ArrayList<Match>();
            semiFinals = new ArrayList<Match>();

            
            // If there are 16 teams 
			if (numTeam == 16) {							
				//setup matches for first round
				for(int i = 0 ; i < 8; i++) {
					if(i < 4) {
						roundOne.add(new Match(0, 3*i, 2, 3, true, teams[i*2], teams[i*2+1]));
						roundOne.get(i).addToLayout(gridPane);
					}
					else {
						roundOne.add(new Match(19, (3*i)%12, 2, 3, false, teams[i*2], teams[i*2+1]));	
						roundOne.get(i).addToLayout(gridPane);
					}
				}
				
				//setup matches for quarterfinals
				for(int i = 0; i < 4; i++) {
					if(i < 2) {
						quarterFinals.add(new Match(3,i*6+1, 2, 6, true));
						quarterFinals.get(i).addToLayout(gridPane);
					}
					else {
						quarterFinals.add(new Match(16,(i*6+1)%12, 2, 6, false));
						quarterFinals.get(i).addToLayout(gridPane);
					}	
				}
				
				//setup matches for semifinals
				for(int i = 0; i < 2; i ++) {
					if(i < 1) {
						semiFinals.add(new Match(5,3,2,10,true));
						semiFinals.get(i).addToLayout(gridPane);
						semiFinals.get(i).activateSemifinal();
						
					}
					else {
						semiFinals.add(new Match(13,3,2,10,false));
						semiFinals.get(i).addToLayout(gridPane);
						semiFinals.get(i).activateSemifinal();
					}
				}
	            setupFinals(gridPane, numTeam);
				
				Label instruction = new Label("Enter scores into each boxes");
				gridPane.add(instruction, 2, 16,10,10);
				
				
			  	//final step to show scene
				Scene scene = new Scene(gridPane,1450,1450);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primaryStage.setScene(scene);
				primaryStage.show();
			}

			// If there are 8 teams
			if (numTeam == 8) {
				//set up first round of matches
				for(int i = 0 ; i < 4;i++) {
				    if(i < 2) {
                        quarterFinals.add(new Match(3,i*6+1, 2, 6, true, teams[i*2], teams[i*2+1]));
                        quarterFinals.get(i).addToLayout(gridPane);
                    }
                    else {
                        quarterFinals.add(new Match(16,(i*6+1)%12, 2, 6, false, teams[i*2], teams[i*2+1]));
                        quarterFinals.get(i).addToLayout(gridPane);
                    }
				}
				
				int numSemifinals = 2;
				//set up second round of matches
				for (int i = 0; i < numSemifinals; i++) {
				    if(i < 1) {
                        semiFinals.add(new Match(5,3,2,10,true));
                        semiFinals.get(i).addToLayout(gridPane);
                        semiFinals.get(i).activateSemifinal();
                    }
                    else {
                        semiFinals.add(new Match(13,3,2,10,false));
                        semiFinals.get(i).addToLayout(gridPane);
                        semiFinals.get(i).activateSemifinal();
                    }
				}
				setupFinals(gridPane, numTeam);
				
				Label instruction = new Label("Enter scores into each boxes");
				gridPane.add(instruction, 2, 16, 10, 10);

				//final step to show scene
				Scene scene = new Scene(gridPane, 1450, 1450);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primaryStage.setScene(scene);
				primaryStage.show();
			}
			
			// If there are only 4 teams
    		if(numTeam == 4) {
    			//set up first round matches
    			for(int i = 0 ; i < 2;i++) {
    			    if(i < 1) {
                        semiFinals.add(new Match(5,3,2,10,true, teams[i*2], teams[i*2+1]));
                        semiFinals.get(i).addToLayout(gridPane);
                    }
                    else {
                        semiFinals.add(new Match(13,3,2,10,false, teams[i*2], teams[i*2+1]));
                        semiFinals.get(i).addToLayout(gridPane);
                    }
    			}
    			
    			setupFinals(gridPane, numTeam);
    
    			Label instruction = new Label("Enter scores into each boxes");
    			gridPane.add(instruction, 3, 7, 10, 10);
    
    			//final step to show scene to user
    			Scene scene = new Scene(gridPane, 1450, 1450);
    			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
    			primaryStage.setScene(scene);
    			primaryStage.show();
    		}
    		
    		// If there are 2 teams
    		if(numTeam == 2) {
    		    //start with finals when only 2 teams
    		    setupFinals(gridPane, numTeam);
    		    
    		    finalLabel1.setText(teams[0].getName());
    		    finalLabel2.setText(teams[1].getName());
    		    
    		    activateFinals();
    			
    			Label instruction = new Label("Enter scores into each boxes");
    			gridPane.add(instruction, 2, 5, 10, 10);
    
    			Scene scene = new Scene(gridPane, 1450, 1450);
    			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
    			primaryStage.setScene(scene);
    			primaryStage.show();
    		}
    		
    		if(numTeam == 1) {
    			//if only 1 team just display the team name
    			Label Champ = new Label("No competition, " + teams[0].getName() + " is the winner");
    			gridPane.add(Champ, 0, 0);
    			Scene scene = new Scene(gridPane, 1450, 1450);
    			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
    			primaryStage.setScene(scene);
    			primaryStage.show();
    		}
    
    		if(numTeam == 0) {
    			Label noWinner = new Label("There is no winner with no teams");
    			gridPane.add(noWinner, 0, 0);
    			Scene scene = new Scene(gridPane, 1450, 1450);
    			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
    			primaryStage.setScene(scene);
    			primaryStage.show();
    		}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
	        String filename = args[0];
	        setup = new SetUp(filename);
		launch(args);
	}
}


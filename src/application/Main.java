package application;

import javafx.scene.control.Button;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
//package cs400;

import java.io.File;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Scanner;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Main extends Application {
	
	static SetUp setup;
	
	static ArrayList<Match> roundOne;
	static ArrayList<Match> quarterFinals;
	static ArrayList<Match> semiFinals;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			GridPane gridPane = new GridPane();
			gridPane.setVgap(20);
			gridPane.setHgap(20);
			
			Team[] teams = setup.getTeam();
			
			// Set up all labels needed
			int numTeam = teams.length;
			if (numTeam == 16) {			
//				int numQuaterfinalist = 8;
//				int numSemifinalist = 4;
//				ArrayList<Label> quaterfinalist = new ArrayList<Label>();
				ArrayList<Label> semifinalist = new ArrayList<Label>();
				roundOne = new ArrayList<Match>();
				quarterFinals = new ArrayList<Match>();
				semiFinals = new ArrayList<Match>();
				
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
					}
					else {
						semiFinals.add(new Match(13,3,2,10,false));
						semiFinals.get(i).addToLayout(gridPane);
					}
				}

		 
	            // Set up label for all finalists and champion
				Label final1 = new Label("finalist1");
				Label final2 = new Label("finalist2");
				Label champion = new Label("Champion");
				

				//Display finalists and champion
				gridPane.add(final1, 7, 6);
				gridPane.add(final2, 12, 6);
				gridPane.add(champion, 9, 7);

				

				Label instruction = new Label("Enter scores into each boxes");
				gridPane.add(instruction, 2, 16,10,10);
				
				
			  	
				Scene scene = new Scene(gridPane,1450,1450);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primaryStage.setScene(scene);
				primaryStage.show();
			}

			
			if (numTeam == 8) {
				// Set up label for all teams
				
				ArrayList<Match> roundOne = new ArrayList<Match>();
				
				for(int i = 0 ; i < 4;i++) {
					if(i < 2) {
						roundOne.add(new Match(0, 3*i, 2, 3, true, teams[i*2], teams[i*2+1]));
						roundOne.get(i).addToLayout(gridPane);
					}
					else {
						roundOne.add(new Match(16, (3*i)%6, 2, 3, false, teams[i*2], teams[i*2+1]));	
						roundOne.get(i).addToLayout(gridPane);
					}
				}
				
				ArrayList<Label> semifinalist = new ArrayList<Label>();

				
				int numSemifinalist = 4;

				for (int i = 0; i < numSemifinalist; i++) {
					semifinalist.add(new Label());
					semifinalist.get(i).setText("Semifinalist" + (i + 1));
				}

				// Set up label for all finalists and champion
				Label final1 = new Label("finalist1");
				Label final2 = new Label("finalist2");
				Label champion = new Label("Champion");
				

				
				// Display semifinalists
				for (int i = 0, row = 1; i < semifinalist.size() / 2; i++, row += 4) {
					gridPane.add(semifinalist.get(i), 2, row);
				}
				for (int i = semifinalist.size() / 2, row = 1; i < semifinalist.size(); i++, row += 4) {
					gridPane.add(semifinalist.get(i), 13, row);
				}
				
				// Display finalists and champion
				gridPane.add(final1, 4, 3);
				gridPane.add(final2, 11, 3);
				gridPane.add(champion, 9, 4);
				
				// Set up for input textbox

				ArrayList<Label> scoreLabels = new ArrayList<Label>();
				for (int i = 0; i < 35; i++) {
					scoreLabels.add(new Label("Score:"));
				}


				//add score labels for final 4
				for (int i = 0, row = 1; i < 2; i++, row += 4) {
					gridPane.add(scoreLabels.get(i), 3, row);
				}
				for (int i = 2, row = 1; i < 4; i++, row += 4) {
					gridPane.add(scoreLabels.get(i), 12, row);
				}
				//add score labels for finalists
				for (int i = 4, row = 3; i < 5; i++, row += 8) {
					gridPane.add(scoreLabels.get(i), 5, row);
				}
				for (int i = 5, row = 3; i < 6; i++, row += 8) {
					gridPane.add(scoreLabels.get(i), 10, row);
				}
			
				Label instruction = new Label("Enter scores into each boxes");
				gridPane.add(instruction, 2, 16, 10, 10);

				Button submit = new Button("Submit scores");
				gridPane.add(submit, 9, 15);
				Scene scene = new Scene(gridPane, 1450, 1450);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primaryStage.setScene(scene);
				primaryStage.show();
			}
			
		if(numTeam == 4) {
			
			ArrayList<Match> roundOne = new ArrayList<Match>();
			
			for(int i = 0 ; i < 2;i++) {
				if(i < 1) {
					roundOne.add(new Match(0, 3*i, 2, 3, true, teams[i*2], teams[i*2+1]));
					roundOne.get(i).addToLayout(gridPane);
				}
				else {
					roundOne.add(new Match(12, (3*i)%3, 2, 3, false, teams[i*2], teams[i*2+1]));	
					roundOne.get(i).addToLayout(gridPane);
				}
			}
			
			ArrayList<Label> semifinalist = new ArrayList<Label>();
			
			int numSemifinalist = 2;
			for (int i = 0; i < numSemifinalist; i++) {
				semifinalist.add(new Label());
				semifinalist.get(i).setText("Semifinalist" + (i + 1));
			}

			// Set up label for all finalists and champion
			Label final1 = new Label("finalist1");
			Label final2 = new Label("finalist2");
			Label champion = new Label("Champion");
			

			gridPane.add(final1, 3, 1);
			gridPane.add(final2, 9, 1);
			gridPane.add(champion, 5, 2);
			
			

			ArrayList<Label> scoreLabels = new ArrayList<Label>();
			for (int i = 0; i < 10; i++) {
				scoreLabels.add(new Label("Score:"));
			}
				


			gridPane.add(scoreLabels.get(0), 4, 1);
     		gridPane.add(scoreLabels.get(1), 8, 1);
			
			
			
			Label instruction = new Label("Enter scores into each boxes");
			gridPane.add(instruction, 3, 5, 10, 10);



			Scene scene = new Scene(gridPane, 1450, 1450);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		}
		if(numTeam == 2) {
			
			ArrayList<Label> teamLabel = new ArrayList<Label>();
			teamLabel.add(new Label());
			teamLabel.get(0).setText("Team1");
			teamLabel.add(new Label());
			teamLabel.get(1).setText("Team2");
			
			gridPane.add(teamLabel.get(0), 0, 0);
			gridPane.add(teamLabel.get(1), 4, 0);
			
			TextField score1 = new TextField();
			score1.setPromptText("Score:");
			score1.setMaxSize(60, 10);
			TextField score2 = new TextField();
			score2.setPromptText("Score:");
			score2.setMaxSize(60, 10);
			
			gridPane.add(score1, 1, 0);
			gridPane.add(score2, 3, 0);
			
			Label Champion = new Label("Champion");
			gridPane.add(Champion, 2, 1);

			
			Label instruction = new Label("Enter scores into each boxes");
			gridPane.add(instruction, 2, 4, 10, 10);

			Button submit = new Button("Submit scores");
			gridPane.add(submit, 1, 3);
			Scene scene = new Scene(gridPane, 700, 400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		}
		if(numTeam == 1) {
			Label Champ = new Label("Champion");
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
                    //Finals set team 1
                    System.out.println("Finals team 1 = " + m.getWinningTeam().getName());
                }else {
                    //Finals set team 2
                    System.out.println("Finals team 2 = " + m.getWinningTeam().getName());
                }
            }
        }
	}
}


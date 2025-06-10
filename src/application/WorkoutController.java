package application;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.fasterxml.jackson.databind.ObjectMapper;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class WorkoutController {

	private Stage stage;
	private Scene scene;
	@FXML
	private VBox nav;
	@FXML
	private AnchorPane content;
	@FXML
	private VBox metadata;
	
	@FXML
	public void initialize() {
		File folder = new File("src/Workouts");
		//System.out.println(folder.isDirectory());
		File[] files = folder.listFiles();
		if(files != null) {
			for(File f : files) {
				String fileName = f.getName();
				String output = fileName.substring(0, fileName.length() - 5);
				//System.out.println(output);
				//add this to the nav menu
				Label workoutLabel = new Label(output);
				workoutLabel.setFont(Font.font(25.0));
				workoutLabel.setCursor(Cursor.HAND);
				workoutLabel.setOnMouseClicked(event -> {
					try {
						OpenWorkout(fileName);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				});
				nav.getChildren().add(workoutLabel);
			}
		}
	}
	
	public void GoToHome(ActionEvent event) throws IOException{
		Parent root = FXMLLoader.load(getClass().getResource("MainScene.fxml"));
		stage = (Stage)((Node)(event.getSource())).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void OpenWorkout(String fileName) throws IOException {
		content.getChildren().clear();
		metadata.getChildren().clear();
		//System.out.print("Here \n");
		//System.out.println("need to open file: " + fileName);
    	String json = new String(Files.readAllBytes(Paths.get("src/Workouts/", fileName)));
        
        ObjectMapper mapper = new ObjectMapper();
        Workout workout = mapper.readValue(json, Workout.class);
        String output = workout.printWorkout();
        //System.out.println(output);
        Label ContentDisplay = new Label(output);
        ContentDisplay.setFont(Font.font(20));
        content.getChildren().add(ContentDisplay);
        
        Label MetadataDisplay = new Label(workout.printMetadata());
        metadata.getChildren().add(MetadataDisplay);
        
	}
	
}

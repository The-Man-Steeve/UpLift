package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class HomeController {
	
	private Stage stage;
	private Scene scene;
	@FXML
	private Label Quote;
	
	@FXML
	public void initialize() {
		File q = new File("src/application/quotes.txt");
		List<String> quotables = new ArrayList<>();
		Scanner reader = null;
		try {
			reader = new Scanner(q);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while (reader.hasNextLine()) {
			quotables.add(reader.nextLine());
		}
		reader.close();
		Random r = new Random();
		Quote.setText(quotables.get(r.nextInt(quotables.size())));
	}
	
	public void GoToCalendar(ActionEvent event) throws IOException{
		Parent root = FXMLLoader.load(getClass().getResource("CalendarScene.fxml"));
		stage = (Stage)((Node)(event.getSource())).getScene().getWindow();
		scene = new Scene(root);
		String css = this.getClass().getResource("CalendarStyle.css").toExternalForm();
		scene.getStylesheets().add(css);
		stage.setScene(scene);
		stage.show();
	}
	
	public void GoToWorkouts(ActionEvent event) throws IOException{
		Parent root = FXMLLoader.load(getClass().getResource("WorkoutScene.fxml"));
		stage = (Stage)((Node)(event.getSource())).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
}

package application;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.MonthDay;
import java.time.YearMonth;
import java.util.List;
import java.util.Scanner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CalendarController {
	
	private Stage stage;
	private Scene scene;
	@FXML
	private Label MonthLabel;
	@FXML
	private GridPane Calendar;
	@FXML
	private Label DateLabel;
	@FXML
	private Label SelectedDay;
	@FXML
	private VBox InfoPanel;
	@FXML
	private Text WorkoutInfo;
	
	private YearMonth currentYearMonth = YearMonth.now();
	private MonthDay today = MonthDay.now();
	private MonthLog log = new MonthLog();
	
	@FXML
	public void initialize() throws IOException {
		MonthLabel.setText(currentYearMonth.getMonth().toString() + "  " + currentYearMonth.getYear());
		populateCalendar();
		
	}
	//----------------------------------------------------------------
	//function returns the user to the home screen
	public void GoToHome(ActionEvent event) throws IOException{
	//---------------------------------------------------------------
		Parent root = FXMLLoader.load(getClass().getResource("MainScene.fxml"));
		stage = (Stage)((Node)(event.getSource())).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	//------------------------------------------------------------------
	//Function changes the current month to the previous month and calls
	//	the function to populate the calendar with the last month's info
	@FXML
	public void prevMonth(ActionEvent event) throws IOException {
		currentYearMonth = currentYearMonth.minusMonths(1);
		MonthLabel.setText(currentYearMonth.getMonth().toString() + "  " + currentYearMonth.getYear());
		populateCalendar();
	}
	//------------------------------------------------------------------------
	//Function changes the current month to the next one and calls the function
	//	to populate the calendar with the next month's info
	@FXML
	public void nextMonth(ActionEvent e) throws IOException {
		currentYearMonth = currentYearMonth.plusMonths(1);
		MonthLabel.setText(currentYearMonth.getMonth().toString() + "  " + currentYearMonth.getYear());
		populateCalendar();
	}
	//---------------------------------------------------------------------
	//Function populates the calendar with the numbers, information, and 
	//	*in the future* pictures
	//
	@FXML
	public void populateCalendar() throws IOException {
		//Part 1:
		// Goal is to construct the filename for the current month to extract the information
		String filename = currentYearMonth.getMonth().toString() + currentYearMonth.getYear() + "Log" + ".json";
		//System.out.println(filename);
		filename = "src/Logs/" + filename;
		//System.out.println(System.getProperty("user.dir"));
		ObjectMapper mapper = new ObjectMapper();
		//
		//Part 2:
		//  Goal is to open the file for the current month
		
		// IF there is not file for the current month:
		//		create a new monthLog that is empty
		File file = new File(filename);
		if (!file.exists()) {
			file.createNewFile();
		}
		
		//IF there are things to map in the file
		//	THEN map the file to the MonthLog
		if (file.length() != 0) {
			try {
				log = mapper.readValue(file, MonthLog.class);
			} catch (StreamReadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DatabindException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//
		//String logBuffer = log.printLog();
		//System.out.print(logBuffer);
		
		//Part 3:
		//Goal: display each day in the calendar for the month
		//
		Calendar.getChildren().removeIf(node -> GridPane.getRowIndex(node) != null && GridPane.getRowIndex(node) > 0);
		LocalDate test = currentYearMonth.atDay(1);
		int dayOfWeek = test.getDayOfWeek().getValue() % 7;
		int week = 1;
		//System.out.print(Calendar);
		for(int i = 1; i <= currentYearMonth.lengthOfMonth(); i++) {
			final int dayNumber = i;
			StackPane cell = new StackPane();
			Label dayLabel = new Label();
			dayLabel.setText("" + i);
			dayLabel.setStyle("-fx-font-size: 30;");
			dayLabel.setPrefSize(40.0, 40.0);
			dayLabel.setAlignment(Pos.CENTER);
			dayLabel.setCursor(Cursor.HAND);
			dayLabel.setOnMouseClicked(event -> clickOnDay(dayNumber, dayLabel, log.getLog()));
			
			cell.getChildren().add(dayLabel);
			if (dayNumber == today.getDayOfMonth() && currentYearMonth.getMonthValue() == today.getMonthValue()){
				clickOnDay(dayNumber, dayLabel, log.getLog());
			}
			
			//AnchorPane.setRightAnchor(dayLabel, 10.0);
			//cell.setStyle("-fx-border-color: black;");
			Calendar.add(cell, dayOfWeek, week);
			//dayLabel.setAlignment(null);
			//System.out.println("Day " + i + " at week: " + week + ", day: " + dayOfWeek);
			dayOfWeek += 1;
			if (dayOfWeek == 7) {
				dayOfWeek = 0;
				week += 1;
			}
			
		}
	}
	
	//-----------------------------------------------------------
	//Function dynamically changes the day label, and should 
	//	populate the logField with that day's workout(s)
	private void clickOnDay(int day, Label clickedDay, List<LogEntry> log) {
		//System.out.println("clicked on " + currentYearMonth.getMonth().toString() + " " + day);
		DateLabel.setText(currentYearMonth.getMonth().toString() + " " + day + ", " + currentYearMonth.getYear());
		WorkoutInfo.setText("No Workout Planned/Logged");
		//Construct the String
		String DayInQuestion = currentYearMonth.getYear() + "-" + currentYearMonth.getMonthValue() + "-" + day;
		//System.out.println(DayInQuestion);
		if (log == null || log.isEmpty()){
			//System.out.println("Log is Empty");
			WorkoutInfo.setText("No Workout Planned/Logged");
		}
		else {
			for (int index = 0; index < log.size(); index++) {
				//System.out.println(log.get(index).getDate());
				if (log.get(index).getDate().equals(DayInQuestion)) {
					//System.out.println(log.get(index).printExercises());
					WorkoutInfo.setText(log.get(index).printExercises());
				}
				//System.out.print(log.get(index).printExercises());
			}
		}
		
		
		if(SelectedDay != null) {
			SelectedDay.getStyleClass().remove("selected-date");
		}
		clickedDay.getStyleClass().add("selected-date");
		SelectedDay = clickedDay;
		
	}
}

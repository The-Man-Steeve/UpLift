package application;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LogEntry {
	@JsonProperty("Date")
	private String Date;
	@JsonProperty("Time")
	private String Time;
	@JsonProperty("Sections")
	private List<WorkoutSection> Sections;
	@JsonProperty("Note")
	private String Note;
	//Add an image in the future
	public LogEntry() {
		//do nothing
	}
	
	public String getDate() {
		return Date;
	}
	public void setDate(String date) {
		Date = date;
	}
	public String getTime() {
		return Time;
	}
	public void setTime(String time) {
		Time = time;
	}
	public List<WorkoutSection> getExercises() {
		return Sections;
	}
	public void setExercises(List<WorkoutSection> exercises) {
		Sections = exercises;
	}
	public String getNote() {
		return Note;
	}
	public void setNote(String note) {
		Note = note;
	}
	public String printExercises() {
		//System.out.println(this.getTitle() + ":");
		String output = "";
		if (this != null){
			for(WorkoutSection e: Sections) {
				output += e.PrintSection();
			}
		}
		return output;
	}
}

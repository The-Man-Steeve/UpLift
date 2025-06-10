package application;

import java.util.List;

public class Workout {
	
	
	public String Title;
	public String Date_Created;
	public List<WorkoutSection> Sections;
	public String Note;
	
	
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getDate_Created() {
		return Date_Created;
	}
	public void setDate_Created(String date_Created) {
		Date_Created = date_Created;
	}
	public List<WorkoutSection> getExercises() {
		return Sections;
	}
	public void setExercises(List<WorkoutSection> workout) {
		this.Sections = workout;
	}
	public void setNote(String note) {
		Note = note;
	}
	public String getNote() {
		return Note;
	}
	public String printWorkout() {
		//System.out.println(this.getTitle() + ":");
		String output = "";
		for(WorkoutSection e: Sections) {
			//Hierarchy:
			//Name: Sets x Reps; Weight, Distance, Time, *RestTime* rest;
			//		Note: *note*
			output += e.PrintSection();
		}
		return output;
	}
	public String printMetadata() {
		String output = "";
		output += "Workout Name: " + this.Title + "\n";
		output += "Date Created: " + Date_Created + "\n";
		output += "Additional Notes: " + this.Note;
		return output;
	}
}

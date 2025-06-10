package application;

import java.util.List;

public class Circuit implements WorkoutSection{

	private int Sets;
	private List<Exercise> Exercises;
	private String Note;
	public int getSets() {
		return Sets;
	}
	public void setSets(int sets) {
		Sets = sets;
	}
	public List<Exercise> getExercises() {
		return Exercises;
	}
	public void setExercises(List<Exercise> exercises) {
		Exercises = exercises;
	}
	public String getNote() {
		return Note;
	}
	public void setNote(String note) {
		Note = note;
	}
	
	//Initializer:
	public Circuit() {
		//do nothing
	}
	
	//ToString
	public String PrintSection() {
		String output = "*Superset*\n";
		for (Exercise e: Exercises) {
			output += "\t" + e.PrintSection();
		}
		return output;
	};
	
}

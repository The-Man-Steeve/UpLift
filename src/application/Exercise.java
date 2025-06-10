package application;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Exercise implements WorkoutSection {
	
	@JsonProperty("Name")
	private String Name;
	@JsonProperty("Sets")
	private int Sets;
	@JsonProperty("Reps")
	private int Reps;
	@JsonProperty("Time")
	private String Time;
	@JsonProperty("Weight")
	private double Weight;
	@JsonProperty("Distance")
	private double Distance;
	@JsonProperty("DistanceMeasurement")
	private String DistanceMeasurement;
	@JsonProperty("WeightMeasurement")
	private String WeightMeasurement;
	@JsonProperty("Equipment")
	private List<String> Equipment;
	@JsonProperty("Note")
	private String Note;
	
	public Exercise() {
		//do nothing
	}
	
	
	
	//name
	public void setName(String n) {
		Name = n;
	}
	public String getName() {
		return Name;
	}
	
	//sets
	public void setSets(int s) {
		Sets = s;
	}
	public int getSets() {
		return Sets;
	}
	
	//reps
	public void setReps(int r) {
		Reps = r;
	}
	public int getReps() {
		return Reps;
	}
	
	//weight
	public void setWeight(double w) {
		Weight = w;
	}
	public double getWeight() {
		return Weight;
	}
	
	//weightMeasurement
	public void setWeightMeasurement(String wm) {
		WeightMeasurement = wm;
	}
	public String getWeightMeasurement() {
		return WeightMeasurement;
	}
	
	//distance
	public void setDistance(double d) {
		Distance = d;
	}
	public double getDistance() {
		return Distance;
	}
	//distance measurement
	public void setDistanceMeasurement(String dm) {
		DistanceMeasurement = dm;
	}
	public String getDistanceMeasurement() {
		return DistanceMeasurement;
	}
	
	//time
	public void setTime(String t) {
		Time = t;
	}
	public String getTime() {
		return Time;
	}
	
	//equipment
	public void setEquipment(List<String> e) {
		Equipment = e;
	}
	public List<String> getEquipment() {
		return Equipment;
	}
	
	//note
	public void setNote(String n) {
		Note = n;
	}
	public String getNote() {
		return Note;
	}
	
	public String PrintSection() {
		String output = "";
		output += (this.getName() + " ");
		output += (this.getSets() > 0 ? this.getSets() : "");
		output += (this.getReps() > 0 ? "x" + this.getReps(): "");
		output += (this.getWeight() > 0 ? ", " + this.getWeight() + " " + this.getWeightMeasurement(): "");
		output += (this.getDistance() != 0 ? ", " + this.getDistance() + " " + this.getDistanceMeasurement(): "");
		output += (this.getTime() != null ? this.getTime(): "");
		output += (this.getNote() != null ? "\n\tNOTE: " + this.getNote(): "");
		output += "\n";
		return output;
	}
}

package application;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MonthLog {
	@JsonProperty("Log")
	private List<LogEntry> Log;

	public List<LogEntry> getLog() {
		return Log;
	}

	public void setLog(List<LogEntry> log) {
		this.Log = log;
	}
	public String printLog() {
		String output = "";
		if (Log == null || Log.isEmpty()) {
			output = "This Log is Empty";
		}
		else {
			for (LogEntry le: Log) {
				output += le.getDate() + "\n";
				output += le.printExercises();
			}
		}
		return output;
	}
}

package application;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "Type" // this will appear in the JSON
)
@JsonSubTypes({
    @JsonSubTypes.Type(value = Exercise.class, name = "Exercise"),
    @JsonSubTypes.Type(value = Circuit.class, name = "Circuit")
})


public interface WorkoutSection {
	public String PrintSection();
}

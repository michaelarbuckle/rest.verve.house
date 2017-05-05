package house.verve.model;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public class Activity {

	String name;
	String description;
	String topic;
	List<Thing> things;
	List<Rule> rules;
	Map<Thing,Action> steps;
	
}

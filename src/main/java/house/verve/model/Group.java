package house.verve.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import org.springframework.data.annotation.Id;

public class Group {
	 public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	 
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Id private String id;

	private String name;
	private Set<GroupItem> items = new TreeSet<GroupItem>();
	List<String> services = new ArrayList<String>();

	public List<String> getServices() {
		return services;
	}
	public void setService(List<String> services) {
		this.services = services;
	}
	public void AddService(String service) {
		this.services.add( service);
	}
	
}

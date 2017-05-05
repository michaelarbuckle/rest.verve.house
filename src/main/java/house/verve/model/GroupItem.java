package house.verve.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import lombok.Data;

@Data
public class GroupItem {

	@Id	private String id;
	String name;
	String className;
	String itemReferenceId;
	@DBRef(lazy=true)
    Group group;	
	
}

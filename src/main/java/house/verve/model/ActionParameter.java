package house.verve.model;

import org.springframework.data.annotation.Id;

public class ActionParameter {

	@Id private String id;

	private String type;
	private String value;
	private String className;
	
	public ActionParameter()
	{}
	public ActionParameter(String id)
	{
		this.id=id;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}

}

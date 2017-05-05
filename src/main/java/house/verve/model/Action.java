package house.verve.model;

import java.util.List;
import java.util.ArrayList;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import lombok.Data;

 
 
@Data
public class Action {

	@Id private String id;
	
	public Action()
	{}
	public Action(String id)
	{this.id=id;}
	
	List<ActionParameter> actionParameters = new ArrayList<ActionParameter> ();
	public List<ActionParameter> getActionParameters() {
		return actionParameters;
	}
	public void setActionParameters(List<ActionParameter> actionParameters) {
		this.actionParameters = actionParameters;
	}
	public void addActionParameter( ActionParameter  actionParameter ) {
		this.actionParameters.add( actionParameter);
	}
	
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	private String service;
	
	private String type;
	private String topic;
	private String status;
	private String correlationId;
	private String createdTimestamp;
	private String completedTimestamp;
	private String tenantId;
	
	
	
	
	public String getTenantId() {
		return tenantId;
	}
	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCorrelationId() {
		return correlationId;
	}
	public void setCorrelationId(String correlationId) {
		this.correlationId = correlationId;
	}
	public String getCreatedTimestamp() {
		return createdTimestamp;
	}
	public void setCreatedTimestamp(String createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}
	public String getCompletedTimestamp() {
		return completedTimestamp;
	}
	public void setCompletedTimestamp(String completedTimestamp) {
		this.completedTimestamp = completedTimestamp;
	}
	private String name;
	@DBRef(lazy=true)
	private Device device;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Device getDevice() {
		return device;
	}
	public void setDevice(Device device) {
		this.device = device;
	}
}

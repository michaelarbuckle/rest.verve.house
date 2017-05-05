package house.verve.model;

import org.springframework.data.annotation.Id;

public class Message {

	public Message()
	{}
	public Message(String id)
	{
		this.id=id;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}

	@Id private String id;
	 private String message;
	 private String data;
	 private String url;
	 private String authorId;
	 
	 private String topic;
	 private String correlationId;
	 private String level;
	 private String timestamp;
	
	 public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getCorrelationId() {
		return correlationId;
	}
	public void setCorrelationId(String correlationId) {
		this.correlationId = correlationId;
	}
}

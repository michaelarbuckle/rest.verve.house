package house.verve.model;

import org.springframework.data.annotation.Id;
import java.util.List;
import java.util.ArrayList;
public class Chat {

	 @Id private String id;
	 public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<Message> getMessages() {
		return messages;
	}
	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public List<User> getToList() {
		return toList;
	}
	public void setToList(List<User> toList) {
		this.toList = toList;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	private List<Message> messages;
	 private String topic;
	 private List<User> toList;
	 private String level;
	 private String timestamp;
}

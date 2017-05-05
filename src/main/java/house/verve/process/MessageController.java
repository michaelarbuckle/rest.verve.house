package house.verve.process;
import house.verve.model.Message;
import house.verve.model.MessageRepository;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

public class MessageController {

	@Autowired
	MessageRepository messageRepository;
	public void handleMessages(List<Message> messages)
	{
		try {
		System.out.println("reached handleMessages()");
		for (Message m :messages){
		messageRepository.save(m);
		}
		} catch(Exception e)
		{
			e.printStackTrace();
			
		}
	}
}

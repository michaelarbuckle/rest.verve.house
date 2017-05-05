package house.verve.event;
import house.verve.model.Action;
import house.verve.model.Message;
import house.verve.model.Sensor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterDeleteEvent;
import org.springframework.data.mongodb.core.mapping.event.AfterSaveEvent;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.data.mongodb.core.mapping.event.BeforeSaveEvent;
import org.springframework.data.mongodb.core.mapping.event.MongoMappingEvent;

public class MessageChangeListener extends AbstractMongoEventListener<Object> {

	@Override
	public void onAfterDelete(AfterDeleteEvent<Object> event) {
		// TODO Auto-generated method stub
		super.onAfterDelete(event);
	}

	@Override
	public void onAfterSave(AfterSaveEvent<Object> event) {
		// TODO Auto-generated method stub
		super.onAfterSave(event);
		
		if (event.getSource() instanceof Message)  
		{
			Message mess = (Message)event.getSource();
			System.out.println(" MessageChangeListner.onAfterSave fires with message="+mess.getMessage());

		}
	}

	@Override
	public void onBeforeSave(BeforeSaveEvent<Object> event) {
		// TODO Auto-generated method stub
		super.onBeforeSave(event);
	}
 

	
}

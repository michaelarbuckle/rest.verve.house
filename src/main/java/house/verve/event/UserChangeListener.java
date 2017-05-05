package house.verve.event;

import house.verve.model.Device;
import house.verve.model.User;
import house.verve.model.Sensor;
import house.verve.model.Space;
import house.verve.model.Timeseries;
import house.verve.model.Action;
import house.verve.model.Message;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;


import house.verve.rules.RulesEngine;
import house.verve.process.TimeSeriesProcessor;
import house.verve.process.ActionController;
import house.verve.process.MessageController;
import house.verve.async.WorkQueueController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterSaveEvent;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.data.mongodb.core.mapping.event.BeforeSaveEvent;
import org.springframework.data.mongodb.core.mapping.event.MongoMappingEvent;
public class UserChangeListener  extends AbstractMongoEventListener<Object> {
    @Autowired
    private MongoOperations mongoOperations;

    @Autowired
    private ActionController actionController;
    
    @Autowired
    private MessageController messageController;
    
    @Autowired
    private WorkQueueController workQueueController;
    
	@Override
	public void onAfterSave(AfterSaveEvent<Object> event) {
		// TODO Auto-generated method stub
		super.onAfterSave(event);
		final Object source = event.getSource();
		if (source instanceof User)  
		{
			User user = (User)source;

			
			
		/*	List<Action> actions = (List<Action>)context.get("ACTIONS");
			actionController.handleActions(actions);
			
			List<Message> messages = (List<Message>)context.get("MESSAGES");								
			messageController.handleMessages(messages);
			*/
		}
	}
	}

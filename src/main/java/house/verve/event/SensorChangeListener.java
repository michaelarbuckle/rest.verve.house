package house.verve.event;
import house.verve.model.User;
import house.verve.model.Sensor;
import house.verve.model.Space;
import house.verve.model.Timeseries;
import house.verve.model.TimeseriesController;
import house.verve.model.Action;
import house.verve.model.Message;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import house.verve.rules.KieRulesEngine;
import house.verve.rules.RuleConstants;
import house.verve.rules.RulesEngine;
import house.verve.utils.DataUtil;
import house.verve.process.TimeSeriesProcessor;
import house.verve.process.ActionController;
import house.verve.process.MessageController;
import house.verve.async.WorkQueueController;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterSaveEvent;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.data.mongodb.core.mapping.event.BeforeSaveEvent;
import org.springframework.data.mongodb.core.mapping.event.MongoMappingEvent;

public class SensorChangeListener extends AbstractMongoEventListener<Object> {
	
	Logger logger = Logger.getLogger(SensorChangeListener.class);

    @Autowired
    private MongoOperations mongoOperations;

    @Autowired
    private TimeSeriesProcessor timeSeriesProcessor;
    
    @Autowired
    private KieRulesEngine rulesEngine;
    
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
		System.out.println(" SensorChangeListner.onAfterSave fires with event class="+event.getSource().getClass());

		if (source instanceof Sensor)  
		{
			Sensor sensor =(Sensor)source;
			//update time series
			Timeseries ts = timeSeriesProcessor.updateTimeseriesForSensor(sensor,DataUtil.parseDateTimeString(sensor.getTimestamp()));
			
			Map context = new HashMap();
			context.put(RuleConstants.TIMESERIES, ts);
			context.put(RuleConstants.SENSOR_LIST, (Sensor)source);
			//context.put(RuleConstants.USER, (Sensor)source);
			Date date = new Date();
/*			SimpleDateFormat sdf = new SimpleDateFormat("YY-MM-dd HH:mm:ss");
				String timestamp = sdf.format(date);
	*/
			String timestamp =DataUtil.getDateTimeString(date);
			context.put(RuleConstants.TIMESTAMP, event.getTimestamp());
			context.put(RuleConstants.COLLECTION, event.getCollectionName());
			List<Action> actions =  new ArrayList<Action> ();
			context.put(RuleConstants.ACTION_LIST, actions);
			List<Message> messages = new ArrayList<Message>();
			context.put(RuleConstants.MESSAGE_LIST, messages);
			//post environmental info to rules engine
			//RulesEngine.getInstance().handleSensorEvent((Sensor)source, context);
			rulesEngine.handleSensorEvent(sensor, context);
			
			
			actionController.handleActions(actions);
			
		/* for testing 	
		 
		  if (messages == null || messages.size() == 0) {
		 	Message m = new Message();
			m.setMessage("humidity exceeded 90% in kitchen."+sdf.format(date));
			m.setTopic("humidity,kitchen.");
			messages.add(m);
			StringBuilder sb = new StringBuilder();
			sb.append(sensor.getName()).append(" reached ").append(sensor.getV()).append(" at ").append(sdf.format(date));

			Message m2 = new Message();
			m2.setMessage(sb.toString());
			m2.setTopic(sensor.getName());
			messages.add(m2);
			}
		*/
			messageController.handleMessages(messages);
			
		}
		  else 
			if (source instanceof Action)  
				{
					 Action  action =( Action)source; 
				}
	}

	@Override
	public void onApplicationEvent(MongoMappingEvent<?> arg0) {
		// TODO Auto-generated method stub
		super.onApplicationEvent(arg0);
	}

	@Override
	public void onBeforeConvert(BeforeConvertEvent<Object> event) {
		// TODO Auto-generated method stub
		super.onBeforeConvert(event);
	}

	@Override
	public void onBeforeSave(BeforeSaveEvent<Object> event) {
		// TODO Auto-generated method stub
		super.onBeforeSave(event);
/*
		final Object source = event.getSource();
		if (source instanceof User && ((User) source).getEmail() != null) {
            mongoOperations.save(((User) source).getEmail());
        }
*/
	}
}
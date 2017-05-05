package house.verve.rules;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.kie.api.KieBase;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.ObjectFilter;
import org.kie.api.runtime.StatelessKieSession;
import org.kie.api.runtime.rule.FactHandle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import house.verve.model.Action;
import house.verve.model.ActionList;
import house.verve.model.Message;
import house.verve.model.MessageList;
import house.verve.model.Sensor;
import house.verve.model.Space;


public class  KieRulesEngine {

 
	private final Logger logger = LoggerFactory.getLogger(KieRulesEngine.class);
	 
    private final KieContainer kieContainer;
    private final KieBase kieBase;

    @Autowired
    public KieRulesEngine(KieContainer kieContainer) {
        logger.info("Initialising a new kie rules session.");
        this.kieContainer = kieContainer;
        this.kieBase = kieContainer.getKieBase("sensorrules");

    }

    public void handleSensorEvent(Sensor sensor, Map context)
	{    	
    	try {
  	  //  KieSession kieSession = kieContainer.newKieSession("ksession-sensor-rules");
  	    KieSession kieSession = kieBase.newKieSession();
 	    
	    List<Action> actionListG =  (List<Action>)context.get(RuleConstants.ACTION_LIST);	    
	    List<Message> messageListG = (List<Message>)context.get(RuleConstants.MESSAGE_LIST);
        
	 
	    ActionList actionList = new ActionList();
	    MessageList messageList = new MessageList();

	    kieSession.insert(messageList);
	    kieSession.insert(actionList);
        kieSession.insert(sensor);
        //check context for space, etc.
        Space space = (Space)context.get(RuleConstants.SPACE);
        kieSession.insert(space);
        
        kieSession.fireAllRules();
        
        Collection results = kieSession.getObjects();
        
        for (Object o:results){
       // System.out.print("session "+o.getClass() );
        }
     //   List<Action> actionList = findActionList(kieSession);
        kieSession.dispose();
        
        actionListG.addAll(actionList.getList());
        messageListG.addAll(messageList.getList());
        
	} catch (Exception e)
	{
		e.printStackTrace();
	}
        
	}
     
    /**
     * Search the {@link KieSession} for bus passes.
     */
    private List<Action> findActionList(KieSession kieSession) {
        
        // Find all BusPass facts and 1st generation child classes of BusPass.
        ObjectFilter busPassFilter = new ObjectFilter() {
            @Override
            public boolean accept(Object object) {
                if (Action.class.equals(object.getClass())) return true;
                if (Action.class.equals(object.getClass().getSuperclass())) return true;
                return false;
            }
        };

        
        List<Action> facts = new ArrayList<Action>();
        for (FactHandle handle : kieSession.getFactHandles(busPassFilter)) {
            facts.add((Action) kieSession.getObject(handle));
        }
        
        // Assumes that the rules will always be generating a single bus pass. 
        return facts;
    }
    
    /**
     * Print out details of all facts in working memory.
     * Handy for debugging.
     */
    @SuppressWarnings("unused")
    private void printFactsMessage(KieSession kieSession) {
        Collection<FactHandle> allHandles = kieSession.getFactHandles();
        
        String msg = "\nAll facts:\n";
        for (FactHandle handle : allHandles) {
            msg += "    " + kieSession.getObject(handle) + "\n";
        }
        System.out.println(msg);
    }
}

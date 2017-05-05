package house.verve.process;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import house.verve.model.Action;
import house.verve.model.ActionRepository;
import house.verve.model.Message;
import house.verve.rules.RuleConstants;

public class ActionController {

	@Autowired
	ActionRepository actionRepository;

	public void handleActions(List<Action> actions)
	{
		try {
			System.out.println("reached handleActions()");
			for (Action a:actions){
			   actionRepository.save(a);
			   System.out.println("save action "+a.getService()+ " id="+a.getId());
			}
			} catch(Exception e)
			{
				e.printStackTrace();
				
			}
		}
}

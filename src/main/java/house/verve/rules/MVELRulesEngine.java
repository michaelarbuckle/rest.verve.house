package house.verve.rules;
import java.util.Map;

import org.mvel2.MVEL;

import java.util.HashMap;
import java.io.Serializable;
import java.math.BigDecimal;

public class MVELRulesEngine {

	
	void handleRule()
	{
		

		   String prefValue = "(A <= B)";
		  Serializable expression = MVEL.compileExpression(prefValue);  
		  
		  Map<String, BigDecimal> expressionVariables = new HashMap<String, BigDecimal>(2);
		  expressionVariables.put("A", new BigDecimal(10));
		  expressionVariables.put("B", new BigDecimal(20));
		  
		  System.out.println( (Boolean)MVEL.executeExpression(expression, expressionVariables));
	}
}

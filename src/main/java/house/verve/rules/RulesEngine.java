package house.verve.rules;
import house.verve.model.User;
import house.verve.model.Sensor;
import java.util.Map;
import java.util.PriorityQueue;
public class RulesEngine {
	
	
	static PriorityQueue eventQueue;

	static public RulesEngine getInstance(){
		if (instance == null)
		{
			synchronized(RulesEngine.class)
			{
				if (instance == null)
				{
					instance = new RulesEngine();
					eventQueue = new PriorityQueue();
				}
				
			}			
		}
		return instance;
		}
	
	private RulesEngine(){};
	private static RulesEngine instance;
	
	public void handleSensorEvent(Sensor sensor, Map context)
	{
		System.out.println("Rules engine posted. Sync or async to queue.");
		//eventQueue.add();
		
	}
/*
	function getRuleString(rule) {
	    var andOrStack = [];
	    var lastExpressionDepth = 0;
	    var tempRuleString = '';
	    var countOpenBrackets = 0;

	    if(!rule || !rule.expressionRows) {
	        return '';
	    }

	    rule.expressionRows.forEach(function(expressionRow, index) {
	        expressionRow.depth = parseInt(expressionRow.depth, 10);
	        if(lastExpressionDepth !== expressionRow.depth) {

	            // if this is an outer expression than the previous
	            if((lastExpressionDepth - expressionRow.depth) > 0) {
	                for(var i=0; i<(lastExpressionDepth - expressionRow.depth); i++) {
	                    tempRuleString += ' ) ';
	                }
	                tempRuleString += ' ' + andOrStack.pop() + ' ';
	            } else {
	                tempRuleString += ' ' + andOrStack.pop() + ' ( ';
	            }

	            lastExpressionDepth = expressionRow.depth;
	        } else {
	            if(index > 0) {
	                tempRuleString += ' ' + andOrStack.pop() + ' ';
	            }
	        }

	        tempRuleString += expressionRow.firstColumn + ' ' + expressionRow.operator + ' "' + expressionRow.expression + '"';
	        andOrStack.push(expressionRow.andOr);
	    });

	    tempRuleString += Array.apply(null, Array(tempRuleString.split('(').length-tempRuleString.split(')').length)) // jshint ignore: line
	            .map(String.prototype.valueOf, ' ) ').join('');

	    return tempRuleString;
	} 
	*/
	
}

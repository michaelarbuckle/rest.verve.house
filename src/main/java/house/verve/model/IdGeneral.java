package house.verve.model;

import org.bson.types.ObjectId;
import java.util.concurrent.atomic.*;

public class IdGeneral {
	   static AtomicInteger nextId = new AtomicInteger();
	    static private int id;

	    public static int instanceIdGeneral() {
	    	
	        id = nextId.incrementAndGet();
	        return id;
	   }
	    
	  static  public ObjectId getObjectId(String sid)
	    {
        if (ObjectId.isValid(sid)) {
            ObjectId objectId = new ObjectId(sid);
            return objectId;

        } else {
        	throw new java.lang.IllegalArgumentException("Invalid id");	            
        }
	    }

	static public  ObjectId getObjectId() { return ObjectId.get();}

	
	public static void main(String[] args)  
	{
		for (int i=0;i< 10; i++)
		{
			System.out.println(IdGeneral.getObjectId());
		}
		
	}
}


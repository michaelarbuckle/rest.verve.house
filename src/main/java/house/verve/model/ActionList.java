package house.verve.model;

import java.util.ArrayList;
public class ActionList {

	private ArrayList<Action> list = new ArrayList<Action>();
	public ActionList()
	{}
	public void add(Action a)
	{
		list.add(a);
	}
	public ArrayList<Action> getList() {
		return list;
	}
}

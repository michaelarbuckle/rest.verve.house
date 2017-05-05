package house.verve.model;

import java.util.ArrayList;
public class MessageList {

	private ArrayList<Message> list = new ArrayList<Message>();
	public MessageList(){}
	public ArrayList<Message> getList() {
		return list;
	}
	public void add(Message a)
	{
		list.add(a);
	}
}

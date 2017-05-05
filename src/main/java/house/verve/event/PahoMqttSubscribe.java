package house.verve.event;
import org.eclipse.paho.client.mqttv3.*;
import org.springframework.beans.factory.annotation.Autowired;

public class PahoMqttSubscribe implements MqttCallback
{
 // @Autowired	
  MqttClient client;

  public PahoMqttSubscribe() {}

  public static void main (String[] args) {
    new PahoMqttSubscribe().doDemo();
  }

  public void doDemo() {
	    try {
	      client = new MqttClient("tcp://m2m.eclipse.org:1883", MqttClient.generateClientId());
	      client.connect();
	      client.setCallback(this);

	      client.subscribe("bbbexample/tmp36/c");

	      // We’ll now idle here sleeping, but your app can be busy
	      // working here instead
	      while (true) {
	      try { Thread.sleep (1000); } catch (InterruptedException e) {}
	      }
	    }
	    catch (MqttException e) { e.printStackTrace (); }
	  }
  
@Override
public void connectionLost(Throwable arg0) {
	// TODO Auto-generated method stub
	
}

@Override
public void deliveryComplete(IMqttDeliveryToken arg0) {
	// TODO Auto-generated method stub
	
}

@Override
public void messageArrived(String topic, MqttMessage message) throws Exception
{
  System.out.println (topic + " " + new String (message.getPayload()));
}
}

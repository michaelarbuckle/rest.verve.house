package house.verve.model;

import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import house.verve.process.TimeSeriesProcessor;
import house.verve.utils.DataUtil;
 
 
@RestController
@RequestMapping("/sensors")
public class SensorController {
	
	 
	@Autowired
	SensorRepository sensorRepository;
	
	
	@Autowired
	TimeSeriesProcessor timeseriesProcessor;
	
	
	    


	
	
	@RequestMapping(method=RequestMethod.GET)
	public List<Sensor> getAllSensors(HttpSession session) {
		/*
		List<String> uuids = (List<String>) session.getAttribute( ModelConstants.UUID_LIST);

		List<Sensor> findByUuid( String uiid);

*/
		
		return sensorRepository.findAll();
	}
 
	@RequestMapping(method=RequestMethod.POST)
	public Sensor createSensor(@Valid @RequestBody Sensor Sensor) {
		return sensorRepository.save(Sensor);
	}
 
	@RequestMapping(value="{id}", method=RequestMethod.GET)
	public ResponseEntity<Sensor> getSensorByUUID(@PathVariable("uuid") String uuid) {
		List<Sensor> SensorList = sensorRepository.findByUuid(uuid);
		if(SensorList == null) {
			return new ResponseEntity<Sensor>(HttpStatus.NOT_FOUND);
		} else {
			Sensor dd =  SensorList.get(0);
			return new ResponseEntity<Sensor>(dd, HttpStatus.OK);
		}
	}
	
	@RequestMapping(value="{uuid}", method=RequestMethod.PUT)
	public ResponseEntity<Sensor> updateSensor(@Valid @RequestBody Sensor info, @PathVariable("uuid") String uuid)
	{

		System.out.println("/sensor PUT"+info);
		
		Sensor sensor = null;
		String deviceUUID = info.getUuid();
		Date dateTime = DataUtil.getDateTime(info.getTimestamp());
		List<Sensor> sensorList = sensorRepository.findByUuid(deviceUUID);
		if(sensorList == null  || sensorList.size() == 0) {
			sensor = new Sensor(info.getUuid());
			sensor.setTimestamp(info.getTimestamp() );
			//return new ResponseEntity<Sensor>(HttpStatus.NOT_FOUND);
		} else
		{
			sensor = sensorList.get(0);
		}
		
		//first update sensors		
 		sensor.setTimestamp(info.getTimestamp());
		

 		timeseriesProcessor.updateTimeseriesForSensor(sensor, dateTime);
 		
		Sensor updatedSensor = sensorRepository.save(sensor);
		
		return new ResponseEntity<Sensor>(updatedSensor, HttpStatus.OK);
	}
	
	@RequestMapping(value="{id}", method=RequestMethod.DELETE)
	public void deleteSensor(@PathVariable("id") String id) {
		sensorRepository.delete(id);
	}
	/*
	 @RequestMapping(value = "/provisionDevice", method = RequestMethod.POST)
	  @ResponseBody
	  public Device provisionDevice(Device  device,  Principal principal,HttpSession session) 
	  {

		 UUID deviceUUID = UUID.randomUUID();
		 device.setUUID(deviceUUID.toString());
		 List<String> sensorNames =device.getTypes();
		 
		 for (String name: sensorNames)
		 {
			 Sensor sensor = new Sensor();		
			 sensor.setuUID(deviceUUID.toString()+"-"+name);
			 sensor.setType(name);
		 	 sensor.setTimestamp(DataUtil.getDateTimeString());
			 
		 }
		 Device provisionedDevice = deviceRepository.save(device);
		 
		  return provisionedDevice;
	  }
	  */
	
}
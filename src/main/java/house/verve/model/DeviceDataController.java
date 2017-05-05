package house.verve.model;

import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/deviceData")
public class DeviceDataController {
	
	Logger logger = Logger.getLogger(DeviceDataController.class);

	
	@Autowired
	DeviceDataRepository deviceDataRepository;

	@Autowired
	SensorRepository sensorRepository;
	
	
	@Autowired
	TimeSeriesProcessor timeseriesProcessor;
	
	@RequestMapping(method=RequestMethod.GET)
	public List<DeviceData> getAllDeviceDatas() {
		logger.info("DeviceData.getAllDeviceDatas GET");
		return deviceDataRepository.findAll();
	}

	@RequestMapping(value="/current",method=RequestMethod.GET)
	public DeviceData getCurrentDeviceData(HttpSession session) {
		List<DeviceData> lst = deviceDataRepository.findAll();
		
		if (lst == null || lst.size() == 0)
		{
			return new DeviceData();
			
		}
		
		return lst.get(0);
	}

	
	
	@RequestMapping(method=RequestMethod.POST)
	public DeviceData createDeviceData(@Valid @RequestBody DeviceData DeviceData) {
		logger.info("DeviceData.createDeviceData POST");

		return deviceDataRepository.save(DeviceData);
	}
 
	@RequestMapping(value="{uuid}", method=RequestMethod.GET)
	public ResponseEntity<DeviceData> getDeviceDataByUUID(@PathVariable("uuid") String uuid) {
		logger.info("DeviceData.createDeviceData POST");
		List<DeviceData> DeviceDataList = deviceDataRepository.findByUuid(uuid);
		if(DeviceDataList == null) {
			return new ResponseEntity<DeviceData>(HttpStatus.NOT_FOUND);
		} else {
			DeviceData dd =  DeviceDataList.get(0);
			return new ResponseEntity<DeviceData>(dd, HttpStatus.OK);
		}
	}
	
/*	@RequestMapping(method=RequestMethod.PUT)
	public ResponseEntity<DeviceData> updateDeviceData(@Valid @RequestBody DeviceData info )*/
	@RequestMapping(value="{uuid}", method=RequestMethod.PUT)
	public ResponseEntity<DeviceData> updateDeviceData(@Valid @RequestBody DeviceData info, @PathVariable("uuid") String uuid)
	{
		logger.info("PUT /deviceData/"+/*uuid+*/"  "+info);

		 
		Date dateTime = new Date();
		String timestamp = DataUtil.getDateTimeString(dateTime);
		
		DeviceData deviceData = null;
		String deviceUUID = info.getUuid();
		//Date dateTime = DataUtil.getDateTime(info.getTimestamp());
		List<DeviceData> deviceDataList = deviceDataRepository.findByUuid(deviceUUID);
		if(deviceDataList == null  || deviceDataList.size() == 0) {
			deviceData = new DeviceData(info.getUuid());
			deviceData.setTimestamp(timestamp );
			//return new ResponseEntity<DeviceData>(HttpStatus.NOT_FOUND);
		} else
		{
			deviceData = deviceDataList.get(0);
		}
		
		//first update sensors		
 		deviceData.setTimestamp(info.getTimestamp());
		
 		List<SensorData> sensorDataList = info.getSensors();
 		logger.info("Sensor Data count ="+sensorDataList.size());
 	 
 		for (SensorData sd : sensorDataList)
 		{
 			logger.info("Sensor Data "+sd);
 			String sensorUUID = deviceUUID + "-"+sd.getType(); 
 			List<Sensor> s = sensorRepository.findByUuid(sd.getUuid()); 
 			Sensor sensor = null;
 			if (s == null || s.size() == 0)
 			{
 				sensor = new Sensor(sensorUUID);
 				sensor.setType(sd.getType());
 			}	else
 			{	
 				sensor = s.get(0);
 			}
 			sd.setTimestamp(timestamp);
 			//sd.setUnits(units);
 			sensor.setValue(sd.getValue());
 			sensor.setTimestamp(timestamp);
 			deviceData.updateSensor(sd);
 			sensorRepository.save(sensor);
 
 			timeseriesProcessor.updateTimeseriesForSensor(sensor, dateTime);
 		}
 		
		DeviceData updatedDeviceData = deviceDataRepository.save(deviceData);
		
		return new ResponseEntity<DeviceData>(updatedDeviceData, HttpStatus.OK);
	}
	
	@RequestMapping(value="{id}", method=RequestMethod.DELETE)
	public void deleteDeviceData(@PathVariable("id") String id) {
		deviceDataRepository.delete(id);
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
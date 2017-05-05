package house.verve.model;

import java.util.List;
 
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
 
 
@RestController
@RequestMapping("/timeseries")
public class TimeseriesController {
	
	Logger logger = Logger.getLogger(TimeseriesController.class);
	
	@Autowired
	TimeseriesRepository timeseriesRepository;
	
	@RequestMapping(method=RequestMethod.GET)
	public List<Timeseries> getAllTimeseriess() {
		logger.info("get timeseries");
		return timeseriesRepository.findAll();
	}
  
	// /timeseries/12312321321-CO2-0
	@RequestMapping(value="{uuid}", method=RequestMethod.GET)
	public ResponseEntity<Timeseries> getTimeseriesByUUID(@PathVariable("uuid") String uuid) {
		
		logger.info("Get Timeseries for uuid "+uuid);
		List<Timeseries> timeseriesList = timeseriesRepository.findByUuid(uuid);
		if(timeseriesList == null || timeseriesList.size() == 0) {
			
			return new ResponseEntity<Timeseries>(HttpStatus.NOT_FOUND);
		} else {
			logger.info("Found timeseries:  "+timeseriesList.get(0));
			
			return new ResponseEntity<Timeseries>(timeseriesList.get(0), HttpStatus.OK);
		}
	}
	
	
	@RequestMapping(value="{id}", method=RequestMethod.DELETE)
	public void deleteTimeseries(@PathVariable("id") String id) {
		timeseriesRepository.delete(id);
	}
	
}
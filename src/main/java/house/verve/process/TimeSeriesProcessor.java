package house.verve.process;

import house.verve.model.Timeseries;
import house.verve.model.Sensor;
import house.verve.model.TimeseriesRepository;
import house.verve.utils.DataUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;

 
public class TimeSeriesProcessor {

	@Autowired
	TimeseriesRepository tRepo;
	
	public Timeseries updateTimeseriesForSensor(Sensor sensor, Date timestamp)
	{
		Timeseries ts = null;
		List<Timeseries> tsl = tRepo.findByUuid(sensor.getUuid());
		if (tsl == null || tsl.size() == 0)
		{
			 ts = this.initTimeseries(sensor);
		} else
		{
			ts = tsl.get(0);// get most recent
		}
		
		ts.setTimepoint(sensor.getVal().floatValue(), timestamp);
		
		
		tRepo.save(ts);
		return ts;
	}
	
	Timeseries initTimeseries(Sensor sensor)
	{
		String dts = DataUtil.getDateTimeString();
		return new Timeseries(sensor.getUuid(), dts);
		
	}
	
	Timeseries mockTimeseries(Sensor sensor)
	{
		String dts = DataUtil.getDateTimeString();
		Timeseries ts = new Timeseries(sensor.getUuid(),dts);
		
		List<Date> dates =DataUtil.getDateSeries(DataUtil.getMidnight(), 900, 96);
		for (Date d:dates)
		{
			float value = DataUtil.getRandomDouble(395.0,700.0).floatValue();
			
			ts.setTimepoint(value, d);
			
		}
		
		return ts;
	}
	
}

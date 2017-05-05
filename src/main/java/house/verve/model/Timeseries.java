package house.verve.model;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.List;
import java.util.ArrayList;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import com.fasterxml.jackson.annotation.JsonIgnore;

import house.verve.utils.DataUtil;

/*
 *  {
                        "$group" : {
                                "_id" : {
                                        "year" : {
                                                "$year" : [
                                                        "$created_on"
                                                ]
                                        },
                                        "dayOfYear" : {
                                                "$dayOfYear" : [
                                                        "$created_on"
                                                ]
                                        },
                                        "hour" : {
                                                "$hour" : [
                                                        "$created_on"
                                                ]
                                        },
                                        "minute" : {
                                                "$minute" : [
                                                        "$created_on"
                                                ]
                                        },
                                        "second" : {
                                                "$second" : [
                                                        "$created_on"
                                                ]
                                        }
                                },
                                "count" : {
                                        "$sum" : {
                                                "$const" : 1
                                        }
                                },
                                "avg" : {
                                        "$avg" : "$value"
                                },
                                "min" : {
                                        "$min" : "$value"
                                },
                                "max" : {
                                        "$max" : "$value"
                                }  
    private void saveStatistics(MeterEvent event){
        long trim = event.getDate().getTime()%86400000;
        DBObject query = BasicDBObjectBuilder.start()
                .append("date", new Date(event.getDate().getTime()-trim))
                .append("source", event.getSource())
                .get();
        DBObject inc = BasicDBObjectBuilder.start()
                .append("daily", 1)
                .append("hourly."+((Integer)event.getDate().getHours()).toString(),1)
                .append("minutely."+event.getDate().getHours()+"."+((Integer)event.getDate().getMinutes()).toString(),1)
                .get();
        BasicDBObject update = new BasicDBObject("$inc", inc);
        statistics.update(query, update, true, false);
    }
 */


/*
 * {
  uuid: "",
  timestamp_day: ISODate(“2013-10-10T23:06:00.000Z”),
  last_timestamp: 
  last_timeslot: 4,
  total_samples: 96,
  type: “CO2”,
  device_uuid:""
  values: {
    0: 999999,
    …
    37: 1000000,
    38: 1500000,
    … 
    59: 1800000
  },
  min:,
  max:,
  avg: 
}

 */


@Data
public class Timeseries {
	
	public Timeseries(String uuid, String day)
	{
		//this.sensor = sensor;
		this.day = day;
		this.uuid =uuid;
	}
/*
	public Sensor getSensor() {
		return sensor;
	}

	public void setSensor(Sensor sensor) {
		this.sensor = sensor;
	}
*/
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uUID) {
		this.uuid = uUID;
	}

	//@DBRef(lazy=true)
	//Sensor sensor;
	// use id = device +":"+sensor +":" + timestamp to handle range queries   ie   "1001:161101"  for november first
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@JsonIgnore
	@Id private String id;
	String uuid;
	String day; 
	@Override
	public String toString() {
		return "Timeseries [uuid=" + uuid + ", day=" + day + ", timezone=" + timezone + ", units=" + units + ", type="
				+ type + ", avg=" + avg + ", max=" + max + ", min=" + min + ", currentIndex=" + currentIndex + "]";
	}

	String timezone;
	String units;	
	String type;
	double avg;
	double max;
	double min;
	
	public String getTimezone() {
		return timezone;
	}
	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}
	public float getH0_0() {
		return h0_0;
	}
	public void setH0_0(float h0_0) {
		this.h0_0 = h0_0;
	}
	public float getH0_15() {
		return h0_15;
	}
	public void setH0_15(float h0_15) {
		this.h0_15 = h0_15;
	}
	public float getH0_30() {
		return h0_30;
	}
	public void setH0_30(float h0_30) {
		this.h0_30 = h0_30;
	}
	public float getH0_45() {
		return h0_45;
	}
	public void setH0_45(float h0_45) {
		this.h0_45 = h0_45;
	}
	public float getH1_0() {
		return h1_0;
	}
	public void setH1_0(float h1_0) {
		this.h1_0 = h1_0;
	}
	public float getH1_15() {
		return h1_15;
	}
	public void setH1_15(float h1_15) {
		this.h1_15 = h1_15;
	}
	public float getH1_30() {
		return h1_30;
	}
	public void setH1_30(float h1_30) {
		this.h1_30 = h1_30;
	}
	public float getH1_45() {
		return h1_45;
	}
	public void setH1_45(float h1_45) {
		this.h1_45 = h1_45;
	}
	public float getH2_0() {
		return h2_0;
	}
	public void setH2_0(float h2_0) {
		this.h2_0 = h2_0;
	}
	public float getH2_15() {
		return h2_15;
	}
	public void setH2_15(float h2_15) {
		this.h2_15 = h2_15;
	}
	public float getH2_30() {
		return h2_30;
	}
	public void setH2_30(float h2_30) {
		this.h2_30 = h2_30;
	}
	public float getH2_45() {
		return h2_45;
	}
	public void setH2_45(float h2_45) {
		this.h2_45 = h2_45;
	}
	public float getH3_0() {
		return h3_0;
	}
	public void setH3_0(float h3_0) {
		this.h3_0 = h3_0;
	}
	public float getH3_15() {
		return h3_15;
	}
	public void setH3_15(float h3_15) {
		this.h3_15 = h3_15;
	}
	public float getH3_30() {
		return h3_30;
	}
	public void setH3_30(float h3_30) {
		this.h3_30 = h3_30;
	}
	public float getH3_45() {
		return h3_45;
	}
	public void setH3_45(float h3_45) {
		this.h3_45 = h3_45;
	}
	public float getH4_0() {
		return h4_0;
	}
	public void setH4_0(float h4_0) {
		this.h4_0 = h4_0;
	}
	public float getH4_15() {
		return h4_15;
	}
	public void setH4_15(float h4_15) {
		this.h4_15 = h4_15;
	}
	public float getH4_30() {
		return h4_30;
	}
	public void setH4_30(float h4_30) {
		this.h4_30 = h4_30;
	}
	public float getH4_45() {
		return h4_45;
	}
	public void setH4_45(float h4_45) {
		this.h4_45 = h4_45;
	}
	public float getH5_0() {
		return h5_0;
	}
	public void setH5_0(float h5_0) {
		this.h5_0 = h5_0;
	}
	public float getH5_15() {
		return h5_15;
	}
	public void setH5_15(float h5_15) {
		this.h5_15 = h5_15;
	}
	public float getH5_30() {
		return h5_30;
	}
	public void setH5_30(float h5_30) {
		this.h5_30 = h5_30;
	}
	public float getH5_45() {
		return h5_45;
	}
	public void setH5_45(float h5_45) {
		this.h5_45 = h5_45;
	}
	public float getH6_0() {
		return h6_0;
	}
	public void setH6_0(float h6_0) {
		this.h6_0 = h6_0;
	}
	public float getH6_15() {
		return h6_15;
	}
	public void setH6_15(float h6_15) {
		this.h6_15 = h6_15;
	}
	public float getH6_30() {
		return h6_30;
	}
	public void setH6_30(float h6_30) {
		this.h6_30 = h6_30;
	}
	public float getH6_45() {
		return h6_45;
	}
	public void setH6_45(float h6_45) {
		this.h6_45 = h6_45;
	}
	public float getH7_0() {
		return h7_0;
	}
	public void setH7_0(float h7_0) {
		this.h7_0 = h7_0;
	}
	public float getH7_15() {
		return h7_15;
	}
	public void setH7_15(float h7_15) {
		this.h7_15 = h7_15;
	}
	public float getH7_30() {
		return h7_30;
	}
	public void setH7_30(float h7_30) {
		this.h7_30 = h7_30;
	}
	public float getH7_45() {
		return h7_45;
	}
	public void setH7_45(float h7_45) {
		this.h7_45 = h7_45;
	}
	public float getH8_0() {
		return h8_0;
	}
	public void setH8_0(float h8_0) {
		this.h8_0 = h8_0;
	}
	public float getH8_15() {
		return h8_15;
	}
	public void setH8_15(float h8_15) {
		this.h8_15 = h8_15;
	}
	public float getH8_30() {
		return h8_30;
	}
	public void setH8_30(float h8_30) {
		this.h8_30 = h8_30;
	}
	public float getH8_45() {
		return h8_45;
	}
	public void setH8_45(float h8_45) {
		this.h8_45 = h8_45;
	}
	public float getH9_0() {
		return h9_0;
	}
	public void setH9_0(float h9_0) {
		this.h9_0 = h9_0;
	}
	public float getH9_15() {
		return h9_15;
	}
	public void setH9_15(float h9_15) {
		this.h9_15 = h9_15;
	}
	public float getH9_30() {
		return h9_30;
	}
	public void setH9_30(float h9_30) {
		this.h9_30 = h9_30;
	}
	public float getH9_45() {
		return h9_45;
	}
	public void setH9_45(float h9_45) {
		this.h9_45 = h9_45;
	}
	public float getH10_0() {
		return h10_0;
	}
	public void setH10_0(float h10_0) {
		this.h10_0 = h10_0;
	}
	public float getH10_15() {
		return h10_15;
	}
	public void setH10_15(float h10_15) {
		this.h10_15 = h10_15;
	}
	public float getH10_30() {
		return h10_30;
	}
	public void setH10_30(float h10_30) {
		this.h10_30 = h10_30;
	}
	public float getH10_45() {
		return h10_45;
	}
	public void setH10_45(float h10_45) {
		this.h10_45 = h10_45;
	}
	public float getH11_0() {
		return h11_0;
	}
	public void setH11_0(float h11_0) {
		this.h11_0 = h11_0;
	}
	public float getH11_15() {
		return h11_15;
	}
	public void setH11_15(float h11_15) {
		this.h11_15 = h11_15;
	}
	public float getH11_30() {
		return h11_30;
	}
	public void setH11_30(float h11_30) {
		this.h11_30 = h11_30;
	}
	public float getH11_45() {
		return h11_45;
	}
	public void setH11_45(float h11_45) {
		this.h11_45 = h11_45;
	}
	public float getH12_0() {
		return h12_0;
	}
	public void setH12_0(float h12_0) {
		this.h12_0 = h12_0;
	}
	public float getH12_15() {
		return h12_15;
	}
	public void setH12_15(float h12_15) {
		this.h12_15 = h12_15;
	}
	public float getH12_30() {
		return h12_30;
	}
	public void setH12_30(float h12_30) {
		this.h12_30 = h12_30;
	}
	public float getH12_45() {
		return h12_45;
	}
	public void setH12_45(float h12_45) {
		this.h12_45 = h12_45;
	}
	public float getH13_0() {
		return h13_0;
	}
	public void setH13_0(float h13_0) {
		this.h13_0 = h13_0;
	}
	public float getH13_15() {
		return h13_15;
	}
	public void setH13_15(float h13_15) {
		this.h13_15 = h13_15;
	}
	public float getH13_30() {
		return h13_30;
	}
	public void setH13_30(float h13_30) {
		this.h13_30 = h13_30;
	}
	public float getH13_45() {
		return h13_45;
	}
	public void setH13_45(float h13_45) {
		this.h13_45 = h13_45;
	}
	public float getH14_0() {
		return h14_0;
	}
	public void setH14_0(float h14_0) {
		this.h14_0 = h14_0;
	}
	public float getH14_15() {
		return h14_15;
	}
	public void setH14_15(float h14_15) {
		this.h14_15 = h14_15;
	}
	public float getH14_30() {
		return h14_30;
	}
	public void setH14_30(float h14_30) {
		this.h14_30 = h14_30;
	}
	public float getH14_45() {
		return h14_45;
	}
	public void setH14_45(float h14_45) {
		this.h14_45 = h14_45;
	}
	public float getH15_0() {
		return h15_0;
	}
	public void setH15_0(float h15_0) {
		this.h15_0 = h15_0;
	}
	public float getH15_15() {
		return h15_15;
	}
	public void setH15_15(float h15_15) {
		this.h15_15 = h15_15;
	}
	public float getH15_30() {
		return h15_30;
	}
	public void setH15_30(float h15_30) {
		this.h15_30 = h15_30;
	}
	public float getH15_45() {
		return h15_45;
	}
	public void setH15_45(float h15_45) {
		this.h15_45 = h15_45;
	}
	public float getH16_0() {
		return h16_0;
	}
	public void setH16_0(float h16_0) {
		this.h16_0 = h16_0;
	}
	public float getH16_15() {
		return h16_15;
	}
	public void setH16_15(float h16_15) {
		this.h16_15 = h16_15;
	}
	public float getH16_30() {
		return h16_30;
	}
	public void setH16_30(float h16_30) {
		this.h16_30 = h16_30;
	}
	public float getH16_45() {
		return h16_45;
	}
	public void setH16_45(float h16_45) {
		this.h16_45 = h16_45;
	}
	public float getH17_0() {
		return h17_0;
	}
	public void setH17_0(float h17_0) {
		this.h17_0 = h17_0;
	}
	public float getH17_15() {
		return h17_15;
	}
	public void setH17_15(float h17_15) {
		this.h17_15 = h17_15;
	}
	public float getH17_30() {
		return h17_30;
	}
	public void setH17_30(float h17_30) {
		this.h17_30 = h17_30;
	}
	public float getH17_45() {
		return h17_45;
	}
	public void setH17_45(float h17_45) {
		this.h17_45 = h17_45;
	}
	public float getH18_0() {
		return h18_0;
	}
	public void setH18_0(float h18_0) {
		this.h18_0 = h18_0;
	}
	public float getH18_15() {
		return h18_15;
	}
	public void setH18_15(float h18_15) {
		this.h18_15 = h18_15;
	}
	public float getH18_30() {
		return h18_30;
	}
	public void setH18_30(float h18_30) {
		this.h18_30 = h18_30;
	}
	public float getH18_45() {
		return h18_45;
	}
	public void setH18_45(float h18_45) {
		this.h18_45 = h18_45;
	}
	public float getH19_0() {
		return h19_0;
	}
	public void setH19_0(float h19_0) {
		this.h19_0 = h19_0;
	}
	public float getH19_15() {
		return h19_15;
	}
	public void setH19_15(float h19_15) {
		this.h19_15 = h19_15;
	}
	public float getH19_30() {
		return h19_30;
	}
	public void setH19_30(float h19_30) {
		this.h19_30 = h19_30;
	}
	public float getH19_45() {
		return h19_45;
	}
	public void setH19_45(float h19_45) {
		this.h19_45 = h19_45;
	}
	public float getH20_0() {
		return h20_0;
	}
	public void setH20_0(float h20_0) {
		this.h20_0 = h20_0;
	}
	public float getH20_15() {
		return h20_15;
	}
	public void setH20_15(float h20_15) {
		this.h20_15 = h20_15;
	}
	public float getH20_30() {
		return h20_30;
	}
	public void setH20_30(float h20_30) {
		this.h20_30 = h20_30;
	}
	public float getH20_45() {
		return h20_45;
	}
	public void setH20_45(float h20_45) {
		this.h20_45 = h20_45;
	}
	public float getH21_0() {
		return h21_0;
	}
	public void setH21_0(float h21_0) {
		this.h21_0 = h21_0;
	}
	public float getH21_15() {
		return h21_15;
	}
	public void setH21_15(float h21_15) {
		this.h21_15 = h21_15;
	}
	public float getH21_30() {
		return h21_30;
	}
	public void setH21_30(float h21_30) {
		this.h21_30 = h21_30;
	}
	public float getH21_45() {
		return h21_45;
	}
	public void setH21_45(float h21_45) {
		this.h21_45 = h21_45;
	}
	public float getH22_0() {
		return h22_0;
	}
	public void setH22_0(float h22_0) {
		this.h22_0 = h22_0;
	}
	public float getH22_15() {
		return h22_15;
	}
	public void setH22_15(float h22_15) {
		this.h22_15 = h22_15;
	}
	public float getH22_30() {
		return h22_30;
	}
	public void setH22_30(float h22_30) {
		this.h22_30 = h22_30;
	}
	public float getH22_45() {
		return h22_45;
	}
	public void setH22_45(float h22_45) {
		this.h22_45 = h22_45;
	}
	public float getH23_0() {
		return h23_0;
	}
	public void setH23_0(float h23_0) {
		this.h23_0 = h23_0;
	}
	public float getH23_15() {
		return h23_15;
	}
	public void setH23_15(float h23_15) {
		this.h23_15 = h23_15;
	}
	public float getH23_30() {
		return h23_30;
	}
	public void setH23_30(float h23_30) {
		this.h23_30 = h23_30;
	}
	public float getH23_45() {
		return h23_45;
	}
	public void setH23_45(float h23_45) {
		this.h23_45 = h23_45;
	}

	int currentIndex;
	
	public int getCurrentIndex() {
		return currentIndex;
	}

	public void setCurrentIndex(int currentIndex) {
		this.currentIndex = currentIndex;
	}

	public void setTimepoint(float value, Date timestamp)
	{
		String fieldName = DataUtil.getTimePointString(timestamp);
		try {
			setFloatField(fieldName, value);
		}catch (Exception e)
		{
			e.printStackTrace();
		}
		 
	}
	
	public void setFloatField(String fieldName, float value)
	        throws NoSuchFieldException, IllegalAccessException {
	    Field field = getClass().getDeclaredField(fieldName);
	    field.setFloat(this, value);
	}
	
    @JsonIgnore
	public List getSeriesAsList()
	{
		List l = new ArrayList();
		
		return l;
	}
//	public class series {
		float h0_0;
		float h0_15;
		float h0_30;
		float h0_45;
		float h1_0;
		float h1_15;
		float h1_30;
		float h1_45;
		float h2_0;
		float h2_15;
		float h2_30;
		float h2_45;
		float h3_0;
		float h3_15;
		float h3_30;
		float h3_45;
		float h4_0;
		float h4_15;
		float h4_30;
		float h4_45;
		float h5_0;
		float h5_15;
		float h5_30;
		float h5_45;
		float h6_0;
		float h6_15;
		float h6_30;
		float h6_45;
		float h7_0;
		float h7_15;
		float h7_30;
		float h7_45;
		float h8_0;
		float h8_15;
		float h8_30;
		float h8_45;
		float h9_0;
		float h9_15;
		float h9_30;
		float h9_45;
		float h10_0;
		float h10_15;
		float h10_30;
		float h10_45;
		float h11_0;
		float h11_15;
		float h11_30;
		float h11_45;
		float h12_0;
		float h12_15;
		float h12_30;
		float h12_45;
		float h13_0;
		float h13_15;
		float h13_30;
		float h13_45;
		float h14_0;
		float h14_15;
		float h14_30;
		float h14_45;
		float h15_0;
		float h15_15;
		float h15_30;
		float h15_45;
		float h16_0;
		float h16_15;
		float h16_30;
		float h16_45;
		float h17_0;
		float h17_15;
		float h17_30;
		float h17_45;
		float h18_0;
		float h18_15;
		float h18_30;
		float h18_45;
		float h19_0;
		float h19_15;
		float h19_30;
		float h19_45;
		float h20_0;
		float h20_15;
		float h20_30;
		float h20_45;
		float h21_0;
		float h21_15;
		float h21_30;
		float h21_45;
		float h22_0;
		float h22_15;
		float h22_30;
		float h22_45;
		float h23_0;
		float h23_15;
		float h23_30;
		float h23_45;
	//}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	public String getUnits() {
		return units;
	}

	public void setUnits(String units) {
		this.units = units;
	}
 

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	
	public double getMax() {
		return max;
	}

	public void setMax(double max) {
		this.max = max;
	}

	public double getMin() {
		return min;
	}

	public void setMin(double min) {
		this.min = min;
	}
	public double getAvg() {
		return avg;
	}

	public void setAvg(double avg) {
		this.avg = avg;
	}
}

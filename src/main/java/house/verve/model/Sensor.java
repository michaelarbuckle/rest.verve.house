/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package house.verve.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Data;

import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;


/*
 * 
PUT can create or update.
I don't see how this would help us. currently we use POST for "create a new resource ID for me" and PUT for edit a resource. 
 The PUT method requests that the enclosed entity be stored under the supplied Request-URI. If the Request-URI refers to an already existing resource, the enclosed entity SHOULD be considered as a modified version of the one residing on the origin server. I
 If the Request-URI does not point to an existing resource, and that URI is capable of being defined as a new resource by the requesting user agent, the origin server can create the resource with that URI."
That is, PUT is used to create or update.

@Id - applied at the field level to mark the field used for identiy purpose.
@Document - applied at the class level to indicate this class is a candidate for mapping to the database. You can specify the name of the collection where the database will be stored.
@DBRef - applied at the field to indicate it is to be stored using a com.mongodb.DBRef.
@Indexed - applied at the field level to describe how to index the field.
@CompoundIndex - applied at the type level to declare Compound Indexes
@GeoSpatialIndexed - applied at the field level to describe how to geoindex the field.
@Transient - by default all private fields are mapped to the document, this annotation excludes the field where it is applied from being stored in the database
@PersistenceConstructor - marks a given constructor - even a package protected one - to use when instantiating the object from the database. Constructor arguments are mapped by name to the key values in the retrieved DBObject.
@Value - this annotation is part of the Spring Framework . Within the mapping framework it can be applied to constructor arguments. This lets you use a Spring Expression Language statement to transform a key's value retrieved in the database before it is used to construct a domain object.
@Field - applied at the field level and described the name of the field as it will be represented in the MongoDB BSON document thus allowing the name to be different than the fieldname of the class.
 */

import org.springframework.data.annotation.Id;
import org.apache.commons.lang.NumberUtils;
import org.bson.types.ObjectId;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
 
@Data
//@Document(collection = "sensors")
@JsonIgnoreProperties("id")
public class Sensor  {
	 public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	 
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	 
	public String getV() {
		return value;
	}
	public void setV(String v) {
		this.value = v;
	}
	public String getU() {
		return units;
	}
	public void setU(String v) {
		this.units = v;
	}
	public String getN() {
		return name;
	}
	public void setN(String v) {
		this.name = v;
	}
	
	
	@Id private String id;
	public Sensor(String uuid)
	{
		this.id = uuid;		 
		this.uuid = uuid;
	}

	/*
	@JsonSerialize(using = ToStringSerializer.class)
	private ObjectId id;
	*/
	String name;
	String type;
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
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
	public List<Double> getDayTimeSeries() {
		return dayTimeSeries;
	}
	public void setDayTimeSeries(List<Double> dayTimeSeries) {
		this.dayTimeSeries = dayTimeSeries;
	}
	public List<Date> getDayTimeSeriesTimestamps() {
		return dayTimeSeriesTimestamps;
	}
	public void setDayTimeSeriesTimestamps(List<Date> dayTimeSeriesTimestamps) {
		this.dayTimeSeriesTimestamps = dayTimeSeriesTimestamps;
	}
	
	public void addDayTimeSeries(Double value,Date timestamp)
	{
		this.dayTimeSeries.add(value);
		this.dayTimeSeriesTimestamps.add(timestamp);
		this.dayAvg=0.0;
		this.dayMax=0.0;
		this.dayMin=0.0;
		
		
	}
	
	public void addWeekTimeSeries(Double value,Date timestamp)
	{
		this.weekTimeSeries.add(value);
		this.weekTimeSeriesTimestamps.add(timestamp);
		this.weekAvg=0.0;
		this.weekMax=0.0;
		this.weekMin=0.0;
		
		
	}
	public Double getDayMin() {
		return dayMin;
	}
	public void setDayMin(Double dayMin) {
		this.dayMin = dayMin;
	}
	public Double getDayMax() {
		return dayMax;
	}
	public void setDayMax(Double dayMax) {
		this.dayMax = dayMax;
	}
	public Double getDayAvg() {
		return dayAvg;
	}
	public void setDayAvg(Double dayAvg) {
		this.dayAvg = dayAvg;
	}
	public List<Double> getWeekTimeSeries() {
		return weekTimeSeries;
	}
	public void setWeekTimeSeries(List<Double> weekTimeSeries) {
		this.weekTimeSeries = weekTimeSeries;
	}
	public List<Date> getWeekTimeSeriesTimestamps() {
		return weekTimeSeriesTimestamps;
	}
	public void setWeekTimeSeriesTimestamps(List<Date> weekTimeSeriesTimestamps) {
		this.weekTimeSeriesTimestamps = weekTimeSeriesTimestamps;
	}
	public Double getWeekMin() {
		return weekMin;
	}
	public void setWeekMin(Double weekMin) {
		this.weekMin = weekMin;
	}
	public Double getWeekMax() {
		return weekMax;
	}
	public void setWeekMax(Double weekMax) {
		this.weekMax = weekMax;
	}
	public Double getWeekAvg() {
		return weekAvg;
	}
	public void setWeekAvg(Double weekAvg) {
		this.weekAvg = weekAvg;
	}
	public void setValue(String value) {
		this.value = value;
	}


	Double val;
	public Double getVal() {
		if (value != null)
		{
		try {	
				return Double.valueOf(value);
	  }  
	  catch(NumberFormatException nfe)  
	  {  
			return null;
	  }  
		}
		return null;
	}
	
	public String getValue() {
		return value;
	}


	String value;
 	String units;
	String timestamp;
	String model;

	String uuid;
	
	List<Double> dayTimeSeries;//
	List<Date> dayTimeSeriesTimestamps;//
	Double dayMin;
	Double dayMax;
	Double dayAvg;

	
	List<Double> weekTimeSeries;//
	List<Date> weekTimeSeriesTimestamps;
	Double weekMin;
	Double weekMax;
	Double weekAvg;
	
	@DBRef(lazy = true)
	private Device device;

	/*
	@OneToOne
	String deviceId;
	*/
	/*
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
*/
	public Device getDevice() {
		return device;
	}
	public void setDevice(Device device) {
		this.device = device;
	}
		}






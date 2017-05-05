/*
 * Copyright 2016 the original author or authors.
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

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
 
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Data
@Document(collection="deviceDatas")
public class DeviceData {

	@JsonIgnore
	@Id	private String id;

	public DeviceData(String uuid)
	{
		this.id = uuid;		 
		this.uuid = uuid;
	}
	
	public DeviceData()
	{}
	
	@Indexed(unique=true) 
	String uuid;
	String name;
	String timestamp;	

	@JsonIgnore
	String tenantId;
	 
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
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uUID) {
		this.uuid = uUID;
	}
	public String getTimestamp() {
		// TODO Auto-generated method stub
		return this.timestamp;
	}
	public void setTimestamp(String timestamp2) {
		// TODO Auto-generated method stub
		this.timestamp=timestamp2;
	}
	
	List<SensorData> sensors=new ArrayList<SensorData>();

	public List<SensorData> getSensors() {
		return sensors;
	}
	public void setSensors(List<SensorData> sensors) {
		this.sensors = sensors;
	}
	
	public void addSensor(SensorData sensor) {
		this.sensors.add(sensor);
	}
		
	public void updateSensor(SensorData sensor) {
		SensorData theSensorData=null;
		for(SensorData sd:this.sensors)
		{
			if(sd.getUuid().equals(sensor.getUuid()))
			{
				theSensorData =sd;
				break;
			}
		}
		if (theSensorData != null)
		{
			theSensorData.setValue(sensor.getValue());
			theSensorData.setTimestamp(sensor.getValue());
		} else
		{
			this.sensors.add(sensor);
		}
		}
	
	public String getTenantId() {
		return tenantId;
	}
	public void setTenantId(String uUID) {
		tenantId = uUID;
	}
	/*
	 @CreatedDate
	 private Date createdDate;
	 @CreatedBy
	 private String createdBy;
	 @LastModifiedDate
	 private Date updatedDate;
	 @LastModifiedBy
	 private String updatedBy;
*/
	@Override
	public String toString() {
		return "DeviceData [id=" + id + ", UUID=" + uuid + ", name=" + name + ", timestamp=" + timestamp + ", sensors="
				+ sensors + "]";
	}




}

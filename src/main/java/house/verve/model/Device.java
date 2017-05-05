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

import java.util.List;
import java.util.ArrayList;
 
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;


@Data
public class Device {
 
	@Id	private String id;

	public Device(String id)
	{
		this.id = id;		 
	}
	
	public Device()
	{}
	
	String name;
	String UUID;
	String SSID_key;
	String hostname;
	String ip_address;
	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	String tenantId;
	
	GeoJsonPoint location;
	 
	 
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
	public String getUUID() {
		return UUID;
	}
	public void setUUID(String uUID) {
		UUID = uUID;
	}
	public String getSSID_key() {
		return SSID_key;
	}
	public void setSSID_key(String sSID_key) {
		SSID_key = sSID_key;
	}
	public String getHostname() {
		return hostname;
	}
	public void setHostname(String hostname) {
		this.hostname = hostname;
	}
	public String getIp_address() {
		return ip_address;
	}
	public void setIp_address(String ip_address) {
		this.ip_address = ip_address;
	}
	List<String> types = new ArrayList<String>();

	public List<String> getTypes() {
		return types;
	}
	public void setType(List<String> types) {
		this.types = types;
	}
	public void AddType(String type) {
		this.types.add( type);
	}

	List<String> services = new ArrayList<String>();

	public List<String> getServices() {
		return services;
	}
	public void setService(List<String> services) {
		this.services = services;
	}
	public void AddService(String service) {
		this.services.add( service);
	}
	
/*	
	String WIFI_access_point;
	String WIFI_ap_password; 

	public String getWIFI_access_point() {
		return WIFI_access_point;
	}
	public void setWIFI_access_point(String wIFI_access_point) {
		WIFI_access_point = wIFI_access_point;
	}
	public String getWIFI_ap_password() {
		return WIFI_ap_password;
	}
	public void setWIFI_ap_password(String wIFI_ap_password) {
		WIFI_ap_password = wIFI_ap_password;
	}
*/	
/*	
	List<Sensor> sensors;

	public List<Sensor> getSensors() {
		return sensors;
	}
	public void setSensors(List<Sensor> sensors) {
		this.sensors = sensors;
	}
*/
	
}

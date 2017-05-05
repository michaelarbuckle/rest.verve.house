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

 
import java.util.List;

import lombok.Data;

import org.springframework.data.mongodb.core.geo.GeoJsonPolygon;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.mapping.Document;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
 
@Data
public class Space {
	
	@Id private String id;

	String name;
	String type;
	private GeoJsonPoint center;
	private float radius;
	/**
	 * {@code location} is stored in GeoJSON format.
	 * 
	 * <pre>
	 * <code>
	 * {
	 *   "type" : "Point",
	 *   "coordinates" : [ x, y ]
	 * }
	 * </code>
	 * </pre>
	 */
	List<GeoJsonPoint> plan;
	private String image;
	private String uUID;
	private String tenantId;
	public String getTenantID() {
		return tenantId;
	}

	public void setTenantID(String tenantID) {
		this.tenantId = tenantID;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getuUID() {
		return uUID;
	}

	public void setuUID(String uUID) {
		this.uUID = uUID;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

 

 	public Space(){}
 	 	
 	public Space(String id)
 	{
 		this.id=id;
 	}
 	
 	public String getName() {
		return name;
	}


	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}


	

//	boolean isContainer;
//	boolean isSecured;
	
	
	public GeoJsonPoint getCenter() {
		return center;
	}

	public void setCenter(GeoJsonPoint center) {
		this.center = center;
	}

	public float getRadius() {
		return radius;
	}

	public void setRadius(float radius) {
		this.radius = radius;
	}

	public List<GeoJsonPoint> getPlan() {
		return plan;
	}

	public void setPlan(List<GeoJsonPoint> plan) {
		this.plan = plan;
	}


	/*
	@JsonDeserialize(using = GeoPolygonJsonDeserializer.class)
  	GeoJsonPolygon plan;
	 */
	
	
}

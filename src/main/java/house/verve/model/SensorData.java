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
import org.bson.types.ObjectId;
import javax.persistence.OneToOne;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
 
public class SensorData {
	 
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String sensorUUID) {
		this.uuid = sensorUUID;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	private String uuid;
	String type;
	String value;
	String units;
	String timestamp;
	@Override
	public String toString() {
		return "SensorData [uuid=" + uuid + ", type=" + type + ", value=" + value + ", units=" + units
				+ ", timestamp=" + timestamp + "]";
	}

}


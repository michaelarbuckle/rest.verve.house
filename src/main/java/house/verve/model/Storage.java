package house.verve.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
/*
 * Storage Containers, Shelving, Baskets and Organizers
 */

 
import static org.springframework.data.mongodb.core.index.GeoSpatialIndexType.*;

import lombok.Value;

import java.util.UUID;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Entity to represent a {@link Store}.
 * 
 * @author Oliver Gierke
 */
@Value
@Document
public class Storage {


	@Id UUID id = UUID.randomUUID();
	

	private Date createdAt = new Date();
	
	UUID uUID;
	GeoJsonPoint location;

	/**
	 * Value object to represent an {@link Address}.
	 * 
	 * @author Oliver Gierke
	 */
	@Value
	public static class Address {

		String street, city, zip;
		@GeoSpatialIndexed(type = GEO_2DSPHERE) Point location;

		/*
		 * (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		public String toString() {
			return String.format("%s, %s %s", street, zip, city);
		}
	}
}
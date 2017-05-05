package house.verve.model;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import lombok.Data;
@Data
public class Structure {

	@Id
	private String id;

	private String streetLine1;
	private String streetLine2;
	private String city;
	private String country;
	private String googleMapsGPS;
	private String typeOfStructure;
	private String image;
	private String tenantId;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getStreetLine1() {
		return streetLine1;
	}
	public void setStreetLine1(String streetLine1) {
		this.streetLine1 = streetLine1;
	}
	public String getStreetLine2() {
		return streetLine2;
	}
	public void setStreetLine2(String streetLine2) {
		this.streetLine2 = streetLine2;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getGoogleMapsGPS() {
		return googleMapsGPS;
	}
	public void setGoogleMapsGPS(String googleMapsGPS) {
		this.googleMapsGPS = googleMapsGPS;
	}
	public String getTypeOfStructure() {
		return typeOfStructure;
	}
	public void setTypeOfStructure(String typeOfStructure) {
		this.typeOfStructure = typeOfStructure;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getTenantId() {
		return tenantId;
	}
	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

}

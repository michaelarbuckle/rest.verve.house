package house.verve.model;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "locations")
public class Location {
  @Id private String id;
  public String getId() {
	return id;
}

public void setId(String id) {
	this.id = id;
}

public String getSubject() {
	return subject;
}

public void setSubject(String subject) {
	this.subject = subject;
}

public GeoJsonPoint getLocation() {
	return location;
}

public void setLocation(GeoJsonPoint location) {
	this.location = location;
}

private String subject;
  private GeoJsonPoint location;

  public Location(final String subject, final GeoJsonPoint location) {
    this.subject = subject;
    this.location = location;
  }
  @Override
  public boolean equals(final Object o) {
    if (this == o) return true;
    if (o == null || this.getClass() != o.getClass()) return false;
    final Location that = (Location) o;
    return Objects.equals(this.getId(), that.getId()) &&
      Objects.equals(this.getSubject(), that.getSubject());
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.getId(), this.getSubject());
  }
}
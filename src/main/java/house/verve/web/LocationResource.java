package house.verve.web;

import house.verve.model.Location;
import house.verve.model.LocationRepository;
import house.verve.model.LocationEntry;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LocationResource {
  @Autowired
  private LocationRepository repository;

  @RequestMapping(value = "/locations", method = RequestMethod.GET)
  public final List<Location> getLocations(
    @RequestParam("lat") String latitude,
    @RequestParam("long") String longitude,
    @RequestParam("d") double distance,
    @RequestParam(value = "s", required = false) String subjects) {

    return this.repository.findBySubjectAndLocationNear(subjects,
      new Point(Double.valueOf(longitude), Double.valueOf(latitude)),
      new Distance(distance, Metrics.KILOMETERS));
  }

  @RequestMapping(value = "/locations", method = RequestMethod.POST)
  @ResponseStatus(HttpStatus.CREATED)
  public final void addLocations(
    @RequestParam("s") String sid,
    @RequestBody List<LocationEntry> entries) {

    List<Location> entities = new ArrayList<>();
    for (LocationEntry location : entries) {
      final GeoJsonPoint locationPoint = new GeoJsonPoint(
        Double.valueOf(location.getLongitude()),
        Double.valueOf(location.getLatitude()));

      entities.add(new Location(sid, locationPoint));
    }

    this.repository.save(entities);
  }
}

package house.verve.model;
import java.util.List;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LocationRepository extends MongoRepository<Location, String> {

	  List<Location> findBySubjectAndLocationNear(String sid, Point p, Distance d);

	}
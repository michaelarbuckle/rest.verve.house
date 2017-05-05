
package house.verve.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.Date;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;
//import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.annotation.Id;

//@RepositoryRestResource(collectionResourceRel = "timeserieslist", path = "timeserieslist")
public interface TimeseriesRepository extends MongoRepository<Timeseries, String>
//,  QueryDslPredicateExecutor<Person> 
{

	//public List<Timeseries> findBySensor(Sensor sensor);
	public List<Timeseries> findByUuid(String uuid);
	public List<Timeseries> findByUuidAndDay(String uuid, Date day);

}

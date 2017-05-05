package house.verve.model;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.geo.Polygon;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RestResource;

//import com.querydsl.core.types.dsl.StringPath;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;
//import org.springframework.data.querydsl.QueryDslPredicateExecutor;


import org.springframework.data.mongodb.repository.Query;

import java.util.List;

import org.springframework.data.annotation.Id;

//@RepositoryRestResource(collectionResourceRel = "sensors", path = "sensors")
public interface SensorRepository extends MongoRepository<Sensor, String>
{

    @RestResource(path="byDevice", rel="device")//   @RestResource(path = "name", rel = "names")
    public List<Sensor> findByDevice(@Param("id") String id);
    
    public List<Sensor> findByUuid( String uiid);


    
}


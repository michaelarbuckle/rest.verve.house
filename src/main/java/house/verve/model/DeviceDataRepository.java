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

//@RepositoryRestResource(collectionResourceRel = "deviceData", path = "deviceData")
public interface DeviceDataRepository extends MongoRepository<DeviceData, String>
{
	
	   public List<DeviceData> findByUuid(@Param("uuid") String uuid);
	 /*
	   public List<DeviceData> findAll();
	   public DeviceData findOne(String id);
	   public DeviceData save(DeviceData deviceData);
	   public DeviceData update(DeviceData deviceData);
	   public void delete(DeviceData deviceData);
	  */
	 
	/*
	@Override
	public void vote(String id, Vote vote) {
	    Query query = new Query(Criteria.where("_id").is(id).and("votes.userid").ne(vote.getUserid()));
	    Update update = new Update().inc("score", vote.getValue()).push("votes", vote);
	    mongoOperations.updateFirst(query, update, Poi.class);
	} 
	
	@Override
	  public DBObject convert(Vote vote) {
	    DBObject dbo = new BasicDBObject();
	    dbo.put("userid", new ObjectId(vote.getUserid()));
	    dbo.put("value", vote.getValue());
	    return dbo;
	  }
	*/
	
	
}


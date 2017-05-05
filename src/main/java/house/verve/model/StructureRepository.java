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

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;

import org.springframework.data.mongodb.repository.Query;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.bson.types.ObjectId;

@RepositoryRestResource(collectionResourceRel = "structures", path = "structures", itemResourceRel="structure")
public interface StructureRepository extends MongoRepository<Structure, String>
{
	
	 @RestResource(path="structuresForTenant")
	    public List<Structure> findStructureByTenantId(@Param("tenantId") String tenantId);

	/*
	 @RestResource(rel = "by-location")
		Page<Space> findByAddressLocationNear(Point location, Distance distance, Pageable pageable);
	*/
	//List<Space> findByLocationWithin(Polygon polygon);
	/*
	private List<Space> getFilteredList()
    {
    	// get a single collection
  	  DBCollection collection = db.getCollection("spaces");

    	 BasicDBObject andQuery = new BasicDBObject();

    	 List<BasicDBObject> obj = new ArrayList<BasicDBObject>();
   	  obj.add(new BasicDBObject("number", 2));
   	  obj.add(new BasicDBObject("name", "mkyong-2"));
   	  andQuery.put("$and", obj);

   	  System.out.println(andQuery.toString());

   	  com.mongodb.DBCursor cursor7 = collection.find(andQuery);
   	  while (cursor7.hasNext()) {
   		System.out.println(cursor7.next());
   	  }
	*/
}


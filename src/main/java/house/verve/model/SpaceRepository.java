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
import org.bson.types.ObjectId;

@RepositoryRestResource(collectionResourceRel = "spaces", path = "spaces")
public interface SpaceRepository extends MongoRepository<Space, String>
{
	  @RestResource(path="spacesForTenant")
	    public List<Space> findSpaceByTenantId(@Param("tenantId") String tenantId);

	/*
	 @RestResource(rel = "by-location")
		Page<Space> findByAddressLocationNear(Point location, Distance distance, Pageable pageable);
	*/
	//List<Space> findByLocationWithin(Polygon polygon);
	
	
	
}


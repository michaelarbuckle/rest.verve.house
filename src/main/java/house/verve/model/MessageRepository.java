
package house.verve.model;

 
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
 
@RepositoryRestResource(collectionResourceRel = "messages", path = "messages")
public interface MessageRepository extends MongoRepository<Message, String>
//,  QueryDslPredicateExecutor<Person> 
{


}

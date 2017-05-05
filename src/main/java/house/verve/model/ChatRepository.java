package house.verve.model;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "chats", path = "chats")

public interface ChatRepository extends MongoRepository<Chat, String>
{
	

}

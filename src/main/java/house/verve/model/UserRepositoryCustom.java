package house.verve.model;
import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepositoryCustom {


	public User registerNewUser(String username, String password, String email,String firstname, String lastname);
	
}

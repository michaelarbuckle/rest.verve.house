package house.verve.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;

public class UserRepositoryImpl implements UserRepositoryCustom {


	    @Autowired
		private MongoOperations operations;

		public User registerNewUser(String username, String password, String email,String firstname, String lastname)
		{
			System.out.println("UserRepoCustomImpl registerNewUser   ");
			User user = new User();
			user.setEmail(email);
			user.setFirstName(firstname);
			user.setFirstName(lastname);
			user.setFirstName(username);
			user.setFirstName(password);
			
			operations.save(user);

			System.out.println("   ");

			return user;
		}
		
}

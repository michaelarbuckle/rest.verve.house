package house.verve.security;
 
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import house.verve.model.User;
import house.verve.model.UserRepository;
@Service
public class AuthenticationService implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		User userInfo = userRepository.findByUsername(username);
		GrantedAuthority authority = new SimpleGrantedAuthority("ADMIN");//new SimpleGrantedAuthority(userInfo.getRole());
		UserDetails userDetails = (UserDetails)new org.springframework.security.core.userdetails.User(userInfo.getUsername(), 
				userInfo.getPassword(), Arrays.asList(authority));
		return userDetails;
	}
} 
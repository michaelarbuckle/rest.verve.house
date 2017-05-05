package house.verve.security;

import house.verve.model.User;
import house.verve.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
 
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Locale;

@Service
public class VerveUserDetailsService implements UserDetailsService {
 
    @Autowired
    private UserRepository userRepository;
 
    static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
 
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        String lowercaseUsername = s.toLowerCase(Locale.ENGLISH);
        
        User user = userRepository.findByUsername(s);
 
        //check if this user with this username exist, if not, throw an exception
        // and stop the login process
        if (user == null) {
            throw new UsernameNotFoundException("User details not found with this username: " + s);
        }
        String role = "admin";
     
            if (!user.isEnabled()) {
                throw new UserNotActivatedException("User " + lowercaseUsername + " was not activated");
            }
            //get the encoded password
            //String encodedPassword = passwordEncoder.encode(password);
            
                List<UserAuthority> authList = getAuthorities(role);
                return new User(lowercaseUsername,user.getPassword(),authList);
    }
//
    
    private List<UserAuthority> getAuthorities(String role) {
        List<UserAuthority> authList = new ArrayList<>();
        authList.add(new UserAuthority("ROLE_USER"));
 
        //you can also add different roles here
        //for example, the user is also an admin of the site, then you can add ROLE_ADMIN
        //so that he can view pages that are ROLE_ADMIN specific
        if (role != null && role.trim().length() > 0) {
            if (role.equals("admin")) {
                authList.add(new UserAuthority("ROLE_ADMIN"));
            }
        }
 
        return authList;
    }
}
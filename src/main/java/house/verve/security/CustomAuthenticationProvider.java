package house.verve.security;

import house.verve.model.UserRepository;
import house.verve.model.User;
import org.jboss.aerogear.security.otp.Totp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

//@Component
public class CustomAuthenticationProvider extends DaoAuthenticationProvider {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {
    	
        // final User user = userRepository.findByEmail(auth.getName());
    	 final User user = userRepository.findByUsername(auth.getName());
    		System.out.println("CustomAuthProvider foundByUsername"+user+"auth.getname"+auth.getName());
    		       
        
        if ((user == null)) {
            throw new BadCredentialsException("Invalid username or password");
        }
        
        final Authentication result = super.authenticate(auth);
  		System.out.println("CustomAuthProvider authenticate"+auth);

  		//return new UsernamePasswordAuthenticationToken(user, auth.getCredentials(), auth.getAuthorities());
  	   
  		
  		 return new UsernamePasswordAuthenticationToken(user, result.getCredentials(), result.getAuthorities());
    }

    private boolean isValidLong(String code) {
        try {
            Long.parseLong(code);
        } catch (final NumberFormatException e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}

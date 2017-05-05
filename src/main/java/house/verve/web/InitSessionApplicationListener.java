package house.verve.web;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;


@Component

public class InitSessionApplicationListener implements ApplicationListener<InteractiveAuthenticationSuccessEvent> {
	@Autowired HttpSession session;
 

	 @Override
	 public void onApplicationEvent(InteractiveAuthenticationSuccessEvent event)
	 {
	     UserDetails person = (UserDetails) event.getAuthentication().getPrincipal();
			      // ....
	        
	        session.setAttribute("currentUserId", person.getUsername().toString());
	        session.setAttribute("TENANT_ID", person.getUsername().toString());
	    	
	       //store lookup data in session
	      //   structures
	      //  zones
	      //  devices
	      //  users
	    
	}
	
}
 
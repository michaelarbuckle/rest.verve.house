package house.verve.security;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.context.annotation.Bean;

public class VerveAuthenticationEntryPoint extends LoginUrlAuthenticationEntryPoint {//implements AuthenticationEntryPoint {
    
	public VerveAuthenticationEntryPoint(String url) 
	{
		super(url); // note 3ii
	}
 
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException) throws IOException, ServletException {
        response.sendError( HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized: Authentication token was either missing or invalid." );
    }
}
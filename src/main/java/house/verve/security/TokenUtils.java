package house.verve.security;

import org.springframework.security.core.userdetails.UserDetails;

interface TokenUtils {
    String getToken(UserDetails userDetails);
    String getToken(UserDetails userDetails, Long expiration);
    boolean validate(String token);
    UserDetails getUserFromToken(String token);
}
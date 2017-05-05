package house.verve.web;

import java.io.UnsupportedEncodingException;
import java.util.Locale;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
 
import house.verve.model.User;
import house.verve.model.UserRepository;
//import house.verve.event.OnRegistrationCompleteEvent;
import house.verve.security.InvalidOldPasswordException;
import house.verve.security.UserNotFoundException;
import house.verve.security.VerveUserDetailsService;
import house.verve.security.GenericResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/registration")
public class RegistrationController {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserRepository userService;

    @Autowired
    private VerveUserDetailsService securityUserService;

   // @Autowired
   // private ICaptchaService captchaService;

    @Autowired
    private MessageSource messages;

    /*
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private ApplicationEventPublisher eventPublisher;
*/
    @Autowired
    private Environment env;

    public static String QR_PREFIX = "https://chart.googleapis.com/chart?chs=200x200&chld=M%%7C0&cht=qr&chl=";
    
    
    public RegistrationController() {
        super();
    }

 

    private boolean isAuthenticated(Authentication authentication) {
        return authentication != null && !(authentication instanceof AnonymousAuthenticationToken) && authentication.isAuthenticated();
    }
    
     @RequestMapping(value = "/registerNewUser", method = RequestMethod.POST)
    @ResponseBody
    public GenericResponse registerUserAccount(@RequestBody  final User accountDto, final HttpServletRequest request) {
        LOGGER.error("Registering user account with information: {}", accountDto);
        accountDto.setPassword(passwordEncoder().encode( accountDto.getPassword()));
        
        final User registered = userService.save(accountDto);
        LOGGER.error("Saved {}", registered);
     //   eventPublisher.publishEvent(new OnRegistrationCompleteEvent(registered, request.getLocale(), getAppUrl(request)));
        return new GenericResponse("success "+registered.getId());
    }

    @RequestMapping(value = "/changePassword", method = RequestMethod.POST)
    @ResponseBody
    public GenericResponse changeUserPassword(final Locale locale, @Valid String newPassword,HttpSession session) {
    	
        final User user = userService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        /*
        if (!userService.checkIfValidOldPassword(user, passwordDto.getOldPassword())) {
            throw new InvalidOldPasswordException();
        }
        */
        newPassword = passwordEncoder().encode(newPassword);
        user.setPassword(newPassword);
       // userService.save(user, newPassword);
        return new GenericResponse(messages.getMessage("message.updatePasswordSuc", null, locale));
    }
 
 

 
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    private String getAppUrl(HttpServletRequest request) {
        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }

}

package house.verve.web;
import java.security.Principal;
import java.io.UnsupportedEncodingException;
import java.util.Locale;
import java.util.UUID;
import java.util.ArrayList;
import java.util.List;

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
import org.springframework.data.repository.query.Param;
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

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;

import house.verve.model.SessionInfo;
import house.verve.model.Space;
import house.verve.model.Device;
import house.verve.model.SpaceRole;
import house.verve.model.Structure;
import house.verve.model.Sensor;
import house.verve.model.DeviceRepository;
import house.verve.model.SpaceRepository;
import house.verve.model.SpaceRoleRepository;
import house.verve.model.StructureRepository;
import house.verve.model.SensorRepository;

@RestController
//@RequestMapping("/registration")
public class SessionController {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserRepository userRepository;
   
    @Autowired
    private MessageSource messages;
    
    @Autowired
    private DeviceRepository deviceRepository;
    @Autowired
    private SpaceRepository spaceRepository;
    @Autowired
    private SpaceRoleRepository spaceRoleRepository;
    @Autowired
    private StructureRepository structureRepository;
    @Autowired
    private SensorRepository sensorRepository;

    
    @Autowired
    private Environment env;

    
    
    public SessionController() {
        super();
    }

   
    
    @RequestMapping(value = "/sessionInfo", method = RequestMethod.GET)
    @ResponseBody
    public SessionInfo sessionInfo(final Locale locale, Principal principal,HttpSession session) {

    	String tenantId = (String)session.getAttribute("TENANT_ID");
    	
    	SessionInfo si = new SessionInfo();
    	if (tenantId != null)
    	{
    		List<User> users = this.userRepository.findAll();
    		si.setUsers(users);
    		List<Device> devices = this.deviceRepository.findAll();
	    	si.setDevices(devices);
	    	List<Space> spaces = this.spaceRepository.findAll();
	    	si.setSpaces(spaces);
	    	List<Structure> structures = this.structureRepository.findAll();
	    	si.setStructures(structures);
	    	/*
	    	List<User> users = this.userRepository.findUserByTenantId(tenantId);
    		si.setUsers(users);
    		List<Device> devices = this.deviceRepository.findDeviceByTenantId(tenantId);
	    	si.setDevices(devices);
	    	List<Space> spaces = this.spaceRepository.findSpaceByTenantId(tenantId);
	    	si.setSpaces(spaces);
	    	List<Structure> structures = this.structureRepository.findStructureByTenantId(tenantId);
	    	si.setStructures(structures);
*/
    	}  else
    	{
    		
    		List users = new ArrayList();
    		User user =this.userRepository.findByUsername(principal.getName());
    		users.add(user);
    		si.setUsers(users);
    		
    	}
    		
        return si;
    }

    

}

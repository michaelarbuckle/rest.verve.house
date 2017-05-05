package house.verve.model;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@Scope("session")
public class SessionInfo {
	
	
	
	public List<Structure> getStructures() {
		return structures;
	}
	public void setStructures(List<Structure> structures) {
		this.structures = structures;
	}
	public List<Space> getSpaces() {
		return spaces;
	}
	public void setSpaces(List<Space> spaces) {
		this.spaces = spaces;
	}
	public List<Device> getDevices() {
		return devices;
	}
	public void setDevices(List<Device> devices) {
		this.devices = devices;
	}
	public List<SpaceRole> getSpaceRoles() {
		return spaceRoles;
	}
	public void setSpaceRoles(List<SpaceRole> spaceRoles) {
		this.spaceRoles = spaceRoles;
	}
	List<Device> devices;
	List<Space> spaces;
	List<SpaceRole> spaceRoles;
	List<Structure> structures;
	List<User> users;
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	 
}

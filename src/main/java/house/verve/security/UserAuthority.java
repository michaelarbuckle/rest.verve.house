package house.verve.security;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnore;

import house.verve.model.User;
@Entity
@IdClass(UserAuthority.class)
public class UserAuthority implements GrantedAuthority {

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	@Id
	private User user;

	@NotNull
	@Id
	private String authority;
	private String role;

	public UserAuthority()
	{}

	
	public UserAuthority(String role)
	{
		this.role=role;
		this.authority=role;
		
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof UserAuthority))
			return false;

		UserAuthority ua = (UserAuthority) obj;
		return ua.getAuthority() == this.getAuthority() || ua.getAuthority().equals(this.getAuthority());
	}

	@Override
	public int hashCode() {
		return getAuthority() == null ? 0 : getAuthority().hashCode();
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + ": " + getAuthority();
	}
}
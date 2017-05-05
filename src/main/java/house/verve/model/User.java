package house.verve.model;

import org.bson.types.ObjectId;
import org.jboss.aerogear.security.otp.api.Base32;
import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import house.verve.security.UserAuthority;

/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */ 

import lombok.Data;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;

import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import org.jboss.aerogear.security.otp.api.Base32;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
//@JsonIgnoreProperties("password")
@JsonIgnoreProperties({"accountNonExpired","accountNonLocked","credentialsNonExpired"})
public class User implements UserDetails{

	//	private final @Id UUID id = UUID.randomUUID();
	
	@Override
	@JsonIgnore
	public List<UserAuthority> getAuthorities() {
		 this.authorities =new ArrayList<UserAuthority>();
	        UserAuthority ua = new UserAuthority();
	        ua.setAuthority("USER");
	        this.authorities.add(ua);
		return authorities;
	}
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	private Long expires;

	@Id private String id;
	private String username;
	private String email;
	private String firstName;
	private String lastName;
	private String password;
	private String image;
	private String tenantId;
	public String getTenantId() {
		return tenantId;
	}
	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	private List<UserAuthority> authorities;

 	 public User()
		{
			 this.secret = Base32.random();
		        this.enabled = true;
		       
		} 
		public User(String id)
		{
			this.id=id;
		}
		
	public User(String username,
	 String password)
	{
		this();
	this.username=username;	
	this.password=password;
	}
	public User(String username,
			 String password,
			 List<UserAuthority> auths)
			{
				this();
			this.username=username;	
			this.password=password;
			this.authorities=auths;
			}	
	public User(String username,
	String email,
	String firstName,
    String lastName,
	 String password)
	{
	this.username=username;	
	this.email= email;
	this.firstName=firstName;
	this.lastName=lastName;
	this.password=password;
	}
	public User(String id,
			String username,
	String email,
	String firstName,
    String lastName,
	 String password)
	{
    this.id=id;
	this.username=username;	
	this.email= email;
	this.firstName=firstName;
	this.lastName=lastName;
	this.password=password;
	}
 
    public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public boolean isUsing2FA() {
		return isUsing2FA;
	}
	public void setUsing2FA(boolean isUsing2FA) {
		this.isUsing2FA = isUsing2FA;
	}
	public String getSecret() {
		return secret;
	}
	public void setSecret(String secret) {
		this.secret = secret;
	}

	private boolean enabled;
    private boolean isUsing2FA;
    private String secret;

    /*
	 @JsonIgnore
	 @JsonProperty(value = "password")
	 @Column(name = "EncryptedPwd", length = 100)
	    public String getEncryptedPwd() {
	        return this.encryptedPwd;
	    }

	    public void setEncryptedPwd(String encryptedPwd) {
	        this.encryptedPwd = encryptedPwd;
	    }
*/
	
	
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Long getExpires()
	{
		return expires;
	}
	public void setExpires(Long e)
	{
		expires=e;
	}
	@Override
	public String toString() {
		return "User [expires=" + expires + ", id=" + id + ", username=" + username + ", email=" + email
				+ ", firstName=" + firstName + ", lastName=" + lastName + ", password=" + password + ", image=" + image
				+ ", tenantId=" + tenantId + ", enabled=" + enabled + ", secret=" + secret + "]";
	}
 
}

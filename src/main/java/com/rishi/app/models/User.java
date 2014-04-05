package com.rishi.app.models;

import java.util.Calendar;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
public class User implements UserDetails {
	/**
	 * 
	 */
	private static final long serialVersionUID = 561432636585798943L;
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
	@Column (unique=true)
    private String userName;
	@Column (unique=true)
	private String email;
	@Column (nullable=false)
	@JsonIgnore
	private String password;
	@Column
	@JsonIgnore
    private Calendar createDate;
	@Column
	@JsonIgnore
    private Calendar lastModified;
	@Column
	@JsonIgnore
	private String salt;
	
	public Long getId() {
		return id;
	}
	
	public String getSalt() {
		return salt;
	}
	
	public void setSalt(String salt) {
		this.salt = salt;
	}
	
	@Override
	public String getUsername() {
		return this.userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public Calendar getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Calendar createDate) {
		this.createDate = createDate;
	}

	public Calendar getLastModified() {
		return lastModified;
	}
	
	public void setLastModified(Calendar lastModified) {
		this.lastModified = lastModified;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}
}
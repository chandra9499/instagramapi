package com.instagramapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
	private Integer id;
	private String username;
	private String name;
	private String email;
	private String userImage;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUserImage() {
		return userImage;
	}
	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}
	public UserDto(Integer id, String username, String name, String email, String userImage) {
		super();
		this.id = id;
		this.username = username;
		this.name = name;
		this.email = email;
		this.userImage = userImage;
	}
	public UserDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}

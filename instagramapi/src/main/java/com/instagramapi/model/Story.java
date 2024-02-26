package com.instagramapi.model;

import java.time.LocalDateTime;

import com.instagramapi.dto.UserDto;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "stories")
public class Story 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name="id",column = @Column(name="user_id")),
			@AttributeOverride(name="email",column = @Column(name="user_email"))
	})
	private UserDto user;
	@NotNull
	private String image;
	private String caption;
	private LocalDateTime timeStamp;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public UserDto getUser() {
		return user;
	}
	public void setUser(UserDto user) {
		this.user = user;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getCaption() {
		return caption;
	}
	public void setCaption(String caption) {
		this.caption = caption;
	}
	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(LocalDateTime timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	public Story() {
		super();
	}
	public Story(int id, UserDto user, String image, String caption, LocalDateTime timeStamp) {
		super();
		this.id = id;
		this.user = user;
		this.image = image;
		this.caption = caption;
		this.timeStamp = timeStamp;
	}
	@Override
	public String toString() {
		return "Story [id=" + id + ", user=" + user + ", image=" + image + ", caption=" + caption + ", timeStamp="
				+ timeStamp + "]";
	}
	
	
}

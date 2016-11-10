package org.launchcode.blogz.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OrderColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "post")
public class Post extends AbstractEntity{
	private String title;
	private String body;
	private User author;
	private Date created;
	private Date modified;

	public Post(String title, String body, User author) {
		super();
		
		this.title = title;
		this.body = body;
		this.author = author; // change this
		this.created = new Date();
		this.modified = created;
		this.updated();
		
		author.addPost(this);
	}
	
	public Post() {}
	
	@NotNull
	@Column(name = "title")
	public String getTitle() {
		return this.title;
	}
	
	public void setTitle(String title) {
		this.title = title;
		this.updated();
	}
	
	@NotNull
	@Column(name = "body")
	public String getBody() {
		return this.body;
	}
	
	public void setBody(String body) {
		this.body = body;
		this.updated();
	}
	
	@ManyToOne // tells db that many posts will be for 1 author (works with @OneToMany)
	public User getAuthor() {
		return this.author;
	}
	
	@NotNull
	@OrderColumn // tells db that this is the default ordering
	@Column(name = "created")
	public Date getCreated() {
		return this.created;
	}

	@SuppressWarnings("unused")
	private void setCreated(Date created) {
		this.created = created;
	}
	
	@NotNull
	@Column(name = "modified")
	public Date getModified() {
		return this.modified;
	}
		
	@SuppressWarnings("unused")
	private void setModified(Date modified) {
		this.modified = modified;
	}
	
	public String toString() {
		return "Title: " + this.title + "\nBody:\n" + this.body + "\nCreated: " + this.created + "\nModified: " + this.modified;
	}
	
	private void updated() {
		this.modified = new Date();
	}

}

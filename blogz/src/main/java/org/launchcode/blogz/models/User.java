package org.launchcode.blogz.models;

import java.util.List;
import java.util.regex.Pattern;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity // tells db that this object should be entered into db
@Table(name = "user") // tells db which table to enter into
public class User extends AbstractEntity{
	
	private String username;
	private String passHash;
	private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
	// all posts by given user
	private List<Post> posts;
	
	public User(String username, String password) {
		super();

		if (!isValidUsername(username)) {
			throw new IllegalArgumentException("Invalid username");
		}
		this.username = username;
		this.passHash = hashPassword(password);
	}
	
	public User() {}
	
	@NotNull
	@Column(name = "username", unique = true)
	public String getUsername() {
		return this.username;
	}
	
	@SuppressWarnings("unused")
	private void setUsername(String username) {
		this.username = username;
	}
	
	@NotNull
	@Column(name = "passHash")
	public String getPassHash() {
		return this.passHash;
	}
	
	public  boolean isMatchingPassword(String password) {
		return encoder.matches(password, passHash);
	}
	
	@SuppressWarnings("unused")
	private void setPassHash(String passHash) {
		this.passHash = passHash;
	}

	public String toString() {
		return "Username: " + this.username + " / Password: " + this.passHash;
	}
	
	public static String hashPassword(String password) {
		return encoder.encode(password);
	}
	
	public static boolean isValidPassword(String password) {
		boolean validPass = Pattern.matches("[a-zA-Z0-9_-]{6,20}$", password);
		return validPass;
	}
	
	public static boolean isValidUsername(String username) {
		boolean validName = Pattern.matches("[a-zA-Z][a-zA-Z0-9_-]{4,11}", username);
		return validName;
	}
	
	protected void addPost(Post post) {
		posts.add(post);
	}
	
	@OneToMany // for every 1 user there will be many posts
	@JoinColumn(name = "author_uid") // look for all posts in posts table that matches user uid value
	public List<Post> getPosts() {
		return posts;
	}
	
	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
	
}

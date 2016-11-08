package org.launchcode.blogz.models;

import java.util.ArrayList;
import java.util.Date;

public class Post extends Entity{
	private String title;
	private String body;
	private String author;
	private final Date created;
	private Date modified;
	private static ArrayList<Post> postList = new ArrayList<Post>();

	public Post(String title, String body, String author) {
		super();
		// TODO Auto-generated constructor stub
		this.title = title;
		this.body = body;
		this.author = "AUTHOR"; // change this
		this.created = new Date();
		this.modified = created;
		Post.postList.add(this);
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public String getBody() {
		return this.body;
	}
	
	public String getAuthor() {
		return this.author;
	}
	
	public Date getCreated() {
		return this.created;
	}
	
	public Date getModified() {
		return this.modified;
	}
		
	public void modifyTitleBody(Post p, String title, String body) {
		p.title = title;
		p.body = body;
		p.modified = new Date();
	}

	public void modifyTitle(Post p, String title) {
		p.title = title;
		p.modified = new Date();
	}

	public void modifyBody(Post p, String body) {
		p.body = body;
		p.modified = new Date();
	}

	public String toString() {
		return "Title: " + this.title + "\nBody:\n" + this.body + "\nCreated: " + this.created + "\nModified: " + this.modified;
	}
	
	public static ArrayList<Post> getPostList() {
//		System.out.println(postList);
		return postList;
	}
	
//	public static void main(String args[]) {
//		Post p = new Post("hello","body");
//		Post p2 = new Post("hello","body");
//		Post p3 = new Post("hello","body");
//		Post p4 = new Post("hello","body");
//		Post p5 = new Post("hello","body");
//		Post p6 = new Post("hello","body");
//		Post p7 = new Post("hello","body");
//		System.out.println(p.getUID());
//		System.out.println(p2.getUID());
//		System.out.println(p3.getUID());
//		System.out.println(p4.getUID());
//		System.out.println(p5.getUID());
//		System.out.println(p6.getUID());
//		System.out.println(p2.getUID());
//	}
}

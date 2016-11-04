package org.launchcode.blogz.models;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class PostAndUserTest {
	
	private static User u;
	private static User u2;
	private static User u3;
	private static User u4;
	private static User u5;
	private static User u6;
	private static User u7;
	private static User u8;
	private static User u9;
	
	@BeforeClass
	public static void userVariables() {
	u = new User("newUser", "pass1");
	u2 = new User("newUser2", "pass12");
	u3 = new User("newUser3", "pass123");
	u4 = new User("newUser4", "pass1234");
	u5 = new User("newUser5", "pass12345");
	u6 = new User("newUser6", "pass123456");
	u7 = new User("newUser7", "pass1234567");
	u8 = new User("newUser8", "pass12345678");
	u9 = new User("newUser9", "pass123456789");
	}

	private static Post p;
	private static Post p2;
	private static Post p3;
	private static Post p4;
	private static Post p5;
	private static Post p6;
	private static Post p7;
	private static Post p8;
	private static Post p9;
	private static Post p10;
		
	@BeforeClass
	public static void postVariables() {
	p = new Post("title", "body");
	p2 = new Post("title2", "body2");
	p3 = new Post("title3", "body3");
	p4 = new Post("title4", "body4");
	p5 = new Post("title5", "body5");
	p6 = new Post("title6", "body6");
	p7 = new Post("title7", "body7");
	p8 = new Post("title8", "body8");
	p9 = new Post("title9", "body9");
	p10 = new Post("title", "body");
	}
	
	private Date date = new Date();
	
	// USER TESTS
	@Test
	public void createUserTest() {
		assertEquals("Did not setup new user", "Username: newUser / Password: 2n55>", u.toString());		
	}

	@Test
	public void userListTest() {
		assertEquals("Incorrect user list count", 9, User.getUserList().size(), 0);
		assertEquals("Incorrect user called", "Username: newUser2 / Password: 2n55>?", User.getUserList().get(1).toString());
		assertEquals("Incorrect user called", "Username: newUser3 / Password: 2n55>?@", User.getUserList().get(2).toString());
		assertEquals("Incorrect user called", "Username: newUser4 / Password: 2n55>?@A", User.getUserList().get(3).toString());
	}

	@Test
	public void passHashTest() {
		assertEquals("Password does not hash correctly", true, User.isValidPassword("pass1234", "2n55>?@A"));
	}

	@Test
	public void validUsernameTest() { // for some reason this adds 9 entries to userList
		assertFalse("Allows too short username", User.isValidUsername("me"));
		assertFalse("Allows too long username", User.isValidUsername("qwertyuiopasd"));
		assertFalse("Allows illegal characters", User.isValidUsername("!@#$%^"));
		assertTrue("Invalidates valid username", User.isValidUsername("aZ-_1234"));
	}
	
	@Test
	public void userUIDTest() { // for some reason this adds 9 entries to userList, postList and userList are stepping on each other, is entityList also?
		assertEquals("Incorrect UID", 6, u6.getUID(), 6);
		assertEquals("Incorrect UID", 1, u.getUID(), 1);
		assertEquals("Incorrect UID", 7, u7.getUID(), 7);
		assertEquals("Incorrect UID", 3, u3.getUID(), 3);
		assertEquals("Incorrect UID", 5, u5.getUID(), 5);
		assertEquals("Incorrect UID", 4, u4.getUID(), 4);
		assertEquals("Incorrect UID", 9, u9.getUID(), 9);
		assertEquals("Incorrect UID", 8, u8.getUID(), 8);
		assertEquals("Incorrect UID", 2, u2.getUID(), 2);
	}

	//POST TESTS
	@Test
	public void createPostTest() {
		assertEquals("Did not setup new post", "Title: title\nBody:\nbody\nCreated: " + date + "\nModified: " + date, p10.toString());
	}

	@Test
	public void postListTest() {
		assertEquals("Not all posts in postList", 10, Post.getPostList().size(), 0);
		assertEquals("Incorrect post called", "Title: title\nBody:\nbody\nCreated: " + date + "\nModified: " + date, Post.getPostList().get(0).toString());
		assertEquals("Incorrect post called", "Title: title2\nBody:\nbody2\nCreated: " + date + "\nModified: " + date, Post.getPostList().get(1).toString());
		assertEquals("Incorrect post called", "Title: title3\nBody:\nbody3\nCreated: " + date + "\nModified: " + date, Post.getPostList().get(2).toString());
	}
	
	@Test
	public void modifyTitleBodyTest() {
		p.modifyTitleBody(p, "title10", "body10");
		
		assertFalse("Post was not modified", "Title: title\nBody:\nbody\nCreated: " + date + "\nModified: " + date == p.toString());
		assertEquals("New title is incorrect", "title10", p.getTitle());
		assertEquals("New body is incorrect", "body10", p.getBody());
		assertFalse("Created date has been modified", date == p.getCreated());
		assertTrue("Modified date is incorrect", date.equals(p.getModified()));
	}

	@Test
	public void modifyTitleTest() {
		p.modifyTitle(p, "title12");
		
		assertFalse("Post was not modified", "Title: title\nBody:\nbody\nCreated: " + date + "\nModified: " + date == p.toString());
		assertEquals("New title is incorrect", "title12", p.getTitle());
		assertFalse("Created date has been modified", date == p.getCreated());
		assertTrue("Modified date is incorrect", date.equals(p.getModified()));
	}

	@Test
	public void modifyBodyTest() {
		p.modifyBody(p, "body13");
		
		assertFalse("Post was not modified", "Title: title\nBody:\nbody\nCreated: " + date + "\nModified: " + date == p.toString());
		assertEquals("New body is incorrect", "body13", p.getBody());
		assertFalse("Created date has been modified", date == p.getCreated());
		assertTrue("Modified date is incorrect", date.equals(p.getModified()));
	}
	
	@Test
	public void postUIDTest() { // for some reason this adds 9 entries to userList
		assertEquals("Incorrect UID", 15, p6.getUID(), 0);
		assertEquals("Incorrect UID", 10, p.getUID(), 0);
		assertEquals("Incorrect UID", 16, p7.getUID(), 0);
		assertEquals("Incorrect UID", 12, p3.getUID(), 0);
		assertEquals("Incorrect UID", 14, p5.getUID(), 0);
		assertEquals("Incorrect UID", 13, p4.getUID(), 0);
		assertEquals("Incorrect UID", 18, p9.getUID(), 0);
		assertEquals("Incorrect UID", 17, p8.getUID(), 0);
		assertEquals("Incorrect UID", 11, p2.getUID(), 0);
	}
	
	@Test
	public void postEqualsTest() {
		assertTrue("Object does not equal itself", p.equals(p));
		assertTrue("Object does not equal itself", p == p);
		assertFalse("2 unequal object are equal", p == p2);
		assertFalse("2 unequal object are equal", p.equals(p2));
		assertFalse("2 unequal object are equal", p2.equals(p));
		
	}
	
	@Test
	public void testUID() {
		assertEquals("Wrong number of UIDs", 19, Entity.getEntityList().size(), 0);
	}

}

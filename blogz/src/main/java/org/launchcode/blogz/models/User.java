package org.launchcode.blogz.models;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class User extends Entity{
	
	private String username;
	private String password;
	private static ArrayList<User> userList = new ArrayList<User>();

	public User(String username, String password) {
		super();
//		Scanner in = new Scanner(System.in);
//		do{
			if (isValidUsername(username)) {
				this.username = username;
//			} else {
//				System.out.println("Invalid username, must be 4-11 characters using letters, numbers, - and _\nPlease enter a valid username: ");
//				this.username = in.next();
			}
//		} while (this.username == null);
//		in.close();
		this.password = User.hashPassword(password);
		User.userList.add(this);
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public String toString() {
		return "Username: " + this.username + " / Password: " + this.password;
	}
	
	public static String hashPassword(String password) {
		int shift = 13;
		String hashPass = "";
		
		for (int i = 0; i < password.length(); i++) {
			char c = (char)(password.charAt(i) + shift);
			if (c > 'z') {
				c = (char)(password.charAt(i) - (75 - shift));
			}
			hashPass += Character.toString(c);
		}
		return hashPass;
	}
	
	public static boolean isValidPassword(String password, String hashPassword) {
		if (User.hashPassword(password).equals(hashPassword)) {
			return true;
		}
		return false;
	}
	
	public static boolean isValidUsername(String username) {
		boolean validName = Pattern.matches("[a-zA-Z][a-zA-Z0-9_-]{4,11}", username);
		return validName;
	}
	
	public static ArrayList<User> getUserList() {
//		System.out.println(userList);
		return userList;
	}
	
//	public static void main(String args[]) {
//		User u = new User("mememe", "Pass1234");
//		User u2 = new User("mememe", "Pass1234");
//		User u3 = new User("mememe", "Pass1234");
//		User u4 = new User("mememe", "Pass1234");
//		User u5 = new User("mememe", "Pass1234");
//		User u6 = new User("mememe", "Pass1234");
//		System.out.println(u.getUID());
//		System.out.println(u2.getUID());
//		System.out.println(u3.getUID());
//		System.out.println(u4.getUID());
//		System.out.println(u5.getUID());
//		System.out.println(u6.getUID());
//		System.out.println(u2.getUID());
//	}
	
}

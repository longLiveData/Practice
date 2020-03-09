package com.dataprocess.twitter;

/**
 * User class
 */
public class User {

	/**
	 * username, nickname and followers
	 */
	private String userName;
	private String nickName;
	private int followers;

	/**
	 * Constructor
	 * @param args: username, nickname, followers
	 */
	public User(String[] args){
		this.userName = args[0];
		this.nickName = args[1];
		this.followers = Integer.parseInt(args[2]);
	}

	/**
	 * return username of User
	 * @return
	 */
	public String getUserName(){
		return this.userName;
	}

	/**
	 * return nickname of User
	 * @return
	 */
	public String getNickName(){
		return this.nickName;
	}

	/**
	 * return followers number of User
	 * @return
	 */
	public int getFollowers(){
		return this.followers;
	}

	/**
	 * return a string contains all
	 * @return
	 */
	public String getUser(){
		return this.getUserName() + "  " + this.getUserName() + "  " + this.getFollowers();
	}

}

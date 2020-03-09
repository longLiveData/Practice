package com.dataprocess.twitter;

import java.util.*;

/**
 * UserList:
 * A LinkedList contains User
 */
public class UserList {

	/**
	 * define userList
	 */
	public List<User> userList;

	/**
	 * Constructor
	 */
	public UserList(){
		this.userList = new LinkedList<User>();
	}

	/**
	 * add a user into the userList
	 * @param user a user object
	 */
	public void add(User user){
		this.userList.add(user);
	}

	/**
	 * sort the list by followers
	 * use Collections, define a new compare by followers
	 */
	public void sort(){
		Collections.sort(this.userList, new FollowersCompare());
	}

	/**
	 * print top ten user of user list
	 * when load data success, sort list by followers
	 * then the first ten is the top ten
	 * notice that there may be duplication
	 * so print those who different form its previous one
	 */
	public void printTopTen(){
		int count = 0;
		int i = 0;
		String preName = "";
		while(count < 10){
			if(!this.userList.get(i).getUserName().equals(preName)){
				System.out.println(this.userList.get(i).getUser());
				preName = this.userList.get(i).getUserName();
				count += 1;
			}
			i += 1;
		}
	}
}

class FollowersCompare implements Comparator<User> {

	@Override
	public int compare(User u1, User u2) {

		if (u1.getFollowers() < u2.getFollowers()) {
			return 1;
		}
		if (u1.getFollowers() > u2.getFollowers()) {
			return -1;
		}
		return 0;
	}
}

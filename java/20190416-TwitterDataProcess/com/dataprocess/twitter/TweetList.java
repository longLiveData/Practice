package com.dataprocess.twitter;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * TweetList
 * A LinkedList contains Tweets
 */
public class TweetList {

	/**
	 * define tweetList
	 */
	public List<Tweet> tweetList;

	/**
	 * Constructor
	 */
	public TweetList(){
		this.tweetList = new LinkedList<Tweet>();
	}

	/**
	 * add a tweet into the tweetList
	 * @param tweet a tweet object
	 */
	public void add(Tweet tweet){
		this.tweetList.add(tweet);
	}

	/**
	 * sort the list by popularity
	 * use Collections, define a new compare by popularity
	 */
	public void sort(){
		Collections.sort(tweetList, new PopularityCompare());
	}

	/**
	 * print the top ten tweet in list
	 * when load data success, sort list by popularity
	 * then the first ten is the top ten
	 */
	public void printTopTen(){
		for(int i=0; i<10; i++)
		{
			System.out.println(this.tweetList.get(i).getTweet());
		}
	}

	/**
	 * search by string: if tweet has searchStr, print out the tweet
	 * str.indexOf(a): if str don't has a, return -1
	 * @param searchStr: the string you want to find
	 */
	public void searchByString(String searchStr){
		for (Tweet tweet: tweetList){
			if(tweet.getTweet().indexOf(searchStr) != -1){
				System.out.println(tweet.getTweet());
			}
		}
	}
}

/**
 * a compare used by Collections.sort
 */
class PopularityCompare implements Comparator<Tweet> {

	@Override
	public int compare(Tweet t1, Tweet t2) {

		if (t1.getPopularity() < t2.getPopularity()) {
			return 1;
		}
		if (t1.getPopularity() > t2.getPopularity()) {
			return -1;
		}
		return 0;
	}
}
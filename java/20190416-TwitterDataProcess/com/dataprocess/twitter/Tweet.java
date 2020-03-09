package com.dataprocess.twitter;

/**
 * Tweet class
 */
public class Tweet {

	private String TweetID;
	private String date;
	private String hour;
	private String userName;
	private String TweetContent;
	private int Favs;
	private int RTs;
	private String latitude;
	private String longitude;

	/**
	 * Constructor
	 * @param args TweetID, date, hour, username, TweetContent, Favs, RTs, latitude, longitude
	 */
	public Tweet(String args[]){

		this.TweetID = args[0];
		this.date = args[1];
		this.hour = args[2];
		this.userName = args[3];
		this.TweetContent = args[4];
		this.Favs = Integer.parseInt(args[5]);
		this.RTs = Integer.parseInt(args[6]);
		this.latitude = args[7];
		this.longitude = args[8];
	}

	/**
	 * get TweetID of the Tweet
	 * @return TweetID
	 */
	public String getTweetID(){
		return this.TweetID;
	}

	/**
	 * get time of the Tweet
	 * @return date string builds hour string
	 */
	public String getTime(){
		return this.date + " " + this.hour;
	}

	/**
	 * get username of the Tweet
	 * @return username
	 */
	public String getUserName(){
		return this.userName;
	}

	/**
	 * get TweetContent of the Tweet
	 * @return TweetContent
	 */
	public String getTweetContent(){
		return this.TweetContent;
	}

	/**
	 * get popularity of the Tweet to sort
	 * @return sum of Favs and RTs
	 */
	public int getPopularity(){
		int pop = this.Favs + this.RTs;
		return pop;
	}

	/**
	 * get latitude of the Tweet
	 * @return latitude
	 */
	public String getLatitude(){
		return this.latitude;
	}

	/**
	 * get longitude of the Tweet
	 * @return longitude
	 */
	public String getLongitude(){
		return this.longitude;
	}

	/**
	 * get infomation of tweet to print in TweetList.java
	 * @return string of time, username, TweetContent and popularity
	 */
	public String getTweet(){
		String tw = "";
		tw += this.getTime() + "  " + this.getUserName() + "  " + this.getTweetContent() + "  " + this.getPopularity();
		return tw;
	}
}

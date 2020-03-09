package com.dataprocess.twitter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * test class
 */
public class test {

	public static String filePath = "file/twitterDataset.xlsx";
        public static String indexPath = "file/index";

	public static void main(String arg[]) throws IOException, Exception {

		TweetList tList = new TweetList();
		UserList uList = new UserList();
		DataLoad dl = new DataLoad(filePath);

		// load data
		System.out.println("data loading...");
		ArrayList<ArrayList<String>> tableData = dl.getAllData();
		tableData.remove(0);

		for(ArrayList<String> lineInfo: tableData) {
			ArrayList<String> args = lineInfo;
			String[] tweetArgs = {args.get(0), args.get(1), args.get(2), args.get(3), args.get(5),
					args.get(6), args.get(7), args.get(8), args.get(9)};
			String[] userArgs = {args.get(3), args.get(4), args.get(10)};
			tList.add(new Tweet(tweetArgs));
			uList.add(new User(userArgs));
		}
		System.out.println("load data!\n");
                
		// sort list by Popularity
		// print top ten
		System.out.println("top ten Tweet:");
		tList.sort();
		tList.printTopTen();
		System.out.println("\n");

		// remove duplicate
		// sort list by followers
		// print top ten
		System.out.println("top ten User:");
		uList.sort();
		uList.printTopTen();
		System.out.println("\n");

		//tweet search based on string matching
		System.out.println("string matching:");
		Scanner sc = new Scanner(System.in);
		System.out.println("please input your string:");
		String searchStr = sc.nextLine();
		tList.searchByString(searchStr);
		System.out.println("\n");
                
                // full text search
                System.out.println("full text search:");
                System.out.println("creating index ...");
                IndexCreater ic = new IndexCreater();
                ic.createIndex(filePath, indexPath);
                System.out.println("index success! in " + indexPath);
                sc = new Scanner(System.in);
		System.out.println("please input your string:");
                searchStr = sc.nextLine();
                ic.searchIndexText(indexPath, searchStr);
	}
}

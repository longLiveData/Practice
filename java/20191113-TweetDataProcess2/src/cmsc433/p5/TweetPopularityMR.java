package cmsc433.p5;

import java.io.IOException;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * Map reduce which takes in a CSV file with tweets as input and output
 * key/value pairs.</br>
 * </br>
 * The key for the map reduce depends on the specified {@link TrendingParameter}
 * , <code>trendingOn</code> passed to
 * {@link #score(Job, String, String, TrendingParameter)}).
 */
public class TweetPopularityMR {

	// For your convenience...
	public static final int          TWEET_SCORE   = 1;
	public static final int          RETWEET_SCORE = 3;
	public static final int          MENTION_SCORE = 1;
	public static final int			 PAIR_SCORE = 1;

	// Is either USER, TWEET, HASHTAG, or HASHTAG_PAIR. Set for you before call to map()
	private static TrendingParameter trendingOn;

	public static class TweetMapper
	extends Mapper<Text, Text, Text, IntWritable> {

		@Override
		public void map(Text key, Text value, Context context)
				throws IOException, InterruptedException {
			// Converts the CSV line into a tweet object
			Tweet tweet = Tweet.createTweet(value.toString());

			// TODO: Your code goes here
			System.out.println(trendingOn);
			System.out.println(TrendingParameter.USER);
			if (trendingOn == TrendingParameter.USER) {
				context.write(new Text(tweet.getRetweetedUser()), new IntWritable(1));
				System.out.println(tweet.getRetweetedUser());
			} else if(trendingOn == TrendingParameter.TWEET) {
				context.write(new Text(tweet.getTextContent()), new IntWritable(1));
			} else if(trendingOn == TrendingParameter.HASHTAG) {
				for (String str: tweet.getHashtags()) {
					context.write(new Text(str), new IntWritable(1));
				}
			}
//			else if(trendingOn == TrendingParameter.HASHTAG_PAIR) {
//				keyStr = tweet.getRetweetedTweet();
//			}
		}
	}

	public static class PopularityReducer
	extends Reducer<Text, IntWritable, Text, IntWritable> {

		@Override
		public void reduce(Text key, Iterable<IntWritable> values, Context context)
				throws IOException, InterruptedException {

			// TODO: Your code goes here
			int sum =0;
			for (IntWritable val : values) {
				sum += val.get();
			}
			context.write(key, new IntWritable(sum));
		}
	}

	/**
	 * Method which performs a map reduce on a specified input CSV file and
	 * outputs the scored tweets, users, or hashtags.</br>
	 * </br>
	 * 
	 * @param job
	 * @param input
	 *          The CSV file containing tweets
	 * @param output
	 *          The output file with the scores
	 * @param trendingOn
	 *          The parameter on which to score
	 * @return true if the map reduce was successful, false otherwise.
	 * @throws Exception
	 */
	public static boolean score(Job job, String input, String output,
			TrendingParameter trendingOn) throws Exception {

		TweetPopularityMR.trendingOn = trendingOn;

		job.setJarByClass(TweetPopularityMR.class);

		// TODO: Set up map-reduce...

		job.setMapperClass(TweetMapper.class);
		job.setReducerClass(PopularityReducer.class);

		// End

		FileInputFormat.addInputPath(job, new Path(input));
		FileOutputFormat.setOutputPath(job, new Path(output));

		return job.waitForCompletion(true);
	}
}

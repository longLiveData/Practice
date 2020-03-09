import java.io.*;
import java.util.ArrayList;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * Student ID: <Please put your 7 digit student ID number here>
 *
 */

public class DNASeqCount 
{

	// The mapper class
	public static class SeqMapper extends Mapper<Object, Text, Text, LongWritable>
	{
		// save patterns
		private ArrayList<Pattern> patternList = new ArrayList<Pattern>();
		
	    /**
	     * Processes a line that is passed to it by writing a key/value pair to the context. 
	     * 
	     * @param index 	A link to the file index
	     * @param value 	The line from the file
	     * @param context 	The Hadoop context object
	     */
	    public void map(Object index, Text value, Context context) throws IOException, InterruptedException {

	    	// for each value, process each pattern in patternList
			for (Pattern pat: this.patternList){

				// get start and end
				String start = pat.getStart();
				String end = pat.getEnd();

				// get output key to write to context
				String keyString = pat.getStart() + "..." + pat.getEnd();

				// get input value
				String line = value.toString();
				int startSearchPosition = 0;

				while (true) {

					// find next startposition end next endposition
					int startPosition = line.indexOf(start, startSearchPosition);
					int endPosition = line.indexOf(end, startSearchPosition);

					// if they are all exists
					if (startPosition != -1 && endPosition != -1) {

						// if endStr is behind startStr, write it and search from the end position
						if (startPosition < endPosition) {
							// System.out.printf("'%s': %d\n", keyString, endPosition - startPosition);
							context.write(new Text(keyString), new LongWritable(endPosition - startPosition - 3));
							startSearchPosition = endPosition;

							// if endStr is behind startStr, search from the start position
						} else {
							startSearchPosition = startPosition;
						}
						// if one of them is not exists, break the loop
					} else {
						break;
					}
				}
			}
		}
	    
	    public void setup(Context context) throws IOException
	    {
	    	// process seq file here and make ArrayList<Pattern> to save pattern information

			// get pattern file by context
			String searchWordsFile = context.getConfiguration().get("searchWords");

			// process each line in pattern file to make a Pattern()
			File file = new File(searchWordsFile);
			InputStreamReader inputReader = new InputStreamReader(new FileInputStream(file));
			BufferedReader bf = new BufferedReader(inputReader);
			String str;
			while ((str = bf.readLine()) != null) {
				// cut each line into start and end parts to build a Pattern
				String[] cutLine = str.split(",");
				this.patternList.add(new Pattern(cutLine[0], cutLine[1]));
			}
			bf.close();
			inputReader.close();
	    }
	}

	// The Reducer class
	public static class SeqReducer extends Reducer<Text, LongWritable, Text, FloatWritable>
	{	
		
		/**
		 * Reduces multiple data values for a given key into a single output for that key
		 * 
		 * @param key 		The key that this particular reduce call will be operating on
		 * @param values 	An array of values associated with the given key
		 * @param context	The Hadoop context object
		 */
	    public void reduce(Text key, Iterable<LongWritable> values, Context context) throws IOException, InterruptedException
	    {
	    	// get key
			String keyString = key.toString();

			// get sum of values and times of key
			int count = 0;
			int sum = 0;
			for (LongWritable value: values){
				count += 1;
				sum += value.get();
			}

			// calc result
			float res = (float)sum / count;

			// System.out.println(key.toString());
			// System.out.println((float)sum/count);

			context.write(new Text(keyString), new FloatWritable(res));
	    }
	}

	   
	/**
	* main program that will be run, including configuration setup
	*
	* @param args		Command line arguments
	*/
	public static void main(String[] args) throws Exception
	{

		Configuration conf = new Configuration();
		conf.set("searchWords", args[1]);

		// process output
		Path outputPath = new Path(args[2]);
		FileSystem fileSystem = FileSystem.get(conf);
		if (fileSystem.exists(outputPath)){
			fileSystem.delete(outputPath, true);
		}

		Job job = Job.getInstance(conf, "Sequence Counter");
		job.setJarByClass(DNASeqCount.class);

		// Set mapper class to SeqMapper defined above
		job.setMapperClass(SeqMapper.class);
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(LongWritable.class);


		// Set combine class to SeqCombiner
		// Uncomment the following when you are ready to test your Combiner
		// job.setCombinerClass(SeqCombiner.class);

		// Set reduce class to IntSumReducer defined above
		job.setReducerClass(SeqReducer.class);
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(LongWritable.class);

	    
		// Input path is first argument when program called
		FileInputFormat.addInputPath(job, new Path(args[0]));

		// Output path is second argument when program called
		FileOutputFormat.setOutputPath(job, new Path(args[2]));

		// waitForCompletion submits the job and waits for it to complete,
		// parameter is verbose. Returns true if job succeeds.
		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}
}




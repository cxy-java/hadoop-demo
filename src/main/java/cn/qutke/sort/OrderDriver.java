package cn.qutke.sort;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class OrderDriver {

	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		String inputPath = "E:\\input\\order.txt";
		String outputPath = "E:\\output2";
		Configuration conf = new Configuration();
		conf.set("mapreduce.app-submission.cross-platform", "true");
		conf.set("mapreduce.framework.name", "local");
		Job job = Job.getInstance(conf);

		job.setJarByClass(OrderDriver.class);
		job.setMapperClass(OrderMapper.class);
		job.setReducerClass(OrderReduce.class);

		job.setMapOutputKeyClass(OrderBean.class);
		job.setMapOutputValueClass(NullWritable.class);

		job.setOutputKeyClass(OrderBean.class);
		job.setOutputValueClass(NullWritable.class);

		FileInputFormat.setInputPaths(job, new Path(inputPath));
		FileOutputFormat.setOutputPath(job, new Path(outputPath));

		job.waitForCompletion(true);
	}
}

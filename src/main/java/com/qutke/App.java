package com.qutke;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;


/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) throws IOException, URISyntaxException, InterruptedException {
		FileSystem fs = FileSystem.get(new URI("hdfs://master:9000"), new Configuration(),"hadoop");
		fs.mkdirs(new Path("/user/qutke"));
		fs.copyFromLocalFile(new Path("e:/Test.doc"), new Path("/user/qutke/"));
		fs.close();
	}
	
}

package hdfs.HDFS;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.junit.Test;

public class HadoopIOTest {

	@Test
	public void putFileToHDFS() throws IOException, InterruptedException, URISyntaxException{
		Configuration config = new Configuration();
		FileSystem fs = FileSystem.get(new URI("hdfs://master:9000"), config, "hadoop");
		InputStream input = new FileInputStream(new File("e:/Test.doc"));
		FSDataOutputStream outputStream = fs.create(new Path("/user/qutke/张三丰.doc"));
		IOUtils.copyBytes(input, outputStream, config);
		IOUtils.closeStream(outputStream);
		IOUtils.closeStream(input);
		fs.close();
	}
	
	@Test
	public void downloadFile() throws IOException, InterruptedException, URISyntaxException{
		Configuration config = new Configuration();
		FileSystem fs = FileSystem.get(new URI("hdfs://master:9000"), config, "hadoop");
		FSDataInputStream inputStream = fs.open(new Path("/user/qutke/张三丰.doc"));
		OutputStream  os = new FileOutputStream(new File("f:/张三丰.doc"));
		IOUtils.copyBytes(inputStream, os, config);
		IOUtils.closeStream(os);
		IOUtils.closeStream(inputStream);
		fs.close();
	}
}

package hdfs.HDFS;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.LocatedFileStatus;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.RemoteIterator;
import org.junit.Test;

public class HadoopTest {

	@Test
	public void createDir() throws IOException, InterruptedException, URISyntaxException {
		FileSystem fs = FileSystem.get(new URI("hdfs://master:9000"), new Configuration(), "hadoop");
		fs.mkdirs(new Path("/user/xxx"));
		fs.close();
	}

	@Test
	public void upload() throws IOException, InterruptedException, URISyntaxException {
		FileSystem fs = FileSystem.get(new URI("hdfs://master:9000"), new Configuration(), "hadoop");
		fs.copyFromLocalFile(new Path("e:/PRML_Translation .pdf"), new Path("/user/qutke"));
		fs.close();
	}

	@Test
	public void download() throws IOException, InterruptedException, URISyntaxException {
		FileSystem fs = FileSystem.get(new URI("hdfs://master:9000"), new Configuration(), "hadoop");
		fs.copyToLocalFile(false, new Path("/user/qutke/张三丰.doc"), new Path("f:/666.doc"), true);
		fs.close();
	}

	@Test
	public void delete() throws IOException, InterruptedException, URISyntaxException {
		FileSystem fs = FileSystem.get(new URI("hdfs://master:9000"), new Configuration(), "hadoop");
		boolean isDelete = fs.delete(new Path("/user/xxx"), true);
		fs.close();
	}

	@Test
	public void rename() throws IOException, InterruptedException, URISyntaxException {
		FileSystem fs = FileSystem.get(new URI("hdfs://master:9000"), new Configuration(), "hadoop");
		fs.rename(new Path("/user/qutke/xy.doc"), new Path("/xiaoyong.doc"));
		fs.close();
	}

	@Test
	public void listFiles() throws IOException, InterruptedException, URISyntaxException {
		FileSystem fs = FileSystem.get(new URI("hdfs://master:9000"), new Configuration(), "hadoop");
		RemoteIterator<LocatedFileStatus> list = fs.listFiles(new Path("/"), true);
		while (list.hasNext()) {
			LocatedFileStatus locatedFileStatus = list.next();
			System.out.println(locatedFileStatus.getPath().getName());
		}
		fs.close();
	}

	@Test
	public void listFileStatus() throws IOException, InterruptedException, URISyntaxException {
		FileSystem fs = FileSystem.get(new URI("hdfs://master:9000"), new Configuration(), "hadoop");
		FileStatus[] fileStatus = fs.listStatus(new Path("/user"));
		for (FileStatus file : fileStatus) {
			if (file.isDirectory()) {
				System.out.println("目录======" + file.getPath().getName());
			} else {
				System.out.println("文件======" + file.getPath().getName());
			}

		}
		fs.close();
	}
}

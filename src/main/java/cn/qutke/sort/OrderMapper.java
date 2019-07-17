package cn.qutke.sort;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class OrderMapper extends Mapper<LongWritable, Text, OrderBean, NullWritable> {

	@Override
	protected void map(LongWritable key, Text value,
			Mapper<LongWritable, Text, OrderBean, NullWritable>.Context context)
			throws IOException, InterruptedException {
		String content = value.toString();
		String[] str = content.split(" ");
		OrderBean order = new OrderBean();
		order.setOrder_id(Integer.parseInt(str[0]));
		order.setAmount(Double.parseDouble(str[1]));
		context.write(order, NullWritable.get());
	}


}

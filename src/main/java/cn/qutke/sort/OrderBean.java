package cn.qutke.sort;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import org.apache.hadoop.io.WritableComparable;

public class OrderBean implements WritableComparable<OrderBean> {

	private int order_id;
	private double amount;

	public OrderBean() {
		super();
	}

	public OrderBean(int order_id, double amount) {
		super();
		this.order_id = order_id;
		this.amount = amount;
	}

	public void write(DataOutput out) throws IOException {
		out.writeInt(order_id);
		out.writeDouble(amount);
	}

	public void readFields(DataInput in) throws IOException {
		order_id = in.readInt();
		amount = in.readDouble();
	}

	public int compareTo(OrderBean o) {
		int result = 0;
		if (order_id > o.getOrder_id()) {
			result = 1;
		} else if (order_id < o.getOrder_id()) {
			result = -1;
		} else {
			if (amount > o.getAmount()) {
				result = 1;
			} else if (amount < o.getAmount()) {
				result = -1;
			}
		}
		return result;
	}

	@Override
	public String toString() {
		return order_id + " " + amount;
	}

	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

}

package com.synqq.server.objects;

public class DataObject implements Comparable<DataObject> {

	String data;
	Integer size=0;
	
	public DataObject(String data) {
		// TODO Auto-generated constructor stub
		if (data != null) {	
			this.data = data;
			this.size = data.length();
		}
	}

	public String getData() {
		return data;
	}

	public Integer getSize() {
		return size;
	}

	@Override
	public String toString() {
		return "DataObject [data=" + data + ", size=" + size + "]";
	}

	@Override
	public int compareTo(DataObject arg0) {
		if (arg0 == null) {
			return -1;
		}
		return getSize().compareTo(arg0.getSize());

	}




}

package com.synqq.server.handlers;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Vector;

import com.synqq.server.objects.DataObject;

public class ServerHandler {

	Vector<DataObject> lines;
	public static final String OUTPUT_FOLDER = "C:/Users/amar-blog/Documents/blogProject/workspace/synqq/CodingTest/data/output/";
	String threadKey;
	
		
	public ServerHandler(String threadKey) {
		this.threadKey = threadKey;
		lines = new Vector<DataObject>();
	}
	public void handleData(String data){
		DataObject obj = new DataObject(data);
		lines.add(obj);
	}
	public void finish()
	{
		Collections.sort(lines);
		int numToKeep = lines.size()*90/100;
		
	     try {
	       BufferedWriter bw = new BufferedWriter(new FileWriter(OUTPUT_FOLDER + threadKey + ".txt"));
	       for (int i=0; i < numToKeep; i++) {
	    	   bw.write(lines.get(i).getData()+ System.getProperty("line.separator"));
	       }
	       bw.flush();
	       bw.close();
	     } 
	     catch (Exception e) {
	       System.err.println("Error: " + e);
	     }
	     
		for (int i=0; i < numToKeep; i++) {
			System.out.println("Lines :" + lines.get(i).getData());
		}
		
		lines.clear();
	}

}

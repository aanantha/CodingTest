package com.synqq.client;

import java.io.*;

import com.synqq.server.handlers.ServerHandler;

public class SynqqClient {

	public SynqqClient(String filename) {
		// TODO Auto-generated constructor stub
		readFile(filename);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new SynqqClient("C:/Users/amar-blog/Documents/blogProject/workspace/synqq/CodingTest/data/input/data.txt");

	}
	
	public void readFile(String filename)
	{
	     //Open the file for reading
	     try {
	       String thisLine = null;
	       BufferedReader br = new BufferedReader(new FileReader(filename));
	       String threadKey = String.valueOf(System.currentTimeMillis());
	       ServerHandler handler = new ServerHandler(threadKey);
	       while ((thisLine = br.readLine()) != null) { 
	         System.out.println(thisLine);
	         handler.handleData(thisLine);
	       } // end while 
	       handler.finish();
       
	 
	       br.close();
	       
	     } // end try
	     catch (IOException e) {
	       System.err.println("Error: " + e);
	     }
	}

}

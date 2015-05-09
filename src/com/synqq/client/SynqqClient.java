package com.synqq.client;

import java.io.*;
import java.net.*;

import com.synqq.server.handlers.ServerHandler;

public class SynqqClient {

	DatagramSocket clientSocket;
	InetAddress IPAddress;
	
	public SynqqClient(String filename) {

		try {
			clientSocket = new DatagramSocket();
			IPAddress = InetAddress.getByName("localhost");
		}
		catch (Exception e)
		{
			System.err.println("Error: " + e);
		}
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
	       	       
	       while ((thisLine = br.readLine()) != null) { 
	         System.out.println(thisLine);
	         sendData(threadKey, thisLine);
	       } // end while      
	 
	       sendData(threadKey, "EOF");
	       br.close();
	       
	     } // end try
	     catch (IOException e) {
	       System.err.println("Error: " + e);
	     }
	}
	
	public void sendData(String threadKey, String line)
	
	{
		byte[] sendData = new byte[1024];
		
		System.out.println("Client sending UDP packet:" + line);
	
	    try {
	    	sendData = (threadKey + "," +  line).getBytes();
	    	DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9877); 
	    	clientSocket.send(sendPacket);
	    }
		catch (Exception e)
		{
			System.err.println("Error: " + e);
		}
	    
	}

}

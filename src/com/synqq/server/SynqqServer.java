package com.synqq.server;

import java.io.*; 
import java.net.*;
import java.util.HashMap;

import com.synqq.server.handlers.*;

public class SynqqServer {
	
	DatagramSocket serverSocket;
	HashMap<String, ServerHandler> handlers = new HashMap<String, ServerHandler> ();

	public SynqqServer() {
		try {
			serverSocket = new DatagramSocket(9877);
			System.out.println("Server Started");
		}
		catch (Exception e) 		{
			System.err.println("Error: " + e);
			System.exit(1);
		}
		readPackets();
	}

	public static void main(String[] args) {
		
		SynqqServer synqqsrvr = new SynqqServer();
	}
	public void readPackets()
	{
		boolean done = false;
		while (!done)
		{
			try {
		            
				byte[] receiveData = new byte[1024]; 	
				DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
				serverSocket.receive(receivePacket); 
							
				if (receivePacket.getLength() > 0) {
					String sentence = new String(receivePacket.getData());
					
					if (sentence != null)
					{
						sentence = sentence.trim();
						
						
						String[] values = sentence.split(",");
						
						if (values.length == 2) {
							if (!handlers.containsKey(values[0])) {
								handlers.put(values[0], new ServerHandler(values[0]));
							}
							if (!values[1].equalsIgnoreCase("EOF")) { 
								
								handlers.get(values[0]).handleData(values[1]);
							}
							else {						
								handlers.get(values[0]).finish();
								handlers.remove(values[0]);
							}
						}
					}				
				}
				else {
					System.out.println("No data received from client");
				}
			}
			catch (Exception e)
			{
				System.err.println("Error: " + e);
				e.printStackTrace();
			}
		            
		}

	}

}

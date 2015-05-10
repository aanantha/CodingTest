package com.synqq.server;

import java.io.*; 
import java.net.*;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.synqq.server.handlers.*;

public class SynqqServerThread implements Runnable {
	
	DatagramSocket serverSocket;
	ConcurrentHashMap<String, ServerHandler> handlers = null;
	
	public SynqqServerThread(ConcurrentHashMap<String, ServerHandler> handlers, DatagramSocket serverSocket) {
		
		this.serverSocket = serverSocket;
		this.handlers = handlers;
	}

	public void run ()
	{
		boolean done = false;
		
		long threadId = Thread.currentThread().getId();
		//System.out.println("Running Thread id: " + threadId);
		
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

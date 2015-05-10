package com.synqq.server;

import java.net.DatagramSocket;
import java.util.concurrent.ConcurrentHashMap;

import com.synqq.server.handlers.ServerHandler;

public class SynqqServer {
	

	public SynqqServer() {
	}
	public static void main(String[] args) {
		DatagramSocket serverSocket=null;
		ConcurrentHashMap<String, ServerHandler> handlers=new ConcurrentHashMap<String, ServerHandler> ();
		
		try {
			serverSocket = new DatagramSocket(9877);
			System.out.println("Server Started");
		}
		catch (Exception e) 		{
			System.err.println("Error: " + e);
			System.exit(1);
		}
		
		for (int i=0; i <5; i++) {
			//System.out.println("Starting thread count=" + i);
			SynqqServerThread srvrthread = new SynqqServerThread(handlers, serverSocket);
			Thread t = new Thread(srvrthread);
	        t.start();
		}	
	}
}

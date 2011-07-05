package com.hixi_hyi.java.program;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.ServerSocket;
import java.net.Socket;

public class StringAcceptServer{

	public static void main(String[] args) throws IOException{
		if(args.length!=1){
			System.err.println("java AcceptServer port");
			System.exit(-1);
		}
		int port = Integer.parseInt(args[0]);
		while(true){
			ServerSocket serverSocket = new ServerSocket(port);
			System.out.println("IPv4:["+Inet4Address.getLocalHost()+"] IPv6:["+Inet6Address.getLocalHost()+"] PORT:["+serverSocket.getLocalPort()+"]");
			Socket sock = serverSocket.accept();
			System.out.println("Connected to echo server(from " +sock.getInetAddress().getHostAddress() +" on PORT:" +sock.getPort() +")");
			BufferedReader br = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			String s;
			while((s=br.readLine())!=null){
				System.out.println(s);
			}
			sock.close();
			serverSocket.close();
		}

	}

}

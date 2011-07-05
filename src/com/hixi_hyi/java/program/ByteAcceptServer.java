package com.hixi_hyi.java.program;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.ServerSocket;
import java.net.Socket;

public class ByteAcceptServer{

	public static void main(String[] args) throws IOException{
		if(args.length!=1){
			System.err.println("java AcceptServer port");
			System.exit(-1);
		}
		int port = Integer.parseInt(args[0]);

		ServerSocket serverSocket = new ServerSocket(port);
		System.out.println("IPv4:["+Inet4Address.getLocalHost()+"] IPv6:["+Inet6Address.getLocalHost()+"] PORT:["+serverSocket.getLocalPort()+"]");
		Socket sock = serverSocket.accept();
		System.out.println("Connected to echo server(from " +sock.getInetAddress().getHostAddress() +" on PORT:" +sock.getPort() +")");
		BufferedInputStream sockbis = new BufferedInputStream(sock.getInputStream());
		BufferedOutputStream sockbos = new BufferedOutputStream(sock.getOutputStream());
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();

		while(true){
			try{
				byte[] buf = new byte[1024];
		        int len = 0;
		        if((len=sockbis.read(buf))!=0){
		            buffer.write(buf, 0, len);
			        System.out.println(buffer.toByteArray());
			        sockbos.flush();
			        buffer.reset();
		        }
			}catch(IOException e){
				e.printStackTrace();
				break;
			}
		}

		buffer.close();
		sockbos.close();
		sockbis.close();
	}

}

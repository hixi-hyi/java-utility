package com.hixi_hyi.java;

import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.UnsupportedCommOperationException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

import sun.net.www.content.audio.wav;

public class RobotMain {
	public static void main(String[] args) throws IOException, NoSuchPortException, PortInUseException, UnsupportedCommOperationException, InterruptedException {
		Map<String, Byte> command = new HashMap<String, Byte>();
		// Geting Started Commands
		command.put("START", (byte) 128);
		command.put("BAUD", (byte) 129);
		// Mode Commands
		command.put("SAFE", (byte) 131);
		command.put("FULL", (byte) 132);
		// Cleaning Commands
		command.put("CLEAN", (byte) 135);
		command.put("MAX", (byte) 136);
		command.put("SPOT", (byte) 134);
		command.put("SEEK_DOCK", (byte) 143);
		command.put("SCHEDULE", (byte) 167);
		command.put("SET_DAY_TIME", (byte) 168);
		command.put("POWER", (byte) 133);
		// Actuator Commands
		command.put("DRIVE", (byte) 137);
		command.put("DRIVE_DIRECT", (byte) 145);
		command.put("DRIVE_PWM", (byte) 146);
		command.put("MOTORS", (byte) 138);
		command.put("PWM_MOTORS", (byte) 144);
		command.put("LEDS", (byte) 139);
		command.put("SCHEDULLING_LEDS", (byte) 162);
		command.put("DIGIT_LEDS_RAW", (byte) 163);
		command.put("DIGIT_LEDS_ASCII", (byte) 164);
		command.put("BUTTONS", (byte) 165);
		command.put("SONG", (byte) 140);
		command.put("PLAY", (byte) 141);
		// Input Command
		command.put("SENSORS", (byte) 142);
		command.put("QUERY_LIST", (byte) 149);
		command.put("STREAM", (byte) 148);
		command.put("PAUSE_RESUME_STREAM", (byte) 150);
		// System.out.println(getComPort());
//		CommPortIdentifier portid = CommPortIdentifier.getPortIdentifier("/dev/cu.ESD200v117-0CC2EC-Gener");
//		SerialPort serialPort = (SerialPort) portid.open("RobotMain", 2000);
//		InputStream in = serialPort.getInputStream();
//		 OutputStream out = serialPort.getOutputStream();
		File file = new File("/dev/cu.ESD200v117-0CC2EC-Gener");
//		while(file.canWrite()){
//			Thread.sleep(1000);
//		}
//		serialPort.setBaudBase(19200);
//        serialPort.setSerialPortParams(19200,SerialPort.DATABITS_8,SerialPort.STOPBITS_1,SerialPort.PARITY_NONE);	
		FileOutputStream out = new FileOutputStream(file);
//		 FileOutputStream out = new FileOutputStream(new
//		 File("/dev/cu.ESD200v117-0CC2EC-Gener"));
		// out.write(command.get("SAFE"));
		byte[] b = { command.get("BUTTONS"), 0x01 };
		out.write(b);
		out.flush();
//		System.out.write(b);
		System.exit(-1);
//		out.write(command.get("SAFE"));
//		out.flush();
		// out.close();
		// System.out.println(b);
		// out.write(command.get("FULL"));
		// out.write(command.get("CLEAN"));
		// out.write(command.get("BAUD"));
		// out.write(11);
		// try {
		// Thread.sleep(1000);
		// } catch (InterruptedException e) {
		// TODO 自動生成された catch ブロック
		// e.printStackTrace();
		// }
		// out.write(command.get("BUTTONS"));
		// out.write(0x01);
		// out.flush();
//		 while(true){
		// try {
		// Thread.sleep(1000);
		// } catch (InterruptedException e) {
		// TODO 自動生成された catch ブロック
		// e.printStackTrace();
		// }
		// }
		// byte[] b = { command.get("DIGIT_LEDS_ASCII"), 53, 53, 53, 53 };
		// out.write(b);
		// FileOutputStream dout = new FileOutputStream(new File("a.dat"));
		// dout.write(b);
		// System.out.println();
		// for(byte a : b){
		// System.out.println((byte)a);
		// }
		// out.write(command.get("DIGIT_LEDS_ASCII"));
		// dout.write(command.get("DIGIT_LEDS_ASCII"));
		// out.write(0x53);
		// out.write(0x53);
		// out.write(0x53);
		// out.write(0x53);
		// out.flush();
		// out.close();
	}
	
	public static java.util.List<String> getComPort() {
		java.util.List<String> portArray = new ArrayList<String>();
		Enumeration portList = CommPortIdentifier.getPortIdentifiers();
		
		CommPortIdentifier portID;
		while (portList.hasMoreElements()) {
			// リストからポートを取り出す
			portID = (CommPortIdentifier) portList.nextElement();
			
			if (!portID.isCurrentlyOwned()) {
				if (portID.getPortType() == CommPortIdentifier.PORT_SERIAL) {
					portArray.add(portID.getName());
				}
			}
		}
		
		return portArray;
	}
}

package com.globalcolosseum;

import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net.Protocol;
import com.badlogic.gdx.net.Socket;
import com.badlogic.gdx.net.SocketHints;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.Logger;

public class NetworkManager {
	protected static Profile profile;
	private int clientID;
	private Socket socket;
	private Logger logger;

	public NetworkManager() {
		logger = new Logger("Network", Logger.INFO);
	}
	
	public boolean connect(String address) {
		final int PORT = 530;
		SocketHints socketHints = new SocketHints();
        // Socket will time our in 4 seconds
        socketHints.connectTimeout = 4000;
        //create the socket and connect to the server entered in the text box ( x.x.x.x format ) on port 530
        socket = Gdx.net.newClientSocket(Protocol.TCP, address, PORT, socketHints);
		return true;
	}
	
	public JSONRPCResponse login(String screenname, String username, String password) {
		send(new JSONRPCRequest("login", new String[] { screenname, username, password }, 0));
		JSONRPCResponse response = receive();
		clientID = response.getID();
		return response;
	}
	
	public void disconnect() {
		send(new JSONRPCRequest("logout", new String[] { profile.getUsername() }, clientID));
	}
	
	public JSONRPCResponse poll() {
		send(new JSONRPCRequest("poll", new String[] { }, clientID));
		return receive();
	}
	
	public void sendRoll(short roll) {
		send(new JSONRPCRequest("postRoll", new String[] { String.valueOf(roll) }, clientID));
	}
	
	public void sendControl(String button) {
		send(new JSONRPCRequest("postControl", new String[] { button }, clientID));
	}
	
	public void sendScore(int score) {
		send(new JSONRPCRequest("postScore", new String[] { String.valueOf(score) }, clientID));
	}
	
	public void send(JSONRPCRequest request) {
		Json json = new Json();
		String content = json.toJson(request);
		
		try {
			socket.getOutputStream().write(content.getBytes());
		} catch (IOException e) {
			logger.info("Fail to send: " + content);
		}
		logger.info("Send: " + content);
	}
	
	public JSONRPCResponse receive() {
		// Read data from the socket
		StringBuilder sb = new StringBuilder();
    	int c;
    	int j = 0;
    	try {
    		do {
    			c = socket.getInputStream().read();
    			sb.append((char)c);
    			if (c == 123) {
    				j++;
    			} else if (c == 125) {
    				j--;
    			}
    		} while(c != 125 || j != 0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	String result = sb.toString().replace("com.MiniGameSDK", "com.globalcolosseum");
    	logger.info("Receive: " + result);
    	
    	Json json = new Json();
		return json.fromJson(JSONRPCResponse.class, result);
	}
}

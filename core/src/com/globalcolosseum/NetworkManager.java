package com.globalcolosseum;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net.HttpMethods;
import com.badlogic.gdx.Net.HttpRequest;
import com.badlogic.gdx.Net.HttpResponse;
import com.badlogic.gdx.Net.HttpResponseListener;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.Queue;

/**
 *
 */

public class NetworkManager {
	protected static Player player;
	private static String address;
	private Queue<JSONRPCRequest> incomingMessages;

	public NetworkManager() {
		incomingMessages = new Queue<JSONRPCRequest>();
	}
	
	public void connect(final String address, String screenname, String username, String password) {
		HttpRequest request = new HttpRequest(HttpMethods.POST);
		request.setUrl("http://" + address);
		Json json = new Json();
		JSONRPCRequest message = new JSONRPCRequest("login", new String[] { screenname, username, password }, 0);
		String content = json.toJson(message);
		request.setContent(content);
		System.out.println("Send: " + content);
		Gdx.net.sendHttpRequest(request, new HttpResponseListener() {
			@Override
			public void handleHttpResponse(HttpResponse httpResponse) {
				String result = httpResponse.getResultAsString();
				System.out.println("Received: " + result);
				Json json = new Json();
				JSONRPCResponse message = json.fromJson(JSONRPCResponse.class, result);
				NetworkManager.address = address;
				NetworkManager.player = (Player)message.getResult();
			}
			
			@Override
			public void failed(Throwable t) {
				System.out.println("something went wrong: " + t.getMessage());
			}
			
			@Override
			public void cancelled() {
				System.out.println("cancelled");
			}
		});
	}
	
	public void disconnect() {
		HttpRequest request = new HttpRequest(HttpMethods.POST);
		request.setUrl("http://" + address);
		Json json = new Json();
		JSONRPCRequest message = new JSONRPCRequest("logout", new String[] { player.getUsername() }, player.getPlayerID());
		request.setContent(json.toJson(message));
		Gdx.net.sendHttpRequest(request, new HttpResponseListener() {
			@Override
			public void handleHttpResponse(HttpResponse httpResponse) {
				String result = httpResponse.getResultAsString();
				System.out.println("Received: " + result);
			}
			
			@Override
			public void failed(Throwable t) {
				System.out.println("something went wrong: " + t.getMessage());
			}
			
			@Override
			public void cancelled() {
				System.out.println("cancelled");
			}
		});
	}
	
	public void poll() {
		HttpRequest request = new HttpRequest(HttpMethods.POST);
		request.setUrl("http://" + address);
		Json json = new Json();
		JSONRPCRequest message = new JSONRPCRequest("poll", null, player.getPlayerID());
		String content = json.toJson(message);
		request.setContent(content);
		System.out.println("Send: " + content);
		Gdx.net.sendHttpRequest(request, new HttpResponseListener() {
			@Override
			public void handleHttpResponse(HttpResponse httpResponse) {
				String result = httpResponse.getResultAsString();
				System.out.println("Received: " + result);
			}
			
			@Override
			public void failed(Throwable t) {
				System.out.println("something went wrong: " + t.getMessage());
			}
			
			@Override
			public void cancelled() {
				System.out.println("cancelled");
			}
		});
	}
	
	public void sendRoll(short roll) {
		HttpRequest request = new HttpRequest(HttpMethods.POST);
		request.setUrl("http://" + address);
		Json json = new Json();
		JSONRPCRequest message = new JSONRPCRequest("post", new String[] { "Roll", String.valueOf(roll) }, player.getPlayerID());
		String content = json.toJson(message);
		request.setContent(content);
		System.out.println("Send: " + content);
		Gdx.net.sendHttpRequest(request, new HttpResponseListener() {
			@Override
			public void handleHttpResponse(HttpResponse httpResponse) {
				String result = httpResponse.getResultAsString();
				System.out.println("Received: " + result);
			}
			
			@Override
			public void failed(Throwable t) {
				System.out.println("something went wrong: " + t.getMessage());
			}
			
			@Override
			public void cancelled() {
				System.out.println("cancelled");
			}
		});
	}
	
	public void sendGameSelection(int gameID) {
		HttpRequest request = new HttpRequest(HttpMethods.POST);
		request.setUrl("http://" + address);
		Json json = new Json();
		JSONRPCRequest message = new JSONRPCRequest("post", new String[] { "GameID", String.valueOf(gameID) }, player.getPlayerID());
		String content = json.toJson(message);
		request.setContent(content);
		System.out.println("Send: " + content);
		Gdx.net.sendHttpRequest(request, new HttpResponseListener() {
			@Override
			public void handleHttpResponse(HttpResponse httpResponse) {
				String result = httpResponse.getResultAsString();
				System.out.println("Received: " + result);
			}
			
			@Override
			public void failed(Throwable t) {
				System.out.println("something went wrong: " + t.getMessage());
			}
			
			@Override
			public void cancelled() {
				System.out.println("cancelled");
			}
		});
	}
	
	public void sendScore(int score) {
		HttpRequest request = new HttpRequest(HttpMethods.POST);
		request.setUrl("http://" + address);
		Json json = new Json();
		JSONRPCRequest message = new JSONRPCRequest("post", new String[] { "Score", String.valueOf(score) }, player.getPlayerID());
		String content = json.toJson(message);
		request.setContent(content);
		System.out.println("Send: " + content);
		Gdx.net.sendHttpRequest(request, new HttpResponseListener() {
			@Override
			public void handleHttpResponse(HttpResponse httpResponse) {
				String result = httpResponse.getResultAsString();
				System.out.println("Received: " + result);
			}
			
			@Override
			public void failed(Throwable t) {
				System.out.println("something went wrong: " + t.getMessage());
			}
			
			@Override
			public void cancelled() {
				System.out.println("cancelled");
			}
		});
	}
}

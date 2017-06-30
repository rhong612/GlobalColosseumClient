package com.globalcolosseum;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net.HttpMethods;
import com.badlogic.gdx.Net.HttpRequest;
import com.badlogic.gdx.Net.HttpResponse;
import com.badlogic.gdx.Net.HttpResponseListener;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.Queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;

/**
 *
 */

public class NetworkManager {
	protected static Profile profile;
	private static String address;
	private Queue<JSONRPCRequest> incomingMessages;
	private GlobalColosseumController controller;

	public NetworkManager(GlobalColosseumController controller) {
		this.controller = controller;
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
				String result = httpResponse.getResultAsString().replace("com.MiniGameSDK", "com.globalcolosseum");
				System.out.println("Received: " + result);
				Json json = new Json();
				JSONRPCResponse message = json.fromJson(JSONRPCResponse.class, result);
				NetworkManager.address = address;
				NetworkManager.profile = (Profile)message.getResult();
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
		JSONRPCRequest message = new JSONRPCRequest("logout", new String[] { profile.getUsername() }, profile.getPlayerID());
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
		JSONRPCRequest message = new JSONRPCRequest("poll", null, profile.getPlayerID());
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
				//Switch to roll screen
				if (message.getResult().equals("Roll")) {
					Gdx.app.postRunnable(new Runnable() {
						@Override
						public void run() {
							controller.setScreen(new DiceRollScreen(controller));
						}
					});
				}
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
		JSONRPCRequest message = new JSONRPCRequest("postRoll", new String[] { String.valueOf(roll) }, profile.getPlayerID());
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
	
	public void sendControl(String button) {
		HttpRequest request = new HttpRequest(HttpMethods.POST);
		request.setUrl("http://" + address);
		Json json = new Json();
		JSONRPCRequest message = new JSONRPCRequest("postControl", new String[] { button }, profile.getPlayerID());
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
		JSONRPCRequest message = new JSONRPCRequest("postScore", new String[] { String.valueOf(score) }, profile.getPlayerID());
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

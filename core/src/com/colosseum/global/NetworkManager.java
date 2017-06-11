package com.colosseum.global;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net.HttpMethods;
import com.badlogic.gdx.Net.HttpRequest;
import com.badlogic.gdx.Net.HttpResponse;
import com.badlogic.gdx.Net.HttpResponseListener;
import com.badlogic.gdx.net.HttpParametersUtils;
import com.badlogic.gdx.utils.Json;

/**
 *
 */

public class NetworkManager {
	protected static Player player;

	public NetworkManager() {
	}
	
	public void connect(String address, String username) {
		Map<String,String> parameters = new HashMap<String,String>();
		parameters.put("Login", username);
		HttpRequest request = new HttpRequest(HttpMethods.POST);
		request.setUrl("http://" + address);
		request.setContent(HttpParametersUtils.convertHttpParameters(parameters));
		Gdx.net.sendHttpRequest(request, new HttpResponseListener() {
			@Override
			public void handleHttpResponse(HttpResponse httpResponse) {
				Json json = new Json();
				NetworkManager.player = json.fromJson(Player.class, httpResponse.getResultAsString());
				System.out.println("Received: " + player.getName());
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

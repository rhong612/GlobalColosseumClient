package com.colosseum.global;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net.HttpMethods;
import com.badlogic.gdx.Net.HttpRequest;
import com.badlogic.gdx.Net.HttpResponse;
import com.badlogic.gdx.Net.HttpResponseListener;

/**
 *
 */

public class NetworkManager {

	public void connect(String address) {
		HttpRequest request = new HttpRequest(HttpMethods.GET);
		request.setUrl(address);
		Gdx.net.sendHttpRequest(request, new HttpResponseListener() {
			@Override
			public void handleHttpResponse(HttpResponse httpResponse) {
				System.out.println("response: " + httpResponse.getResultAsString());
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

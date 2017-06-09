package com.colosseum.global;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net.HttpMethods;
import com.badlogic.gdx.Net.HttpRequest;
import com.badlogic.gdx.Net.HttpResponse;
import com.badlogic.gdx.Net.HttpResponseListener;
import com.badlogic.gdx.Net.Protocol;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.net.ServerSocket;
import com.badlogic.gdx.net.ServerSocketHints;
import com.badlogic.gdx.net.Socket;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

/**
 *
 */

public class NetworkManager {
	
	
	public NetworkManager() {
		
	}
	
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

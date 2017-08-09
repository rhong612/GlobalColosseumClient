package com.globalcolosseum;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.colosseum.global.Arkanoid.ArkanoidMain;
import com.colosseum.global.Arkanoid.ArkanoidStart;

public class GlobalColosseumController extends Game {

	private NetworkManager networkManager;
	private String screenName;
	private String ipAddress;
	private String userName;
	private String password;

	@Override
	public void create () {
		networkManager = new NetworkManager(this);
		setScreen(new LoginScreen(this));
	}

	@Override
	public void setScreen(final Screen screen) {
		Gdx.app.postRunnable(new Runnable() {
			@Override
			public void run() {
				if (GlobalColosseumController.this.getScreen() != null) {
					GlobalColosseumController.this.getScreen().dispose();
				}

				GlobalColosseumController.super.setScreen(screen);
			}
		});
	}

	@Override
	public void dispose () {
		getScreen().dispose();
	}

	public NetworkManager getNetworkManager() {
		return networkManager;
	}

	public void setLoginInformation(String screenName, String ipAddress, String userName, String password) {
		this.screenName = screenName;
		this.ipAddress = ipAddress;
		this.userName = userName;
		this.password = password;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public String getScreenName() {
		return screenName;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

	public void startGame(String gameID) {
		//Arkanoid
		if (gameID.equals("GameID=-1428952957;Level=1")) {
			Gdx.app.postRunnable(new Runnable() {
				@Override
				public void run() {
					if (GlobalColosseumController.this.getScreen() != null) {
						GlobalColosseumController.this.getScreen().dispose();
					}

					GlobalColosseumController.super.setScreen(new ArkanoidStart(GlobalColosseumController.this));
				}
			});
		}
	}
}

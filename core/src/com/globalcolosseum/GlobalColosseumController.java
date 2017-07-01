package com.globalcolosseum;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.colosseum.global.Arkanoid.Arkanoid;

public class GlobalColosseumController extends Game {

	private NetworkManager networkManager;

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
}

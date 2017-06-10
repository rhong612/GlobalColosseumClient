package com.colosseum.global;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.colosseum.global.Arkanoid.Arkanoid;

public class GlobalColosseumController extends Game {

	private NetworkManager networkManager;

	@Override
	public void create () {
		networkManager = new NetworkManager();
		//setScreen(new Arkanoid(networkManager)); //TODO: Remove. This is for testing purposes
		setScreen(new LoginScreen(this, networkManager));
	}

	@Override
	public void setScreen(Screen screen) {
		if (this.getScreen() != null) {
			this.getScreen().dispose();
		}
		super.setScreen(screen);
	}

	@Override
	public void dispose () {
		getScreen().dispose();
	}
}

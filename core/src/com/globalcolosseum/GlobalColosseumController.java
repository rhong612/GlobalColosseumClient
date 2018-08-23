package com.globalcolosseum;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.Json;
import com.colosseum.global.Arkanoid.ArkanoidStart;
import com.globalcolosseum.PlaneGame.PlaneMain;

public class GlobalColosseumController extends Game {
	private NetworkManager networkManager;
	enum GameState
    {
        START, WAIT, ROLL, PICK, GAME;
    }
	private volatile GameState state;
	private volatile boolean POLLED;
	private Profile profile;
	
	@Override
	public void create() {
		setScreen(new LoginScreen(this));
		networkManager = new NetworkManager();
	}

	public void connect(final String address, String screenname, String username, String password) {
		if (networkManager.connect(address))
		{
			JSONRPCResponse response = networkManager.login(screenname, username, password);
			profile = (Profile)response.getResult();
			setScreen(new WaitingScreen(this));
			state = GameState.WAIT;
			POLLED = false;
		}
	}

	public NetworkManager getNetworkManager() {
		return networkManager;
	}
	
	public void poll() {
		// Have it been successfully polled yet?
		if (!POLLED) {
			POLLED = true;
			
			JSONRPCResponse response = networkManager.poll();
			if (response.getResult() != null) {
				//Switch to roll screen
				if (response.getResult().equals("roll") && state != GameState.ROLL) {
					state = GameState.ROLL;
					setScreen(new DiceRollScreen(this));
				}
				//Switch to game selection screen
				else if (response.getResult().equals("pick") && state != GameState.PICK) {
					state = GameState.PICK;
					setScreen(new GameSelectionScreen(this));
				}
				//Switch back to main screen
				else if (response.getResult().equals("disconnect")) {
					state = GameState.START;
					setScreen(new LoginScreen(this));
				}
				//Identify the correct game and start it
				else if (response.getResult().toString().contains("GameID")) {
					state = GameState.GAME;
					startGame(response.getResult().toString());
				}
			}
			
			POLLED = false;
		}
	}

	public void startGame(final String gameID) {
		//Arkanoid
		if (gameID.contains("GameID=-1428952957;")) {
			Gdx.app.postRunnable(new Runnable() {
				@Override
				public void run() {
					if (GlobalColosseumController.this.getScreen() != null) {
						GlobalColosseumController.this.getScreen().dispose();
					}

					int level = 0;
					if (gameID.contains("Level=1")) {
						level = 1;
					}
					else if (gameID.contains("Level=2")) {
						level = 2;
					}
					else {
						level = 3;
					}
					GlobalColosseumController.super.setScreen(new ArkanoidStart(GlobalColosseumController.this, level));
				}
			});
			//Plane
		} else if (gameID.contains("GameID=965296586;")) {
			Gdx.app.postRunnable(new Runnable() {
				@Override
				public void run() {
					if (GlobalColosseumController.this.getScreen() != null) {
						GlobalColosseumController.this.getScreen().dispose();
					}
					
					short level = 0;
					if (gameID.contains("Level=1")) {
						level = 1;
					}
					else if (gameID.contains("Level=2")) {
						level = 2;
					}
					else {
						level = 3;
					}
					GlobalColosseumController.super.setScreen(new PlaneMain(GlobalColosseumController.this, level));
				}
			});
		}
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
}

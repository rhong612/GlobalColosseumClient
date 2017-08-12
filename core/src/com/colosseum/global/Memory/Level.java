package com.colosseum.global.Memory;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Level implements InputProcessor

{
	private int score;
	
	private Camera camera;
	private Viewport viewport;
	
	private boolean gameOver;
	
	ArrayList<TileBoard> tileBoardList = new ArrayList<TileBoard>();
	private Tile tile;

	public Level(Viewport viewport) 
	{
		score = 0;
		gameOver = false;
		this.viewport = viewport;
	}
	
	public void changeTileColor()
	{
		if (Gdx.input.isTouched())
		{
			Vector3 click = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(click);
			tileBoardList.add(new TileBoard(tile));
		}
	}

	@Override
	public boolean keyDown(int keycode) 
	{
		return false;
	}

	@Override
	public boolean keyUp(int keycode) 
	{
		return false;
	}

	@Override
	public boolean keyTyped(char character) 
	{
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) 
	{
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) 
	{
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) 
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) 
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) 
	{
		// TODO Auto-generated method stub
		return false;
	}
	
	public boolean isGameOver()
	{
		return gameOver;
	}
	
	public int getScore()
	{
		return score;
	}
}

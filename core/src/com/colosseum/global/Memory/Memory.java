package com.colosseum.global.Memory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.globalcolosseum.GlobalColosseumController;
import com.globalcolosseum.WaitingScreen;

public class Memory implements Screen 
{
	private GlobalColosseumController controller;
	private TileBoard tileBoard;
	private Tile tile;
	private Level level;

    private ShapeRenderer renderer;
	
	private Camera camera;
	private Viewport viewport;
	
	public static final int GAME_WIDTH = 1000;
	public static final int GAME_HEIGHT = 1000;
	
		public Memory(GlobalColosseumController controller)
		{
			this.controller = controller;
			tile = new Tile(10, 10, 10, 10);
			tileBoard = new TileBoard(tile);
			renderer = new ShapeRenderer();

	        camera = new OrthographicCamera();
	        viewport = new FitViewport(1600, 900, camera);
	        viewport.apply();
	        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
	        camera.update();
	        renderer.setProjectionMatrix(camera.combined);
		}

		@Override
		public void dispose() 
		{
			tileBoard.dispose();
		}

		@Override
		public void hide() 
		{
			
		}

		@Override
		public void pause() 
		{
			// TODO Auto-generated method stub
			
		}

		@Override
		public void render(float arg0)
		{
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); //Clears the screen
			
			tileBoard.draw();
			
			if(level.isGameOver())
			{
				controller.getNetworkManager().sendScore(level.getScore());
				controller.setScreen(new WaitingScreen(controller));
			}
		}

		@Override
		public void resize(int width, int height) 
		{
			viewport.update(width, height);
			camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
			camera.update();
		}

		@Override
		public void resume() 
		{
			// TODO Auto-generated method stub
			
		}

		@Override
		public void show()
		{
			camera = new OrthographicCamera();
			viewport = new FitViewport(GAME_WIDTH, GAME_HEIGHT, camera);
			viewport.apply();
			camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
			camera.update();
			
			level = new Level(viewport);
			
			tileBoard = new TileBoard(tile);
		}
}



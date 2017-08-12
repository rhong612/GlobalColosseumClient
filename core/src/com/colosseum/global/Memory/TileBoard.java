package com.colosseum.global.Memory;

import java.util.Random;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class TileBoard extends Stage
{
	private ShapeRenderer renderer;
	
	private Tile[][] board;
	private Tile tile;
	
	public TileBoard(Tile tile)
	{
		renderer = new ShapeRenderer();
		this.tile = tile;
		board = new Tile[10][10];
	}
	
	public void draw()
	{
		for(int i = 0; i < board.length; i++)
		{
			for(int j = 0; j < board[i].length; j++)
			{
				Tile tiles = new Tile(tile.getX(), tile.getY(), tile.getWidth(), tile.getHeight());
				board[i][j] = tiles;
				
				renderer.begin(ShapeRenderer.ShapeType.Filled);
		        renderer.setColor(Color.WHITE); 
		       
		        int bottomPadding = 15;
		        int numberOfRows = 10;
		        int numberOfColumns = 10;
		        final int tileWidth = 50;
		        final int tileHeight = 45;
		        
		        for (int row = 0; row < numberOfRows; row++) 
		        {
		        	for (int column = 0; column < numberOfColumns; column++) 
		        	{
			        	renderer.rect(row * tileWidth, bottomPadding + column * tileHeight, tileWidth, tileHeight);	
		        	}
		        }
		        
				renderer.end();
			}
		}
	}
	
	//Change some tiles to red
	public void randomTilesRed()
	{
		int numberOfRedTiles = 50;
		int tileWidth = 50;
		int tileHeight = 45;
		
		for (int i = 0; i < numberOfRedTiles; i++)
		{
			
		}
		
	}
	
	public void dispose()
	{
		renderer.dispose();
	}
}
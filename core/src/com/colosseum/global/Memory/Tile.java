package com.colosseum.global.Memory;

public class Tile 
{
	private float x, y, width, height;

	public Tile(float x, float y, float width, float height)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public float getX()
	{
		return x;
	}
	
	public float getY()
	{
		return y;
	}
	
	public float getWidth()
	{
		return width;
	}

	public float getHeight()
	{
		return height;
	}

}

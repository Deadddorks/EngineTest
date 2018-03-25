package com.deaddorks.engine_test.level;

public class LevelSize
{
	
	private final int width, height;
	
	public LevelSize(final int width, final int height)
	{
		this.width = width;
		this.height = height;
	}
	
	public int getWidth()
	{
		return width;
	}
	public int getHeight()
	{
		return height;
	}
	
	@Override
	public String toString()
	{
		return "{LevelString} (width: ["+ width +"], height: ["+ height +"])";
	}
}
package com.deaddorks.engine_test.level;

public class WorldInfo
{
	
	private final int blockSize;
	private final int windowWidth;
	private final int windowHeight;
	
	public WorldInfo(final int blockSize, final int windowWidth, final int windowHeight)
	{
		this.blockSize = blockSize;
		this.windowWidth = windowWidth;
		this.windowHeight = windowHeight;
	}
	
	public int getBlockSize()
	{
		return blockSize;
	}
	
	public int getWindowWidth()
	{
		return windowWidth;
	}
	
	public int getWindowHeight()
	{
		return windowHeight;
	}
	
}

package com.deaddorks.engine_test.world;

import javaLibraries.util.math.RangeShiftFunction;

public class WorldInfo
{
	
	private final RangeShiftFunction locX, locY;
	private final RangeShiftFunction scaleX, scaleY;
	private final int windowWidth, windowHeight;
	
	public WorldInfo(final int windowWidth, final int windowHeight)
	{
		this.windowWidth = windowWidth;
		this.windowHeight = windowHeight;
		
		locX = new RangeShiftFunction(0, (float) windowWidth, -1f, 1f);
		locY = new RangeShiftFunction(0, (float) windowHeight, -1f, 1f);
		
		scaleX = new RangeShiftFunction(0, (float) windowWidth, 0, 2f);
		scaleY = new RangeShiftFunction(0, (float) windowHeight, 0, 2f);
	}
	
	public int getWindowWidth()
	{
		return windowWidth;
	}
	
	public int getWindowHeight()
	{
		return windowHeight;
	}
	
	public float locX(final int x)
	{
		return locX.oldToNew(x);
	}
	public float locY(final int y)
	{
		return locY.oldToNew(y);
	}
	
	public float scaleX(final int x)
	{
		return scaleX.oldToNew(x);
	}
	public float scaleY(final int y)
	{
		return scaleY.oldToNew(y);
	}
	
	
}

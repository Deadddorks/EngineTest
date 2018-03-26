package com.deaddorks.engine_test.world;

public class WorldState
{

	private double xOffset;
	private double yOffset;
	
	public WorldState(final double xOffset, final double yOffset)
	{
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}
	
	public void setxOffset(final double xOffset)
	{
		this.xOffset = xOffset;
	}
	public void setyOffset(final double yOffset)
	{
		this.yOffset = yOffset;
	}
	
	public double getxOffset()
	{
		return xOffset;
	}
	public double getyOffset()
	{
		return yOffset;
	}
	
}

package com.deaddorks.engine_test.blocks;

public enum BlockInfo
{
	
	DIRT(0.365f, 0.25f, 0.216f, 1.0f),
	STONE(0.38f, 0.38f, 0.38f, 1.0f),
	AIR(0.698f, 0.922f, 0.95f, 1.0f),
	LAVA(1.0f, 0.435f, 0.0f, 1.0f)
	;
	
	private final float c1, c2, c3, c4;
	
	BlockInfo(final float c1, final float c2, final float c3, final float c4)
	{
		this.c1 = c1;
		this.c2 = c2;
		this.c3 = c3;
		this.c4 = c4;
	}
	
	public float getC1()
	{
		return c1;
	}
	
	public float getC2()
	{
		return c2;
	}
	
	public float getC3()
	{
		return c3;
	}
	
	public float getC4()
	{
		return c4;
	}
	
}

package com.deaddorks.engine_test.level;

import com.deaddorks.engine_test.models.LevelModel;
import com.deaddorks.engine_test.world.WorldInfo;
import com.deaddorks.engine_test.world.WorldState;

public class Level
{
	
	private final String levelName;
	private final LevelModel levelModel;
	
	public Level(final String levelName, final char[][] array, final int blockSize, final LevelSize size, final WorldInfo world, final WorldState worldState)
	{
		this.levelName = levelName;
		this.levelModel = new LevelModel(array, blockSize, size, world, worldState);
	}
	
	public String getLevelName()
	{
		return levelName;
	}
	
	public LevelModel getLevelModel()
	{
		return levelModel;
	}
	
}

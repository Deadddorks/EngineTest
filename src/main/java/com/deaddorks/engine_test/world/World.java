package com.deaddorks.engine_test.world;

import com.deaddorks.engine.ui.UI;
import com.deaddorks.engine_test.level.Level;
import com.deaddorks.engine_test.level.LevelLoader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class World
{
	
	private final UI.FrameInfoObject frameInfoObject;
	private int dX, space;
	
	private final WorldInfo worldInfo;
	private final LevelLoader loader;
	private Level level;
	private final Queue<String> levels;
	
	public World(final UI.FrameInfoObject frameInfoObject, final WorldInfo worldInfo)
	{
		this.frameInfoObject = frameInfoObject;
		this.worldInfo = worldInfo;
		
		levels = new LinkedList<>();
		try(BufferedReader reader = new BufferedReader(new FileReader(new File("levels/levels.txt"))))
		{
			String line;
			while ((line = reader.readLine()) != null)
			{
				levels.add(line);
			}
		}
		catch (IOException e)
		{
			throw new RuntimeException("Error reading levels. " + e.getClass().getSimpleName() + ", " + e.getMessage() + ".");
		}
		
		loader = new LevelLoader(20, worldInfo, "levels/", ".tdw");
		if (hasNextLevel())
		{
			loadNextLevel();
		}
		else
		{
			throw new RuntimeException("No Levels were specified");
		}
		
	}
	
	public void gameTick()
	{
	
	}
	
	public boolean hasNextLevel()
	{
		return !levels.isEmpty();
	}
	
	public void loadNextLevel()
	{
		level = loader.load(levels.poll());
	}
	
	public Level getLevel()
	{
		return level;
	}
	
	public void updateInputs(final int dX, final int space)
	{
		this.dX = dX;
		this.space = space;
	}

}

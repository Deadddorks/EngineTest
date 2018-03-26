package com.deaddorks.engine_test.level;

import com.deaddorks.engine_test.level.exceptions.FileReadException;
import com.deaddorks.engine_test.level.exceptions.LevelFormatException;
import com.deaddorks.engine_test.level.exceptions.LevelSizeTooSmallException;
import com.deaddorks.engine_test.level.exceptions.VariedLineLengthException;
import com.deaddorks.engine_test.world.WorldInfo;
import com.deaddorks.engine_test.world.WorldState;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LevelLoader
{
	
	/*
	-------------------------
	' ' = Air
	'D' = Dirt
	'S' = Stone
	'L' = Lava
	'E' = End of level
	-------------------------
	 */
	
	private static final int MIN_LEVEL_WIDTH = 10;
	private static final int MIN_LEVEL_HEIGHT = 10;
	
	private final int blockSize;
	private final WorldInfo worldInfo;
	private final String locPath, extension;
	
	public LevelLoader(final int blockSize, final WorldInfo worldInfo,
					   final String locPath, final String extension)
	{
		this.blockSize = blockSize;
		this.worldInfo = worldInfo;
		
		this.locPath = locPath;
		this.extension = extension;
	}
	
	public Level load(final String levelName)
	{
		List<String> lines = new ArrayList<>();
		
		String line;
		try (BufferedReader reader = new BufferedReader(new FileReader(new File(locPath + levelName + extension))))
		{
			while ((line = reader.readLine()) != null)
			{
				lines.add(line);
			}
		}
		catch (IOException e)
		{
			throw new FileReadException(levelName, e);
		}
		
		LevelSize size = getLevelSize(levelName, lines);
		System.out.println(size.toString());
		validateLevelFormat(levelName, lines);
		List<String> processed = preProcessLines(lines);
		char[][] array = listToArray(levelName, size, processed);
		
		return new Level(levelName, array, blockSize, size, worldInfo, new WorldState(0, 0));
	}
	
	private static LevelSize getLevelSize(final String levelName, final List<String> lines)
	{
		int width = -1, height = lines.size();
		
		for (String line : lines)
		{
			if (width == -1)
			{
				width = line.length();
			}
			else
			{
				if (width != line.length())
				{
					throw new VariedLineLengthException(levelName);
				}
			}
		}
		
		if (width < MIN_LEVEL_WIDTH || height < MIN_LEVEL_HEIGHT)
		{
			throw new LevelSizeTooSmallException(levelName, width, height, MIN_LEVEL_WIDTH, MIN_LEVEL_HEIGHT);
		}
		
		return new LevelSize(width, height);
	}
	
	private static void validateLevelFormat(final String levelName, final List<String> lines)
	{
		String line;
		char first, last;
		String middle;
		
		for (int i = 0; i < lines.size(); i++)
		{
			line = lines.get(i);
			
			first = line.charAt(0);
			last = line.charAt(line.length() - 1);
			middle = line.substring(1, line.length() - 1);
			// System.out.println("first: ["+ Character.toString(first) +"], last: ["+ Character.toString(last) +"], middle: ["+ middle +"]");
			
			if (i == 0 || i == lines.size() - 1)
			{
				if (first != '+' || last != '+')
				{
					throw new LevelFormatException(levelName);
				}
				for (char c : middle.toCharArray())
				{
					if (c != '-')
					{
						throw new LevelFormatException(levelName);
					}
				}
			}
			else
			{
				if (first != '|' || last != '|')
				{
					throw new LevelFormatException(levelName);
				}
				for (char c : middle.toCharArray())
				{
					if (c == '+' || c == '-' || c == '|')
					{
						throw new LevelFormatException(levelName);
					}
				}
			}
		}
		
	}
	
	private static List<String> preProcessLines(final List<String> lines)
	{
		List<String> newLines = new ArrayList<>();
		
		for (String l : lines)
		{
			newLines.add(l.replaceAll("[-+|]", "S"));
		}
		
		return newLines;
	}
	
	private static char[][] listToArray(final String levelName, final LevelSize size, final List<String> lines)
	{
		char[][] array = new char[size.getHeight()][];
		
		for (int i = 0; i < lines.size(); i++)
		{
			array[i] = lines.get(i).toCharArray();
		}
		
		return array;
	}
	
}

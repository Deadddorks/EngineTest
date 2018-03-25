package com.deaddorks.engine_test.exceptions;

public class LevelLoadException extends RuntimeException
{
	public LevelLoadException()
	{
	
	}
	public LevelLoadException(final String levelName, final String message)
	{
		super("Failed to load level ["+ levelName +"]. " + message + ".");
	}
}

package com.deaddorks.engine_test.exceptions;

public class LevelFormatException extends LevelLoadException
{
	public LevelFormatException(final String levelName)
	{
		super(levelName, "Must be surrounded by +, -, |, and not contain any in the middle");
	}
}

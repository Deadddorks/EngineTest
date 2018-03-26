package com.deaddorks.engine_test.level.exceptions;

public class VariedLineLengthException extends LevelLoadException
{
	public VariedLineLengthException(final String levelName)
	{
		super(levelName, "Varied line lengths");
	}
}

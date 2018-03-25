package com.deaddorks.engine_test.exceptions;

public class VariedLineLengthException extends LevelLoadException
{
	public VariedLineLengthException(final String levelName)
	{
		super(levelName, "Varied line lengths");
	}
}

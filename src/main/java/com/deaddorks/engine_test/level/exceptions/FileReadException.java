package com.deaddorks.engine_test.level.exceptions;

import java.io.IOException;

public class FileReadException extends LevelLoadException
{
	public FileReadException(final String levelName, final IOException e)
	{
		super(levelName, e.getMessage());
	}
}

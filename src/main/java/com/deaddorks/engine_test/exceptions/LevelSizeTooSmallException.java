package com.deaddorks.engine_test.exceptions;

public class LevelSizeTooSmallException extends LevelLoadException
{
	public LevelSizeTooSmallException(final String levelName, final int width, final int height,
									  final int minWidth, final int minHeight)
	{
		super(levelName, "Level too small ["+ width +", "+ height +"]. Min is ["+ minWidth +", "+ minHeight +"]");
	}
}

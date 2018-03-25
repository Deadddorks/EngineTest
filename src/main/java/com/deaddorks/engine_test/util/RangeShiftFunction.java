package com.deaddorks.engine_test.util;

// ~~~~~~~~~~ Imports ~~~~~~~~~~
import java.security.InvalidParameterException;
// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

/**
 * Allows conversion from one range to another, allows converting from a -> b & b -> 1
 *
 * @author Deaddorks
 * @version 1.1
 */
public class RangeShiftFunction
{
	
	// ~~~~~~~~~~ Variables ~~~~~~~~~~
	private final float oldMin, oldMax, newMin, newMax;
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	/**
	 * Creates a RangeShiftFunction with the specified old range and new range
	 * @param oldMin min of old range
	 * @param oldMax max of old range
	 * @param newMin min of new range
	 * @param newMax max of new range
	 */
	public RangeShiftFunction(final float oldMin, final float oldMax, final float newMin, final float newMax)
	{
		if (oldMax - oldMin == 0 || newMax - newMin == 0)
		{
			throw new InvalidParameterException("Can not have a range of 0.");
		}
		
		this.oldMin = oldMin;
		this.oldMax = oldMax;
		this.newMin = newMin;
		this.newMax = newMax;
	}
	
	/**
	 * Converts a number from the old range to the new range
	 * @param d number to be converted
	 * @return the converted value
	 */
	public float oldToNew(final float d)
	{
		return (d - oldMin) * (newMax - newMin) / (oldMax - oldMin) + newMin;
	}
	/**
	 * Converts a number from the new range to the old range
	 * @param d number to be converted
	 * @return the converted value
	 */
	public float newToOld(final float d)
	{
		return (d - newMin) * (oldMax - oldMin) / (newMax - newMin) + oldMin;
	}
	
}
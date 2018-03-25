package com.deaddorks.engine_test.level;

import com.deaddorks.engine.buffers.IBO;
import com.deaddorks.engine.buffers.VAO;
import com.deaddorks.engine.buffers.VBO;
import com.deaddorks.engine.model.Model;
import com.deaddorks.engine.shader.Shader;
import com.deaddorks.engine_test.blocks.BlockInfo;
import com.deaddorks.engine_test.util.RangeShiftFunction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Level
{
	
	private final String levelName;
	private final char[][] array;
	private final LevelSize size;
	private final WorldInfo world;
	
	private Model model;
	
	public Level(final String levelName, final char[][] array, final LevelSize size, final WorldInfo world)
	{
		this.levelName = levelName;
		this.array = array;
		this.size = size;
		this.world = world;
	}
	
	public void buildModel(final Shader shader)
	{
		Map<Character, BlockInfo> map = new HashMap<>();
		map.put(' ', BlockInfo.AIR);
		map.put('D', BlockInfo.DIRT);
		map.put('S', BlockInfo.STONE);
		map.put('L', BlockInfo.LAVA);
		
		RangeShiftFunction xShift = new RangeShiftFunction(0, world.getWindowWidth(), -1, 1);
		RangeShiftFunction yShift = new RangeShiftFunction(0, world.getWindowHeight(), -1, 1);
		
		BlockInfo block;
		List<Float> positions = new ArrayList<>();
		List<Float> colors = new ArrayList<>();
		List<Integer> indices = new ArrayList<>();
		
		int p = 0;
		for (int i = 0; i < size.getWidth(); i++)
		{
			for (int j = 0; j < size.getHeight(); j++)
			{
				indices.add(p);
				indices.add(p+1);
				indices.add(p+2);
				indices.add(p+1);
				indices.add(p+2);
				indices.add(p+3);
				
				block = map.get(array[size.getHeight() - j - 1][i]);
				for (int k = 0; k < 4; k++)
				{
					colors.add(block.getC1());
					colors.add(block.getC2());
					colors.add(block.getC3());
					colors.add(block.getC4());
				}
				
				positions.add(xShift.oldToNew(i * world.getBlockSize()));
				positions.add(yShift.oldToNew(j * world.getBlockSize()));
				positions.add(0f);
				
				positions.add(xShift.oldToNew((i + 1) * world.getBlockSize()));
				positions.add(yShift.oldToNew(j * world.getBlockSize()));
				positions.add(0f);
				
				positions.add(xShift.oldToNew(i * world.getBlockSize()));
				positions.add(yShift.oldToNew((j + 1) * world.getBlockSize()));
				positions.add(0f);
				
				positions.add(xShift.oldToNew((i + 1) * world.getBlockSize()));
				positions.add(yShift.oldToNew((j + 1) * world.getBlockSize()));
				positions.add(0f);
				
				p += 4;
			}
		}
		
		float[] positionArray = new float[positions.size()];
		for (int i = 0; i < positions.size(); i++)
		{
			positionArray[i] = positions.get(i);
		}
		
		float[] colorArray = new float[colors.size()];
		for (int i = 0; i < colors.size(); i++)
		{
			colorArray[i] = colors.get(i);
		}
		
		int[] indiceArray = new int[indices.size()];
		for (int i = 0; i < indices.size(); i++)
		{
			indiceArray[i] = indices.get(i);
		}
		
		VAO vao = new VAO();
		vao.bind();
		vao.bindVBO(0, 3, new VBO(positionArray));
		vao.bindVBO(1, 4, new VBO(colorArray));
		VAO.unbind();
		
		model = new Model(new IBO(indiceArray), vao, shader);
		
	}
	
	public Model getModel()
	{
		return model;
	}
	
}

package com.deaddorks.engine_test.models;

import com.deaddorks.engine.model.RawModel;
import com.deaddorks.engine.shader.Shader;
import com.deaddorks.engine_test.blocks.BlockInfo;
import com.deaddorks.engine_test.level.LevelSize;
import com.deaddorks.engine_test.world.WorldInfo;
import com.deaddorks.engine_test.world.WorldState;

import java.util.HashMap;
import java.util.Map;

public class LevelModel extends RawModel
{
	
	private final int blockSize;
	
	private final Shader shader;
	private final LevelSize levelSize;
	private final WorldInfo worldInfo;
	private final WorldState worldState;
	private final BlockModel[][] blocks;
	
	public LevelModel(final char[][] array, final int blockSize, final LevelSize levelSize, final WorldInfo worldInfo, final WorldState worldState)
	{
		shader = Shader.parseShaderFromFile("shaders/entity/");
		this.levelSize = levelSize;
		this.worldInfo = worldInfo;
		this.worldState = worldState;
		
		this.blockSize = blockSize;
		
		Map<Character, BlockInfo> blockMap = new HashMap<>();
		blockMap.put(' ', BlockInfo.AIR);
		blockMap.put('D', BlockInfo.DIRT);
		blockMap.put('S', BlockInfo.STONE);
		blockMap.put('L', BlockInfo.LAVA);
		blockMap.put('W', BlockInfo.WATER);
		
		blocks = new BlockModel[levelSize.getHeight()][levelSize.getWidth()];
		for (int i = 0; i < levelSize.getHeight(); i++)
		{
			for (int j = 0; j < levelSize.getWidth(); j++)
			{
				blocks[i][j] = new BlockModel(j, i, blockSize, blockMap.get(array[i][j]), worldInfo);
			}
		}
		
	}
	
	@Override
	public void render()
	{
		shader.uniform3f("location", (float) worldState.getxOffset(), (float) worldState.getyOffset(), 0);
		
		shader.use();
		for (int i = 0; i < levelSize.getHeight(); i++)
		{
			for (int j = 0; j < levelSize.getWidth(); j++)
			{
				blocks[i][j].render();
			}
		}
		Shader.unbind();
	}
	
	@Override
	public void destroy()
	{
		shader.destroy();
		for (BlockModel[] models : blocks)
		{
			for (BlockModel model : models)
			{
				model.destroy();
			}
		}
	}
	
}

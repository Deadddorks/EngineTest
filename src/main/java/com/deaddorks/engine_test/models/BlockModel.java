package com.deaddorks.engine_test.models;

import com.deaddorks.engine.buffers.IBO;
import com.deaddorks.engine.buffers.VAO;
import com.deaddorks.engine.buffers.VBO;
import com.deaddorks.engine.model.RawModel;
import com.deaddorks.engine_test.blocks.BlockInfo;
import com.deaddorks.engine_test.world.WorldInfo;

import static org.lwjgl.opengl.GL11.*;

public class BlockModel extends RawModel
{
	
	private final IBO ibo;
	private final VAO vao;
	
	public BlockModel(final int x, final int y, final int blockWidth, final BlockInfo blockInfo, final WorldInfo worldInfo)
	{
		ibo	= new IBO(new int[] {
				0, 1, 2,
				0, 2, 3
		});
		vao = new VAO();
		vao.bind();
		vao.bindVBO(0, 3, new VBO(new float[] {
				worldInfo.locX(x * blockWidth), worldInfo.locY(y * blockWidth), 0f,
				worldInfo.locX((x + 1) * blockWidth), worldInfo.locY(y * blockWidth), 0f,
				worldInfo.locX(x * blockWidth), worldInfo.locY((y + 1) * blockWidth), 0f,
				worldInfo.locX((x + 1) * blockWidth), worldInfo.locY((y + 1) * blockWidth), 0f
		}));
		vao.bindVBO(1, 4, new VBO(new float[] {
				blockInfo.getC1(), blockInfo.getC2(), blockInfo.getC3(), blockInfo.getC4(),
				blockInfo.getC1(), blockInfo.getC2(), blockInfo.getC3(), blockInfo.getC4(),
				blockInfo.getC1(), blockInfo.getC2(), blockInfo.getC3(), blockInfo.getC4(),
				blockInfo.getC1(), blockInfo.getC2(), blockInfo.getC3(), blockInfo.getC4()
		}));
		VAO.unbind();
	}
	
	@Override
	public void render()
	{
		ibo.bind();
		vao.bind();
		
		glDrawElements(GL_TRIANGLES, ibo.getElementCount(), GL_UNSIGNED_INT, 0);
		
		IBO.unbind();
		VAO.unbind();
	}
	
	@Override
	public void destroy()
	{
		ibo.destroy();
		vao.destroy();
	}
}

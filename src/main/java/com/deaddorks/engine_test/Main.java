package com.deaddorks.engine_test;

import com.deaddorks.engine.input.Inputs;
import com.deaddorks.engine.render.Renderer;
import com.deaddorks.engine.ui.UI;
import com.deaddorks.engine_test.world.World;
import com.deaddorks.engine_test.world.WorldInfo;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

public class Main
{
	
	public static void main(String[] args)
	{
		
		UI ui = new UI("Engine Test", 1200, 800, false, false)
		{
			
			private World world;
			private WorldInfo worldInfo;
			
			@Override
			protected void init()
			{
				System.out.println("width: ["+ width +"], height: ["+ height +"]");
				worldInfo = new WorldInfo(width, height);
				world = new World(getFrameInfoObject(), worldInfo);
			}
			
			@Override
			protected void gameLoop()
			{
				glClear(GL_COLOR_BUFFER_BIT);
				
				world.gameTick();
				Renderer.renderModel(world.getLevel().getLevelModel());
				
				glfwSwapBuffers(window.getId());
				glfwPollEvents();
			}
			
			@Override
			protected void handleInputs()
			{
				if (Inputs.isKeyPressed(GLFW_KEY_ESCAPE))
				{
					glfwSetWindowShouldClose(window.getId(), true);
				}
				
				int dX = (Inputs.isKeyPressed(GLFW_KEY_LEFT) ? -1 : 0) + (Inputs.isKeyPressed(GLFW_KEY_RIGHT) ? 1 : 0);
				int space = Inputs.isKeyPressed(GLFW_KEY_SPACE) ? 1 : 0;
				
				world.updateInputs(dX, space);
				
			}
			
			@Override
			protected void cleanUp()
			{
			
			}
			
			
		};
		
		ui.run();
		
	}
	
}

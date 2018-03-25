package com.deaddorks.engine_test;

import com.deaddorks.engine.model.Model;
import com.deaddorks.engine.render.Renderer;
import com.deaddorks.engine.shader.Shader;
import com.deaddorks.engine.ui.UI;
import com.deaddorks.engine_test.level.Level;
import com.deaddorks.engine_test.level.LevelLoader;

import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;

public class Main
{
	
	public static void main(String[] args)
	{
		
		
		
		UI ui = new UI("Engine Test", 750, 500, false)
		{
			
			private Shader shader;
			private LevelLoader loader;
			private Level level;
			
			@Override
			protected void init()
			{
				shader = Shader.parseShaderFromFile("shaders/basic/vertex.shader", "shaders/basic/fragment.shader");
				loader = new LevelLoader(20, 750, 500, "levels/", ".tdw");
				loadLevel("level-1");
			}
			
			@Override
			protected void gameLoop()
			{
				glClear(GL_COLOR_BUFFER_BIT);
				
				Renderer.renderModel(level.getModel());
				
				glfwSwapBuffers(window.getId());
				glfwPollEvents();
			}
			
			@Override
			protected void cleanUp()
			{
				level.getModel().destroy();
			}
			
			private void loadLevel(final String levelName)
			{
				level = loader.load(levelName);
				level.buildModel(shader);
			}
			
		};
		
		ui.run();
		
	}
	
}

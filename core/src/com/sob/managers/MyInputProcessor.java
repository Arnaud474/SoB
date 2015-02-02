package com.sob.managers;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class MyInputProcessor extends InputAdapter
{
	private OrthographicCamera cam;
	
	public MyInputProcessor(OrthographicCamera cam) 
	{
		this.cam = cam;
	}


	@Override
	public boolean scrolled(int amount) 
	{
		cam.zoom += amount / 25f;
		return true;
	}
	
	
	@Override
	public boolean keyDown(int keycode) 
	{
		switch(keycode)
		{
		
			case Keys.W:
				GameKeys.setKey(GameKeys.W, true);
				break;
			case Keys.A:
				GameKeys.setKey(GameKeys.A, true);
				break;
			case Keys.S:
				GameKeys.setKey(GameKeys.S, true);
				break;
			case Keys.D:
				GameKeys.setKey(GameKeys.D, true);
				break;
		
		}
			
		return true;
	}

	@Override
	public boolean keyUp(int keycode) 
	{
		switch(keycode)
		{
		
			case Keys.W:
				GameKeys.setKey(GameKeys.W, false);
				break;
			case Keys.A:
				GameKeys.setKey(GameKeys.A, false);
				break;
			case Keys.S:
				GameKeys.setKey(GameKeys.S, false);
				break;
			case Keys.D:
				GameKeys.setKey(GameKeys.D, false);
				break;
		}
		
		return true;
	}
	
}

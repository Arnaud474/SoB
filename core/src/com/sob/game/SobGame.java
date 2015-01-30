package com.sob.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.sob.game.screens.Splash;


public class SobGame extends Game
{
	
	public static final String TITLE = "Symphony of Blades", VERSION = "0.0.0.1";
	
	@Override
	public void create () 
    {
		setScreen(new Splash());
	}

   	@Override
    public void dispose() 
   	{
        super.dispose(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void resume() 
    {
        super.resume(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void pause() 
    {
        super.pause(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void resize(int width, int height) 
    {
        super.resize(width, height); //To change body of generated methods, choose Tools | Templates.
    }

	@Override
	public void render () 
    {
		super.render();
	}
}

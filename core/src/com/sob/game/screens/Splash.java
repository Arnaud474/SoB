/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sob.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class Splash implements Screen 
{
	private Sprite background;
	private Sprite title;
	private SpriteBatch batch;
	private BitmapFont font;
	private Sound swordDraw;
	private final double FADE_TIME = 2.0; //seconds 
	private double timeAcc = 0;
	private boolean fading = false;
	private String message = "Press Any Key ...";

    public Splash() 
    {
    }

    @Override
    public void show() 
    {
    	batch = new SpriteBatch();
    	
    	//Creation du background
    	Texture backgroundTexture = new Texture(Gdx.files.internal("screens/splash.png"));
        background = new Sprite(backgroundTexture);
        background.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        
        //Creation du titre
        Texture titleTexture = new Texture(Gdx.files.internal("screens/title.png"));
        title = new Sprite(titleTexture);
        title.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()/4);
        title.setPosition(0, Gdx.graphics.getHeight()-title.getHeight());
        
        //Creation du message
        font = new BitmapFont();
        font.scale(1);
        
        //Creation du son
        swordDraw = Gdx.audio.newSound(Gdx.files.internal("sounds/sfx/swordDraw.mp3"));
        
    }

    @Override
    public void render(float delta) 
    {
    	//Nettoyage du background
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        float opacite = getSpriteOpacity(delta);
        
        //Ouverture du batch et affichage des sprites
        batch.begin();
        background.draw(batch);
        title.draw(batch, opacite);
        
        //Changer l'opacite du text
        font.setColor(0, 0, 0, opacite);
        font.draw(batch, message, Gdx.graphics.getWidth()/2 - (font.getBounds(message).width/2), Gdx.graphics.getHeight()/2);
        
        //Fermeture du batch
        batch.end();
         
        if(Gdx.input.isKeyPressed(Input.Keys.ANY_KEY))
        {
        	((Game)Gdx.app.getApplicationListener()).setScreen(new MainMenu());
        	swordDraw.play(1.0f);
        }

        
        	
    }

    @Override
    public void resize(int width, int height) 
    {
        
    }

    @Override
    public void pause() 
    {
        
    }

    @Override
    public void resume() 
    {
       
    }

    @Override
    public void hide() 
    {
        
    }

    @Override
    public void dispose() 
    {
        batch.dispose();
        background.getTexture().dispose();
        title.getTexture().dispose();
        font.dispose();
        
    }
    
    
    //Fonction qui permet de faire un fade sur un sprite
    public float getSpriteOpacity(float delta)
    {
    	float alpha = 0; //opacite
    	
    	timeAcc += delta;
    	
    	//Si le sprite n'est pas en train de deteindre
    	if(!fading)
    	{
    		
    		//Calculer l'opacite en descendant de 1
    		alpha = (float) (1 - timeAcc/FADE_TIME);
    	
    		//Si le alpha est plus petit
    		if(alpha <= 0)
    		{
    			alpha = 0;
    			fading = true;
    			timeAcc = 0;
    		}
    		
    	}
    	
    	else
    	{
    		//Calculer le alpha a partir de 0
    		alpha = (float) (timeAcc/FADE_TIME);
    		
    		//Si le alpha est plus grand que 1
    		if(alpha >= 1)
    		{
    			alpha = 1;
    			fading = false;
    			timeAcc = 0;
    		}
    	}
    	
    	return alpha;
    }
    
}

package com.sob.game.screens;

import java.util.ArrayList;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;


public class MainMenu implements Screen 
{
	private Stage stage;
	private TextureAtlas atlas;
	private Skin skin;
	private ArrayList<TextButton> buttons;
	private TextButtonStyle style;
	private BitmapFont black;
	private Sound swordDraw;
	
	
	@Override
	public void show() 
	{
		//Creation du background
		Texture backgroundTexture = new Texture(Gdx.files.internal("screens/splash.png"));
		Image backgroundImage = new Image(backgroundTexture);
		/*background = new Sprite(backgroundTexture);
		background.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());*/
		
		//Creation du titre
		Texture titleTexture = new Texture(Gdx.files.internal("screens/title.png"));
		Image titleImage = new Image(titleTexture);
		titleImage.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()/4);
		titleImage.setPosition(0, Gdx.graphics.getHeight()-titleImage.getHeight());
        /*title = new Sprite(titleTexture);
        title.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()/4);
        title.setPosition(0, Gdx.graphics.getHeight()-title.getHeight());*/
		
		//Creation du son
        swordDraw = Gdx.audio.newSound(Gdx.files.internal("sounds/sfx/swordDraw.mp3"));

		black = new BitmapFont();
        
		//Setup pour les boutons
		atlas = new TextureAtlas("ui/button.pack");
		skin = new Skin();
		skin.addRegions(atlas);
		style = new TextButtonStyle();
		style.up = skin.getDrawable("button");
		style.down = skin.getDrawable("buttonpressed");
		style.font = black;
		
		
		//Creation de la liste et ajout des boutons
		buttons = new ArrayList<TextButton>();
		buttons.add(new TextButton("Play", style));
		buttons.add(new TextButton("Options", style));
		buttons.add(new TextButton("Exit", style));
      
		//Changement de la position des boutons
		positionButton();
		
		stage = new Stage();
		
		//Creation du listener
		MenuListener ml = new MenuListener();
		
		stage.addActor(backgroundImage);
		stage.addActor(titleImage);
		
		
		//On ajoute tous les boutons ds la liste
		for(TextButton b: buttons)
		{
			b.addListener(ml);
			stage.addActor(b);
		}
		
		
		
		
		stage.getViewport().update(1280, 720, false);
		
		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void render(float delta) 
	{
		//Nettoyage du background
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.getBatch().begin();
        //background.draw(stage.getBatch());
        //title.draw(stage.getBatch());
        stage.getBatch().end();
        
        
        
        stage.act(Gdx.graphics.getDeltaTime()); 
        stage.draw();
        
        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE))
        {
        	((Game)Gdx.app.getApplicationListener()).setScreen(new Splash());
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
		

	}
	
	public void positionButton()
	{
		int position = Gdx.graphics.getHeight() - ((Gdx.graphics.getHeight()/4)*2);
		
		for(TextButton b: buttons)
		{
			b.setPosition(Gdx.graphics.getWidth()/2 - (b.getWidth()/2), position);
			position -= b.getHeight() + 20;
		}
	}
	
	public class MenuListener extends ClickListener
	{

		
		@Override
		public void clicked(InputEvent event, float x, float y) 
		{
			
			
			
			String buttonName = ((TextButton)event.getListenerActor()).getText().toString();
			
			System.out.println(buttonName);
			System.out.println(buttonName.length());
			
			
			if(buttonName.equals("Play"))
			{
				System.out.println("Go to game");
				stage.addAction(Actions.fadeOut(1));
				swordDraw.play(1.0f);
				
				((Game)Gdx.app.getApplicationListener()).setScreen(new FightScreen());
			}
			
			else if(buttonName.equals("Options"))
			{
				System.out.println("Go to Options");
			}
			
			else if(buttonName.equals("Exit"))
			{
				System.out.println("Exit the game");
				Gdx.app.exit();
			}
				
		}
		
	}

}

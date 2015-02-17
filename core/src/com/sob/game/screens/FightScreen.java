package com.sob.game.screens;

import static com.sob.game.B2DVars.PPM;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.sob.managers.B2DContactListener;
import com.sob.managers.DynamicCamera;
import com.sob.managers.GameKeys;
import com.sob.managers.MyInputProcessor;
import com.sob.game.gameobjects.DynamicObject;
import com.sob.game.gameobjects.Hero;
import com.sob.game.gameobjects.MapT;
import com.sob.game.gameobjects.Skill;
import com.sob.game.gameobjects.StaticObject;


public class FightScreen implements Screen 
{
	private World world;
	private MapT map;
	private Box2DDebugRenderer b2dr;
	private DynamicCamera cam;
	private ArrayList<Hero> heros = new ArrayList<Hero>();
	private HashMap<String, Skill> skills = new HashMap<String, Skill>();
	private Hero hero;
	private Vector2 movement = new Vector2();
	private float speed = 5;
	private float jump = 10;

	@Override
	public void show() 
	{
		//Creation du world box2D
		world = new World(new Vector2(0, -9.8f), true);
		//Creation du renderer box2D
		b2dr = new Box2DDebugRenderer();
		//Creation d'une camera orthogonale
		cam = new DynamicCamera(Gdx.graphics.getWidth() / PPM, Gdx.graphics.getHeight() / PPM);
		
		//Creation du personnage principal
		hero = new Hero(200, 100, 32, 64, "Mage");
		hero.setBody(world);
		heros.add(hero);
		
		hero = new Hero(200, 100, 32, 64, "Mage");
		hero.setBody(world);
		heros.add(hero);
		
		hero = new Hero(2400, 100, 32, 64, "Warrior");
		hero.setBody(world);
		heros.add(hero);
		
		//Generation de la map et de son renderer
		map = new MapT(world, "map/map1.tmx", cam);
		map.generateB2DObjects();
		map.generateTiledMapRenderer();
		
		//Assignation du contact listener pour les collisions
		world.setContactListener(new B2DContactListener(heros));
		
		//Assignation du input processor pour gerer les keypress
		Gdx.input.setInputProcessor(new MyInputProcessor(cam));
	}

	@Override
	public void render(float delta) 
	{
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		//Fait avancer le world
		world.step(1/60f, 6, 2);
		
		//S'occupe de gerer le input du joueur
		handleInput();
		
		//Update le status des touches
		GameKeys.update();
		
		//Positionnement de la camera en fonction du hero
		//System.out.println(heros.get(0).getBody().getPosition().x +", "+ heros.get(0).getBody().getPosition().y);
		//Affichage de la position en bas a gauche de la camera
		cam.dynamicMovement(heros);
		//cam.position.set(heros.get(0).getBody().getPosition().x, heros.get(0).getBody().getPosition().y, 0);
		cam.update();
		
		//Render du world pour qu'on puisse voir les bodies
		b2dr.render(world, cam.combined);
		//Render la map graphique
		//map.getRenderer().render();
		
	}

	@Override
	public void resize(int width, int height) 
	{
		cam.viewportWidth = width / PPM;
		cam.viewportHeight = height / PPM;
		cam.update();	
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() 
	{
		dispose();
		
	}

	@Override
	public void dispose() 
	{
		world.dispose();
		b2dr.dispose();
	}
	
	public void handleInput()
	{
		//Si la touche W est pressed
		if(GameKeys.isPressed(GameKeys.W))
		{
			if(heros.get(0).isGrounded)
			{
				System.out.println("Jump");
				heros.get(0).jump();
			}
		}
		
		else if(GameKeys.isDown(GameKeys.A))
		{
			heros.get(0).move("left");
			
		}
		
		else if(GameKeys.isDown(GameKeys.D))
		{
			heros.get(0).move("right");
		}
		
		else if(GameKeys.isDown(GameKeys.S))
		{
			//heros.get(0).getBody().applyForceToCenter(0, -speed/2, true);
		}
		
	}
}

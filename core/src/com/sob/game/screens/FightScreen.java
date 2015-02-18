package com.sob.game.screens;

import static com.sob.game.B2DVars.PLAYER_ID;
import static com.sob.game.B2DVars.PPM;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

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
import com.sob.managers.MapT;
import com.sob.managers.MyInputProcessor;
import com.sob.managers.SkillManager;
import com.sob.game.gameobjects.DynamicObject;
import com.sob.game.gameobjects.Hero;
import com.sob.game.gameobjects.StaticObject;
import com.sob.game.skills.Skill;


public class FightScreen implements Screen 
{
	private World world;
	private MapT map;
	private Box2DDebugRenderer b2dr;
	private DynamicCamera cam;
	private SkillManager skillMg = new SkillManager();
	
	@Override
	public void show() 
	{
		//Creation de tous les skills qui peuvent etre dans le jeu
		skillMg.createSkills();
		
		//Creation du world box2D
		world = new World(new Vector2(0, -9.8f), true);
		//Creation du renderer box2D
		b2dr = new Box2DDebugRenderer();
		//Creation d'une camera orthogonale
		cam = new DynamicCamera(Gdx.graphics.getWidth() / PPM, Gdx.graphics.getHeight() / PPM);
		
		
		//Generation de la map et de son renderer
		map = new MapT(world, "map/map1.tmx", cam);
		map.generateB2DObjects();
		map.generateTiledMapRenderer();
		map.generateHeroes();
		
		//Assignation du contact listener pour les collisions
		world.setContactListener(new B2DContactListener(map.getHeros()));
		
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
		//System.out.println(map.getHeros().get(PLAYER_ID).getBody().getPosition().x +", "+ map.getHeros().get(PLAYER_ID).getBody().getPosition().y);
		//Affichage de la position en bas a gauche de la camera
		cam.dynamicMovement(map.getHeros());
		//cam.position.set(map.getHeros().get(PLAYER_ID).getBody().getPosition().x, map.getHeros().get(PLAYER_ID).getBody().getPosition().y, 0);
		cam.update();
		
		//Render du world pour qu'on puisse voir les bodies
		b2dr.render(world, cam.combined);
		//Render la map graphique
		map.getRenderer().render();
		
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
		//Si la touche W est pressed, la touche pour sauter
		if(GameKeys.isPressed(GameKeys.W))
		{
			if(map.getHeros().get(PLAYER_ID).isGrounded)
			{
				System.out.println("Jump");
				map.getHeros().get(PLAYER_ID).jump();
			}
		}
		
		//Pour bouger a gauche
		else if(GameKeys.isDown(GameKeys.A))
		{
			map.getHeros().get(PLAYER_ID).move("left");
			
		}
		
		//Pour bouger a droite
		else if(GameKeys.isDown(GameKeys.D))
		{
			map.getHeros().get(PLAYER_ID).move("right");
		}
		
		//Pour bouger vers le bas
		else if(GameKeys.isDown(GameKeys.S))
		{
			//map.getHeros().get(PLAYER_ID).getBody().applyForceToCenter(0, -speed/2, true);
		}
		
		//Pour le skill1
		else if(GameKeys.isPressed(GameKeys.SKILL_1))
		{
			//System.out.println("Attaque de base");
			//System.out.println(map.getHeros().get(PLAYER_ID).getSkillSet().get(0));
			map.getHeros().get(PLAYER_ID).useSkill(skillMg.getSkills().get(map.getHeros().get(PLAYER_ID).getSkillSet().get(0)));
		}
		
		//Pour le skill2
		else if(GameKeys.isPressed(GameKeys.SKILL_2))
		{
			//System.out.println("Skill 1");
			map.getHeros().get(PLAYER_ID).useSkill(skillMg.getSkills().get(map.getHeros().get(PLAYER_ID).getSkillSet().get(1)));
		}
		
		//Pour le skill3
		else if(GameKeys.isPressed(GameKeys.SKILL_3))
		{
			//System.out.println("Skill 2");	
			map.getHeros().get(PLAYER_ID).useSkill(skillMg.getSkills().get(map.getHeros().get(PLAYER_ID).getSkillSet().get(2)));
		}
		
		//Pour le skill4
		else if(GameKeys.isPressed(GameKeys.SKILL_4))
		{
			//System.out.println("Skill 3");		
			map.getHeros().get(PLAYER_ID).useSkill(skillMg.getSkills().get(map.getHeros().get(PLAYER_ID).getSkillSet().get(3)));
		}
		
		//Pour le skill1
		else if(GameKeys.isPressed(GameKeys.SKILL_5))
		{
			//System.out.println("Skill 4");
			map.getHeros().get(PLAYER_ID).useSkill(skillMg.getSkills().get(map.getHeros().get(PLAYER_ID).getSkillSet().get(4)));
		}
	}
	
}

package com.sob.managers;

import static com.sob.game.B2DVars.PPM;

import java.util.ArrayList;
import java.util.Iterator;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.sob.game.gameobjects.Hero;
import com.sob.game.gameobjects.StaticObject;

public class MapT
{
	//Le world box 2D utilise pour la physique
	private World world;
	//La TiledMap provenant d'un fichier tmx
	private TiledMap map;
	//Tous les objects solides sur la map
	private ArrayList<Body> walls;
	//Tous les spawn points sur la map
	private ArrayList<Vector2> spawns;
	//Renderer pour les textures de la map
	private OrthogonalTiledMapRenderer renderer;
	//Camera du monde B2D
	private DynamicCamera cam;
	//Liste de tous les heros qui sont sur la map
	private ArrayList<Hero> heros = new ArrayList<Hero>();
	
	
	public MapT(World parent, String mapName, DynamicCamera cam) 
	{
		this.world = parent;
		this.map = new TmxMapLoader().load(mapName);
		this.cam = cam;
	}
	
	//Pour generer les objets B2D pour la physique
	public void generateB2DObjects()
	{
		//Pour obtenir le layer objects
		MapLayer layer = map.getLayers().get("walls");
		
		//Pour obtenir tout les objets qui sont dans le layer 
		MapObjects objects = layer.getObjects();
		
		for(MapObject obj: objects)
		{
			RectangleMapObject rect = (RectangleMapObject) obj;
			StaticObject floor = new StaticObject(rect.getRectangle().x+(rect.getRectangle().width/2), rect.getRectangle().y+(rect.getRectangle().height/2), rect.getRectangle().width/2, rect.getRectangle().height/2);
			floor.setBody(world);
		}
		
		/*//Pour obtenir le layer de spawn points
		layer = map.getLayers().get("spawn");
		
		//Pour obtenir tout les objets qui sont dans le layer 
		objects = layer.getObjects();
		
		for(MapObject obj: objects)
		{
			RectangleMapObject rect = (RectangleMapObject) obj;
			StaticObject floor = new StaticObject(rect.getRectangle().x+(rect.getRectangle().width/2), rect.getRectangle().y+(rect.getRectangle().height/2), rect.getRectangle().width/2, rect.getRectangle().height/2);
			floor.setBody(world);
		}*/
	}
	
	//Generate map renderer
	public void generateTiledMapRenderer()
	{
		//Pour que les tiles de 64 x 64 représentent 1m
		float unitScale = 1 / PPM;

		//Generation du TiledMapRender
		renderer = new OrthogonalTiledMapRenderer(this.map, unitScale);
		renderer.setView(cam);
	}

	public OrthogonalTiledMapRenderer getRenderer() 
	{
		renderer.setView(cam);
		return renderer;
	}
	
	//Pour generer des heros sur la map
	public void generateHeroes()
	{
		Hero hero;
		
		//Creation du personnage principal
		hero = new Hero(200, 100, 32, 64, "Warrior");
		hero.setBody(world);
		heros.add(hero);
		
		hero = new Hero(200, 100, 32, 64, "Mage");
		hero.setBody(world);
		heros.add(hero);
		
		hero = new Hero(2400, 100, 32, 64, "Warrior");
		hero.setBody(world);
		heros.add(hero);
	}
	
	public ArrayList<Hero> getHeros() 
	{
		return heros;
	}

}

package com.sob.game.gameobjects;

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
import com.badlogic.gdx.physics.box2d.World;

public class MapT
{
	World world;
	TiledMap map;
	ArrayList<Body> walls;
	ArrayList<Vector2> spawns;
	OrthogonalTiledMapRenderer renderer;
	OrthographicCamera cam;
	
	public MapT(World parent, String mapName, OrthographicCamera cam) 
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
			System.out.println("X : "+rect.getRectangle().x+" Y : "+rect.getRectangle().y);
			System.out.println("W : "+rect.getRectangle().width+" H : "+rect.getRectangle().height);
			StaticObject floor = new StaticObject(rect.getRectangle().x+(rect.getRectangle().width/2), rect.getRectangle().y+(rect.getRectangle().height/2), rect.getRectangle().width/2, rect.getRectangle().height/2);
			floor.setBody(world);
		}
		
		StaticObject floor = new StaticObject(0, 0, 1, 1);
		floor.setBody(world);
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

}

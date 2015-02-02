package com.sob.game.gameobjects;

import static com.sob.game.B2DVars.PPM;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

public abstract class GameObject 
{
	protected Vector2 position;
	protected Body body;
	protected float width;
	protected float height;
	
	public GameObject(float x, float y, float w, float h)
	{
		this.position = new Vector2(x / PPM, y / PPM);
		this.width = w / PPM;
		this.height = h / PPM;
	}
	
	public abstract Vector2 getPosition();
	public abstract void setPosition(float x, float y);
	public abstract Body getBody();
	public abstract void setBody(World w);
}

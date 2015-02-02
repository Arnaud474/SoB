package com.sob.ecs.components;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.sob.ecs.Component;

public class PhysicsComponent extends Component
{
	public final static String ID = "Physics";
	private final Vector2 MAX_SPEED = new Vector2(18f, 18f);
	
	private float width;
	private float height;
	private Body body;
	private Vector2 acceleration;
	private boolean isGrounded = false;
	

	public PhysicsComponent(World w, BodyDef.BodyType bodyType) 
	{
		super(ID);
	}
	
	
}

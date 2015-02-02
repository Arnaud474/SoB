package com.sob.ecs.systems;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.sob.ecs.EntityManager;

public class PhysicsSystem extends EntitySystem implements ContactListener
{

	private World world;
	
	
	public PhysicsSystem(EntityManager em) 
	{
		super(em);
	}
	
	@Override
	public void init() 
	{
		 world = new World(new Vector2(0, -30f), true);
		 world.setContactListener(this);
	}
	
	@Override
	public void update(float delta) 
	{
		 world.step(delta, 6, 2);
	}

	//Contact Listening
	
	@Override
	public void beginContact(Contact contact) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void endContact(Contact contact) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
		// TODO Auto-generated method stub
		
	}

}

package com.sob.game.gameobjects;


import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class DynamicObject extends GameObject
{
	protected final Vector2 MAX_VELOCITY = new Vector2(18f, 18f);
	
	public DynamicObject(float x, float y, float w, float h) 
	{
		super(x, y, w, h);
	}

	@Override
	public Vector2 getPosition() 
	{
		return body.getPosition();
	}

	@Override
	public void setPosition(float x, float y) 
	{
		position = new Vector2(x, y);
	}

	@Override
	public Body getBody()
	{
		return body;
	}

	@Override
	public void setBody(World w) 
	{
		BodyDef bdef = new BodyDef();
		bdef.position.set(position);
		bdef.type = BodyType.DynamicBody;
		bdef.bullet = true;
		body = w.createBody(bdef);
		
		body.setSleepingAllowed(false);
		
		System.out.println("W : " + width + "H : " + height);
		
		//Creation of the rectangle fixture (Middle of the capsule)
		PolygonShape rectShape = new PolygonShape();
		rectShape.setAsBox(width-0.04f, height-((width)), new Vector2(position.x + 0.01f, position.y), 0);
		
		FixtureDef rectFixtDef = new FixtureDef();
		rectFixtDef.shape = rectShape;
		body.createFixture(rectFixtDef).setUserData("hero");
		
		//Creation of the circle fixture (Top of the capsule)
		CircleShape circShape1 = new CircleShape();
		circShape1.setRadius(width);
		circShape1.setPosition(new Vector2(position.x, position.y+(height-width)));
		
		FixtureDef circFixtDef = new FixtureDef();
		circFixtDef.shape = circShape1;
		body.createFixture(circFixtDef).setUserData("head");
		
		//Creation of the circle fixture (Bottom of the capsule)
		CircleShape circShape2 = new CircleShape();
		circShape2.setRadius(width);
		circShape2.setPosition(new Vector2(position.x, position.y-(height-width)));
				
		circFixtDef = new FixtureDef();
		circFixtDef.shape = circShape2;
		body.createFixture(circFixtDef).setUserData("hero");
		
		//Sensor for the foot
		PolygonShape rectShape2 = new PolygonShape();
		rectShape2.setAsBox(width/2, width/4, new Vector2(position.x, position.y-height), 0);
		
		FixtureDef rectFixtDef2 = new FixtureDef();
		rectFixtDef2.shape = rectShape2;
		rectFixtDef2.isSensor = true;
		body.createFixture(rectFixtDef2).setUserData("foot");
		
		
		rectShape.dispose();
		rectShape2.dispose();
		circShape1.dispose();
		circShape2.dispose();
	}
	
	
	
}

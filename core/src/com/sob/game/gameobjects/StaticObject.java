package com.sob.game.gameobjects;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class StaticObject extends GameObject
{

	public StaticObject(float x, float y, float w, float h) 
	{
		super(x, y, w, h);
	}

	@Override
	public Vector2 getPosition() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPosition(float x, float y) 
	{
		// TODO Auto-generated method stub	
	}

	@Override
	public Body getBody() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setBody(World w) 
	{
		BodyDef bdef = new BodyDef();
		bdef.position.set(position);
		bdef.type = BodyType.StaticBody;
		body = w.createBody(bdef);
		
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(width, height);
		
		FixtureDef fdef = new FixtureDef();
		fdef.friction = 0.8f;
		fdef.shape = shape;
		body.createFixture(fdef).setUserData("ground");
	}

}

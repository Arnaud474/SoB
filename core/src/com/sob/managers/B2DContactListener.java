package com.sob.managers;

import static com.sob.game.B2DVars.PLAYER_ID;
import java.util.ArrayList;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.sob.game.gameobjects.Hero;

public class B2DContactListener implements ContactListener
{
	private ArrayList<Hero> heros;
	
	public B2DContactListener(ArrayList<Hero> heros) 
	{
		this.heros = heros;
	}

	@Override
	public void beginContact(Contact contact) 
	{
		Fixture fa = contact.getFixtureA();
		Fixture fb = contact.getFixtureB();
		
		//Si l'une des fixtures est le foot et l'autre le ground
		if(fa.getUserData().equals("foot") && fb.getUserData().equals("ground"))
		{
			if(fa.getBody().equals(heros.get(PLAYER_ID).getBody()))
			{
				System.out.println("Le hero principal touche au sol");
				heros.get(PLAYER_ID).isGrounded = true;
			}
			else
			{
				System.out.println("Nope");
			}
		}
		else if(fb.getUserData().equals("foot") && fa.getUserData().equals("ground"))
		{
			if(fb.getBody().equals(heros.get(PLAYER_ID).getBody()))
			{
				System.out.println("Le hero principal touche au sol");
				heros.get(PLAYER_ID).isGrounded = true;
			}
			else
			{
				System.out.println("Nope");
			}
		}
		
		
	}

	@Override
	public void endContact(Contact contact) 
	{
		Fixture fa = contact.getFixtureA();
		Fixture fb = contact.getFixtureB();
		
		//Si l'une des fixtures est le foot et l'autre le ground
		if(fa.getUserData().equals("foot") && fb.getUserData().equals("ground"))
		{
			if(fa.getBody().equals(heros.get(PLAYER_ID).getBody()))
			{
				System.out.println("Le hero principal ne touche plus au sol");
				heros.get(PLAYER_ID).getBody().setLinearDamping(PLAYER_ID);
				heros.get(PLAYER_ID).isGrounded = false;
			}
			else
			{
				System.out.println("Nope");
			}
		}
		else if(fb.getUserData().equals("foot") && fa.getUserData().equals("ground"))
		{
			System.out.println("Un fb hero touche au sol");
			
			if(fb.getBody().equals(heros.get(PLAYER_ID).getBody()))
			{
				System.out.println("Le hero principal ne touche plus au sol");
				heros.get(PLAYER_ID).getBody().setLinearDamping(PLAYER_ID);
				heros.get(PLAYER_ID).isGrounded = false;
			}
			else
			{
				System.out.println("Nope");
			}
		}
		
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) 
	{
		
		
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) 
	{
		
	}
	
	public boolean isPlayerOnGround()
	{
		return false;
		
	}

}

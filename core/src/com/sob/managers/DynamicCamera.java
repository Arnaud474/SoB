package com.sob.managers;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.sob.game.gameobjects.Hero;

public class DynamicCamera extends OrthographicCamera
{
	private float offsetX = 6f;
	private float offsetY = 6f;
	private float moveSpeed = 0.08f;

	public DynamicCamera(float viewportWidth, float viewportHeight) 
	{
		super(viewportWidth, viewportHeight);
	}
	
	//Permet de bouger la camera dynamiquement en fonction du nombre de dynamic bodies
	public void dynamicMovement(ArrayList<Hero> heros)
	{
		float minX = Float.POSITIVE_INFINITY;
		float maxX = Float.NEGATIVE_INFINITY;
		float minY = Float.POSITIVE_INFINITY;
		float maxY = Float.NEGATIVE_INFINITY;
		
		//System.out.println("X : " + minX + ", " + maxX + "Y : " + minY + ", " + maxY);
		
		for(Hero h : heros)
		{
			//X Bounds
		    if (h.getPosition().x < minX)
		      minX = h.getPosition().x;
		 
		    if (h.getPosition().x > maxX)
		      maxX = h.getPosition().x;
		 
		    //Y Bounds
		    if (h.getPosition().y < minY)
		      minY = h.getPosition().y;
		 
		    if (h.getPosition().y > maxY)
		      maxY = h.getPosition().y;
		}
		
		//System.out.println("X : " + minX + ", " + maxX + "Y : " + minY + ", " + maxY);
	
		
		position.x = (maxX - minX)/2 + minX;
		position.y = (maxY - minY)/2 + minY;
	
		
		
		float newZoomX = (maxX - minX + offsetX) / viewportWidth;
		float newZoomY = (maxY - minY + offsetY) / viewportHeight;
		
		//Si le Zoom en X est plus grand
		if(newZoomX >= newZoomY)
			if(newZoomX < 1)
			{
				zoom = 1;
			}
			else
			{
				zoom = newZoomX;
			}
		//Si le Zoom en Y est plus grand
		else
		{
			if(newZoomY < 1)
			{
				zoom = 1;
			}
			else
			{
				zoom = newZoomY;
			}
		}
		
		
		//Does not work
		/*if((maxX - minX) < 20f)
			viewportWidth = 20f;
		else
			viewportWidth = maxX - minX;
		
		if((maxY - minY) < 11.25f)
			viewportHeight = 11.25f;
		else	
			viewportHeight = maxY - minY;*/
		
	}
	
}

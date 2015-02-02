package com.sob.ecs.components;

import com.badlogic.gdx.math.Vector2;
import com.sob.ecs.Component;

public class PositionComponent extends Component
{
	public final static String ID = "Position";
	private Vector2 position;
	
	public PositionComponent(Integer x,  Integer y)
	{
		super(ID);
		position = new Vector2(x, y);
	}
	
	public Vector2 getPosition()
	{
		return position;
		
	}
}

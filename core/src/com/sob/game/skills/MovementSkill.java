package com.sob.game.skills;

public class MovementSkill extends Skill
{
	private float movement;
	
	public MovementSkill(String name, String type, float damage, float width, float height, float movement) 
	{
		super(name, type, damage, width, height);
		this.movement = movement;
	}

}

package com.sob.game.skills;

public class RangedSkill extends Skill
{
	private float distance;

	public RangedSkill(String name, String type, float damage, float width, float height, float distance) 
	{
		super(name, type, damage, width, height);
		this.distance = distance;
	}

}

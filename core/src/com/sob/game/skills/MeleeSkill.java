package com.sob.game.skills;

public class MeleeSkill extends Skill
{
	private float duration;
	
	public MeleeSkill(String name, String type, float damage, float width, float height, float duration) 
	{
		super(name, type, damage, width, height);
		this.duration = duration;
	}
	
	public void test()
	{
		System.out.println("This is a melee skill");
	}

}

package com.sob.ecs.systems;

import com.sob.ecs.EntityManager;

public abstract class EntitySystem 
{
	private EntityManager em;
	
	public EntitySystem(EntityManager em)
	{
        this.em = em;
    }
	
	public void init(){};
	
	public void update(float delta){};
	
}

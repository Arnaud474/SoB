package com.sob.ecs;

import java.util.HashMap;

public class Entity
{
	private Integer id;
	private EntityManager manager;

	public Entity(Integer id)
	{
		this.id = id;
	}
	
	public void setEntityManager(EntityManager e)
	{
		manager = e;
	}
	
	public void addComponent(Integer entity, Component c)
	{
		manager.addComponent(id, c);
	}
	
	public Integer getId()
	{
		return id;
	}
	
}
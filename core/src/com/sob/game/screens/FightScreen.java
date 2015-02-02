package com.sob.game.screens;

import com.badlogic.gdx.Screen;
import com.sob.ecs.Entity;
import com.sob.ecs.EntityManager;
import com.sob.ecs.components.PhysicsComponent;
import com.sob.ecs.components.PositionComponent;
import com.sob.ecs.components.StatisticComponent;

public class FightScreen implements Screen
{
	EntityManager em = new EntityManager();

	@Override
	public void show() 
	{
		
		
		Entity e = new Entity(em.createEntity());
		e.setEntityManager(em);
		
		e.addComponent(e.getId(), new PositionComponent(100, 100));
		e.addComponent(e.getId(), new StatisticComponent(10, 250));
		
		
		System.out.println(em.allComponents.get(1).keySet());
	}

	@Override
	public void render(float delta) 
	{
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}

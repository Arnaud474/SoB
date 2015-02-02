package com.sob.ecs.components;

import com.sob.ecs.Component;

public class StatisticComponent extends Component
{
	public static final String ID = "Statistic";
	private Integer level;
	private Integer currentHealth;
	private Integer maxHealth;
	
	public StatisticComponent(Integer level, Integer currentH) 
	{
		super(ID);
		this.level = level;
		this.currentHealth = currentH;
		maxHealth = currentHealth;
	}	
}

package com.sob.game.gameobjects;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

public class Skill 
{
	private String name;		
	private Integer damage;  	
	private float width;	   
	private float height;    
	private float distance;
	private float duration;
	
	public Skill()
	{
		
	}
	
	public void loadSkillFromFile(String file)
	{
		//Creation d'une classe property qui contiendra les donnees du fichier
		Properties prop = new Properties();
				
		try 
		{
			//Pour lire le fichier
			InputStream input = new FileInputStream("bin/properties/skills/"+file);
			
			//Insertion des donnees dans la classe property
			 prop.load(input);
			 
			 this.name = prop.getProperty("name").trim();
			 this.damage = Integer.parseInt(prop.getProperty("damage").trim());
			 this.width = Float.parseFloat(prop.getProperty("damage").trim());
			 this.height = Float.parseFloat(prop.getProperty("height").trim());
			 this.distance = Float.parseFloat(prop.getProperty("distance").trim());
			 this.duration = Float.parseFloat(prop.getProperty("duration").trim());
			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public String getName()
	{
		return name;
	}
}

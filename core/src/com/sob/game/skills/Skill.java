package com.sob.game.skills;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

public class Skill 
{
	protected String name;
	protected String type;
	protected float damage;  	
	protected float width;	   
	protected float height;
	
	public Skill(String name, String type, float damage, float width, float height)
	{
		this.name = name;
		this.type = type;
		this.damage = damage;
		this.width = width;
		this.height = height;
	}
	
	
	public String getName()
	{
		return name;
	}
	
	public String getTypeFromFile(String file)
	{
		//Creation d'une classe property qui contiendra les donnees du fichier
		Properties prop = new Properties();
		
		//Type
		String type = "";
		
		try 
		{
			//Pour lire le fichier
			InputStream input = new FileInputStream("bin/properties/skills/"+file);
			
			//Insertion des donnees dans la classe property
			prop.load(input);
			
			type = prop.getProperty("type").trim();
			
			input.close();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		return type;
	}
}

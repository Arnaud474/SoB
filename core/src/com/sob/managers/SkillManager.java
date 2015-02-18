package com.sob.managers;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

import com.sob.game.skills.MeleeSkill;
import com.sob.game.skills.MovementSkill;
import com.sob.game.skills.RangedSkill;
import com.sob.game.skills.Skill;

public class SkillManager 
{
	private HashMap<String, Skill> skills = new HashMap<String, Skill>();

	public SkillManager()
	{
		
	}
	
	private Skill loadSkillFromFile(String file)
	{
		//Creation d'une classe property qui contiendra les donnees du fichier
		Properties prop = new Properties();
		
		try 
		{
			//Pour lire le fichier
			InputStream input = new FileInputStream("bin/properties/skills/"+file);
			
			//Insertion des donnees dans la classe property
			 prop.load(input);
			 
			 String name = prop.getProperty("name").trim();
			 String type = prop.getProperty("type").trim();
			 float damage = Integer.parseInt(prop.getProperty("damage").trim());
			 float width = Float.parseFloat(prop.getProperty("damage").trim());
			 float height = Float.parseFloat(prop.getProperty("height").trim());
			 
			 
			 //Si le type de skill est melee
			 if(type.equals("melee"))
			 {
				 float duration = Float.parseFloat(prop.getProperty("duration").trim());
				 MeleeSkill skill = new MeleeSkill(name, type, damage, width, height, duration);
				 skills.put(name, skill);
			 }
			 //Si le type de skill est ranged
			 else if(type.equals("ranged"))
			 {
				 float distance = Float.parseFloat(prop.getProperty("distance").trim());
				 RangedSkill skill = new RangedSkill(name, type, damage, width, height, distance);
				 skills.put(name, skill);
				 
			 }
			 
			 //Si le type de skill est movement
			 else if(type.equals("movement"))
			 {
				 float movement = Float.parseFloat(prop.getProperty("movement").trim());
				 MovementSkill skill = new MovementSkill(name, type, damage, width, height, movement);
				 skills.put(name, skill);
			 }
			 
			 
			 
			 input.close();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		return null;
	}
	
	public void createSkills()
	{
		//Folder dans lequel on regarde les fichiers
		File folder = new File("bin/properties/skills");
		//Liste de fichiers
		File[] listOfFiles = folder.listFiles();
		
		//Pour chaque fichier properties de skill
		for(File file: listOfFiles)
		{
			System.out.println(file.getName());
			loadSkillFromFile(file.getName());
		}
		
		System.out.println(skills.keySet());
	}
	
	public HashMap<String, Skill> getSkills() 
	{
		return skills;
	}

}

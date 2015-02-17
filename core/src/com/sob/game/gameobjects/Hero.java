package com.sob.game.gameobjects;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Properties;
import javax.swing.filechooser.FileSystemView;

public class Hero extends DynamicObject 
{
	private String name;		//Nom du joueur
	private Integer maxHP;  	//Points de vie maximum
	private Integer mana;	   //Points de mana pour utiliser un skill
	private Integer power;     //Statistique d'attaque, definit le nombre de dommage physique que le characteur peut infliger
	private Integer speed;     	//Statistique de vitesse, definit la vitesse d'attaque du personnage
	private Integer luck;    	//Statistique de chance, definit la chance de toucher l'ennemi et la chance d'eviter
	private Integer defense;    //Statistique de defence, definit la defence contre les attaques physiques
	private Integer resistance; //Statistique de resistance, definit la defence contre les attaques magiques
	private ArrayList<String> skillSet;	//Liste de skills que le hero peut utiliser
	
	public boolean isGrounded = false;

	public Hero(float x, float y, float w, float h, String className) 
	{
		super(x, y, w, h);
		loadStatsFromFile(className);
	}
	
	//Pour sauter
	public void jump()
	{
		getBody().setLinearVelocity(getBody().getLinearVelocity().x, 10);
	}
	
	//Pour bouger le hero
	public void move(String orientation)
	{
		if(orientation == "left")
			getBody().setLinearVelocity(-speed, getBody().getLinearVelocity().y);
		else if(orientation == "right")
			getBody().setLinearVelocity(+speed, getBody().getLinearVelocity().y);
	}
	
	//Pour utiliser un skill
	public void useSkill()
	{
		
	}
	
	//Prendre du degat
	public void takeDamage(Attack a)
	{
		
	}
	
	//Permet de loader les statistiques du Hero en fonction de sa classe
	public void loadStatsFromFile(String className)
	{
		//Creation d'une classe property qui contiendra les donnees du fichier
		Properties prop = new Properties();
		
		try 
		{
			//Pour lire le fichier
			InputStream input = new FileInputStream("bin/properties/heroes/"+className+".properties");
			
			//Insertion des donnees dans la classe property
			 prop.load(input);
			 
			//Insertion des valeurs dans les variables
			this.name = prop.getProperty("name").trim();
			this.maxHP = Integer.parseInt(prop.getProperty("maxhp").trim());
			this.mana = Integer.parseInt(prop.getProperty("mana").trim());
			this.power = Integer.parseInt(prop.getProperty("power").trim());
			this.speed = Integer.parseInt(prop.getProperty("speed").trim());
			this.luck = Integer.parseInt(prop.getProperty("luck").trim());
			this.defense = Integer.parseInt(prop.getProperty("defense").trim());
			this.resistance = Integer.parseInt(prop.getProperty("resistance").trim());
			
			//Affichage du nom de la classe
			System.out.println(this.name);
			
			//Pour les skills
			skillSet = new ArrayList<String>();
			
			for(int i = 0; i < 5; i++)
			{
				skillSet.add(prop.getProperty("skill"+i).trim());
				System.out.println(skillSet.get(skillSet.size()-1));
			}
			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

}

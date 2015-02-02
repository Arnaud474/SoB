package com.sob.game.gameobjects;

public class Hero extends DynamicObject 
{
	private String name;		//Nom du joueur
	private Integer level;		//Niveau du joueur
	private Integer maxHP;  	//Points de vie maximum
	private Integer attack;     //Statistique d'attaque, definit le nombre de dommage physique que le characteur peut infliger
	private Integer magic;    	//Statistique de magie, definit le nombre de dommage magique que le characteur peut infliger
	private Integer skill;      //Statistique d'expertise, definit la chance de faire un coup critique sur le character
	private Integer speed;     	//Statistique de vitesse, definit la vitesse d'attaque du personnage
	private Integer luck;    	//Statistique de chance, definit la chance de toucher l'ennemi et la chance d'eviter
	private Integer defense;    //Statistique de defence, definit la defence contre les attaques physiques
	private Integer resistance; //Statistique de resistance, definit la defence contre les attaques magiques
	
	public boolean isGrounded = false;

	public Hero(float x, float y, float w, float h) 
	{
		super(x, y, w, h);
	}

}

package com.sob.ecs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import com.sob.ecs.components.PositionComponent;

public class EntityManager
{
	
	// 	EntityId		ComponentId	 Component Object
	public HashMap<Integer, HashMap<String, Component>> allComponents;
	//Id courrant de l'entité
	private Integer lowestEntityId = 0;

	//Controleur
	public EntityManager()
	{
		//Initialisation des structures de donnees
		allComponents = new HashMap<Integer, HashMap<String, Component>>();
	}

	// Creation d'un nouvel entity avec un unique ID
	public Integer createEntity()
	{
		lowestEntityId++;
		
		//Ajout d'un Hashmap vide pour les components
		allComponents.put(lowestEntityId, new HashMap<String, Component>());

		return lowestEntityId;
	}

	//Permet d'enlever une entite
	public void removeEntity(Entity entity)
	{
		//On enleve les components dans la liste de components
		allComponents.remove(entity.getId());
	}

	//Pour ajouter un component a une entite
	public void addComponent(Integer entity, Component c)
	{
		//Insertion du component dans le dictionnaire de components de l'entité
		allComponents.get(entity).put(c.ID, c);	
	}

	//Pour obtenir un component specifique d'une entite specifique
	public Component getComponent(Integer entityId, String componentType)
	{
		Component c = null;
		
		//Pour obtenir tous les components d'une entity
		HashMap<String, Component> components = allComponents.get(entityId);

		//Exception handling si il n'y a pas d'entity
		try
		{
			//Obtenir le component qui a cet id spécifique
			c = components.get(componentType);
		}
		catch(Exception e)
		{
			e.printStackTrace(System.out);
		}

		return c;
	}

	//Pour obtenir tous les entités qui ont un certain component
	public ArrayList<Integer> getAllEntitiesWithComponent(Component componentType)
	{
		//Liste pour contenir les ID de toutes les entités qui possède le component
		ArrayList<Integer> entities = new ArrayList<Integer>();

		//Boucle for améliorer pour parcourir toutes les components pour chaque entité
		for(Integer id : allComponents.keySet())
		{
			//Pour obtenir le component si il existe dans l'entité
			Component  c = allComponents.get(id).get(componentType.ID);

			//Si l'entité ne contient pas le component
			if(c == null)
			{
				System.out.println("L'entité ne contient pas ce component");
			}
			//Sinon si elle le possède
			else
			{
				//Ajouter le ID de l'entité à la liste
				entities.add(id);
			}
		}
		
		return entities;
	}

	//Pour obtenir tous les components d'un certain type
	public ArrayList<Component> getAllComponentsOfType(Component componentType)
	{
		//Liste pour contenir les components
		ArrayList<Component> components = new ArrayList<Component>();

		//Boucle améliorer pour parcourir les entités et leurs components
		for(HashMap<String, Component> entityComponents : allComponents.values())
		{
			Component c = entityComponents.get(componentType.ID);

			//Si le component n'existe pas pour l'entité
			if(c == null)
			{
				System.out.println("L'entité ne contient pas ce component");
			}
			//Sinon si l'entité possède le component
			else
			{
				components.add(c);
			}
		}
		
		return components;
	}
	
	//Pour obtenir tous les ID des entites
	public Set<Integer> getAllEntitiesId()
	{
		return allComponents.keySet();
	}


}
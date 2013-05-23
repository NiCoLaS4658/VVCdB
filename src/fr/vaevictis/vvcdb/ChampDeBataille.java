package fr.vaevictis.vvcdb;

import java.util.List;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class ChampDeBataille 
{
	public ChampDeBataille(String nom)
	{
		this.nom = nom.toLowerCase();
		this.ajouterMap();
	}
	public void ajouterMap()
	{
		champsdebataille.put(this.nom, this);
	}
	public void soustraireMap()
	{
		champsdebataille.remove(this.nom);
	}
	public static ChampDeBataille getMap(String key)
	{
		return champsdebataille.get(key);
	}
	
	public String getNom()
	{
		return this.nom;
	}
	public Location getDefaultSpawn()
	{
		return this.defaultSpawn;
	}
	public void setDefaultSpawn(Location l)
	{
		this.defaultSpawn = l;
	}
	public List<Player> getJoueurs()
	{
		return this.joueurs;
	}
	public void playerJoin(Player p)
	{
		joueurs.add(p);
	}
	public void playerLeave(Player p)
	{
		joueurs.remove(joueurs.lastIndexOf(p));
	}
	public static void arret()
	{
		usedCdB = "";
		for (Position p : champsdebataille.get(usedCdB).positions)
		{
			p.setFaction("");
			p.timer = -1;
		}
	}
	public static void lancerAttaque()
	{
		
	}
	public List getPositions()
	{
		return this.positions;
	}
	private Location defaultSpawn;
	private List<Player> joueurs;
	private List<Position> positions;
	private String nom;
	public static Map<String, ChampDeBataille> champsdebataille;
	public static String usedCdB;

}

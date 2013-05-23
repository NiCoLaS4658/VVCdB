package fr.vaevictis.vvcdb;

import org.bukkit.Location;

public class Position 
{
	public Position(Location loc, boolean couverte, String nom, ChampDeBataille cdb)
	{
		this.location = loc;
		this.couverte = couverte;
		this.nom = nom;
		this.champdebataille = cdb.getNom();
		this.faction = "";
		this.timer = -1;
	}

	public Location getLocation()
	{
		return this.location;
	}
	public boolean isCouverte()
	{
		return this.couverte;
	}
	public String getChampdebataille()
	{
		return this.champdebataille;
	}
	public String getNom()
	{
		return this.nom;
	}
	public String getFaction()
	{
		return this.faction;
	}
	public void setFaction(String f)
	{
		this.faction = f;
	}
	private String champdebataille;
	private Location location;
	private boolean couverte;
	private String faction;
	private String nom;
	public byte timer;
}

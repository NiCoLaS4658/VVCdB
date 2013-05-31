package fr.vaevictis.vvcdb;

public class VVFaction 
{
	public VVFaction(String nom)
	{
		this.nom = nom;
		this.points = 0;
	}
	
	private String nom;
	public String getNom()
	{
		return this.nom;
	}
	private int points;
	public int getPoints()
	{
		return this.points;
	}
	public void setPoints(int points)
	{
		this.points = points;
	}
	public String getSaisonPointsPath()
	{
		return "saison." + this.nom;
	}
	
	public static VVFaction ahmosis = new VVFaction("ahmosis");
	public static VVFaction embrosia = new VVFaction("embrosia");
	public static VVFaction irilis = new VVFaction("irilis");
	public static VVFaction koshaimas = new VVFaction("koshaimas");
	public static VVFaction skaahnorik = new VVFaction("skaahnorik");
	public static VVFaction[] factions = {ahmosis, embrosia, irilis, koshaimas, skaahnorik};
	
	public static VVFaction get(String nom)
	{
		for (VVFaction f : factions)
		{
			if (nom == f.getNom())
			{
				return f;
			}
		}
		return null;
	}
}

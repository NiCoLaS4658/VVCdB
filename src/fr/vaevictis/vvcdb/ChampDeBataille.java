package fr.vaevictis.vvcdb;

import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.util.Vector;

import com.massivecraft.factions.FPlayers;

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
		locationsjoueurs.add(p.getLocation());
		inventairesjoueurs.add(p.getInventory());
		VVCdB.equipPlayerArmor(p);
	}
	public void playerLeave(Player p)
	{
		p.getInventory().clear();
		p.getInventory().setArmorContents(inventairesjoueurs.get(joueurs.lastIndexOf(p)).getArmorContents());
		p.getInventory().setContents(inventairesjoueurs.get(joueurs.lastIndexOf(p)).getContents());
		p.teleport(locationsjoueurs.get(joueurs.lastIndexOf(p)));
		joueurs.remove(joueurs.lastIndexOf(p));
	}
	public static void arret()
	{
		ChampDeBataille cdb = champsdebataille.get(usedCdB);
		usedCdB = "";
		for (Position p : cdb.positions)
		{
			p.setFaction("");
			p.timer = -1;
			p.setFactionTaking("");
		}
		for (Player p : cdb.joueurs)
		{
			cdb.playerLeave(p);
		}
	}
	public static void lancerAttaque(String factionid)
	{
		usedCdB = factionid.toLowerCase();
		ChampDeBataille cdb = champsdebataille.get(usedCdB);
	}
	public List getPositions()
	{
		return this.positions;
	}
	public void spawnPlayer(Player p)
	{
		int nombrepos = 0;
		for (Position pos : positions)
		{
			if (pos.getFaction() == FPlayers.i.get(p).getFaction().getId())
			{
				nombrepos++;
			}
		}
		int i = (int) Math.random() * nombrepos;
		nombrepos = 0;
		for (Position pos : positions)
		{
			if (pos.getFaction() == FPlayers.i.get(p).getFaction().getId())
			{
				nombrepos++;
			}
			if (nombrepos == i)
			{
				int direction = (int) Math.random() * 4;
				int x = (int) (Math.random() * 20);
				Location l = new Location(Bukkit.getWorld("world"), 0D, 0D, 0D);
				if (direction == 0)
				{
					l = pos.getLocation().add(new Vector(x, 0, Math.sqrt(400 - Math.pow((double) x, 2))));
				}
				else if (direction == 1)
				{
					l = pos.getLocation().add(new Vector(-x, 0, Math.sqrt(400 - Math.pow((double) x, 2))));
				}
				else if (direction == 2)
				{
					l = pos.getLocation().add(new Vector(x, 0, -Math.sqrt(400 - Math.pow((double) x, 2))));
				}
				else if (direction == 3)
				{
					l = pos.getLocation().add(new Vector(-x, 0, -Math.sqrt(400 - Math.pow((double) x, 2))));
				}
				p.teleport(l);
			}
		}
		if (nombrepos == 0)
		{
			// Creer un spawn si aucun point !
		}
	}
	private Location defaultSpawn;
	private List<Player> joueurs;
	private List<Location> locationsjoueurs;
	private List<PlayerInventory> inventairesjoueurs;
	private List<Position> positions;
	private String nom;
	public static Map<String, ChampDeBataille> champsdebataille;
	public static String usedCdB;

}

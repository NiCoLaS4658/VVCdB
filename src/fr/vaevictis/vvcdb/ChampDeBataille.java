package fr.vaevictis.vvcdb;

import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.util.Vector;

import com.massivecraft.factions.FPlayers;

import fr.vaevictis.timertasks.TimerTaskGainPoints;

public class ChampDeBataille 
{
	public ChampDeBataille(String nom)
	{
		this.nom = nom.toLowerCase();
		this.ajouterMap();
		this.pointsToGet = 50;
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
		return getMap(key);
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
		boolean lancer = false;
		for (Player p2 : joueurs)
		{
			if (FPlayers.i.get(p).getFaction().getId() != FPlayers.i.get(p2).getFaction().getId())
			{
				lancer = true;
			}
		}
		if (lancer == true && cdbstarted == false)
		{
			lancerAttaque();
		}
		playerwholeft.remove(p);
		pointsToGet = 50 + 5 * (joueurs.size() + playerwholeft.size());
	}
	public void playerLeave(Player p)
	{
		p.getInventory().clear();
		p.getInventory().setArmorContents(inventairesjoueurs.get(joueurs.lastIndexOf(p)).getArmorContents());
		p.getInventory().setContents(inventairesjoueurs.get(joueurs.lastIndexOf(p)).getContents());
		p.teleport(locationsjoueurs.get(joueurs.lastIndexOf(p)));
		joueurs.remove(p);
		playerwholeft.add(p);
	}
	public static void arret()
	{
		ChampDeBataille cdb = getMap(usedCdB);
		usedCdB = "";
		cdbstarted = false;
		cdb.pointsToGet = 50;
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
		cdb.playerwholeft.clear();
	}
	public static void lancerAttaque()
	{
		ChampDeBataille cdb = getMap(usedCdB);
		cdbstarted = true;
		Bukkit.getServer().getScheduler().runTaskTimer(Bukkit.getServer().getPluginManager().getPlugin("VVCdB"), new TimerTaskGainPoints(), 400, 400);
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
		int nombreposbis = nombrepos;
		nombrepos = 0;
		if (nombreposbis !=0)
		{
			for (Position pos : positions)
			{
				if (pos.getFaction() == FPlayers.i.get(p).getFaction().getId())
				{
					nombrepos++;
				}
				if (nombrepos == i)
				{
					int direction = (int) (Math.random() * 4);
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
		}
		else if (nombrepos == 0)
		{
			p.teleport(spawns.get( (int) (Math.random() * this.spawns.size())));
		}
	}
	public int pointsToGet;
	private List<Location> spawns;
	private Location defaultSpawn;
	private List<Player> joueurs;
	private List<Player> playerwholeft;
	private List<Location> locationsjoueurs;
	private List<PlayerInventory> inventairesjoueurs;
	private List<Position> positions;
	private String nom;
	public static Map<String, ChampDeBataille> champsdebataille;
	public static String usedCdB;
	public static boolean cdbstarted;
}

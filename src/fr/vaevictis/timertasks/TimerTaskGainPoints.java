package fr.vaevictis.timertasks;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.massivecraft.factions.FPlayers;

import fr.vaevictis.vvcdb.ChampDeBataille;
import fr.vaevictis.vvcdb.Position;
import fr.vaevictis.vvcdb.VVFaction;

public class TimerTaskGainPoints extends BukkitRunnable 
{

	@Override
	public void run() 
	{
		for (Position pos : ChampDeBataille.getMap(ChampDeBataille.usedCdB).getPositions())
		{
			if (pos.getFaction().toLowerCase() != "")
			{
				VVFaction.get(pos.getFaction().toLowerCase()).setPoints(VVFaction.get(pos.getFaction().toLowerCase()).getPoints() + 1);
				if (VVFaction.get(pos.getFaction().toLowerCase()).getPoints() >= ChampDeBataille.getMap(ChampDeBataille.usedCdB).pointsToGet)
				{
					FileConfiguration config = Bukkit.getServer().getPluginManager().getPlugin("VVCdB").getConfig();
					int joueurs = 0;
					for (Player p : ChampDeBataille.getMap(ChampDeBataille.usedCdB).getJoueurs())
					{
						if (FPlayers.i.get(p).getFaction().getId() == pos.getFaction().toLowerCase())
						{
							joueurs++;
						}
					}
					config.set(VVFaction.get(pos.getFaction().toLowerCase()).getSaisonPointsPath(), config.getInt(VVFaction.get(pos.getFaction().toLowerCase()).getSaisonPointsPath()) + (int) (0.5 + Math.pow(1.3D, joueurs)));
					Bukkit.getServer().getPluginManager().getPlugin("VVCdB").saveConfig();
					ChampDeBataille.getMap(ChampDeBataille.usedCdB).arret();
					this.cancel();
				}
			}
		}
	}
}

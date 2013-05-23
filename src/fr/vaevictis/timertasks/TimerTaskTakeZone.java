package fr.vaevictis.timertasks;

import java.util.TimerTask;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.massivecraft.factions.FPlayers;
import com.massivecraft.factions.Factions;

import fr.vaevictis.vvcdb.ChampDeBataille;
import fr.vaevictis.vvcdb.Position;

public class TimerTaskTakeZone extends BukkitRunnable
{
	private Position position;
	private int taskid;
	private String factionid;
	
	public TimerTaskTakeZone(Position position, String factionid)
	{
		this.position = position;
		this.factionid = factionid;
	}
	
	@Override
	public void run()
	{
		for (Player player : ChampDeBataille.champsdebataille.get(ChampDeBataille.usedCdB).getJoueurs())
		{
			if (FPlayers.i.get(player).getFactionId() == factionid)
			{
				if (player.getLocation().distance(position.getLocation()) <= 5)
				{
					if (position.timer == -1)
					{
						position.timer = (byte) (position.isCouverte() ? 1 : 2);
					}
					else
					{
						position.timer += (byte) (position.isCouverte() ? 1 : 2);
					}
					if (position.timer == 40)
					{
						position.timer = -1;
						position.setFaction(this.factionid);
						Bukkit.getServer().broadcastMessage(ChatColor.DARK_RED + "[Champ de Bataille] La position " + position.getNom() + " a été pris par " + Factions.i.get(position.getFaction()).getTag() + ".");
						this.cancel();
					}
					return;
				}
			}
		}
	}
}

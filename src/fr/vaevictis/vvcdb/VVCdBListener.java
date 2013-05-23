package fr.vaevictis.vvcdb;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import com.massivecraft.factions.FPlayer;
import com.massivecraft.factions.FPlayers;
import com.massivecraft.factions.Factions;

import fr.vaevictis.timertasks.TimerTaskTakeZone;

public class VVCdBListener implements Listener 
{
	private VVCdB plugin;

	public VVCdBListener(VVCdB plugin)
	{
		this.plugin = plugin;
	}
	
	public List<Position> getPositions()
	{
		return ChampDeBataille.champsdebataille.get(ChampDeBataille.usedCdB).getPositions();
	}
	@EventHandler(priority = EventPriority.LOWEST)
	public void onPlayerMoving(PlayerMoveEvent e)
	{
		if (ChampDeBataille.usedCdB != "")
		{
			for (Position p : getPositions())
			{
				if (e.getTo().distance(p.getLocation()) <= 5)
				{
					Bukkit.getServer().getScheduler().runTaskTimer(this.plugin, new TimerTaskTakeZone(p, FPlayers.i.get(e.getPlayer()).getFaction().getId()), 20, 20);
				}
			}
		}
	}
}

package fr.vaevictis.timertasks;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.bukkit.scheduler.BukkitRunnable;

import fr.vaevictis.vvcdb.ChampDeBataille;
import fr.vaevictis.vvcdb.Position;

public class TimerTaskGainPoints extends BukkitRunnable 
{

	@Override
	public void run() 
	{
		Map<String, Integer> positions = new HashMap<String, Integer>();
		positions.put("Ahmosis", 0);
		positions.put("Embrosia", 0);
		positions.put("Irilis", 0);
		positions.put("Koshaimas", 0);
		positions.put("Skaahnorik", 0);
		for (Position pos : ChampDeBataille.getMap(ChampDeBataille.usedCdB).getPositions())
		{
			if (pos.getFaction() != "")
			{

			}
		}
	}
}

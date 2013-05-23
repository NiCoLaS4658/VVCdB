package fr.vaevictis.vvcdb;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class VVCdB extends JavaPlugin
{
	private VVCdBListener listener;
	@Override
	public void onEnable()
	{
		ChampDeBataille.usedCdB = "";
		// Chargement
	}
	
	@Override
	public void onDisable()
	{
		// Sauvegarde
	}
	
	public boolean onCommand(CommandSender sender, Command command, String label,String[] args)
	{
		Player p = (Player) sender;
		if (label.equalsIgnoreCase("cdbjoin") && args.length <= 1 && p.hasPermission("vvcdb.basic"))
		{
			if (ChampDeBataille.usedCdB != "" || ChampDeBataille.champsdebataille.get(args[0].toLowerCase()) != null)
			{
				if (ChampDeBataille.usedCdB == "")
				{
					ChampDeBataille.usedCdB = args[0].toLowerCase();
					ChampDeBataille.lancerAttaque();
				}
				ChampDeBataille.champsdebataille.get(ChampDeBataille.usedCdB).playerJoin(p);
			}
			return true;
		}
		else if (label.equalsIgnoreCase("cdbcreate"))
		{
			return true;
		}
		else if (label.equalsIgnoreCase("cdbaddposition"))
		{
			return true;
		}
		else if (label.equalsIgnoreCase("cdbsetdefaultspawn"))
		{
			
			return true;
		}
		else if (label.equalsIgnoreCase("cdbleave") && args.length == 0 && p.hasPermission("vvcdb.basic"))
		{
			ChampDeBataille.champsdebataille.get(ChampDeBataille.usedCdB).playerLeave(p);
			if (ChampDeBataille.champsdebataille.get(ChampDeBataille.usedCdB).getJoueurs().isEmpty())
			{
				ChampDeBataille.arret();
			}
		}
		return false;
	}
}

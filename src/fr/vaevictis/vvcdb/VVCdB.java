package fr.vaevictis.vvcdb;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import com.massivecraft.factions.FPlayers;

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
	
	public static String getArmure(Player p)
	{
		String armorpath = "armures." + p.getName();
		FileConfiguration config = Bukkit.getServer().getPluginManager().getPlugin("VVCdB").getConfig();
		return (config.get(armorpath)  == null || config.getString(armorpath) == "") ? "basic" : config.getString(armorpath);
	}
	public static void setArmure(Player p, String s)
	{
		String armorpath = "armures." + p.getName();
		FileConfiguration config = Bukkit.getServer().getPluginManager().getPlugin("VVCdB").getConfig();
		config.set(armorpath, s);
	}
	public static void equipPlayerArmor(Player p)
	{
		switch (getArmure(p))
		{
			case "basic":
				break;
			case "ahmosis1":
				break;
			case "ahmosis2":
				break;
			case "embrosia1":
				break;
			case "embrosia2":
				break;
			case "irilis1":
				break;
			case "irilis2":
				break;
			case "skaahnorik1":
				break;
			case "skaahnorik2":
				break;
			case "koshaimas1":
				break;
			case "koshaimas2":
				break;
		}
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
					ChampDeBataille.lancerAttaque(args[0]);
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
			return true;
		}
		else if (label.equalsIgnoreCase("acheterarmure") && args.length == 1 && p.hasPermission("vvcdb.basic"))
		{
			if (args[0].equalsIgnoreCase("légère") || args[0].equalsIgnoreCase("legere") || args[0].equalsIgnoreCase("1"))
			{
				switch (FPlayers.i.get(p).getFaction().getTag())
				{
					case "Ahmosis":
						setArmure(p, "ahmosis1");
						break;
					case "Embrosia":
						setArmure(p, "embrosia1");
						break;
					case "Irilis":
						setArmure(p, "irilis1");
						break;
					case "Skaahnorik":
						setArmure(p, "skaahnorik1");
						break;
					case "Koshaimas":
						setArmure(p, "koshaimas1");
						break;
				}
			}
			else if (args[0].equalsIgnoreCase("lourde") || args[0].equalsIgnoreCase("2"));
			{
				switch (FPlayers.i.get(p).getFaction().getTag())
				{
					case "Ahmosis":
						setArmure(p, "ahmosis2");
						break;
					case "Embrosia":
						setArmure(p, "embrosia2");
						break;
					case "Irilis":
						setArmure(p, "irilis2");
						break;
					case "Skaahnorik":
						setArmure(p, "skaahnorik2");
						break;
					case "Koshaimas":
						setArmure(p, "koshaimas2");
						break;
				}
			}
			return true;
		}
		return false;
	}
}

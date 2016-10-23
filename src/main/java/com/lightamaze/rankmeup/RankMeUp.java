package com.lightamaze.rankmeup;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.scarsz.discordsrv.DiscordSRV;
import com.scarsz.discordsrv.jda.entities.TextChannel;

public final class RankMeUp extends JavaPlugin {
	Boolean rmuDisabled = false;
    @Override
    public void onEnable() {

    	
    }
    @Override
    public void onDisable() {
    }
    
    
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    	if (cmd.getName().equalsIgnoreCase("rankmeup")||(cmd.getName().equalsIgnoreCase("rmu"))) {
    		if (!rmuDisabled) {
    			//Player player = getServer().getPlayer(sender.getName());
    			Player player = (Player)sender;
    			TextChannel channel = DiscordSRV.getTextChannelFromChannelName("rankmeup");
    			Location playerLocation = player.getLocation();
    			double x = playerLocation.getX();
    			double y = playerLocation.getY();
    			double z = playerLocation.getZ();
    			String world = playerLocation.getWorld().getName();
    			String locationString = String.format("%s - %.2f, %.2f, %.2f", world, x, y, z);
    			DiscordSRV.sendMessage(channel, "**"+sender.getName()+ "** is requesting to be ranked up for their build at coordinates **" + locationString+"** @here");
    			sender.sendMessage("You have requested for an admin to grade your build at "+ChatColor.YELLOW+locationString+ChatColor.WHITE+". Please be patient, it may take a while.");
    		}
    		else {
    			sender.sendMessage(ChatColor.RED+"RankMeUp submission is currently disabled.");
    		}
    	}
	
    	if (cmd.getName().equalsIgnoreCase("rmutoggle")) {
    		if (!rmuDisabled) {
    			sender.sendMessage(ChatColor.YELLOW+"RankMeUp submission is now disabled.");
    			rmuDisabled = true;
    		}
    		else {
    			sender.sendMessage(ChatColor.YELLOW+"RankMeUp submission is now enabled.");
    			rmuDisabled = false;
    		}
    	}

    	
			
    	return true;
    }
}
    	


	
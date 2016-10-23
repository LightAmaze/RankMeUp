package com.lightamaze.rankmeup;

import com.scarsz.discordsrv.DiscordSRV;
import com.scarsz.discordsrv.jda.entities.TextChannel;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class RankMeUp extends JavaPlugin {
  Boolean rmuDisabled = false;

  @Override
  public void onEnable() { }

  @Override
  public void onDisable() { }

  private String formatLocation(Location location) {
    // format: world - x, y, z
    return String.format("%s - %.2f, %.2f, %.2f",
         location.getWorld().getName(),
         location.getX(),
         location.getY(),
         location.getZ());
  }

  private void sendRankMessage(CommandSender sender) {
    Player player = (Player)sender;
    TextChannel channel = DiscordSRV.getTextChannelFromChannelName("rankmeup");
    String locationString = formatLocation(player.getLocation());
    DiscordSRV.sendMessage(channel, "**" + sender.getName() +
      "** is requesting to be ranked up for their build at coordinates **" + 
      locationString + "** @here");
    sender.sendMessage("You have requested for an admin to grade your build at " + 
      ChatColor.YELLOW + locationString + ChatColor.WHITE + 
      ". Please be patient, it may take a while.");
  }
    
  @Override
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    String cmdName = cmd.getName();
    if (cmdName.equalsIgnoreCase("rankmeup") || (cmdName.equalsIgnoreCase("rmu"))) {
      if (!rmuDisabled) {
        this.sendRankMessage(sender);
      }
      else {
        sender.sendMessage(ChatColor.RED + 
            "RankMeUp submission is currently disabled.");
      }
    }

    if (cmdName.equalsIgnoreCase("rmutoggle")) {
      if (!rmuDisabled) {
        sender.sendMessage(ChatColor.YELLOW + 
            "RankMeUp submission is now disabled.");
        rmuDisabled = true;
      }
      else {
        sender.sendMessage(ChatColor.YELLOW + 
            "RankMeUp submission is now enabled.");
        rmuDisabled = false;
      }
    }

    return true;
  }
}

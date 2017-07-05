package nl.dev7kasper.NukkitTester.main;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.entity.Entity;
import cn.nukkit.entity.data.LongEntityData;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.server.ServerCommandEvent;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.TextFormat;;

public class NukkitTester extends PluginBase implements Listener {
	
    @Override
    public void onEnable() {
        this.getLogger().info(TextFormat.DARK_GREEN + "NukkitTester ON");

        this.getLogger().info(String.valueOf(this.getDataFolder().mkdirs()));

        //Register the EventListener
        this.getServer().getPluginManager().registerEvents((this), this);
    }
    
    @Override
    public void onDisable() {
        this.getLogger().info(TextFormat.DARK_RED + "NukkitTester OFF");
    }

    @Override
    public boolean onCommand(CommandSender s, Command command, String label, String[] args) {
        switch (command.getName()) {
            case "leash":
                try {
                    if(s.isPlayer()) {
                    	Player p = (Player) s;
                    	s.sendMessage("Leashing the shit outta everyone.");
                    	for(Entity e : p.getLevel().getNearbyEntities(p.getBoundingBox(), p)) {
                    		e.setDataProperty(new LongEntityData(38, p.getId()));
                    		s.sendMessage("Leashed " + Long.toString(e.getId()) + "!");
                    	}
                    } else {
                    	s.sendMessage("Whattcha doing not being a player?");
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            break;
            case "unleash": 
                try {
                    if(s.isPlayer()) {
                    	Player p = (Player) s;
                    	for(Entity e : p.getLevel().getNearbyEntities(p.getBoundingBox(), p)) {
                    		e.setDataProperty(new LongEntityData(38, -1));
                    	}
                    } else {
                    	s.sendMessage("Whattcha doing not being a player?");
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            break;
        }
        return true;
    }
    
    @EventHandler
    public void onServerCommand(ServerCommandEvent event) {
    	
    }
}

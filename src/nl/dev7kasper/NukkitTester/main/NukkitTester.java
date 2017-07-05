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
        switch (command.getName().toLowerCase()) {
            case "leash":
                try {
                    if(s.isPlayer()) {
                    	Player p = (Player) s;
                    	s.sendMessage("Leashing the sh*t outta everyone.");
                    	for(Entity e : p.getLevel().getEntities()) {
                    		e.setDataFlag(0, 28, true);
                    		e.setDataProperty(new LongEntityData(38, p.getId()));
                    		s.sendMessage("Leashed " + Long.toString(e.getId()) + " to " + p.getId() + "!");
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
                    	if(args.length > 0) {
                    		if(args[0].equalsIgnoreCase("toself")) {
                    			s.sendMessage("Unleashing the sh*t outta everyone, self-style");
                            	for(Entity e : p.getLevel().getEntities()) {
                            		e.setDataProperty(new LongEntityData(38, e.getId()));
                            		s.sendMessage("(Tried) Unleashing " + Long.toString(e.getId()) + " to " + e.getId() + "!");
                            	}
                            	break;
                    		} else if(args[0].equalsIgnoreCase("respawn")){
                    			s.sendMessage("Unleashing the sh*t outta everyone, respawn-style");
                            	for(Entity e : p.getLevel().getEntities()) {
                            		e.respawnToAll();
                            		s.sendMessage("(Tried) Unleashing " + Long.toString(e.getId()) + " by respawning?");
                            	}
                            	break;
                    		}
                    	}
                    	s.sendMessage("Unleashing the sh*t outta everyone.");
                    	for(Entity e : p.getLevel().getEntities()) {
                    		e.setDataFlag(0, 28, false);
                    		e.setDataProperty(new LongEntityData(38, -1));
                    		s.sendMessage("(Tried) Unleashing " + Long.toString(e.getId()) + " from " + p.getId() + "!");
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

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
        this.getLogger().info(TextFormat.YELLOW + "NukkitTester ON");

        this.getLogger().info(String.valueOf(this.getDataFolder().mkdirs()));

        //Register the EventListener
        this.getServer().getPluginManager().registerEvents((this), this);
    }
    
    @Override
    public void onDisable() {
        this.getLogger().info(TextFormat.YELLOW + "NukkitTester OFF");
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
                    		e.setDataFlag(Entity.DATA_FLAGS, Entity.DATA_FLAG_LEASHED, true);
                    		e.setDataProperty(new LongEntityData(Entity.DATA_LEAD_HOLDER_EID, p.getId()));
                    		s.sendMessage("Leashed " + Long.toString(e.getId()) + " to " + p.getId() + "!");
                    	}
                    } else {
                    	s.sendMessage(TextFormat.DARK_RED + "[Error] Whattcha doing not being a player?");
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
                    		switch(args[0].toLowerCase()) {
                    			case "toself":
                        			s.sendMessage("Unleashing the sh*t outta everyone, selfy style.");
                                	for(Entity e : p.getLevel().getEntities()) {
                                		e.setDataProperty(new LongEntityData(Entity.DATA_LEAD_HOLDER_EID, e.getId()));
                                		s.sendMessage("(Tried) Unleashing " + Long.toString(e.getId()) + " to " + e.getId() + "!");
                                	}
                    			return true;
                    			case "respawn":
                        			s.sendMessage("Unleashing the sh*t outta everyone, respawn-style.");
                                	for(Entity e : p.getLevel().getEntities()) {
                                		e.respawnToAll();
                                		s.sendMessage("(Tried) Unleashing " + Long.toString(e.getId()) + " by respawning?");
                                	}
                				return true;
                    			case "zero":
                    			case "0":
                        			s.sendMessage("Unleashing the sh*t outta everyone, " + TextFormat.BLACK + "Zorro" + TextFormat.RESET + " style.");
                                	for(Entity e : p.getLevel().getEntities()) {
                                		e.setDataFlag(Entity.DATA_FLAGS, Entity.DATA_FLAG_LEASHED, false);
                                		e.setDataProperty(new LongEntityData(Entity.DATA_LEAD_HOLDER_EID, 0));
                                		s.sendMessage("(Tried) Unleashing " + Long.toString(e.getId()) + " by zeroing.");
                                	}
                				return true;
                    			case "chuck":
                    				if(args.length > 1 && args[1].equalsIgnoreCase("norris")) {
                            			s.sendMessage("Unleashing the sh*t outta everyone, " + TextFormat.YELLOW + "Chuck Norris" + TextFormat.RESET + " style!");
                                    	for(Entity e : p.getLevel().getEntities()) {
                                    		e.setDataFlag(Entity.DATA_FLAGS, Entity.DATA_FLAG_LEASHED, false);
                                    		e.setDataProperty(new LongEntityData(Entity.DATA_LEAD_HOLDER_EID, -1));
                                    		e.despawnFromAll();
                                    		e.spawnToAll();
                                    	}
                                    	return true;
                    				}
                    				break;
                    		}
                    	}
                    	s.sendMessage("Unleashing the sh*t outta everyone.");
                    	for(Entity e : p.getLevel().getEntities()) {
                    		e.setDataFlag(Entity.DATA_FLAGS, Entity.DATA_FLAG_LEASHED, false);
                    		e.setDataProperty(new LongEntityData(Entity.DATA_LEAD_HOLDER_EID, -1));
                    		s.sendMessage("(Tried) Unleashing " + Long.toString(e.getId()) + " from " + p.getId() + "!");
                    	}
                    } else {
                    	s.sendMessage(TextFormat.DARK_RED + "[Error] Whattcha doing not being a player?");
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

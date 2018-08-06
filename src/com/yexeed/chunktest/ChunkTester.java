package com.yexeed.chunktest;

import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.level.Level;
import cn.nukkit.level.Position;
import cn.nukkit.plugin.PluginBase;

public class ChunkTester extends PluginBase {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(command.getName().equalsIgnoreCase("chunkload")){
            if(args.length < 1){
                sender.sendMessage("Select radius");
                return true;
            }
            String num = args[0];
            int numm;
            try{
                numm = Integer.parseInt(num);
            }catch (NumberFormatException e){
                sender.sendMessage("Radius must be an integer");
                return true;
            }
            if(numm < 1){
                sender.sendMessage("Radius can't be lower than 0!");
                return true;
            }
            this.loadChunks(numm);

        }
        return false;
    }

    private void loadChunks(int radius){
        Level lev = this.getServer().getDefaultLevel();
        Position spawn = lev.getSpawnLocation();
        int x = spawn.getFloorX() >> 4;
        int z = spawn.getFloorZ() >> 4;
        this.getLogger().info("Loading chunks (radius = " + radius + ")");
        int chunksLoaded = 0;
        for(int chunkX = -radius; chunkX <= radius; chunkX++){
            for(int chunkZ = -radius; chunkZ <= radius; chunkZ++){
                if(Math.sqrt(chunkX * chunkX + chunkZ * chunkZ) <= radius){
                    lev.loadChunk(chunkX + x, chunkZ + z);
                    chunksLoaded++;
                }
            }
        }
        this.getLogger().info(chunksLoaded + " chunks loaded");
    }
}

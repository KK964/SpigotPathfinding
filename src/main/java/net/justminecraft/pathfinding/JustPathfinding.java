package net.justminecraft.pathfinding;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class JustPathfinding extends JavaPlugin implements Listener {

    private static JustPathfinding plugin;

    public void onEnable() {
        plugin = this;
    }

    public List<Block> getPath(Entity e, Location l) {
        Block startBlock = e.getLocation().getBlock();
        Block endBlock = l.getBlock();
        int[] startLocation = {startBlock.getX(), startBlock.getY(), startBlock.getZ()};
        int[] endLocation = {endBlock.getX(), endBlock.getY(), endBlock.getZ()};
        World w = e.getWorld();
        List<Node> pathNode = new Pathfinding().getRoute(null, startLocation, endLocation, w);
        List<Block> path = new ArrayList<>();
        for (Node n : pathNode)
            path.add(w.getBlockAt(n.getX(), n.getY(), n.getZ()));
        return path;
    }
}

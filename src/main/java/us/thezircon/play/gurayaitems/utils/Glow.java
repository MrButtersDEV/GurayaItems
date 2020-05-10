package us.thezircon.play.gurayaitems.utils;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.WrappedDataWatcher;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import us.thezircon.play.gurayaitems.GurayaItems;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class Glow {

    private static final GurayaItems plugin = GurayaItems.getPlugin(GurayaItems.class);

    public static void setGlow(Player player, Entity entity) {
        ProtocolManager pm = ProtocolLibrary.getProtocolManager();
        PacketContainer packet2 = pm.createPacket(PacketType.Play.Server.ENTITY_METADATA);
        packet2.getIntegers().write(0, entity.getEntityId());
        WrappedDataWatcher watcher = new WrappedDataWatcher();
        WrappedDataWatcher.Serializer serializer = WrappedDataWatcher.Registry.get(Byte.class);
        watcher.setEntity(player);
        watcher.setObject(0, serializer, (byte) (0x40));
        packet2.getWatchableCollectionModifier().write(0, watcher.getWatchableObjects());
        try {
            ProtocolLibrary.getProtocolManager().sendServerPacket(player, packet2);
            if (plugin.glowingInfo.containsKey(player)) {
                ArrayList<Entity> glowList = plugin.glowingInfo.get(player);
                glowList.add(entity);
                plugin.glowingInfo.put(player, glowList);
            } else {
                ArrayList<Entity> glowList = new ArrayList<>();
                glowList.add(entity);
                plugin.glowingInfo.put(player, glowList);
            }
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public static void addGlowList(Player player, Entity entity) {
        if (plugin.glowingInfo.containsKey(player)) {
            ArrayList<Entity> glowList = plugin.glowingInfo.get(player);
            glowList.add(entity);
            plugin.glowingInfo.put(player, glowList);
        } else {
            ArrayList<Entity> glowList = new ArrayList<>();
            glowList.add(entity);
            plugin.glowingInfo.put(player, glowList);
        }
    }

    public static void removeGlowList(Player player, Entity entity){
        if (plugin.glowingInfo.containsKey(player)) {
            ArrayList<Entity> glowList = plugin.glowingInfo.get(player);
            glowList.remove(entity);
            plugin.glowingInfo.put(player, glowList);
        }
    }

    public static void unglow(Player player, Entity entity) {
        ProtocolManager pm = ProtocolLibrary.getProtocolManager();
        PacketContainer packet2 = pm.createPacket(PacketType.Play.Server.ENTITY_METADATA);
        packet2.getIntegers().write(0, entity.getEntityId());
        WrappedDataWatcher watcher = new WrappedDataWatcher();
        WrappedDataWatcher.Serializer serializer = WrappedDataWatcher.Registry.get(Byte.class);
        watcher.setEntity(player);
        //watcher.setObject(0, serializer, (byte) (0x40));
        packet2.getWatchableCollectionModifier().write(0, watcher.getWatchableObjects());
        try {
            ProtocolLibrary.getProtocolManager().sendServerPacket(player, packet2);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}

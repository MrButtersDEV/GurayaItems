package us.thezircon.play.gurayaitems;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import us.thezircon.play.gurayaitems.commands.GiveItem;
import us.thezircon.play.gurayaitems.commands.Reload;
import us.thezircon.play.gurayaitems.items.Tracker;
import us.thezircon.play.gurayaitems.listeners.*;

import java.util.ArrayList;
import java.util.HashMap;

public final class GurayaItems extends JavaPlugin {
  //  private ProtocolManager protocolManager;

    public HashMap<Player, Player> trackingInfo = new HashMap<>();
    public HashMap<Player, ArrayList<Entity>> glowingInfo = new HashMap<>();
    public HashMap<Player, Long> cooldownSmokeBomb = new HashMap<>();
    public HashMap<Player, Long> cooldownGrapplingHook = new HashMap<>();
    public HashMap<Player, Long> cooldownMinigun = new HashMap<>();
    public HashMap<Player, Long> cooldownRiggedPen = new HashMap<>();

    @Override
    public void onEnable() {
        // Plugin startup logic
      //  protocolManager = ProtocolLibrary.getProtocolManager();
        //Config
        getConfig().options().copyDefaults();
        saveDefaultConfig(); // Saves Config.

        //Commands
        getCommand("gitems").setExecutor(new GiveItem());
        getCommand("guraya-reload").setExecutor(new Reload());



/*
protocolManager.addPacketListener(
  new PacketAdapter(this, ListenerPriority.NORMAL, PacketType.Play.Server.ENTITY_METADATA) {
    @Override
    public void onPacketSending(PacketEvent e) {
        Player player = e.getPlayer();
        ItemStack helmet = player.getInventory().getHelmet();
        if (helmet.getItemMeta().getLore().equals(XRayGoggles.getGoggles().getItemMeta().getLore()) && helmet.getItemMeta().getDisplayName().equals(XRayGoggles.getGoggles().getItemMeta().getDisplayName())) {
            PacketContainer packet = e.getPacket();
            int entityID = packet.getIntegers().read(0);
            for (Entity en : player.getNearbyEntities(25, 25,25)) {
                if (entityID==en.getEntityId()) {
                    System.out.println("Noted!");
                    WrappedDataWatcher watcher = new WrappedDataWatcher();
                    WrappedDataWatcher.Serializer serializer = WrappedDataWatcher.Registry.get(Byte.class);
                    watcher.setEntity(player);
                    watcher.setObject(0, serializer, (byte) (0x40));
                    packet.getWatchableCollectionModifier().write(0, watcher.getWatchableObjects());
                    Glow.addGlowList(player, en);
                }
            }
        }
    }
});    */

        //Listeners
        getServer().getPluginManager().registerEvents(new InteractListener(), this);
        getServer().getPluginManager().registerEvents(new TogglePenListener(), this);
        getServer().getPluginManager().registerEvents(new ExecuteSmokeBombListener(), this);
        getServer().getPluginManager().registerEvents(new ActivateTrackerListener(), this);
        getServer().getPluginManager().registerEvents(new LeaveServerListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerFishListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerGrappleListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerShootListener(), this);


        //Update Locators
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                Tracker tracker = new Tracker();
                for (Player players : Bukkit.getServer().getOnlinePlayers()) {
                    if (trackingInfo.containsKey(players)) {

                        Tracker trackingTracker = new Tracker(trackingInfo.get(players));

                        if (players.getLocation().distance(trackingInfo.get(players).getLocation())<100) {
                            players.setCompassTarget(trackingInfo.get(players).getLocation());
                        } else {
                            players.setCompassTarget(players.getWorld().getSpawnLocation());
                            players.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cTracking of your target has been lost."));
                            int slot = players.getInventory().first(trackingTracker.getTracker());
                            players.getInventory().setItem(slot, tracker.getTracker());
                            trackingInfo.remove(players);
                        }
                    }

        /*        if (glowingInfo.containsKey(players)) {
                    ArrayList<Entity> glowList = glowingInfo.get(players);
                    for (Entity en : glowList) {
                        if (en.isValid() && en.getLocation().distance(players.getLocation())>25) {
                            Glow.removeGlowList(players, en);
                            Glow.unglow(players, en);
                        }
                    }
                }  */

                }
            }
        }, 0L, 20L);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

        //Reset all player trackers.
        Tracker tracker = new Tracker();
        for (Player players : Bukkit.getOnlinePlayers()) {
            if (trackingInfo.containsKey(players)) {
                Tracker trackingTracker = new Tracker(trackingInfo.get(players));
                players.setCompassTarget(players.getWorld().getSpawnLocation());
                int slot = players.getInventory().first(trackingTracker.getTracker());
                players.getInventory().setItem(slot, tracker.getTracker());
                trackingInfo.remove(players);
            }
        }
    }
}

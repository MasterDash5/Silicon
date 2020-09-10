package dashnetwork.silicon.listeners;

import dashnetwork.silicon.Injector;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Injector.registerPackets(event.getPlayer());
    }

}

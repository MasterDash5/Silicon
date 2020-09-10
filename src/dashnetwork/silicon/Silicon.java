package dashnetwork.silicon;

import dashnetwork.silicon.listeners.JoinListener;
import org.bukkit.plugin.java.JavaPlugin;

public class Silicon extends JavaPlugin {

    private static Silicon instance;

    public static Silicon getInstance() {
        return instance;
    }

    @Override
    public void onLoad() {
        try {
            Injector.registerAll();
        } catch (Exception exception) {
            exception.printStackTrace();

            getServer().shutdown();
        }
    }

    @Override
    public void onEnable() {
        instance = this;

        // getServer().getPluginManager().registerEvents(new JoinListener(), this);
    }

    @Override
    public void onDisable() {
        getServer().shutdown();
    }

}

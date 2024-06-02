package net.scottkloon;

import net.scottkloon.collectibles.WingsFlameCommand;
import org.bukkit.plugin.java.JavaPlugin;

public class KloonAPI extends JavaPlugin  {

    @Override
    public void onEnable() {

        this.getCommand("wing").setExecutor(new WingsFlameCommand(this));
    }

    @Override
    public void onDisable() {
        // Código para quando o plugin é desabilitado (opcional)
    }


}


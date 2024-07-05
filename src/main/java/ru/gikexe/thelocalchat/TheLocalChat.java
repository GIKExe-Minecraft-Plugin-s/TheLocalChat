package ru.gikexe.thelocalchat;

import org.bukkit.plugin.java.JavaPlugin;

public final class TheLocalChat extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new ChatListener(), this);
    }

    @Override
    public void onDisable() {}
}

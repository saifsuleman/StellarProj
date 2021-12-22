package io.pulsarlabs.stellarproj.data;

import com.google.common.base.Charsets;
import io.pulsarlabs.stellarproj.StellarProj;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class YamlConfig {
    private final String name;
    private File file;
    private FileConfiguration config;

    public YamlConfig(String name, String defaults) {
        if (!name.toLowerCase().endsWith(".yml")) {
            name += ".yml";
        }
        this.name = name;
        load(defaults != null ? StellarProj.getInstance().getResource(defaults) : null);
    }

    public YamlConfig(String name) {
        this(name, null);
    }

    private void load(InputStream stream) {
        try {
            file = new File(StellarProj.getInstance().getDataFolder() + "/" + name);
            if (!file.exists()) {
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }
                file.createNewFile();
            }
            config = YamlConfiguration.loadConfiguration(file);
            if (stream != null) {
                config.setDefaults(YamlConfiguration.loadConfiguration(new InputStreamReader(stream, Charsets.UTF_8)));
                config.options().copyDefaults(true);
            }
            save();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }

    public File getFile() {
        return file;
    }

    public FileConfiguration getConfig() {
        return config;
    }

    public void save() {
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

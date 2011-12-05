package me.herghost.Fiery.util;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

import me.herghost.Fiery.Fiery;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class ConfigurationHandler 
{
	private FileConfiguration config;
    private File file;
    
    public ConfigurationHandler(String fileName, String header) 
    {
        this.file = new File(fileName);
        this.config = new YamlConfiguration();
        this.config.options().header(header);
        if (!this.file.exists()) 
        	{
            	create();
        	} 
        
        else 
        	{
            	load();
        	}
    }
    
    public void load() 
    {
        try 
        	{
            	this.config.load(this.file);
        	} 
        
        catch (Exception ex) 
        	{
            	ex.printStackTrace();
        	}
    }
    
    public void save() 
    {
        try 
        	{
            	this.config.save(this.file);
        	} 
        
        catch (IOException ex) 
        	{
            	ex.printStackTrace();
        	}
    }
    
    private void create() {
        try {
        	Fiery.getLogger().log("Creating new file at " + this.file.getName() + ".");
            this.file.getParentFile().mkdirs();
            this.file.createNewFile();
            save();
        } catch (IOException ex) {
        	Fiery.getLogger().log(Level.SEVERE, "Unable to create " + this.file.getPath() + ".");
        }
    }
    
    public void copyDefaults(boolean value) {
    	this.config.options().copyDefaults(value);
    }
    
    public void addDefault(String path, String string) {
    	this.config.addDefault(path, string);
    }
    
    public void addDefault(String path, boolean b) {
    	this.config.addDefault(path, b);
    }
    
    public void addDefault(String path, int value) {
    	this.config.addDefault(path, value);
    }
    
    public void addDefault(String path, double value) {
    	this.config.addDefault(path, value);
    }

    public void removeKey(String path) {
        this.config.set(path, null);
    }

    public boolean pathExists(String path) {
        return this.config.get(path) != null;
    }

    public boolean pathExists(int path) {
        return pathExists("" + path);
    }

    public String getString(String path) {
        if (this.pathExists(path)) {
            return this.config.get(path).toString();
        }
        return "";
    }

    public String getString(String path, String value) {
        if (this.pathExists(path)) {
            return this.config.getString(path);
        } else {
            this.setString(path, value);
        }
        return value;
    }

    public void setString(String path, String value) {
        this.config.set(path, value);
    }

    public int getInt(String path) {
        if (this.pathExists(path)) {
            if (this.config.getString(path) == null) {
                return this.config.getInt(path);
            }
            return Integer.parseInt(this.config.getString(path));
        }
        return 0;
    }

    public int getInt(String path, int value) {
        return this.config.getInt(path, value);
    }

    public void setInt(String path, int value) {
        this.config.set(path, value);
    }

    public double getDouble(String path) {
        if (this.pathExists(path)) {
            if (this.config.getString(path) == null) {
                if (this.config.get(path) instanceof Integer) {
                    return this.config.getInt(path);
                }
                return this.config.getDouble(path);
            }
            return Double.parseDouble(this.config.getString(path));
        }
        return 0;
    }

    public double getDouble(String path, double value) {
        return this.config.getDouble(path, value);
    }

    public void setDouble(String path, double value) {
        this.config.set(path, String.valueOf(value));
    }

    public long getLong(String path) {
        if (this.pathExists(path)) {
            if (this.config.getString(path) == null) {
                if (this.config.get(path) instanceof Integer) {
                    return this.config.getInt(path);
                }
                return this.config.getLong(path);
            }
            return Long.parseLong(this.config.getString(path));
        }
        return 0;
    }

    public long getLong(String path, long value) {
        return this.config.getInt(path, (int) value);
    }

    public void setLong(String path, long value) {
        this.config.set(path, value);
    }

    public boolean getBoolean(String path) {
        if (this.pathExists(path)) {
            if (this.config.getString(path) == null) {
                return this.config.getBoolean(path);
            }
            return Boolean.parseBoolean(this.config.getString(path));
        }
        return false;
    }

    public boolean getBoolean(String path, boolean value) {
        return this.config.getBoolean(path, value);
    }

    public void setBoolean(String path, boolean value) {
        this.config.set(path, value);
    }

    public Object getRaw(String path) {
        return this.config.get(path);
    }

    public void setRaw(String path, Object value) {
        this.config.set(path, value);
    }

    public Set<String> getKeys(String path) {
        if (path == null || path.isEmpty())
            return this.config.getRoot().getKeys(false);
        if (this.config.getConfigurationSection(path) == null) {
            return new HashSet<String>();
        }
        return this.config.getConfigurationSection(path).getKeys(false);
    }

	public void addDefault(String path, List<String> list) {
		this.config.addDefault(path, list);
	}

	public List<?> getList(String path) {
		return this.config.getList(path);

	}

	
}

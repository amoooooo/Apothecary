package coffee.amo.apothecary;

import coffee.amo.apothecary.compat.CompatHolder;
import com.google.gson.JsonElement;
import com.mojang.datafixers.util.Pair;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoader;
import net.minecraftforge.fml.ModLoadingContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CompatResolver {
    public static final List<Class<?>> loadedEntities = new ArrayList<>();

    public static final List<CompatHolder> compatibleMods = new ArrayList<>();

    // Unused rn
    public static void init(){
        for (CompatHolder compatHolder : compatibleMods) {
            if (ModList.get().isLoaded(compatHolder.modid)) {
                try {
                    Class<?> clazz = Class.forName(compatHolder.className);
                    loadedEntities.add(clazz);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

package coffee.amo.apothecary;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import shadows.apotheosis.Apoth;

import java.lang.reflect.Method;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ApothecaryEvents {

    @SubscribeEvent
    static void overrideDamage(EntityJoinLevelEvent e){
        if(e.getLevel().isClientSide) return;
        if(e.getEntity() instanceof AbstractHurtingProjectile prof){
            int damage = 0;
            if(prof.getOwner() instanceof Player pl){
                if(pl.getAttributes().hasAttribute(Apoth.Attributes.ARROW_DAMAGE.get())){
                    damage = (int) pl.getAttributes().getValue(Apoth.Attributes.ARROW_DAMAGE.get());
                }
            }
            for(Class<?> clazz : CompatResolver.loadedEntities){
                if(clazz.isInstance(e.getEntity())) {
                    String methodName = CompatResolver.compatibleMods.stream().filter(compatHolder -> compatHolder.className.equals(clazz.getName())).findFirst().get().methodName;
                    String damageMethodName = CompatResolver.compatibleMods.stream().filter(compatHolder -> compatHolder.className.equals(clazz.getName())).findFirst().get().getDamageMethodName;
                    try {
                        Method method = clazz.getMethod(methodName, Number.class);
                        //unused
                        Object[] parameters = new Object[method.getParameterCount()];
                        Method getDamage = clazz.getMethod(damageMethodName);
                        method.invoke(getDamage.invoke(clazz.cast(e.getEntity())), damage);
                    } catch (Exception exception) {
                        Apothecary.LOGGER.error("Error while trying to invoke method " + methodName + " on class " + clazz.getName());
                        exception.printStackTrace();
                    }
                }
            }
        }
    }
}

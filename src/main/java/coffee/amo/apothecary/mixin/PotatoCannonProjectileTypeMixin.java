package coffee.amo.apothecary.mixin;

import coffee.amo.apothecary.compat.CannonExtension;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(targets = "com.simibubi.create.content.curiosities.weapons.PotatoCannonProjectileType")
public class PotatoCannonProjectileTypeMixin implements CannonExtension {

    @Shadow
    private int damage;

    @Override
    public void setDamage(int damage) {
        this.damage = damage;
    }
}

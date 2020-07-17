package tfar.enchantablehorsearmor.mixin;

import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.item.HorseArmorItem;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import tfar.enchantablehorsearmor.EnchantableHorseArmor;

@Mixin(targets = {"net.minecraft.enchantment.EnchantmentTarget$1",//ARMOR
				"net.minecraft.enchantment.EnchantmentTarget$7",//ARMOR_FEET
				"net.minecraft.enchantment.EnchantmentTarget$8",//ARMOR_LEGS
				"net.minecraft.enchantment.EnchantmentTarget$9",//ARMOR_CHEST
				"net.minecraft.enchantment.EnchantmentTarget$10",//ARMOR_HEAD
})
public class EnchantmentTargetMixin {

	@Inject(method = "isAcceptableItem(Lnet/minecraft/item/Item;)Z",at = @At("HEAD"),cancellable = true)
	private void enchantHorseArmor(Item itemIn, CallbackInfoReturnable<Boolean> cir){
		if (itemIn instanceof HorseArmorItem) cir.setReturnValue(true);
	}
}

package tfar.enchantablehorsearmor;

import com.google.common.collect.Sets;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.VertexConsumers;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.HorseEntity;
import net.minecraft.item.HorseArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class EnchantableHorseArmor {

	public static VertexConsumer renderHorseArmorGlintHook(VertexConsumer old, MatrixStack matrixStackIn, VertexConsumerProvider bufferIn, int packedLightIn, HorseEntity entitylivingbaseIn,
																												 float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
		ItemStack stack = entitylivingbaseIn.getArmorType();
		HorseArmorItem horseArmorItem = (HorseArmorItem) stack.getItem();
		boolean glint = stack.hasGlint();
		if (glint) {
			Identifier texture = horseArmorItem.getEntityTexture();
			return VertexConsumers.dual(
							bufferIn.getBuffer(RenderLayer.getEntityGlint()),
							bufferIn.getBuffer(RenderLayer.getEntityCutoutNoCull(texture))
			);
		}
		return old;
	}

	public static final Set<EnchantmentTarget> acceptable = Arrays.stream(EnchantmentTarget.values())
					.filter(enchantmentTarget -> enchantmentTarget.ordinal() < 5).collect(Collectors.toSet());

	public static void checkHorseHook(Enchantment enchantment, LivingEntity livingEntity, CallbackInfoReturnable<Integer> cir) {
		if (livingEntity instanceof HorseEntity) {
			ItemStack armor = ((HorseEntity) livingEntity).getArmorType();
			if (armor.getItem() instanceof HorseArmorItem) {
				int level = EnchantmentHelper.getLevel(enchantment, armor);
				cir.setReturnValue(level);
			}
		}
	}

	public static boolean isAcceptable(Item itemIn,EnchantmentTarget target) {
		return itemIn instanceof HorseArmorItem;
	}
}

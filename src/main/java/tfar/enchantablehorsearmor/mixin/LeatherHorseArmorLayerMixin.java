package tfar.enchantablehorsearmor.mixin;

import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.HorseArmorFeatureRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.passive.HorseEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import tfar.enchantablehorsearmor.EnchantableHorseArmor;

@Mixin(HorseArmorFeatureRenderer.class)
public class LeatherHorseArmorLayerMixin {

	@ModifyVariable(method = "render",at = @At(value = "INVOKE_ASSIGN",target = "Lnet/minecraft/client/render/VertexConsumerProvider;getBuffer(Lnet/minecraft/client/render/RenderLayer;)Lnet/minecraft/client/render/VertexConsumer;"))
	private VertexConsumer renderHorseArmorGlint(VertexConsumer old, MatrixStack matrixStackIn, VertexConsumerProvider bufferIn, int packedLightIn, HorseEntity entitylivingbaseIn,
																							 float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
		return EnchantableHorseArmor.renderHorseArmorGlintHook(old, matrixStackIn, bufferIn, packedLightIn, entitylivingbaseIn, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch);
	}
}

package com.falaut.angelwings;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.client.ICurioRenderer;

public class AngelWingsClientRenderer implements ICurioRenderer {
    public static final ModelResourceLocation WING_MODEL = ModelResourceLocation.standalone(ResourceLocation.fromNamespaceAndPath(AngelWings.MODID, "item/angel_wings_wing"));

    @Override
    public <T extends LivingEntity, M extends EntityModel<T>> void render(ItemStack stack, SlotContext slotContext, PoseStack poseStack,
                                                                          RenderLayerParent<T, M> renderLayerParent, MultiBufferSource bufferSource,
                                                                          int light, float limbSwing, float limbSwingAmount, float partialTicks,
                                                                          float ageInTicks, float netHeadYaw, float headPitch) {
        LivingEntity wearer = slotContext.entity();
        if (!(renderLayerParent.getModel() instanceof HumanoidModel<?> humanoidModel)) {
            return;
        }

        BakedModel wingModel = Minecraft.getInstance().getModelManager().getModel(WING_MODEL);
        boolean flying = wearer instanceof Player player && player.getAbilities().flying;
        float tickTime = wearer.tickCount + partialTicks;
        float flap = 20.0F + (Mth.sin(tickTime * (flying ? 0.4F : 0.2F)) + 0.5F) * (flying ? 30.0F : 5.0F);

        poseStack.pushPose();
        humanoidModel.body.translateAndRotate(poseStack);
        poseStack.translate(0.0D, 0.5D, 0.2D);

        for (int i = 0; i < 2; i++) {
            poseStack.pushPose();
            poseStack.mulPose(Axis.YP.rotationDegrees(i == 0 ? flap : 180.0F - flap));
            poseStack.translate(-1.0D, 0.0D, 0.0D);
            poseStack.mulPose(Axis.ZP.rotationDegrees(-60.0F));
            poseStack.scale(1.5F, -1.5F, -1.5F);
            Minecraft.getInstance().getItemRenderer().render(stack, ItemDisplayContext.NONE, false, poseStack, bufferSource, light, OverlayTexture.NO_OVERLAY, wingModel);
            poseStack.popPose();
        }

        poseStack.popPose();
    }
}

package com.falaut.angelwings;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.List;

public class AngelWingsItem extends Item implements ICurioItem {
    public AngelWingsItem(Properties properties) {
        super(properties);
    }

    @Override
    public boolean canEquipFromUse(SlotContext slotContext, ItemStack stack) {
        return true;
    }

    @Override
    public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
        toggleFlying(slotContext.entity(), true);
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        toggleFlying(slotContext.entity(), false);
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        toggleFlying(slotContext.entity(), true);
    }

    public void toggleFlying(LivingEntity entity, boolean enable) {
        if (entity instanceof Player player) {
            if (player.isCreative() || player.isSpectator()) {
                return;
            }

            if (player.getAbilities().mayfly != enable) {
                player.getAbilities().mayfly = enable;
                if (!enable) {
                    player.getAbilities().flying = false;
                }
                player.onUpdateAbilities();
            }
        }
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @NotNull Item.TooltipContext context, @NotNull List<Component> tooltip, @NotNull TooltipFlag flag) {
        tooltip.add(Component.translatable("tooltip.angelwings.angel_wings.description").withStyle(ChatFormatting.AQUA));
    }

}

package com.falaut.angelwings;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

@Mod(AngelWings.MODID)
public class AngelWings {
    public static final String MODID = "angel_wings";
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Registries.ITEM, MODID);
    public static final Supplier<Item> ANGEL_WINGS = ITEMS.register("angel_wings", () -> new AngelWingsItem(new Item.Properties().stacksTo(1)));

    public AngelWings(IEventBus modEventBus) {
        ITEMS.register(modEventBus);
        modEventBus.register(this);
        if (FMLEnvironment.dist == Dist.CLIENT) {
            AngelWingsClient.register(modEventBus);
        }
    }

    @SubscribeEvent
    public void buildContents(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.COMBAT) {
            event.accept(ANGEL_WINGS.get());
        }
    }

}

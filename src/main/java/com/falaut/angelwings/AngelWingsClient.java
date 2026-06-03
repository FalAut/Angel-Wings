package com.falaut.angelwings;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.ModelEvent;
import top.theillusivec4.curios.api.client.CuriosRendererRegistry;

public class AngelWingsClient {
    public static void register(IEventBus modEventBus) {
        modEventBus.addListener(AngelWingsClient::clientSetup);
        modEventBus.addListener(AngelWingsClient::registerAdditionalModels);
    }

    private static void clientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> CuriosRendererRegistry.register(AngelWings.ANGEL_WINGS.get(), AngelWingsClientRenderer::new));
    }

    private static void registerAdditionalModels(ModelEvent.RegisterAdditional event) {
        event.register(AngelWingsClientRenderer.WING_MODEL);
    }
}

package org.asch.bulkit.api.registry;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.asch.bulkit.api.BulkItApi;

public class CreativeModTabs {
    private static final DeferredRegister<CreativeModeTab> CREATIVE_TABS;

    private static final CreativeModeTab.Builder DISKS_BUILDER;
    private static final DeferredHolder<CreativeModeTab, CreativeModeTab> DISKS;

    static {
        CREATIVE_TABS = DeferredRegister.create(BuiltInRegistries.CREATIVE_MODE_TAB, BulkItApi.ID);

        DISKS_BUILDER = CreativeModeTab.builder().title(Component.literal("BulkIt! - Disks")).withSearchBar().displayItems(CreativeModTabs::disks);
        DISKS = CREATIVE_TABS.register("disks", DISKS_BUILDER::build);
    }

    public static void register(IEventBus modBus) {
        CREATIVE_TABS.register(modBus);
    }

    private static void disks(CreativeModeTab.ItemDisplayParameters displayParameters, CreativeModeTab.Output output) {
        BulkItRegistries.DISKS.getEntries().stream().map(DeferredHolder::get).forEach(output::accept);
    }
}

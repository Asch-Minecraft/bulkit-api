package org.asch.bulkit.api.registry;

import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NewRegistryEvent;
import net.neoforged.neoforge.registries.RegistryBuilder;
import org.asch.bulkit.api.BulkItApi;
import org.asch.bulkit.api.resource.ResourceType;
import org.jetbrains.annotations.NotNull;

public class BulkItRegistries {
    public static class Keys {
        public static final @NotNull ResourceKey<Registry<Item>> DISKS_KEY =
                ResourceKey.create(Registries.ITEM.registryKey(), BulkItApi.rl("disks"));
        public static final @NotNull ResourceKey<Registry<DataComponentType<?>>> RESOURCES_KEY =
                ResourceKey.createRegistryKey(BulkItApi.rl("resources"));
        public static final @NotNull ResourceKey<Registry<ResourceType<?>>> RESOURCE_TYPES_KEY =
                ResourceKey.createRegistryKey(BulkItApi.rl("resource_types"));
    }

    public static final @NotNull DeferredRegister.Items DISKS = DeferredRegister.createItems(BulkItApi.ID + ".disks");
    public static final @NotNull Registry<DataComponentType<?>> RESOURCES = new RegistryBuilder<>(Keys.RESOURCES_KEY).sync(true).create();
    public static final @NotNull Registry<ResourceType<?>> RESOURCE_TYPES = new RegistryBuilder<>(Keys.RESOURCE_TYPES_KEY).sync(true).create();

    public static void register(IEventBus modBus) {
        DISKS.register(modBus);
    }

    public static void register(NewRegistryEvent event) {
        event.register(RESOURCES);
        event.register(RESOURCE_TYPES);
    }
}

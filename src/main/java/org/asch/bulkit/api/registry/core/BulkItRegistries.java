package org.asch.bulkit.api.registry.core;

import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.resources.ResourceKey;
import net.neoforged.neoforge.registries.NewRegistryEvent;
import net.neoforged.neoforge.registries.RegistryBuilder;
import org.asch.bulkit.api.BulkItApi;
import org.asch.bulkit.api.resource.ResourceType;
import org.jetbrains.annotations.NotNull;

public class BulkItRegistries {
    public static class Keys {
        public static final @NotNull ResourceKey<Registry<DataComponentType<?>>> RESOURCES =
                ResourceKey.createRegistryKey(BulkItApi.location("resources"));
        public static final @NotNull ResourceKey<Registry<ResourceType<?>>> RESOURCE_TYPES =
                ResourceKey.createRegistryKey(BulkItApi.location("resource_types"));
    }

    public static final @NotNull Registry<DataComponentType<?>> RESOURCES =
            new RegistryBuilder<>(Keys.RESOURCES).sync(true).create();
    public static final @NotNull Registry<ResourceType<?>> RESOURCE_TYPES =
            new RegistryBuilder<>(Keys.RESOURCE_TYPES).sync(true).create();

    public static void register(NewRegistryEvent event) {
        event.register(RESOURCES);
        event.register(RESOURCE_TYPES);
    }
}

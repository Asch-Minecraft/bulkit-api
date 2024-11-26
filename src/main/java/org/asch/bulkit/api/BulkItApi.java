package org.asch.bulkit.api;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.registries.RegistryBuilder;
import org.asch.bulkit.api.resource.ResourceType;

public class BulkItApi {
    public static String ID = "bulkit";

    public static ResourceKey<Registry<ResourceType<?>>> RESOURCE_TYPE_REGISTRY_NAME =
            ResourceKey.createRegistryKey(rl("resource_types"));
    public static Registry<ResourceType<?>> RESOURCE_TYPE_REGISTRY =
            new RegistryBuilder<>(RESOURCE_TYPE_REGISTRY_NAME).sync(true).create();

    public static ResourceLocation rl(String path) {
        return ResourceLocation.fromNamespaceAndPath(ID, path);
    }
}

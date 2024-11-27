package org.asch.bulkit.api;

import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.NewRegistryEvent;
import net.neoforged.neoforge.registries.RegistryBuilder;
import org.asch.bulkit.api.resource.ResourceType;

public class BulkItApi {
    public static String ID = "bulkit";

    public static ResourceKey<Registry<ResourceType<?>>> RESOURCE_TYPE_REGISTRY_NAME = ResourceKey.createRegistryKey(rl("resource_types"));
    public static Registry<ResourceType<?>> RESOURCE_TYPE_REGISTRY = new RegistryBuilder<>(RESOURCE_TYPE_REGISTRY_NAME).sync(true).create();

    public static ResourceKey<Registry<DataComponentType<?>>> RESOURCE_REGISTRY_NAME = ResourceKey.createRegistryKey(rl("resources"));

    public static ResourceKey<Registry<Item>> DISK_REGISTRY_NAME = ResourceKey.createRegistryKey(rl("items.disk"));
    public static Registry<Item> DISK_REGISTRY = new RegistryBuilder<>(DISK_REGISTRY_NAME).sync(true).create();

    public static ResourceLocation rl(String path) {
        return ResourceLocation.fromNamespaceAndPath(ID, path);
    }

    public static void registerBulkItRegistries(NewRegistryEvent event) {
        event.register(DISK_REGISTRY);
        event.register(RESOURCE_TYPE_REGISTRY);
    }
}

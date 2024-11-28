package org.asch.bulkit.api;

import com.mojang.logging.LogUtils;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.asch.bulkit.api.registry.BulkItRegistries;
import org.asch.bulkit.api.resource.ResourceType;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

import java.util.function.UnaryOperator;

public class BulkItApi {
    public static String ID = "bulkit";
    public static Logger LOGGER = LogUtils.getLogger();

    public static ResourceLocation rl(String path) {
        return ResourceLocation.fromNamespaceAndPath(ID, path);
    }

    public static class Mod {
        public final @NotNull String modId;
        private final @NotNull DeferredRegister.Items disks;
        private final @NotNull DeferredRegister.DataComponents resources;
        private final @NotNull DeferredRegister<ResourceType<?>> resourceTypes;

        public Mod(@NotNull String modId) {
            this.modId = modId;
            this.disks = DeferredRegister.createItems(modId + ".disks");
            this.resources = DeferredRegister.createDataComponents(BulkItRegistries.Keys.RESOURCES_KEY, modId);
            this.resourceTypes = DeferredRegister.create(BulkItRegistries.Keys.RESOURCE_TYPES_KEY, modId);
        }

        public void register(IEventBus modBus) {
            disks.register(modBus);
            resources.register(modBus);
            resourceTypes.register(modBus);
        }

        public <R> DeferredHolder<ResourceType<?>, ResourceType<R>> registerResourceType(@NotNull String name, @NotNull Registry<R> registry, @NotNull UnaryOperator<ResourceType.Builder<R>> func) {
            return resourceTypes.register(name, func.apply(new ResourceType.Builder<>(name, registry, disks, resources)));
        }
    }
}

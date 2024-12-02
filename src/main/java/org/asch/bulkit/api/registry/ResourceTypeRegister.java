package org.asch.bulkit.api.registry;

import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.asch.bulkit.api.registry.core.BulkItRegistries;
import org.asch.bulkit.api.resource.Resource;
import org.asch.bulkit.api.resource.ResourceType;
import org.jetbrains.annotations.NotNull;

import java.util.function.UnaryOperator;

public class ResourceTypeRegister extends DeferredRegister<ResourceType<?>> {
    private final @NotNull DeferredRegister.DataComponents resourceRegister;

    public ResourceTypeRegister(String modId) {
        super(BulkItRegistries.Keys.RESOURCE_TYPES, modId);
        this.resourceRegister = DeferredRegister.createDataComponents(BulkItRegistries.Keys.RESOURCES, modId);
    }

    public <R> @NotNull DeferredHolder<ResourceType<?>, ResourceType<R>> registerResourceType(
            @NotNull String name,
            @NotNull Registry<R> registry,
            @NotNull UnaryOperator<ResourceType.Builder<R>> func) {
        ResourceType.Builder<R> builder = new ResourceType.Builder<>(name, registry, registerResource(name, registry));
        return this.register(name, func.apply(builder));
    }

    public void register(@NotNull IEventBus modBus) {
        super.register(modBus);
        resourceRegister.register(modBus);
    }

    private <R> @NotNull DeferredHolder<DataComponentType<?>, DataComponentType<Resource<R>>> registerResource(
            @NotNull String name,
            @NotNull Registry<R> registry) {
        return resourceRegister.registerComponentType(name, builder ->
                builder.persistent(Resource.codec(registry))
                        .networkSynchronized(Resource.streamCodec(registry))
                        .cacheEncoding());
    }
}

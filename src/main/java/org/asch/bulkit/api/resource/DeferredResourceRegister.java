package org.asch.bulkit.api.resource;

import net.neoforged.neoforge.registries.DeferredRegister;
import org.asch.bulkit.api.BulkItApi;
import org.jetbrains.annotations.NotNull;

import java.util.function.UnaryOperator;

public class DeferredResourceRegister extends DeferredRegister<ResourceType<?>> {
    private final @NotNull DeferredRegister.Items items;
    private final @NotNull DeferredRegister.DataComponents dataComponents;

    public DeferredResourceRegister(@NotNull String modid, @NotNull DeferredRegister.Items items) {
        this(modid, items, DeferredRegister.createDataComponents(BulkItApi.RESOURCE_DATA_COMPONENTS_REGISTRY_NAME, modid));
    }

    public DeferredResourceRegister(@NotNull String modid, @NotNull DeferredRegister.Items items, @NotNull DeferredRegister.DataComponents dataComponents) {
        super(BulkItApi.RESOURCE_TYPE_REGISTRY_NAME, modid);
        this.items = items;
        this.dataComponents = dataComponents;
    }

    public <R> DeferredResourceType<R> registerResourceType(@NotNull String name, UnaryOperator<ResourceType.Builder<R>> func) {
        ResourceType.Builder<R> builder = new ResourceType.Builder<>(name, items, dataComponents);
        return (DeferredResourceType<R>) super.register(name, func.apply(builder));
    }
}

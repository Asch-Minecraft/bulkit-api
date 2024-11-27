package org.asch.bulkit.api.registry;

import net.minecraft.core.Registry;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.asch.bulkit.api.BulkItApi;
import org.asch.bulkit.api.resource.ResourceType;
import org.jetbrains.annotations.NotNull;

import java.util.function.UnaryOperator;

public class ResourceTypeRegister extends DeferredRegister<ResourceType<?>> {
    private final @NotNull DiskItemRegister diskItems;
    private final @NotNull DeferredRegister.DataComponents resourceDataComponents;

    public ResourceTypeRegister(@NotNull String modid) {
        super(BulkItApi.RESOURCE_TYPE_REGISTRY_NAME, modid);
        this.diskItems = new DiskItemRegister(modid);
        this.resourceDataComponents = DeferredRegister.createDataComponents(BulkItApi.RESOURCE_REGISTRY_NAME, modid);
    }

    public <R> DeferredHolder<ResourceType<?>, ResourceType<R>> registerResourceType(@NotNull String name, @NotNull Registry<R> registry, @NotNull UnaryOperator<ResourceType.Builder<R>> func) {
        return super.register(name, func.apply(new ResourceType.Builder<>(name, registry, diskItems, resourceDataComponents)));
    }

    @Override
    public void register(@NotNull IEventBus bus) {
        super.register(bus);

        this.diskItems.register(bus);
        this.resourceDataComponents.register(bus);
    }
}

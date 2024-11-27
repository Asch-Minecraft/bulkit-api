package org.asch.bulkit.api.resource;

import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.asch.bulkit.api.item.DiskItem;
import org.asch.bulkit.api.registry.DiskItemRegister;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class ResourceType<R> {
    public final @NotNull String name;
    public final @NotNull DeferredHolder<Item, DiskItem> diskHolder;
    public final @NotNull DeferredHolder<DataComponentType<?>, DataComponentType<Resource<R>>> resourceHolder;

    protected ResourceType(@NotNull String name, @NotNull DeferredHolder<Item, DiskItem> diskHolder, @NotNull DeferredHolder<DataComponentType<?>, DataComponentType<Resource<R>>> resourceHolder) {
        this.name = name;
        this.diskHolder = diskHolder;
        this.resourceHolder = resourceHolder;
    }

    public static class Builder<R> implements Supplier<ResourceType<R>> {
        private final @NotNull String name;
        private final @NotNull DeferredHolder<Item, DiskItem> diskHolder;
        private final @NotNull DeferredHolder<DataComponentType<?>, DataComponentType<Resource<R>>> resourceHolder;

        public Builder(@NotNull String name, @NotNull Registry<R> registry, @NotNull DiskItemRegister diskItems, @NotNull DeferredRegister.DataComponents resourceDataComponents) {
            this.name = name;
            this.diskHolder = diskItems.registerDisk(name, DiskItem::new);
            this.resourceHolder = resourceDataComponents.registerComponentType(name, builder -> builder.persistent(Resource.codec(registry)).networkSynchronized(Resource.streamCodec(registry)).cacheEncoding());
        }

        @Override
        public @NotNull ResourceType<R> get() {
            return new ResourceType<>(name, diskHolder, resourceHolder);
        }
    }
}

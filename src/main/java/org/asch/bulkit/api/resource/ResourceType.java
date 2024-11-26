package org.asch.bulkit.api.resource;

import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.asch.bulkit.api.item.DiskItem;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class ResourceType<R> {
    final @NotNull String name;
    final @NotNull DeferredItem<DiskItem> diskHolder;
    final @NotNull DeferredHolder<DataComponentType<?>, DataComponentType<Resource<R>>> resourceHolder;

    protected ResourceType(@NotNull String name, @NotNull DeferredItem<DiskItem> diskHolder, @NotNull DeferredHolder<DataComponentType<?>, DataComponentType<Resource<R>>> resourceHolder) {
        this.name = name;
        this.diskHolder = diskHolder;
        this.resourceHolder = resourceHolder;
    }

    public static class Builder<R> implements Supplier<ResourceType<R>> {
        private final @NotNull String name;
        private final @NotNull DeferredRegister.DataComponents dataComponents;

        private final @NotNull DeferredItem<DiskItem> diskHolder;
        private DeferredHolder<DataComponentType<?>, DataComponentType<Resource<R>>> resourceHolder;

        public Builder(@NotNull String name, @NotNull DeferredRegister.Items items, @NotNull DeferredRegister.DataComponents dataComponents) {
            this.name = name;
            this.diskHolder = items.registerItem("disk_" + name, DiskItem::new);
            this.dataComponents = dataComponents;
        }

        public Builder<R> registry(@NotNull Registry<R> registry) {
            resourceHolder = dataComponents.registerComponentType(name, builder -> builder.persistent(Resource.codec(registry)).networkSynchronized(Resource.streamCodec(registry)).cacheEncoding());
            return this;
        }

        @Override
        public @NotNull ResourceType<R> get() {
            return new ResourceType<>(name, diskHolder, resourceHolder);
        }
    }
}

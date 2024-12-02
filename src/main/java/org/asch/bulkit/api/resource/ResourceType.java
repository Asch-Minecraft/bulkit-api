package org.asch.bulkit.api.resource;

import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.core.component.DataComponentType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class ResourceType<R> {
    public final @NotNull String name;
    public final @NotNull DeferredHolder<DataComponentType<?>, DataComponentType<Resource<R>>> resourceHolder;
    public final @NotNull Resource<R> emptyResource;

    protected ResourceType(@NotNull String name, @NotNull DeferredHolder<DataComponentType<?>, DataComponentType<Resource<R>>> resourceHolder, @Nullable R emptyResource) {
        this.name = name;
        this.resourceHolder = resourceHolder;
        this.emptyResource = new Resource<>(emptyResource, DataComponentPatch.EMPTY);
    }

    public static class Builder<R> implements Supplier<ResourceType<R>> {
        private final @NotNull String name;
        private final @NotNull DeferredHolder<DataComponentType<?>, DataComponentType<Resource<R>>> resourceHolder;
        private @Nullable R emptyResource = null;

        public Builder(@NotNull String name,
                       @NotNull Registry<R> registry,
                       @NotNull DeferredHolder<DataComponentType<?>, DataComponentType<Resource<R>>> resourceHolder) {
            this.name = name;
            this.resourceHolder = resourceHolder;
        }

        public @NotNull Builder<R> empty(R emptyResource) {
            this.emptyResource = emptyResource;
            return this;
        }

        @Override
        public @NotNull ResourceType<R> get() {
            return new ResourceType<>(name, resourceHolder, emptyResource);
        }
    }
}

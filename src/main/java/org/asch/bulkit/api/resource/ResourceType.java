package org.asch.bulkit.api.resource;

import net.minecraft.core.component.DataComponentType;
import net.neoforged.neoforge.registries.DeferredHolder;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class ResourceType<R> {
    public final @NotNull String name;
    public final @NotNull Supplier<DataComponentType<Resource<R>>> dataComponentType;
    public final long baseStackMultiplier;

    private ResourceType(@NotNull String name,
                         @NotNull Supplier<DataComponentType<Resource<R>>> dataComponentTypeHolder,
                         long baseMultiplier) {
        this.name = name;
        this.dataComponentType = dataComponentTypeHolder;
        this.baseStackMultiplier = baseMultiplier;
    }

    public static <R> Builder<R> builder(@NotNull String name,
                                         @NotNull DeferredHolder<DataComponentType<?>, DataComponentType<Resource<R>>> dataComponentTypeHolder) {
        return new Builder<>(name, dataComponentTypeHolder);
    }

    public static class Builder<R> implements Supplier<ResourceType<R>> {
        private final @NotNull String name;
        private final @NotNull DeferredHolder<DataComponentType<?>, DataComponentType<Resource<R>>> dataComponentTypeHolder;
        private long baseStackMultiplier = 1;

        private Builder(@NotNull String name,
                        @NotNull DeferredHolder<DataComponentType<?>, DataComponentType<Resource<R>>> dataComponentTypeHolder) {
            this.name = name;
            this.dataComponentTypeHolder = dataComponentTypeHolder;
        }

        public @NotNull Builder<R> baseStackMultiplier(long baseStackMultiplier) {
            this.baseStackMultiplier = baseStackMultiplier;
            return this;
        }

        @Override
        public @NotNull ResourceType<R> get() {
            return new ResourceType<>(name, dataComponentTypeHolder, baseStackMultiplier);
        }
    }
}

package org.asch.bulkit.api.resource;

import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.registries.DeferredHolder;
import org.asch.bulkit.api.BulkItApi;

public class DeferredResourceType<R> extends DeferredHolder<ResourceType<?>, ResourceType<R>> {
    public static <R> DeferredResourceType<R> createResourceType(ResourceKey<ResourceType<?>> key) {
        return new DeferredResourceType<>(key);
    }

    public static <R> DeferredResourceType<R> createResourceType(ResourceLocation key) {
        return createResourceType(ResourceKey.create(BulkItApi.RESOURCE_TYPE_REGISTRY_NAME, key));
    }

    protected DeferredResourceType(ResourceKey<ResourceType<?>> key) {
        super(key);
    }
}

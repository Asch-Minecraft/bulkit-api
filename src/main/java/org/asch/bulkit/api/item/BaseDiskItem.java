package org.asch.bulkit.api.item;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import org.asch.bulkit.api.registry.BulkItRegistries;
import org.asch.bulkit.api.resource.ResourceType;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public abstract class BaseDiskItem extends Item {
    private final @NotNull ResourceKey<ResourceType<?>> resourceTypeKey;

    protected BaseDiskItem(@NotNull ResourceKey<ResourceType<?>> resourceTypeKey, @NotNull Properties properties) {
        super(properties);
        this.resourceTypeKey = resourceTypeKey;
    }

    public @NotNull ResourceType<?> getResourceType() {
        return Objects.requireNonNull(BulkItRegistries.RESOURCE_TYPES.getValue(this.resourceTypeKey));
    }
}

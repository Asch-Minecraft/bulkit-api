package org.asch.bulkit.api.item;

import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import org.asch.bulkit.api.registry.BulkItRegistries;
import org.asch.bulkit.api.resource.ResourceType;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.function.Function;

public class DiskItem extends Item {
    public static final int MAX_STACK_SIZE = 16;
    private final @NotNull ResourceKey<ResourceType<?>> resourceTypeKey;

    protected DiskItem(@NotNull ResourceKey<ResourceType<?>> resourceTypeKey, @NotNull Properties properties) {
        super(properties.stacksTo(MAX_STACK_SIZE));
        this.resourceTypeKey = resourceTypeKey;
    }

    public @NotNull ResourceType<?> getResourceType() {
        return Objects.requireNonNull(BulkItRegistries.RESOURCE_TYPES.getValue(this.resourceTypeKey));
    }

    public static Function<Properties, DiskItem> builder(Holder<ResourceType<?>> resourceType) {
        return (properties) -> new DiskItem(Objects.requireNonNull(resourceType.getKey()), properties);
    }
}

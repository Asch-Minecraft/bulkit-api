package org.asch.bulkit.api.item;

import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import org.asch.bulkit.api.registry.core.BulkItDataComponents;
import org.asch.bulkit.api.resource.ResourceType;

import java.util.function.Function;

public class DiskItem extends Item {
    public static final int MAX_STACK_SIZE = 16;

    protected DiskItem(ResourceKey<ResourceType<?>> resourceTypeKey, Properties properties) {
        super(properties
                .component(BulkItDataComponents.Disk.RESOURCE_TYPE, resourceTypeKey)
                .component(BulkItDataComponents.Disk.AMOUNT, BulkItDataComponents.Disk.Default.AMOUNT)
                .component(BulkItDataComponents.Disk.LOCKED, BulkItDataComponents.Disk.Default.LOCKED)
                .component(BulkItDataComponents.Disk.VOID_EXCESS, BulkItDataComponents.Disk.Default.VOID_EXCESS)
                .component(BulkItDataComponents.Disk.MODS, BulkItDataComponents.Disk.Default.EMPTY_MODS)
                .stacksTo(MAX_STACK_SIZE)
        );
    }

    public static Function<Properties, DiskItem> builder(Holder<ResourceType<?>> resourceType) {
        return properties -> new DiskItem(resourceType.getKey(), properties);
    }
}

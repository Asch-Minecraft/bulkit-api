package org.asch.bulkit.api.registry;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.asch.bulkit.api.BulkItApi;
import org.asch.bulkit.api.item.DiskItem;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;

public class DiskItemRegister extends DeferredRegister<Item> {
    public DiskItemRegister(String namespace) {
        super(BulkItApi.DISK_REGISTRY_NAME, namespace);
    }

    public <I extends DiskItem> @NotNull DeferredHolder<Item, I> registerDisk(@NotNull String name, @NotNull Function<Item.Properties, ? extends I> func, @NotNull Item.Properties props) {
        return super.register(name, (key) -> func.apply(props.setId(ResourceKey.create(BulkItApi.DISK_REGISTRY_NAME, key))));
    }

    public <I extends DiskItem> @NotNull DeferredHolder<Item, I> registerDisk(@NotNull String name, @NotNull Function<Item.Properties, ? extends I> func) {
        return this.registerDisk(name, func, new Item.Properties());
    }
}

package org.asch.bulkit.api.registry.core;

import com.mojang.serialization.Codec;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.component.ItemContainerContents;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.asch.bulkit.api.BulkItApi;
import org.asch.bulkit.api.resource.ResourceType;
import org.jetbrains.annotations.NotNull;

public class BulkItDataComponents {
    public static class Disk {
        private static final @NotNull String NAME = BulkItApi.ID + ".disk";
        private static final @NotNull DeferredRegister.DataComponents DATA_COMPONENTS =
                DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, NAME);

        public static final @NotNull DeferredHolder<DataComponentType<?>, DataComponentType<ResourceKey<ResourceType<?>>>> RESOURCE_TYPE =
                DATA_COMPONENTS.registerComponentType("resource_type", (builder ->
                        builder.persistent(ResourceKey.codec(BulkItRegistries.Keys.RESOURCE_TYPES))
                                .networkSynchronized(ResourceKey.streamCodec(BulkItRegistries.Keys.RESOURCE_TYPES))
                                .cacheEncoding()
                ));

        public static final @NotNull DeferredHolder<DataComponentType<?>, DataComponentType<Long>> AMOUNT =
                DATA_COMPONENTS.registerComponentType("amount", (builder ->
                        builder.persistent(Codec.LONG)
                                .networkSynchronized(ByteBufCodecs.LONG)
                                .cacheEncoding()
                ));

        public static final @NotNull DeferredHolder<DataComponentType<?>, DataComponentType<Boolean>> LOCKED =
                DATA_COMPONENTS.registerComponentType("locked", (builder ->
                        builder.persistent(Codec.BOOL)
                                .networkSynchronized(ByteBufCodecs.BOOL)
                                .cacheEncoding()
                ));
        public static final @NotNull DeferredHolder<DataComponentType<?>, DataComponentType<Boolean>> VOID_EXCESS =
                DATA_COMPONENTS.registerComponentType("void_excess", (builder ->
                        builder.persistent(Codec.BOOL)
                                .networkSynchronized(ByteBufCodecs.BOOL)
                                .cacheEncoding()
                ));

        public static final @NotNull DeferredHolder<DataComponentType<?>, DataComponentType<ItemContainerContents>> MODS =
                DATA_COMPONENTS.registerComponentType("mods", (builder ->
                        builder.persistent(ItemContainerContents.CODEC)
                                .networkSynchronized(ItemContainerContents.STREAM_CODEC)
                                .cacheEncoding()
                ));

        public static class Default {
            public static final long AMOUNT = 0L;
            public static final boolean LOCKED = false;
            public static final boolean VOID_EXCESS = false;
            public static final ItemContainerContents EMPTY_MODS = ItemContainerContents.EMPTY;
        }
    }

    public static void register(IEventBus modBus) {
        Disk.DATA_COMPONENTS.register(modBus);
    }
}

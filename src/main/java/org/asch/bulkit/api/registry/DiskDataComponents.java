package org.asch.bulkit.api.registry;

import com.mojang.serialization.Codec;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.asch.bulkit.api.BulkItApi;
import org.jetbrains.annotations.NotNull;

public class DiskDataComponents {
    public static final @NotNull String DISK_DATA_NAME;
    private static final @NotNull DeferredRegister.DataComponents DISK_DATA;
    public static final @NotNull DeferredHolder<DataComponentType<?>, DataComponentType<Long>> AMOUNT;
    public static final @NotNull DeferredHolder<DataComponentType<?>, DataComponentType<Boolean>> LOCKED;
    public static final @NotNull DeferredHolder<DataComponentType<?>, DataComponentType<Boolean>> VOID_EXCESS;

    static {
        DISK_DATA_NAME = BulkItApi.ID + ".disk";
        DISK_DATA = DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, DISK_DATA_NAME);
        AMOUNT = DISK_DATA.registerComponentType("amount", (builder -> builder.persistent(Codec.LONG).networkSynchronized(ByteBufCodecs.LONG).cacheEncoding()));
        LOCKED = DISK_DATA.registerComponentType("locked", (builder -> builder.persistent(Codec.BOOL).networkSynchronized(ByteBufCodecs.BOOL).cacheEncoding()));
        VOID_EXCESS = DISK_DATA.registerComponentType("void_excess", (builder -> builder.persistent(Codec.BOOL).networkSynchronized(ByteBufCodecs.BOOL).cacheEncoding()));
    }

    public static void register(IEventBus modBus) {
        DISK_DATA.register(modBus);
    }
}

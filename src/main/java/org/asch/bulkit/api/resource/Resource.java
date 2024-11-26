package org.asch.bulkit.api.resource;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import org.jetbrains.annotations.NotNull;

public record Resource<R>(Holder<R> holder, DataComponentPatch components) {
    public static <R> @NotNull Codec<Resource<R>> codec(@NotNull Registry<R> registry) {
        return RecordCodecBuilder.create(instance -> instance.group(registry.holderByNameCodec().fieldOf("h").forGetter(Resource<R>::holder), DataComponentPatch.CODEC.optionalFieldOf("c", DataComponentPatch.EMPTY).forGetter(Resource<R>::components)).apply(instance, Resource<R>::new));
    }

    public static <R> @NotNull StreamCodec<RegistryFriendlyByteBuf, Resource<R>> streamCodec(@NotNull Registry<R> registry) {
        return StreamCodec.composite(ByteBufCodecs.holderRegistry(registry.key()), Resource<R>::holder, DataComponentPatch.STREAM_CODEC, Resource::components, Resource<R>::new);
    }
}

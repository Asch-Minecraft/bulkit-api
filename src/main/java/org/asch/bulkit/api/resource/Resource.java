package org.asch.bulkit.api.resource;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import org.jetbrains.annotations.NotNull;

public record Resource<R>(R resource, DataComponentPatch components) {
    public static <R> @NotNull Codec<Resource<R>> codec(@NotNull Registry<R> registry) {
        return RecordCodecBuilder.create(instance ->
                instance.group(
                        registry.byNameCodec().fieldOf("r").forGetter(Resource<R>::resource),
                        DataComponentPatch.CODEC.optionalFieldOf("c", DataComponentPatch.EMPTY).forGetter(Resource<R>::components)
                ).apply(instance, Resource<R>::new)
        );
    }

    public static <R> @NotNull StreamCodec<RegistryFriendlyByteBuf, Resource<R>> streamCodec(@NotNull Registry<R> registry) {
        return StreamCodec.composite(
                ByteBufCodecs.registry(registry.key()), Resource<R>::resource,
                DataComponentPatch.STREAM_CODEC, Resource::components,
                Resource<R>::new
        );
    }
}

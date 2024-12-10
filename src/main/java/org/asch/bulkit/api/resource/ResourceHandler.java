package org.asch.bulkit.api.resource;

import net.minecraft.world.item.ItemStack;
import org.asch.bulkit.api.capability.IDiskHandler;
import org.asch.bulkit.api.item.DiskItem;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.function.Function;

public class ResourceHandler<R> {
    private final @NotNull ItemStack disk;
    private final @NotNull Function<@Nullable Resource<R>, Long> maxStackSizeSupplier;
    private final @NotNull IDiskHandler diskHandler;
    private final @NotNull ResourceType<R> resourceType;

    public ResourceHandler(@NotNull ItemStack disk, @NotNull Function<@Nullable Resource<R>, Long> maxStackSizeSupplier) {
        this.disk = disk;
        if (!(this.disk.getItem() instanceof DiskItem)) {
            throw new IllegalArgumentException("invalid itemstack item (must be DiskItem)");
        }

        this.maxStackSizeSupplier = maxStackSizeSupplier;
        this.diskHandler = Objects.requireNonNull(disk.getCapability(IDiskHandler.CAPABILITY));

        //noinspection unchecked
        this.resourceType = (ResourceType<R>) this.diskHandler.getResourceType();
    }

    public @Nullable Resource<R> getResource() {
        return this.disk.get(this.resourceType.dataComponentType);
    }

    public long insert(@NotNull Resource<R> resource, long amount, boolean simulate) {
        if (amount == 0) {
            return 0;
        }

        Resource<R> currentResource = getResource();
        if (isEmpty()) {
            if (!simulate) {
                this.disk.set(this.resourceType.dataComponentType, resource);
            }
            currentResource = resource;
        } else if (resource != currentResource) {
            return amount;
        }

        long maxStackSize = maxStackSizeSupplier.apply(currentResource);
        long capacity = diskHandler.getCapacity(maxStackSize);

        long currentAmount = diskHandler.getAmount();
        long remainingCapacity = capacity - currentAmount;

        long amountToInsert = amount;
        if ((amountToInsert > remainingCapacity) && !diskHandler.isVoidExcess()) {
            amountToInsert = remainingCapacity;
        }

        if (!simulate) {
            diskHandler.setAmount(currentAmount + amountToInsert);
        }

        return amount - amountToInsert;
    }

    public long extract(long amount, boolean simulate) {
        if (amount == 0) {
            return 0;
        }

        if (isEmpty()) {
            return 0;
        }

        long currentAmount = diskHandler.getAmount();
        long amountToExtract = Math.min(currentAmount, amount);
        if (!simulate) {
            diskHandler.setAmount(currentAmount - amountToExtract);
        }

        return amountToExtract;
    }

    public long getMaxStackSize() {
        return maxStackSizeSupplier.apply(getResource());
    }

    public boolean isValidResource(@NotNull Resource<R> resource) {
        return Objects.equals(resource, getResource());
    }

    public boolean isEmpty() {
        return !this.disk.has(this.resourceType.dataComponentType);
    }
}

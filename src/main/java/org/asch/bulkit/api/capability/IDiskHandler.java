package org.asch.bulkit.api.capability;

import net.neoforged.neoforge.capabilities.ItemCapability;
import org.asch.bulkit.api.BulkItApi;
import org.asch.bulkit.api.resource.ResourceType;

public interface IDiskHandler {
    ItemCapability<IDiskHandler, Void> CAPABILITY = ItemCapability.createVoid(BulkItApi.location("disk_handler"), IDiskHandler.class);

    /**
     * Retrieves the resource type of the disk
     *
     * @return Resource type of the disk
     */
    ResourceType<?> getResourceType();

    /**
     * Retrieves the amount of resource stored in the disk
     *
     * @return Amount of resource stored
     */
    long getAmount();

    /**
     * Sets the amount of resource stored in the disk
     *
     * @param amount Amount of resource to store
     */
    void setAmount(long amount);

    /**
     * Checks if the "voiding" of the excess resource is enabled
     *
     * @return true if "voiding" excess resource
     */
    boolean isVoidExcess();

    /**
     * Enables/disables the "voiding" of the excess resource
     *
     * @param voidExcess true to enable "voiding" excess resource
     */
    void setVoidExcess(boolean voidExcess);

    /**
     * Retrieves the maximum capacity of the storage
     *
     * @param maxStackSize Maximum stack size of the stored resources
     * @return Maximum capacity of the storage
     */
    long getCapacity(long maxStackSize);
}

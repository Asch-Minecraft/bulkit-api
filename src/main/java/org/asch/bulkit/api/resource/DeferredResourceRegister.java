package org.asch.bulkit.api.resource;

import net.neoforged.neoforge.registries.DeferredRegister;
import org.asch.bulkit.api.BulkItApi;

public class DeferredResourceRegister extends DeferredRegister<ResourceType<?>> {
    protected DeferredResourceRegister(String modid) {
        super(BulkItApi.RESOURCE_TYPE_REGISTRY_NAME, modid);
    }
}

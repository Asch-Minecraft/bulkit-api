package org.asch.bulkit.api.resource;

public class ResourceType<R> {
    private final ResourceTypeSupplier<R> supplier;

    public ResourceType(ResourceTypeSupplier<R> supplier) {
        this.supplier = supplier;
    }

    @FunctionalInterface
    public interface ResourceTypeSupplier<R> {
        Resource<R> create();
    }
}

package org.asch.bulkit.api.item;

import net.minecraft.world.item.Item;

public class DiskItem extends Item {
    public static final int MAX_STACK_SIZE = 16;

    public DiskItem(Properties properties) {
        super(properties.stacksTo(MAX_STACK_SIZE));
    }
}

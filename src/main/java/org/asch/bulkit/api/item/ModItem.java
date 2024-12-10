package org.asch.bulkit.api.item;

import net.minecraft.world.item.Item;

public class ModItem extends Item {
    public static final int MAX_STACK_SIZE = 16;

    protected ModItem(Properties properties) {
        super(properties.stacksTo(MAX_STACK_SIZE));
    }
}

package org.asch.bulkit.api;

import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;

public class BulkItApi {
    public static String ID = "bulkit";
    public static Logger LOGGER = LogUtils.getLogger();

    public static ResourceLocation location(String path) {
        return ResourceLocation.fromNamespaceAndPath(ID, path);
    }
}

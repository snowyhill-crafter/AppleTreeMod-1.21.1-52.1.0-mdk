package com.snowyhill.appletreemod.client.renderer;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.util.Pair;
import com.snowyhill.appletreemod.AppleTreeMod;
import com.snowyhill.appletreemod.entity.ModBoatEntity;
import com.snowyhill.appletreemod.entity.ModChestBoatEntity;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ChestBoatModel;
import net.minecraft.client.model.ListModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.BoatRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.vehicle.Boat;

import java.util.Map;
import java.util.stream.Stream;

public class ModBoatRenderer extends BoatRenderer {
    private final Map<ModBoatEntity.Type, Pair<ResourceLocation, ListModel<Boat>>> boatResources;

    public ModBoatRenderer(EntityRendererProvider.Context context, boolean isChestBoat) {
        super(context, isChestBoat);
        this.boatResources = Stream.of(ModBoatEntity.Type.values()).collect(ImmutableMap.toImmutableMap(
                type -> type,
                type -> Pair.of(
                        ResourceLocation.fromNamespaceAndPath(AppleTreeMod.MOD_ID, getTexturePath(type, isChestBoat)),
                        isChestBoat ? new ChestBoatModel(context.bakeLayer(ModelLayers.createChestBoatModelName(Boat.Type.OAK)))
                                : new BoatModel(context.bakeLayer(ModelLayers.createBoatModelName(Boat.Type.OAK)))
                )
        ));
    }

    private static String getTexturePath(ModBoatEntity.Type type, boolean isChestBoat) {
        return isChestBoat ? "textures/entity/chest_boat/" + type.getName() + ".png"
                : "textures/entity/boat/" + type.getName() + ".png";
    }

    // 1.21.1 では getModelWithLocation ではなくこちらを正しく使う必要があります
    @Override
    public Pair<ResourceLocation, ListModel<Boat>> getModelWithLocation(Boat boat) {
        if (boat instanceof ModChestBoatEntity modChestBoat) {
            return this.boatResources.get(modChestBoat.getModVariant());
        } else if (boat instanceof ModBoatEntity modBoat) {
            return this.boatResources.get(modBoat.getModVariant());
        }
        // フォールバック（バニラのオークを返すなど）
        return this.boatResources.get(ModBoatEntity.Type.APPLE);
    }
}
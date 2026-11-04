package com.roninpath.init;

import com.roninpath.RoninPath;
import com.roninpath.entity.OniEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES =
            DeferredRegister.create(Registries.ENTITY_TYPE, RoninPath.MODID);

    public static final DeferredHolder<EntityType<?>, EntityType<OniEntity>> ONI = ENTITIES.register(
            "oni",
            () -> EntityType.Builder.of(OniEntity::new, MobCategory.MONSTER)
                    .sized(0.6f, 1.95f)
                    .clientTrackingRange(10)
                    .build(RoninPath.MODID + ":oni"));

    private ModEntities() {}
}

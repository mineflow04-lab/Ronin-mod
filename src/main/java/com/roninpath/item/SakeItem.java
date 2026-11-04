package com.roninpath.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;

/** Sake: eaten like food (nutrition and effects come from the food component) but with the drinking animation. */
public class SakeItem extends Item {
    public SakeItem(Properties properties) {
        super(properties);
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.DRINK;
    }
}

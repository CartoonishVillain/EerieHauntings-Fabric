package com.cartoonishvillain.eeriehauntings.items;

import com.cartoonishvillain.eeriehauntings.Register;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Supplier;

public enum Materials implements Tier {

    UNEARTHLY(0, 256, 2.5f, 0, 30, () ->{return Ingredient.of(Register.UNEARTHLYGEM);});

    private final int harvestLevel;
    private final int maxUses;
    private final float efficiency;
    private  final float attackDamage;
    private  final  int enchantability;
    private  final Supplier<Ingredient> repairMaterial;
    Materials(int harvestLevel, int maxUses, float efficiency, float attackDamage, int enchantability, Supplier<Ingredient> repairMaterial){
        this.harvestLevel = harvestLevel;
        this.maxUses = maxUses;
        this.efficiency = efficiency;
        this.attackDamage = attackDamage;
        this.enchantability = enchantability;
        this.repairMaterial = repairMaterial;
    }

    @Override
    public int getUses() {
        return maxUses;
    }

    @Override
    public float getSpeed() {
        return efficiency;
    }

    @Override
    public float getAttackDamageBonus() {
        return attackDamage;
    }

    @Override
    public int getLevel() {
        return harvestLevel;
    }

    @Override
    public int getEnchantmentValue() {
        return enchantability;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return repairMaterial.get();
    }
}

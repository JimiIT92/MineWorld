package org.mineworld.core;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;
import org.mineworld.MineWorld;

import java.util.function.Supplier;

/**
 * {@link MineWorld MineWorld} {@link ArmorItem armors} and {@link HorseArmorItem horse armors}
 */
public final class MWArmors {


    //#region Armors

    public static final RegistryObject<Item> EMERALD_HELMET = registerArmorItem("emerald_helmet", MWArmorMaterials.EMERALD, ArmorItem.Type.HELMET);
    public static final RegistryObject<Item> EMERALD_CHESTPLATE = registerArmorItem("emerald_chestplate", MWArmorMaterials.EMERALD, ArmorItem.Type.CHESTPLATE);
    public static final RegistryObject<Item> EMERALD_LEGGINGS = registerArmorItem("emerald_leggings", MWArmorMaterials.EMERALD, ArmorItem.Type.LEGGINGS);
    public static final RegistryObject<Item> EMERALD_BOOTS = registerArmorItem("emerald_boots", MWArmorMaterials.EMERALD, ArmorItem.Type.BOOTS);
    public static final RegistryObject<Item> EMERALD_HORSE_ARMOR = registerHorseArmorItem("emerald", 13);

    //#endregion

    /**
     * Register an {@link ArmorItem armor item}
     *
     * @param name {@link String The item name}
     * @param armorMaterial {@link ArmorMaterial The armor material}
     * @param slot {@link ArmorItem.Type The armor item type}
     * @param featureFlags {@link FeatureFlag The feature flags that needs to be enabled for this item to be registered}
     * @return {@link RegistryObject<Item> The registered item}
     */
    private static RegistryObject<Item> registerArmorItem(final String name, final ArmorMaterial armorMaterial, final ArmorItem.Type slot, final FeatureFlag... featureFlags) {
        return MWItems.registerItem(name, () -> new ArmorItem(armorMaterial, slot, MWItems.basicProperties(featureFlags)));
    }

    /**
     * Register a {@link HorseArmorItem horse armor item}
     *
     * @param materialName {@link String The armor material name}
     * @param protection {@link Integer The armor protection amount}
     * @param featureFlags {@link FeatureFlag The feature flags that needs to be enabled for this item to be registered}
     * @return {@link RegistryObject<Item> The registered item}
     */
    private static RegistryObject<Item> registerHorseArmorItem(final String materialName, final int protection, final FeatureFlag... featureFlags) {
        final ResourceLocation textureLocation = new ResourceLocation(MineWorld.MODID, "textures/entity/horse/armor/horse_armor_" + materialName + ".png");
        return MWItems.registerItem(materialName + "_horse_armor", () -> new HorseArmorItem(protection, textureLocation, MWItems.basicProperties(featureFlags).stacksTo(1)));
    }

    /**
     * Register the {@link MineWorld MineWorld} {@link Item items}
     *
     * @param eventBus {@link IEventBus Event Bus}
     */
    public static void register(final IEventBus eventBus) { }

    /**
     * {@link MineWorld MineWorld} {@link ArmorMaterial armor materials}
     */
    public enum MWArmorMaterials implements ArmorMaterial {

        //#region Armor Materials

        EMERALD("emerald", 35, new int[]{3, 6, 8, 3}, 13, SoundEvents.ARMOR_EQUIP_DIAMOND, 2.5F, 0.0F, () -> Ingredient.of(Items.EMERALD)),
        RUBY("ruby", 35, new int[]{3, 6, 8, 3}, 13, SoundEvents.ARMOR_EQUIP_DIAMOND, 2.5F, 0.0F, () -> Ingredient.of(MWItems.RUBY.get())),
        SAPPHIRE("sapphire", 35, new int[]{3, 6, 8, 3}, 13, SoundEvents.ARMOR_EQUIP_DIAMOND, 2.5F, 0.0F, () -> Ingredient.of(MWItems.SAPPHIRE.get()));

        //#endregion

        /**
         * Armor item {@link Integer slots durability}
         */
        private static final int[] HEALTH_PER_SLOT = new int[]{13, 15, 16, 11};
        /**
         * {@link String The armor name}
         */
        private final String name;
        /**
         * {@link Integer The aromr item durability multiplier}
         */
        private final int durabilityMultiplier;
        /**
         * {@link Integer The armor item slot protection values}
         */
        private final int[] slotProtections;
        /**
         * {@link Integer The armor enchantment value}
         */
        private final int enchantmentValue;
        /**
         * {@link SoundEvent The armor equip sound effect}
         */
        private final SoundEvent sound;
        /**
         * {@link Float The armor toughness}
         */
        private final float toughness;
        /**
         * {@link Float The armor knockback resistance}
         */
        private final float knockbackResistance;
        /**
         * {@link LazyLoadedValue<Ingredient> The armor item repair ingredient}
         */
        private final LazyLoadedValue<Ingredient> repairIngredient;

        /**
         * Constructor. Set the {@link ArmorMaterial armor material} properties
         *
         * @param name {@link String The armor name}
         * @param durabilityMultiplier {@link Integer The aromr item durability multiplier}
         * @param slotProtections {@link Integer The armor item slot protection values}
         * @param enchantmentValue {@link Integer The armor enchantment value}
         * @param sound {@link SoundEvent The armor equip sound effect}
         * @param toughness {@link Float The armor toughness}
         * @param knockbackResistance {@link Float The armor knockback resistance}
         * @param repairIngredient {@link Supplier<Ingredient> The armor item repair ingredient}
         */
        MWArmorMaterials(final String name, final int durabilityMultiplier, final int[] slotProtections, final int enchantmentValue, final SoundEvent sound, final float toughness, final float knockbackResistance, final Supplier<Ingredient> repairIngredient) {
            this.name = name;
            this.durabilityMultiplier = durabilityMultiplier;
            this.slotProtections = slotProtections;
            this.enchantmentValue = enchantmentValue;
            this.sound = sound;
            this.toughness = toughness;
            this.knockbackResistance = knockbackResistance;
            this.repairIngredient = new LazyLoadedValue<>(repairIngredient);
        }

        /**
         * Get the {@link Integer armor item type durability value}
         *
         * @param type {@link ArmorItem.Type The armor item type}
         * @return {@link Integer The armor item type durability value}
         */
        public int getDurabilityForType(final ArmorItem.Type type) {
            return HEALTH_PER_SLOT[type.getSlot().getIndex()] * this.durabilityMultiplier;
        }

        /**
         * Get the {@link Integer armor item type protection value}
         *
         * @param type {@link ArmorItem.Type The armor item type}
         * @return {@link Integer The armor item type protection value}
         */
        public int getDefenseForType(final ArmorItem.Type type) {
            return this.slotProtections[type.getSlot().getIndex()];
        }

        /**
         * Get the {@link Integer armor item enchantment value}
         *
         * @return {@link Integer The armor item enchantment value}
         */
        public int getEnchantmentValue() {
            return this.enchantmentValue;
        }

        /**
         * Get the {@link SoundEvent armor equip sound effect}
         *
         * @return {@link SoundEvent The armor equip sound effect}
         */
        public @NotNull SoundEvent getEquipSound() {
            return this.sound;
        }

        /**
         * Get the {@link Ingredient armor item repair ingredient}
         *
         * @return {@link Ingredient The armor item repair ingredient}
         */
        public @NotNull Ingredient getRepairIngredient() {
            return this.repairIngredient.get();
        }

        /**
         * Get the {@link String armor name}
         *
         * @return {@link String The armor name}
         */
        public @NotNull String getName() {
            return MineWorld.MODID + ":" + this.name;
        }

        /**
         * Get the {@link Float armor toughness}
         *
         * @return {@link Float The armor toughness}
         */
        public float getToughness() {
            return this.toughness;
        }

        /**
         * Get the {@link Float armor knockback resistance}
         *
         * @return {@link Float The armor knockback resistance}
         */
        public float getKnockbackResistance() {
            return this.knockbackResistance;
        }
    }
}

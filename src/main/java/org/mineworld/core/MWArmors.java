package org.mineworld.core;

import net.minecraft.Util;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;
import org.mineworld.MineWorld;

import java.util.EnumMap;
import java.util.function.Supplier;

/**
 * {@link MineWorld MineWorld} {@link ArmorItem armors} and {@link HorseArmorItem horse armors}
 */
public final class MWArmors {


    //#region Armors

    public static final RegistryObject<Item> CHAINMAIL_HORSE_ARMOR = registerHorseArmorItem("chainmail", 4);
    public static final RegistryObject<Item> EMERALD_HELMET = registerArmorItem("emerald_helmet", MWArmorMaterials.EMERALD, ArmorItem.Type.HELMET);
    public static final RegistryObject<Item> EMERALD_CHESTPLATE = registerArmorItem("emerald_chestplate", MWArmorMaterials.EMERALD, ArmorItem.Type.CHESTPLATE);
    public static final RegistryObject<Item> EMERALD_LEGGINGS = registerArmorItem("emerald_leggings", MWArmorMaterials.EMERALD, ArmorItem.Type.LEGGINGS);
    public static final RegistryObject<Item> EMERALD_BOOTS = registerArmorItem("emerald_boots", MWArmorMaterials.EMERALD, ArmorItem.Type.BOOTS);
    public static final RegistryObject<Item> EMERALD_HORSE_ARMOR = registerHorseArmorItem("emerald", 13);
    public static final RegistryObject<Item> RUBY_HELMET = registerArmorItem("ruby_helmet", MWArmorMaterials.RUBY, ArmorItem.Type.HELMET);
    public static final RegistryObject<Item> RUBY_CHESTPLATE = registerArmorItem("ruby_chestplate", MWArmorMaterials.RUBY, ArmorItem.Type.CHESTPLATE);
    public static final RegistryObject<Item> RUBY_LEGGINGS = registerArmorItem("ruby_leggings", MWArmorMaterials.RUBY, ArmorItem.Type.LEGGINGS);
    public static final RegistryObject<Item> RUBY_BOOTS = registerArmorItem("ruby_boots", MWArmorMaterials.RUBY, ArmorItem.Type.BOOTS);
    public static final RegistryObject<Item> RUBY_HORSE_ARMOR = registerHorseArmorItem("ruby", 13);
    public static final RegistryObject<Item> SAPPHIRE_HELMET = registerArmorItem("sapphire_helmet", MWArmorMaterials.SAPPHIRE, ArmorItem.Type.HELMET);
    public static final RegistryObject<Item> SAPPHIRE_CHESTPLATE = registerArmorItem("sapphire_chestplate", MWArmorMaterials.SAPPHIRE, ArmorItem.Type.CHESTPLATE);
    public static final RegistryObject<Item> SAPPHIRE_LEGGINGS = registerArmorItem("sapphire_leggings", MWArmorMaterials.SAPPHIRE, ArmorItem.Type.LEGGINGS);
    public static final RegistryObject<Item> SAPPHIRE_BOOTS = registerArmorItem("sapphire_boots", MWArmorMaterials.SAPPHIRE, ArmorItem.Type.BOOTS);
    public static final RegistryObject<Item> SAPPHIRE_HORSE_ARMOR = registerHorseArmorItem("sapphire", 13);
    public static final RegistryObject<Item> NETHERITE_HORSE_ARMOR = registerHorseArmorItem("netherite", 15);

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
    public enum MWArmorMaterials implements StringRepresentable, ArmorMaterial {

        //#region Armor materials

        EMERALD("emerald", 35, Util.make(new EnumMap<>(ArmorItem.Type.class), (enumMap) -> {
            enumMap.put(ArmorItem.Type.BOOTS, 3);
            enumMap.put(ArmorItem.Type.LEGGINGS, 6);
            enumMap.put(ArmorItem.Type.CHESTPLATE, 8);
            enumMap.put(ArmorItem.Type.HELMET, 3);
        }), 13, SoundEvents.ARMOR_EQUIP_DIAMOND, 2.5F, 0.05F, () -> Ingredient.of(Items.EMERALD)),
        RUBY("ruby", 35, Util.make(new EnumMap<>(ArmorItem.Type.class), (enumMap) -> {
            enumMap.put(ArmorItem.Type.BOOTS, 3);
            enumMap.put(ArmorItem.Type.LEGGINGS, 6);
            enumMap.put(ArmorItem.Type.CHESTPLATE, 8);
            enumMap.put(ArmorItem.Type.HELMET, 3);
        }), 13, SoundEvents.ARMOR_EQUIP_DIAMOND, 2.5F, 0.05F, () -> Ingredient.of(MWItems.RUBY.get())),
        SAPPHIRE("sapphire", 35, Util.make(new EnumMap<>(ArmorItem.Type.class), (enumMap) -> {
            enumMap.put(ArmorItem.Type.BOOTS, 3);
            enumMap.put(ArmorItem.Type.LEGGINGS, 6);
            enumMap.put(ArmorItem.Type.CHESTPLATE, 8);
            enumMap.put(ArmorItem.Type.HELMET, 3);
        }), 13, SoundEvents.ARMOR_EQUIP_DIAMOND, 2.5F, 0.05F, () -> Ingredient.of(MWItems.SAPPHIRE.get()));

        //#endregion

        /**
         * {@link StringRepresentable.EnumCodec The enum codec for this enum}
         */
        public static final StringRepresentable.EnumCodec<MWArmorMaterials> CODEC = StringRepresentable.fromEnum(MWArmorMaterials::values);
        /**
         * {@link EnumMap The base protection values for each armor item type}
         */
        private static final EnumMap<ArmorItem.Type, Integer> HEALTH_FUNCTION_FOR_TYPE = Util.make(new EnumMap<>(ArmorItem.Type.class), (enumMap) -> {
            enumMap.put(ArmorItem.Type.BOOTS, 13);
            enumMap.put(ArmorItem.Type.LEGGINGS, 15);
            enumMap.put(ArmorItem.Type.CHESTPLATE, 16);
            enumMap.put(ArmorItem.Type.HELMET, 11);
        });

        /**
         * {@link String The armor material name}
         */
        private final String name;
        /**
         * {@link Integer The armor material durability multiplier}
         */
        private final int durabilityMultiplier;
        /**
         * {@link EnumMap The armor item protection values}
         */
        private final EnumMap<ArmorItem.Type, Integer> protectionFunctionForType;
        /**
         * {@link Integer The armor enchantment value}
         */
        private final int enchantmentValue;
        /**
         * {@link SoundEvent The armor equip sound}
         */
        private final SoundEvent sound;
        /**
         * {@link Float The armor thoughness}
         */
        private final float toughness;
        /**
         * {@link Float The armor knockback resistance}
         */
        private final float knockbackResistance;
        /**
         * {@link LazyLoadedValue<Ingredient> The armor repair ingredient}
         */
        private final LazyLoadedValue<Ingredient> repairIngredient;

        /**
         * Constructor. Set the armor material properties
         *
         * @param name {@link String The armor material name}
         * @param durabilityMultiplier {@link Integer The armor material durability multiplier}
         * @param protectionFunctionForType {@link EnumMap The armor item protection values}
         * @param enchantmentValue {@link Integer The armor enchantment value}
         * @param sound {@link SoundEvent The armor equip sound}
         * @param thoughness {@link Float The armor thoughness}
         * @param knockbackResistance {@link Float The armor knockback resistance}
         * @param ingredient {@link LazyLoadedValue<Ingredient> The armor repair ingredient}
         */
        MWArmorMaterials(final String name, final int durabilityMultiplier, final EnumMap<ArmorItem.Type, Integer> protectionFunctionForType, final int enchantmentValue, final SoundEvent sound, final float thoughness, final float knockbackResistance, final Supplier<Ingredient> ingredient) {
            this.name = new ResourceLocation(MineWorld.MODID, name).toString();
            this.durabilityMultiplier = durabilityMultiplier;
            this.protectionFunctionForType = protectionFunctionForType;
            this.enchantmentValue = enchantmentValue;
            this.sound = sound;
            this.toughness = thoughness;
            this.knockbackResistance = knockbackResistance;
            this.repairIngredient = new LazyLoadedValue<>(ingredient);
        }

        /**
         * Get the {@link Integer armor item durability} based on its {@link ArmorItem.Type type}
         *
         * @param type {@link ArmorItem.Type The armor item type}
         * @return {@link Integer The armor item durability}
         */
        public int getDurabilityForType(final @NotNull ArmorItem.Type type) {
            return HEALTH_FUNCTION_FOR_TYPE.get(type) * this.durabilityMultiplier;
        }

        /**
         * Get the {@link Integer armor item protection value} based on its {@link ArmorItem.Type type}
         *
         * @param type {@link ArmorItem.Type The armor item type}
         * @return {@link Integer The armor item protection}
         */
        public int getDefenseForType(final @NotNull ArmorItem.Type type) {
            return this.protectionFunctionForType.get(type);
        }

        /**
         * Get the {@link Integer armor enchantment value}
         *
         * @return {@link Integer The armor enchantment value}
         */
        public int getEnchantmentValue() {
            return this.enchantmentValue;
        }

        /**
         * Get the {@link SoundEvent armor equip sound}
         *
         * @return {@link SoundEvent The armor equip sound}
         */
        public @NotNull SoundEvent getEquipSound() {
            return this.sound;
        }

        /**
         * Get the {@link Ingredient armor repair item}
         *
         * @return {@link Integer The armor repair item}
         */
        public @NotNull Ingredient getRepairIngredient() {
            return this.repairIngredient.get();
        }

        /**
         * Get the {@link String armor material name}
         *
         * @return {@link String The armor material name}
         */
        public @NotNull String getName() {
            return this.name;
        }

        /**
         * Get the {@link Float armor thoughness}
         *
         * @return {@link Float The armor thoughness}
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

        /**
         * Get the {@link String armor material serialized name}
         *
         * @return {@link String The armor material serialized name}
         */
        public @NotNull String getSerializedName() {
            return this.name;
        }
    }
}

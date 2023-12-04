package org.mineworld.core;

import net.minecraft.Util;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.NotNull;
import org.mineworld.MineWorld;
import org.mineworld.helper.KeyHelper;

import java.util.EnumMap;
import java.util.function.Supplier;

/**
 * {@link MineWorld MineWorld} {@link ArmorMaterial armor materials}
 */
public enum MWArmorMaterials implements StringRepresentable, ArmorMaterial {

    COPPER("copper", 24, Util.make(new EnumMap<>(ArmorItem.Type.class), (enumMap) -> {
        enumMap.put(ArmorItem.Type.BOOTS, 2);
        enumMap.put(ArmorItem.Type.LEGGINGS, 5);
        enumMap.put(ArmorItem.Type.CHESTPLATE, 7);
        enumMap.put(ArmorItem.Type.HELMET, 3);
    }), 9, SoundEvents.COPPER_HIT,1.0F, 0.0F, () -> Ingredient.of(Items.COPPER_INGOT)),
    SILVER("silver", 24, Util.make(new EnumMap<>(ArmorItem.Type.class), (enumMap) -> {
        enumMap.put(ArmorItem.Type.BOOTS, 2);
        enumMap.put(ArmorItem.Type.LEGGINGS, 5);
        enumMap.put(ArmorItem.Type.CHESTPLATE, 7);
        enumMap.put(ArmorItem.Type.HELMET, 3);
    }), 9, SoundEvents.ARMOR_EQUIP_GOLD,1.0F, 0.0F, () -> Ingredient.of(MWItems.SILVER_INGOT.get())),
    BRONZE("bronze", 11, Util.make(new EnumMap<>(ArmorItem.Type.class), (enumMap) -> {
        enumMap.put(ArmorItem.Type.BOOTS, 1);
        enumMap.put(ArmorItem.Type.LEGGINGS, 4);
        enumMap.put(ArmorItem.Type.CHESTPLATE, 5);
        enumMap.put(ArmorItem.Type.HELMET, 2);
    }), 11, SoundEvents.ARMOR_EQUIP_IRON,0.0F, 0.0F, () -> Ingredient.of(MWItems.BRONZE_INGOT.get())),
    ALUMINUM("aluminum", 6, Util.make(new EnumMap<>(ArmorItem.Type.class), (enumMap) -> {
        enumMap.put(ArmorItem.Type.BOOTS, 1);
        enumMap.put(ArmorItem.Type.LEGGINGS, 3);
        enumMap.put(ArmorItem.Type.CHESTPLATE, 5);
        enumMap.put(ArmorItem.Type.HELMET, 2);
    }), 11, SoundEvents.ARMOR_EQUIP_GENERIC,0.0F, 0.0F, () -> Ingredient.of(MWItems.ALUMINUM_INGOT.get())),
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
    }), 13, SoundEvents.ARMOR_EQUIP_DIAMOND, 2.5F, 0.05F, () -> Ingredient.of(MWItems.SAPPHIRE.get())),
    CREEPER("creeper"),
    ZOMBIE("zombie"),
    HUSK("husk"),
    DROWNED("drowned"),
    SKELETON("skeleton"),
    WITHER_SKELETON("wither_skeleton"),
    STRAY("stray"),
    PIGLIN("piglin"),
    WITCH("witch"),
    STRAW("straw"),
    INVISIBILITY_CLOAK("invisibility_cloak");

    /**
     * {@link StringRepresentable.EnumCodec The enum codec for this enum}
     */
    public static final StringRepresentable.EnumCodec<MWArmorMaterials> CODEC = StringRepresentable.fromEnum(MWArmorMaterials::values);
    /**
     * {@link EnumMap The base protection values for each armor id type}
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
     * {@link EnumMap The armor id protection values}
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
     * {@link LazyLoadedValue <Ingredient> The armor repair ingredient}
     */
    private final LazyLoadedValue<Ingredient> repairIngredient;
    /**
     * {@link Boolean If the armor material is a cosmetic one}
     */
    private final boolean isCosmetic;

    /**
     * Cosmetic constructor. Set the armor material properties
     * to be just a cosmetic item and don't provide any protection
     *
     * @param name {@link String The armor material name}
     */
    MWArmorMaterials(final String name) {
        this(name, SoundEvents.ARMOR_EQUIP_LEATHER);
    }

    /**
     * Cosmetic constructor. Set the armor material properties
     * to be just a cosmetic item and don't provide any protection
     *
     * @param name {@link String The armor material name}
     * @param equipSound {@link SoundEvent The equip sound}
     */
    MWArmorMaterials(final String name, final SoundEvent equipSound) {
        this(name, 0, Util.make(new EnumMap<>(ArmorItem.Type.class), (enumMap) -> {
            enumMap.put(ArmorItem.Type.BOOTS, 0);
            enumMap.put(ArmorItem.Type.LEGGINGS, 0);
            enumMap.put(ArmorItem.Type.CHESTPLATE, 0);
            enumMap.put(ArmorItem.Type.HELMET, 0);
        }), 0, equipSound, 0.0F, 0.0F, () -> Ingredient.EMPTY);
    }

    /**
     * Constructor. Set the armor material properties
     *
     * @param name {@link String The armor material name}
     * @param durabilityMultiplier {@link Integer The armor material durability multiplier}
     * @param protectionFunctionForType {@link EnumMap The armor id protection values}
     * @param enchantmentValue {@link Integer The armor enchantment value}
     * @param sound {@link SoundEvent The armor equip sound}
     * @param thoughness {@link Float The armor thoughness}
     * @param knockbackResistance {@link Float The armor knockback resistance}
     * @param ingredient {@link LazyLoadedValue<Ingredient> The armor repair ingredient}
     */
    MWArmorMaterials(final String name, final int durabilityMultiplier, final EnumMap<ArmorItem.Type, Integer> protectionFunctionForType, final int enchantmentValue, final SoundEvent sound, final float thoughness, final float knockbackResistance, final Supplier<Ingredient> ingredient) {
        this.name = KeyHelper.location(name).toString();
        this.durabilityMultiplier = durabilityMultiplier;
        this.protectionFunctionForType = protectionFunctionForType;
        this.enchantmentValue = enchantmentValue;
        this.sound = sound;
        this.toughness = thoughness;
        this.knockbackResistance = knockbackResistance;
        this.repairIngredient = new LazyLoadedValue<>(ingredient);
        this.isCosmetic = durabilityMultiplier <= 0;
    }

    /**
     * Get the {@link Integer armor id durability} based on its {@link ArmorItem.Type type}
     *
     * @param type {@link ArmorItem.Type The armor id type}
     * @return {@link Integer The armor id durability}
     */
    public int getDurabilityForType(final @NotNull ArmorItem.Type type) {
        return HEALTH_FUNCTION_FOR_TYPE.get(type) * this.durabilityMultiplier;
    }

    /**
     * Get the {@link Integer armor id protection value} based on its {@link ArmorItem.Type type}
     *
     * @param type {@link ArmorItem.Type The armor id type}
     * @return {@link Integer The armor id protection}
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
     * Get the {@link Ingredient armor repair id}
     *
     * @return {@link Integer The armor repair id}
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

    /**
     * Check if an {@link ArmorMaterial armor material} is a cosmetic material
     *
     * @param material {@link ArmorMaterial The armor material}
     * @return {@link Boolean True if is a cosmetic material}
     */
    public static boolean isCosmetic(final ArmorMaterial material) {
        return material instanceof MWArmorMaterials armorMaterial && armorMaterial.isCosmetic;
    }

}
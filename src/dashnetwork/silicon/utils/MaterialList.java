package dashnetwork.silicon.utils;

import org.bukkit.Material;

import java.util.Map;

public class MaterialList {

    public static final org.bukkit.Material END_ROD = addMaterial("END_ROD", 198);
    public static final org.bukkit.Material CHORUS_PLANT = addMaterial("CHORUS_PLANT", 199);
    public static final org.bukkit.Material CHORUS_FLOWER = addMaterial("CHORUS_FLOWER", 200);
    public static final org.bukkit.Material PURPUR_BLOCK = addMaterial("PURPUR_BLOCK", 201);
    public static final org.bukkit.Material PURPUR_PILLAR = addMaterial("PURPUR_PILLAR", 202);
    public static final org.bukkit.Material PURPUR_STAIRS = addMaterial("PURPUR_STAIRS", 203);
    public static final org.bukkit.Material PURPUR_DOUBLE_SLAB = addMaterial("PURPUR_DOUBLE_SLAB", 204);
    public static final org.bukkit.Material PURPUR_SLAB = addMaterial("PURPUR_SLAB", 205);
    public static final org.bukkit.Material END_BRICKS = addMaterial("END_BRICKS", 206);
    public static final org.bukkit.Material BEETROOTS = addMaterial("BEETRROOTS", 207);
    public static final org.bukkit.Material GRASS_PATH = addMaterial("GRASS_PATH", 208);
    public static final org.bukkit.Material END_GATEWAY = addMaterial("END_GATEWAY", 209);
    public static final org.bukkit.Material REPEATING_COMMAND_BLOCK = addMaterial("REPEATING_COMMAND_BLOCK", 210);
    public static final org.bukkit.Material CHAIN_COMMAND_BLOCK = addMaterial("CHAIN_COMMAND_BLOCK", 211);
    public static final org.bukkit.Material FROSTED_ICE = addMaterial("FROSTED_ICE", 212);
    public static final org.bukkit.Material MAGMA_BLOCK = addMaterial("MAGMA_BLOCK", 213);
    public static final org.bukkit.Material NETHER_WART_BLOCK = addMaterial("NETHER_WART_BLOCK", 214);
    public static final org.bukkit.Material RED_NETHER_BRICK = addMaterial("red_nether_brick", 215);
    public static final org.bukkit.Material BONE_BLOCK = addMaterial("bone_block", 216);
    public static final org.bukkit.Material STRUCTURE_VOID = addMaterial("structure_void", 217);

    public static final org.bukkit.Material BEETROOT = addMaterial("beetroot", 434);
    public static final org.bukkit.Material BEETROOT_SEEDS = addMaterial("beetroot_seeds", 435);
    public static final org.bukkit.Material ELYTRA = addMaterial("elytra", 443);

    public static void startup() {}

    private static Material addMaterial(String name, int id) {
        Material material = DynamicEnumType.addEnum(Material.class, name, new Class[]{Integer.TYPE}, new Object[]{id});
        ReflectionUtils.<Map<String, Material>>getFieldValue(Material.class, "BY_NAME", null).put(name, material);
        Material[] byId = ReflectionUtils.getFieldValue(Material.class, "byId", null);
        byId[id] = material;
        ReflectionUtils.setFieldValue(Material.class, "byId", null, byId);

        return material;
    }

}

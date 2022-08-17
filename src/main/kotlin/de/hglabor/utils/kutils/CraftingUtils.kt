package de.hglabor.utils.kutils

import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.Recipe
import org.bukkit.inventory.ShapedRecipe

/**
 * Utils for getting random crafting items and their materials.
 * Example usage:
 * ```
 * val craftingUtils = CraftingUtils()
 * // ...
 * val item = craftingUtils.randomItem() // Get a random item
 * val craftingIngredients = item.ingredients // Get the ingredients
 * players.forEach { craftingIngredients.addToInv(it) } // Add the ingredients to the inventories
 * ```
 * @param customItems when this is empty, every material not in [CraftingUtils.excludedItems] is possible.
 */
class CraftingUtils(private val customItems: List<Material> = listOf()) {
    val materials = run {
        if (customItems.isNotEmpty()) return@run customItems
        val materials = Material.values().filter { !it.isExcluded() }.filter { it.hasShapedRecipe }.toMutableList()
        return@run materials.apply { addAll(specialItems) }
    }

    /** @return a new random item to craft */
    fun randomItem() = materials.random()

    companion object {
        val specialItems = arrayOf(Material.WARPED_FUNGUS_ON_A_STICK, Material.RED_BED)
        val woodTypes = arrayOf("oak", "jungle", "spruce", "birch", "acacia", "dark_oak", "crimson", "warped", "mangrove")
        val excludedItems: Array<String> = arrayOf(
            *woodTypes.filter { it != "oak" }.toTypedArray(), "carpet", "pane", "terracotta", "glass",
            "banner", "bed", "wall", "slab", "stairs", "blackstone", "quartz", "sandstone",
            "polished", "chiseled", "pillar", "map"
        )
    }
}

private fun Material.isExcluded() = CraftingUtils.excludedItems.any { name.contains(it, ignoreCase = true) }
private fun Material.isLogItem() = name.endsWith("_planks", ignoreCase = true) || this == Material.STICK

val ItemStack.recipes: MutableList<Recipe> get() = Bukkit.getRecipesFor(this)
val Material.recipes get() = stack().recipes
val Material.hasShapedRecipe get() = recipes.any { it is ShapedRecipe }

private val ItemStack.originMaterials get() = type.originMaterials

/** Mostly logs for sticks and planks and some special cases. Null otherwise. */
private val Material.originMaterials: List<ItemStack>? get() {
    if (isLogItem()) return listOf(Material.OAK_LOG.stack())

    val itemStacks = ArrayList<ItemStack>()
    with(itemStacks) {
        when (this@originMaterials) {
            Material.FISHING_ROD -> addAll(Material.OAK_LOG, Material.STRING.stack(2))
            Material.CHEST -> add(Material.OAK_LOG.stack(2))
            Material.TNT -> addAll(Material.SAND.stack(4), Material.GUNPOWDER.stack(5))
            else -> {}
        }
    }
    return itemStacks.ifEmpty { null }
}

/** the first shaped recipe if the material has any and null otherwise */
val Material.shapedRecipe: ShapedRecipe? get() = recipes.filterIsInstance<ShapedRecipe>().firstOrNull()
val ItemStack.ingredients: List<ItemStack> get() = type.ingredients
val Material.ingredients: List<ItemStack> get() {
    val itemStacksForCrafting: MutableList<ItemStack> = ArrayList()
    shapedRecipe?.let {
        for (value: ItemStack? in it.ingredientMap.values) itemStacksForCrafting.addAll((value ?: continue).originMaterials ?: listOf(value))
    }
    return itemStacksForCrafting
}
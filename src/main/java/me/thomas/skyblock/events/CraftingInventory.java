package me.thomas.skyblock.events;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.inventory.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class CraftingInventory implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (!event.getView().getTitle().contains("Crafting Menu")) return;
        if (event.getCurrentItem() == null) return;
        if (event.getCurrentItem().getType() == Material.GRAY_STAINED_GLASS_PANE || event.getCurrentItem().getType() == Material.BARRIER) {
            event.setCancelled(true);
            return;
        }
        Inventory inv = event.getInventory();
        List<Integer> slots = Arrays.asList(10, 11, 12, 19, 20, 21, 28, 29, 30);
        List<ItemStack> pattern = new ArrayList<>();
        for (int i : slots) {
            pattern.add(inv.getItem(i));
        }
        Iterator<Recipe> recipeIterator = Bukkit.recipeIterator();
        while (recipeIterator.hasNext()) {
            Recipe recipe = recipeIterator.next();
            if (recipe instanceof ShapelessRecipe) {
                if (((ShapelessRecipe) recipe).getIngredientList().containsAll(pattern)) {
                    inv.setItem(24, recipe.getResult());
                }
            } else if (recipe instanceof ShapedRecipe) {
                for (ItemStack item : pattern) {
                    if (((ShapedRecipe) recipe).getIngredientMap().containsValue(item)) {
                        inv.setItem(24, recipe.getResult());
                    }
                }
            }
        }
    }

    @EventHandler
    public void onMove(InventoryMoveItemEvent event) {

    }
}

package com.narxoz.rpg;

import com.narxoz.rpg.artifact.*;
import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.vault.ChronomancerEngine;
import com.narxoz.rpg.vault.VaultRunResult;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Homework 9 Demo: Visitor + Memento ===");

        Inventory armanInventory = new Inventory();
        armanInventory.addArtifact(new Weapon("Iron Sword", 50, 6, 4));
        armanInventory.addArtifact(new Potion("Small Potion", 20, 1, 10));

        Inventory danaInventory = new Inventory();
        danaInventory.addArtifact(new Scroll("Scroll of Light", 70, 1, "Light Shield"));
        danaInventory.addArtifact(new Armor("Leather Armor", 35, 5, 2));

        List<Hero> party = new ArrayList<>();
        party.add(new Hero("Arman", 40, 12, 9, 3, 60, armanInventory));
        party.add(new Hero("Dana", 28, 22, 5, 1, 110, danaInventory));

        ChronomancerEngine engine = new ChronomancerEngine();
        VaultRunResult result = engine.runVault(party);

        System.out.println("\n=== Final result ===");
        System.out.println(result);
    }
}
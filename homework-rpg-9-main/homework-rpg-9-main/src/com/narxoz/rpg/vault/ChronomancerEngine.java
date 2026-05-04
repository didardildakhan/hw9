package com.narxoz.rpg.vault;

import com.narxoz.rpg.artifact.*;
import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.combatant.HeroMemento;
import com.narxoz.rpg.memento.Caretaker;
import java.util.List;

public class ChronomancerEngine {
    public VaultRunResult runVault(List<Hero> party) {
        if (party == null || party.isEmpty()) {
            return new VaultRunResult(0, 0, 0);
        }

        System.out.println("\n=== Chronomancer's Vault Run ===");
        System.out.println("Heroes entering the vault:");
        for (Hero hero : party) {
            System.out.println(hero);
        }

        Inventory vaultInventory = createVaultInventory();
        System.out.println("\n--- Visitor pattern: artifact appraisal ---");

        GoldAppraiser goldAppraiser = new GoldAppraiser();
        vaultInventory.accept(goldAppraiser);
        System.out.println("Total appraised gold: " + goldAppraiser.getTotalGold());

        System.out.println();
        EnchantmentScanner scanner = new EnchantmentScanner();
        vaultInventory.accept(scanner);

        System.out.println();
        CurseDetector curseDetector = new CurseDetector();
        vaultInventory.accept(curseDetector);
        System.out.println("Cursed artifacts found: " + curseDetector.getCursedCount());

        System.out.println();
        WeightCalculator weightCalculator = new WeightCalculator();
        vaultInventory.accept(weightCalculator);
        System.out.println("Total weight: " + weightCalculator.getTotalWeight());

        System.out.println("\n--- Memento pattern: save and rewind ---");
        Hero target = party.get(0);
        Caretaker caretaker = new Caretaker();

        System.out.println("Before save: " + target);
        caretaker.save(target.createMemento());
        System.out.println("Snapshot saved. Caretaker size: " + caretaker.size());

        target.takeDamage(18);
        target.spendMana(5);
        target.spendGold(20);
        target.getInventory().addArtifact(new Ring("Cracked Time Ring", 90, 1, 3));
        System.out.println("After vault trap: " + target);

        HeroMemento rewindPoint = caretaker.undo();
        target.restoreFromMemento(rewindPoint);
        System.out.println("After rewind: " + target);

        int mementosCreated = 1;
        int restoredCount = rewindPoint == null ? 0 : 1;
        return new VaultRunResult(vaultInventory.size(), mementosCreated, restoredCount);
    }

    private Inventory createVaultInventory() {
        Inventory inventory = new Inventory();
        inventory.addArtifact(new Weapon("Sun Blade", 120, 8, 7));
        inventory.addArtifact(new Potion("Greater Healing Potion", 45, 1, 25));
        inventory.addArtifact(new Scroll("Scroll of Doom", 80, 1, "Doom Nova"));
        inventory.addArtifact(new Ring("Emerald Ring", 150, 1, 6));
        inventory.addArtifact(new Armor("Titan Plate", 140, 24, 9));
        return inventory;
    }
}
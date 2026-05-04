package com.narxoz.rpg.artifact;

public class CurseDetector implements ArtifactVisitor {
    private int cursedCount;

    public int getCursedCount() {
        return cursedCount;
    }

    public void visit(Weapon weapon) {
        if (weapon.getAttackBonus() >= 8) {
            cursedCount++;
            System.out.println("CurseDetector: " + weapon.getName() + " is blood-bound and dangerous");
        } else {
            System.out.println("CurseDetector: " + weapon.getName() + " is safe");
        }
    }

    public void visit(Potion potion) {
        if (potion.getHealing() < 10) {
            cursedCount++;
            System.out.println("CurseDetector: " + potion.getName() + " may be unstable");
        } else {
            System.out.println("CurseDetector: " + potion.getName() + " is safe");
        }
    }

    public void visit(Scroll scroll) {
        if (scroll.getSpellName().toLowerCase().contains("doom")) {
            cursedCount++;
            System.out.println("CurseDetector: " + scroll.getName() + " contains forbidden magic");
        } else {
            System.out.println("CurseDetector: " + scroll.getName() + " is safe");
        }
    }

    public void visit(Ring ring) {
        if (ring.getMagicBonus() >= 5) {
            cursedCount++;
            System.out.println("CurseDetector: " + ring.getName() + " whispers to its owner");
        } else {
            System.out.println("CurseDetector: " + ring.getName() + " is safe");
        }
    }

    public void visit(Armor armor) {
        if (armor.getWeight() > 20) {
            cursedCount++;
            System.out.println("CurseDetector: " + armor.getName() + " feels too heavy to remove");
        } else {
            System.out.println("CurseDetector: " + armor.getName() + " is safe");
        }
    }
}
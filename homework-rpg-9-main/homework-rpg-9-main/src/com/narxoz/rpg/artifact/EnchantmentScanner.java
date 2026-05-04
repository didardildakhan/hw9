package com.narxoz.rpg.artifact;

public class EnchantmentScanner implements ArtifactVisitor {
    public void visit(Weapon weapon) {
        System.out.println("EnchantmentScanner: " + weapon.getName() + " has attack aura +" + weapon.getAttackBonus());
    }

    public void visit(Potion potion) {
        System.out.println("EnchantmentScanner: " + potion.getName() + " restores " + potion.getHealing() + " HP");
    }

    public void visit(Scroll scroll) {
        System.out.println("EnchantmentScanner: " + scroll.getName() + " contains spell " + scroll.getSpellName());
    }

    public void visit(Ring ring) {
        System.out.println("EnchantmentScanner: " + ring.getName() + " gives magic +" + ring.getMagicBonus());
    }

    public void visit(Armor armor) {
        System.out.println("EnchantmentScanner: " + armor.getName() + " gives defense +" + armor.getDefenseBonus());
    }
}
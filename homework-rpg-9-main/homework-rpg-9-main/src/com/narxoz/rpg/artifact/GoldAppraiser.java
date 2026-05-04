package com.narxoz.rpg.artifact;

public class GoldAppraiser implements ArtifactVisitor {
    private int totalGold;

    public int getTotalGold() {
        return totalGold;
    }

    public void visit(Weapon weapon) {
        int price = weapon.getValue() + weapon.getAttackBonus() * 12;
        totalGold += price;
        System.out.println("GoldAppraiser: weapon " + weapon.getName() + " costs " + price + " gold");
    }

    public void visit(Potion potion) {
        int price = potion.getValue() + potion.getHealing() * 2;
        totalGold += price;
        System.out.println("GoldAppraiser: potion " + potion.getName() + " costs " + price + " gold");
    }

    public void visit(Scroll scroll) {
        int price = scroll.getValue() + 35;
        totalGold += price;
        System.out.println("GoldAppraiser: scroll " + scroll.getName() + " costs " + price + " gold");
    }

    public void visit(Ring ring) {
        int price = ring.getValue() + ring.getMagicBonus() * 25;
        totalGold += price;
        System.out.println("GoldAppraiser: ring " + ring.getName() + " costs " + price + " gold");
    }

    public void visit(Armor armor) {
        int price = armor.getValue() + armor.getDefenseBonus() * 15;
        totalGold += price;
        System.out.println("GoldAppraiser: armor " + armor.getName() + " costs " + price + " gold");
    }
}
package com.narxoz.rpg.artifact;

public class WeightCalculator implements ArtifactVisitor {
    private int totalWeight;

    public int getTotalWeight() {
        return totalWeight;
    }

    public void visit(Weapon weapon) {
        totalWeight += weapon.getWeight();
        System.out.println("WeightCalculator: weapon " + weapon.getName() + " weighs " + weapon.getWeight());
    }

    public void visit(Potion potion) {
        totalWeight += potion.getWeight();
        System.out.println("WeightCalculator: potion " + potion.getName() + " weighs " + potion.getWeight());
    }

    public void visit(Scroll scroll) {
        totalWeight += scroll.getWeight();
        System.out.println("WeightCalculator: scroll " + scroll.getName() + " weighs " + scroll.getWeight());
    }

    public void visit(Ring ring) {
        totalWeight += ring.getWeight();
        System.out.println("WeightCalculator: ring " + ring.getName() + " weighs " + ring.getWeight());
    }

    public void visit(Armor armor) {
        totalWeight += armor.getWeight();
        System.out.println("WeightCalculator: armor " + armor.getName() + " weighs " + armor.getWeight());
    }
}
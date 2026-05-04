package com.narxoz.rpg;

import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.council.CouncilEngine;
import com.narxoz.rpg.council.CouncilRunResult;
import com.narxoz.rpg.guild.Captain;
import com.narxoz.rpg.guild.GuildHall;
import com.narxoz.rpg.guild.Healer;
import com.narxoz.rpg.guild.Quartermaster;
import com.narxoz.rpg.guild.Scout;
import com.narxoz.rpg.quest.Quest;
import com.narxoz.rpg.quest.QuestIterator;
import com.narxoz.rpg.quest.QuestLog;
import com.narxoz.rpg.quest.QuestPriority;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Homework 10 Demo: Iterator + Mediator ===");

        List<Hero> party = new ArrayList<>();
        party.add(new Hero("Arman", 42, 12, 9, 4, 80));
        party.add(new Hero("Dana", 34, 20, 6, 2, 120));

        QuestLog questLog = new QuestLog();
        questLog.add(new Quest("Clear the Spider Cave", QuestPriority.NORMAL, 90, false));
        questLog.add(new Quest("Escort the Merchant Caravan", QuestPriority.LOW, 60, false));
        questLog.add(new Quest("Stop the Cursed Knight", QuestPriority.HIGH, 180, true));
        questLog.add(new Quest("Recover the Ancient Map", QuestPriority.NORMAL, 110, false));
        questLog.add(new Quest("Defend the Northern Gate", QuestPriority.URGENT, 250, true));

        GuildHall guildHall = new GuildHall();
        Quartermaster quartermaster = new Quartermaster("Boris", guildHall);
        Scout scout = new Scout("Mira", guildHall);
        Healer healer = new Healer("Aina", guildHall);
        Captain captain = new Captain("Rashid", guildHall);

        System.out.println("\n--- Manual Iterator demo: reverse order ---");
        QuestIterator reverse = questLog.reverse();

        while (reverse.hasNext()) {
            System.out.println(reverse.next());
        }

        System.out.println("\n--- Manual Mediator demo ---");
        captain.issueOrder("orders", "All officers prepare for the northern campaign.");
        scout.reportRoute("scout", "Mountain road is dangerous but faster.");
        quartermaster.requestSupplies("supply", "Need arrows, ropes, torches, and food.");
        healer.prepareAid("healing", "Prepare antidotes and field bandages.");

        CouncilEngine engine = new CouncilEngine();
        CouncilRunResult result = engine.runCouncil(party, questLog, guildHall);

        System.out.println("\n=== Final Council Result ===");
        System.out.println(result);
    }
}
package com.narxoz.rpg.council;

import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.guild.GuildHall;
import com.narxoz.rpg.guild.GuildMediator;
import com.narxoz.rpg.quest.Quest;
import com.narxoz.rpg.quest.QuestIterator;
import com.narxoz.rpg.quest.QuestLog;
import com.narxoz.rpg.quest.QuestPriority;
import java.util.List;

public class CouncilEngine {
    public CouncilRunResult runCouncil(List<Hero> party, QuestLog questLog, GuildMediator hall) {
        int questsTraversed = 0;
        int messagesRouted = 0;

        System.out.println("\n=== Council Engine Started ===");
        System.out.println("Heroes at the table:");

        for (Hero hero : party) {
            System.out.println(" - " + hero);
        }

        System.out.println("\nIterator demo: arrival order");
        QuestIterator ordered = questLog.ordered();

        while (ordered.hasNext()) {
            Quest quest = ordered.next();
            questsTraversed++;

            System.out.println("Planning quest: " + quest);

            if (quest.isUrgent() || quest.getPriority().ordinal() >= QuestPriority.HIGH.ordinal()) {
                hall.dispatch("orders", null, "Priority mission selected: " + quest.getTitle());
                messagesRouted++;

                hall.dispatch("danger", null, "High risk quest: " + quest.getTitle());
                messagesRouted++;
            } else {
                hall.dispatch("supply", null, "Standard supplies for: " + quest.getTitle());
                messagesRouted++;
            }
        }

        System.out.println("\nIterator demo: urgent/high priority only");
        QuestIterator priority = questLog.priorityAtLeast(QuestPriority.HIGH);

        while (priority.hasNext()) {
            Quest quest = priority.next();
            questsTraversed++;

            System.out.println("High priority review: " + quest.getTitle());

            hall.dispatch("scout", null, "Scout route for: " + quest.getTitle());
            messagesRouted++;
        }

        System.out.println("\nIterator demo: reward sorted extension");
        QuestIterator rewardSorted = questLog.rewardSorted();

        while (rewardSorted.hasNext()) {
            Quest quest = rewardSorted.next();
            questsTraversed++;

            System.out.println("Reward review: " + quest.getTitle() + " pays " + quest.getRewardGold() + " gold");
        }

        int membersNotified = 0;

        if (hall instanceof GuildHall) {
            GuildHall guildHall = (GuildHall) hall;
            membersNotified = guildHall.getDeliveredMessages();
        }

        return new CouncilRunResult(questsTraversed, messagesRouted, membersNotified);
    }
}
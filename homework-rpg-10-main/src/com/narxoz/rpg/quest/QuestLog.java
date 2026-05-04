package com.narxoz.rpg.quest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

public class QuestLog {
    private final List<Quest> quests = new ArrayList<>();

    public void add(Quest quest) {
        if (quest != null) {
            quests.add(quest);
        }
    }

    public int size() {
        return quests.size();
    }

    public QuestIterator ordered() {
        return new OrderedQuestIterator(this);
    }

    public QuestIterator reverse() {
        return new ReverseQuestIterator(this);
    }

    public QuestIterator priorityAtLeast(QuestPriority threshold) {
        return new PriorityQuestIterator(this, threshold);
    }

    public QuestIterator rewardSorted() {
        return new RewardSortedQuestIterator(this);
    }

    List<Quest> snapshot() {
        return Collections.unmodifiableList(new ArrayList<>(quests));
    }
}

class RewardSortedQuestIterator implements QuestIterator {
    private final List<Quest> snapshot;
    private int cursor;

    RewardSortedQuestIterator(QuestLog questLog) {
        this.snapshot = new ArrayList<>(questLog.snapshot());
        this.snapshot.sort(Comparator.comparingInt(Quest::getRewardGold).reversed());
        this.cursor = 0;
    }

    public boolean hasNext() {
        return cursor < snapshot.size();
    }

    public Quest next() {
        if (!hasNext()) throw new NoSuchElementException();
        return snapshot.get(cursor++);
    }
}
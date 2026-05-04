package com.narxoz.rpg.quest;

import java.util.List;
import java.util.NoSuchElementException;

public class OrderedQuestIterator implements QuestIterator {
    private final List<Quest> snapshot;
    private int cursor;

    public OrderedQuestIterator(QuestLog questLog) {
        this.snapshot = questLog.snapshot();
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
package com.narxoz.rpg.quest;

import java.util.List;
import java.util.NoSuchElementException;

public class ReverseQuestIterator implements QuestIterator {
    private final List<Quest> snapshot;
    private int cursor;

    public ReverseQuestIterator(QuestLog questLog) {
        this.snapshot = questLog.snapshot();
        this.cursor = snapshot.size() - 1;
    }

    public boolean hasNext() {
        return cursor >= 0;
    }

    public Quest next() {
        if (!hasNext()) throw new NoSuchElementException();
        return snapshot.get(cursor--);
    }
}
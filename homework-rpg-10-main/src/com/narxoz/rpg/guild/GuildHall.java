package com.narxoz.rpg.guild;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GuildHall implements GuildMediator {
    private final Map<String, List<GuildMember>> membersByTopic = new HashMap<>();
    private int routedMessages;
    private int deliveredMessages;

    public void register(GuildMember member) {
        if (member == null) return;

        addSubscriber("all", member);

        if (member instanceof Captain) {
            addSubscriber("scout", member);
            addSubscriber("supply", member);
            addSubscriber("healing", member);
            addSubscriber("lore", member);
        } else if (member instanceof Scout) {
            addSubscriber("orders", member);
            addSubscriber("lore", member);
        } else if (member instanceof Quartermaster) {
            addSubscriber("orders", member);
            addSubscriber("scout", member);
            addSubscriber("reward", member);
        } else if (member instanceof Healer) {
            addSubscriber("orders", member);
            addSubscriber("danger", member);
            addSubscriber("healing", member);
        } else {
            addSubscriber("orders", member);
        }
    }

    public void dispatch(String topic, GuildMember from, String payload) {
        String safeTopic = topic == null || topic.isBlank() ? "all" : topic;
        String safePayload = payload == null ? "" : payload;
        String senderName = from == null ? "CouncilEngine" : from.getName();

        routedMessages++;

        System.out.println("\n[GuildHall] " + senderName + " dispatches topic '" + safeTopic + "': " + safePayload);

        for (GuildMember member : subscribersFor(safeTopic)) {
            if (member != from) {
                deliveredMessages++;
                member.receive(safeTopic, from, safePayload);
            }
        }
    }

    public int getRoutedMessages() {
        return routedMessages;
    }

    public int getDeliveredMessages() {
        return deliveredMessages;
    }

    protected void addSubscriber(String topic, GuildMember member) {
        membersByTopic.computeIfAbsent(topic, key -> new ArrayList<>());

        if (!membersByTopic.get(topic).contains(member)) {
            membersByTopic.get(topic).add(member);
        }
    }

    protected List<GuildMember> subscribersFor(String topic) {
        return membersByTopic.getOrDefault(topic, List.of());
    }
}
package com.narxoz.rpg.guild;

public class Healer extends GuildMember {
    public Healer(String name, GuildMediator mediator) {
        super(name, mediator);
    }

    public void prepareAid(String topic, String payload) {
        getMediator().dispatch(topic, this, payload);
    }

    public void receive(String topic, GuildMember from, String payload) {
        String sender = from == null ? "CouncilEngine" : from.getName();
        System.out.println("[Healer " + getName() + "] received " + topic + " from " + sender
                + ": prepares healing kits for: " + payload);
    }
}
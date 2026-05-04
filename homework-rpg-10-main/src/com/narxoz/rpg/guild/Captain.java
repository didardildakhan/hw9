package com.narxoz.rpg.guild;

public class Captain extends GuildMember {
    public Captain(String name, GuildMediator mediator) {
        super(name, mediator);
    }

    public void issueOrder(String topic, String payload) {
        getMediator().dispatch(topic, this, payload);
    }

    public void receive(String topic, GuildMember from, String payload) {
        String sender = from == null ? "CouncilEngine" : from.getName();
        System.out.println("[Captain " + getName() + "] received " + topic + " from " + sender
                + ": command plan updated after message: " + payload);
    }
}
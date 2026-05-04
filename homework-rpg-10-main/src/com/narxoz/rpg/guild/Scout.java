package com.narxoz.rpg.guild;

public class Scout extends GuildMember {
    public Scout(String name, GuildMediator mediator) {
        super(name, mediator);
    }

    public void reportRoute(String topic, String payload) {
        getMediator().dispatch(topic, this, payload);
    }

    public void receive(String topic, GuildMember from, String payload) {
        String sender = from == null ? "CouncilEngine" : from.getName();
        System.out.println("[Scout " + getName() + "] received " + topic + " from " + sender
                + ": checks route and enemy movement for: " + payload);
    }
}
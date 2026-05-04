package com.narxoz.rpg.guild;

public class Quartermaster extends GuildMember {
    public Quartermaster(String name, GuildMediator mediator) {
        super(name, mediator);
    }

    public void requestSupplies(String topic, String payload) {
        getMediator().dispatch(topic, this, payload);
    }

    public void receive(String topic, GuildMember from, String payload) {
        String sender = from == null ? "CouncilEngine" : from.getName();
        System.out.println("[Quartermaster " + getName() + "] received " + topic + " from " + sender
                + ": prepares equipment and budget for: " + payload);
    }
}
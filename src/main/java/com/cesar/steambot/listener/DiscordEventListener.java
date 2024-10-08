package com.cesar.steambot.listener;

import com.cesar.steambot.service.SteamService;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.springframework.stereotype.Component;

@Component
public class DiscordEventListener extends ListenerAdapter {

    private final SteamService steamService;

    public DiscordEventListener(SteamService steamService) {
        this.steamService = steamService;
    }
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        // TODO lidar com comandos recebidos pelo command
    }
}

package com.cesar.steambot.commands;

import com.cesar.steambot.service.SteamService;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class SteamProfileCommand implements Command {

    private final SteamService steamService;

    public SteamProfileCommand(SteamService steamService) {
        this.steamService = steamService;
    }

    @Override
    public void execute(MessageReceivedEvent event) {
        String[] parts = event.getMessage().getContentRaw().split(" ");
        if (parts.length > 1) {
            String steamUsername = parts[1];
            String userInfo = steamService.getUserInfo(steamService.getSteamIdFromUsername(steamUsername));
            event.getChannel().sendMessage(userInfo).queue();
        } else {
            event.getChannel().sendMessage("Usage: !steamprofile <Steam Username>").queue();
        }
    }
}

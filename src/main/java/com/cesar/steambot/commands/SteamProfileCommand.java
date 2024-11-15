package com.cesar.steambot.commands;

import com.cesar.steambot.service.SteamService;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.springframework.stereotype.Component;

@Component("!steamprofile")
public class SteamProfileCommand implements Command {

    private final SteamService steamService;

    public SteamProfileCommand(SteamService steamService) {
        this.steamService = steamService;
    }

    @Override
    public void execute(MessageReceivedEvent event) {
        String[] parts = event.getMessage().getContentRaw().split(" ");
        if (parts.length > 1) {
            if (parts[1].matches("^76561198\\d{9}$")){ // check if the user passed the ID
                String steamId = parts[1];
                String userInfo = steamService.getUserInfo(steamId);
                event.getChannel().sendMessage(userInfo).queue();
            } else { // if the user have a vanity url the username will work
                String steamUsername = parts[1];
                String userInfo = steamService.getUserInfo(steamService.getSteamIdFromUsername(steamUsername));
                event.getChannel().sendMessage(userInfo).queue();
            }
        } else {
            event.getChannel().sendMessage("Usage: !steamprofile <Steam ID or your custom URL name>").queue();
        }
    }
}

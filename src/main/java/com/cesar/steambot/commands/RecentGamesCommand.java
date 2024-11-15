package com.cesar.steambot.commands;

import com.cesar.steambot.service.SteamService;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.springframework.stereotype.Component;

@Component("!recentgames")
public class RecentGamesCommand implements Command {

    private final SteamService steamService;

    public RecentGamesCommand(SteamService steamService) {
        this.steamService = steamService;
    }

    @Override
    public void execute(MessageReceivedEvent event) {
        String[] parts = event.getMessage().getContentRaw().split(" ");
        if (parts.length > 1) {
            if (parts[1].matches("^76561198\\d{9}$")) {
                String steamId = parts[1];
                String userRecentGames  = steamService.getRecentGamesPlayedByUser(steamId);
                event.getChannel().sendMessage(userRecentGames).queue();
            } else {
                String steamUsername = parts[1];
                String userRecentGames  = steamService.getRecentGamesPlayedByUser(steamService.getSteamIdFromUsername(steamUsername));
                event.getChannel().sendMessage(userRecentGames).queue();
            }
        } else {
            event.getChannel().sendMessage("Usage: !recentgames <Steam ID or your custom URL name>").queue();
        }
    }
}

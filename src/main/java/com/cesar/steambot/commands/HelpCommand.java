package com.cesar.steambot.commands;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.springframework.stereotype.Component;

@Component("!help")
public class HelpCommand implements Command {

    @Override
    public void execute(MessageReceivedEvent event) {
        String helpMessage = "List of available commands:\n" +
                "!steamprofile <Steam Username> - display basic info of the player's profile\n" +
                "!help - display available commands\n" +
                "!recentgames <Steam Username> - display recent games played by the user in the last two weeks\n";
        event.getChannel().sendMessage(helpMessage).queue();
    }
}

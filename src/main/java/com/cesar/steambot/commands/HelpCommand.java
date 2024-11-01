package com.cesar.steambot.commands;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.springframework.stereotype.Component;

@Component("!help")
public class HelpCommand implements Command {

    @Override
    public void execute(MessageReceivedEvent event) {
        String helpMessage = "List of available commands:\n" +
                "!steamprofile <Steam Username> - display the player name and profile link\n" +
                "!help - display available commands";
        event.getChannel().sendMessage(helpMessage).queue();
    }
}
